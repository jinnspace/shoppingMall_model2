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

public class OrderDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		String url = "";
		if(loginUser==null) {
			url = "shop.do?command=loginForm";
		}else{
			
			// �ֹ���ȣ�� ��ȸ�� �ֹ�(��ǰ)����Ʈ ����
			OrderDao odao = OrderDao.getInstance();
			ArrayList<OrderVO> orderList = odao.selectOrdersByOseq(oseq);
			
			//������Ʈ�� �ֹ�����Ʈ�� ����ϴ�.
			//String [] kindList = {"�����Ϸ�", "�����", "��ۿϷ�", "����Ȯ��"};
			request.setAttribute("orderList", orderList);
			
			// ���Ϲ��� �ֹ�����Ʈ�� ù��°�� �̿��� orderDetail�̶� �̸����� ������Ʈ�� ����ϴ�.
			request.setAttribute("orderDetail", orderList.get(0));
			
			// �ֹ� �� �ݾ��� ����ϰ� ������Ʈ�� ����ϴ�.
			int totalPrice = 0;
			for(OrderVO ovo : orderList)
					totalPrice += ovo.getPrice2()* ovo.getQuantity();
			request.setAttribute("totalPrice", totalPrice);
			url = "mypage/orderDetail.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
