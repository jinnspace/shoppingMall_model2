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

public class OrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "mypage/orderList.jsp";
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			url = "shop.do?command=loginForm";
		}else {
			
			OrderDao odao = OrderDao.getInstance();
			//oder_view���� �ֹ���ȣ�� �ֹ� �˻�
			ArrayList<OrderVO>list = odao.selectOrdersByOseq(oseq);
			// ��ȸ�� �ֹ����� �Ѱ����ݾ��� ���
			int totalPrice = 0;
			for(OrderVO ovo : list) totalPrice += ovo.getPrice2()*ovo.getQuantity();
			
			// ����Ʈ�� �����ݾ��� request�� �¿� �����ϴ�. 
			request.setAttribute("orderList", list);
			request.setAttribute("totalPrice", totalPrice);
			
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
