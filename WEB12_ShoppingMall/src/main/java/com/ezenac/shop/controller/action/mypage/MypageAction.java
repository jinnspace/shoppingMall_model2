package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.OrderDao;
import com.ezenac.shop.dto.MemberVO;
import com.ezenac.shop.dto.OrderVO;

public class MypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "mypage/mypage.jsp";
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			url = "shop.do?command=loginForm";
		}else{
			
			//mypage.jsp에 최종 전달돼서 화면에 보여질 리스트 생성
			ArrayList<OrderVO> finalList = new ArrayList<OrderVO>();
			//(finalList.get(0).getPname() -> "xxxx외 2건")
			
			// 진행중인 주문내역
			// 현재 로그인한 사용자의 아직 배송안된 주문내역이 보여집니다.
			// 예를 들어 한번에 2,3개의 상품씩 3회에 걸쳐 주문한 상태라면,,, 그리고 그 주문들이 하나도 배송이 안된 상태(배송전)이라면
			// 진행중인 주문 내역은 3줄이 표시됩니다.(orders 테이블 기준 주문 건별 표시)
			// 표시내용은 주문 건별 대표상품의 이름을 이용해 - 슬리퍼 포함 3, 겨울용 부츠 외 2.. 등 내용으로 총 3줄이 표시
			// 그리고 각 행에는 상세보기 버튼이 있어 클릭하면 그 주문에 속한 세개의 상품을 볼 수 있습니다. 
			
			// 이를 위해 가장 먼저 필요하나 사항은 주문 번호들입니다.
			// order_view에서 주문자 아이디로 검색하고, result가 1인 주문들을 검색해서 주문번호들을 조회합니다.
			// 위의 예를 든 상태라면, 주문번호들이 다음과 같습니다.
			// 22 22 22 	24 24 24 	26 26 26 	27 27 27 <- 조회된 주문 번호들입니다. 
			
			// 그러나 정작 우리에게 필요한 건 23 24 26 27이므로, 조회할 때 distinct 키워들을 써서 조회해 옵니다.
			//select distinct oseq from order_view where id=? and result='1'
			// 주문번호(oseq)만 조회할거라면 orders테이블에서 distinct 없이 조회하면 될듯도 하지만
			//orders 테이블에는 result 필드가 없어서 배송전 주문이 구분된 번호가 조회되지 않습니다. 
			
			// 중복을 없앤 배송전(처리전) 주문번호들을 조회합니다.
			OrderDao odao = OrderDao.getInstance();
			ArrayList<Integer> oseqList = odao.selectOseqOrderIng(mvo.getId());
			
			// 조회된 주문번호들로 반복실행합니다.
			for(Integer oseq : oseqList) {
				
				// 현재 주문번호로 주문내역을 조회합니다.
				ArrayList<OrderVO> orderListByOseq = odao.selectOrdersByOseq(oseq);
				
				// 조회한 주문리스트에 세개의 상품이 있다면...
				// 첫번째 상품을 꺼냅니다.
				OrderVO ovo = orderListByOseq.get(0);
				
				// 꺼낸 상품의 이름은 ' 현재상품 이름 포함 x건'으로 수정합니다.
				ovo.setPname(ovo.getPname()+ "포함 " + orderListByOseq.size() + "건");
				
				// 결제한 총금액을 계산한 후 
				int totalPrice = 0;
				for(OrderVO ovo1 : orderListByOseq)
					totalPrice +=ovo1.getPrice2() * ovo1.getQuantity();
				
				// ovo 객체의 price2를 앞에서 계산한 총금액으로 수정합니다. 
				ovo.setPrice2(totalPrice);
				
				// 모든 변경ㅇ을 마친 ovo를 finalList에 담습니다.
				finalList.add(ovo);
			}
			request.setAttribute("orderList", finalList);
			request.setAttribute("title", "진행중인 주문 내역");
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
