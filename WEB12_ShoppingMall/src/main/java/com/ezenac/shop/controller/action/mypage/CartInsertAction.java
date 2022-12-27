package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.CartDao;
import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.MemberVO;

public class CartInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// cartvo�� ���� �Ķ���͸� �ְ�, cart ���̺� ���ڵ� �߰��մϴ�.
	// cart ����(mypage����) ������ �α��� ���Ŀ� ��ȿ�� �����̹Ƿ�, ���� �α��� �������� ���� �����մϴ�.
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		String url="";
		if(mvo == null) {
			url = "shop.do?command=loginForm";
		}else {
			CartVO cvo = new CartVO();
			cvo.setId(mvo.getId());
			cvo.setPseq(Integer.parseInt(request.getParameter("pseq")));
			cvo.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			
			CartDao cdao = CartDao.getInstance();
			cdao.insertCart(cvo);
			url = "shop.do?command=cartList";
		}
		response.sendRedirect(url);
	}

}
