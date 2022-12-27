package com.ezenac.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;

public class AdminProductWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "admin/product/productWrite.jsp";
		HttpSession session= request.getSession();
		String loginAdmin = (String)session.getAttribute("loginAdmin");
		if(loginAdmin==null) {
			url = "shop.do?command=admin";
		}else {
			String [] kindList = {"Heels", "Boots", "Sandals", "Sneakers", "Slipers", "On sale"};
		// kind �� �Է½� ȭ�鿡 ǥ�õ� ī�װ� �̸����� ���� �迭�� �ְ� request�� ��� �̵��մϴ�.
			request.setAttribute("kindList", kindList);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
