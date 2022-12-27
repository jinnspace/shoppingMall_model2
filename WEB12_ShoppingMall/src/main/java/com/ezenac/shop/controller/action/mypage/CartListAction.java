package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.CartDao;
import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.MemberVO;

public class CartListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "mypage/cartList.jsp"; //���� ������
		
		HttpSession session= request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo == null) {
			url = "shop.do?command = loginForm";
		}else {
			// �α��� ������ ���̵�� īƮ ����Ʈ�� �˻��� ���Ϲް� ������Ʈ�� ������ �̵��մϴ�. 
			CartDao cdao = CartDao.getInstance();
			ArrayList<CartVO> list = cdao.selectCart(mvo.getId());
			request.setAttribute("cartList", list);
			
			// ��ٱ��Ͽ� ��ǰ�� ��� �������� �� ������ �ݾ��� ����մϴ�.
			// list ���� ��ǰ�� �ϳ��ϳ� ������ price2* Quantity�� �ջ��ϴ� ������� ����մϴ�.
			 int totalPrice = 0;
			 for(CartVO cvo:list)
				 totalPrice += (cvo.getPrice2() * cvo.getQuantity());
			 
			 request.setAttribute("totalPrice", totalPrice);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
