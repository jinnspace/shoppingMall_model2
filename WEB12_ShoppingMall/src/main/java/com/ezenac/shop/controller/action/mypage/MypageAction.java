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
			
			//mypage.jsp�� ���� ���޵ż� ȭ�鿡 ������ ����Ʈ ����
			ArrayList<OrderVO> finalList = new ArrayList<OrderVO>();
			//(finalList.get(0).getPname() -> "xxxx�� 2��")
			
			// �������� �ֹ�����
			// ���� �α����� ������� ���� ��۾ȵ� �ֹ������� �������ϴ�.
			// ���� ��� �ѹ��� 2,3���� ��ǰ�� 3ȸ�� ���� �ֹ��� ���¶��,,, �׸��� �� �ֹ����� �ϳ��� ����� �ȵ� ����(�����)�̶��
			// �������� �ֹ� ������ 3���� ǥ�õ˴ϴ�.(orders ���̺� ���� �ֹ� �Ǻ� ǥ��)
			// ǥ�ó����� �ֹ� �Ǻ� ��ǥ��ǰ�� �̸��� �̿��� - ������ ���� 3, �ܿ�� ���� �� 2.. �� �������� �� 3���� ǥ��
			// �׸��� �� �࿡�� �󼼺��� ��ư�� �־� Ŭ���ϸ� �� �ֹ��� ���� ������ ��ǰ�� �� �� �ֽ��ϴ�. 
			
			// �̸� ���� ���� ���� �ʿ��ϳ� ������ �ֹ� ��ȣ���Դϴ�.
			// order_view���� �ֹ��� ���̵�� �˻��ϰ�, result�� 1�� �ֹ����� �˻��ؼ� �ֹ���ȣ���� ��ȸ�մϴ�.
			// ���� ���� �� ���¶��, �ֹ���ȣ���� ������ �����ϴ�.
			// 22 22 22 	24 24 24 	26 26 26 	27 27 27 <- ��ȸ�� �ֹ� ��ȣ���Դϴ�. 
			
			// �׷��� ���� �츮���� �ʿ��� �� 23 24 26 27�̹Ƿ�, ��ȸ�� �� distinct Ű������ �Ἥ ��ȸ�� �ɴϴ�.
			//select distinct oseq from order_view where id=? and result='1'
			// �ֹ���ȣ(oseq)�� ��ȸ�ҰŶ�� orders���̺��� distinct ���� ��ȸ�ϸ� �ɵ� ������
			//orders ���̺��� result �ʵ尡 ��� ����� �ֹ��� ���е� ��ȣ�� ��ȸ���� �ʽ��ϴ�. 
			
			// �ߺ��� ���� �����(ó����) �ֹ���ȣ���� ��ȸ�մϴ�.
			OrderDao odao = OrderDao.getInstance();
			ArrayList<Integer> oseqList = odao.selectOseqOrderIng(mvo.getId());
			
			// ��ȸ�� �ֹ���ȣ��� �ݺ������մϴ�.
			for(Integer oseq : oseqList) {
				
				// ���� �ֹ���ȣ�� �ֹ������� ��ȸ�մϴ�.
				ArrayList<OrderVO> orderListByOseq = odao.selectOrdersByOseq(oseq);
				
				// ��ȸ�� �ֹ�����Ʈ�� ������ ��ǰ�� �ִٸ�...
				// ù��° ��ǰ�� �����ϴ�.
				OrderVO ovo = orderListByOseq.get(0);
				
				// ���� ��ǰ�� �̸��� ' �����ǰ �̸� ���� x��'���� �����մϴ�.
				ovo.setPname(ovo.getPname()+ "���� " + orderListByOseq.size() + "��");
				
				// ������ �ѱݾ��� ����� �� 
				int totalPrice = 0;
				for(OrderVO ovo1 : orderListByOseq)
					totalPrice +=ovo1.getPrice2() * ovo1.getQuantity();
				
				// ovo ��ü�� price2�� �տ��� ����� �ѱݾ����� �����մϴ�. 
				ovo.setPrice2(totalPrice);
				
				// ��� ���椷�� ��ģ ovo�� finalList�� ����ϴ�.
				finalList.add(ovo);
			}
			request.setAttribute("orderList", finalList);
			request.setAttribute("title", "�������� �ֹ� ����");
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
