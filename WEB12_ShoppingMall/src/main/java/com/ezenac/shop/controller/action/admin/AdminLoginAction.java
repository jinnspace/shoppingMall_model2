package com.ezenac.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.AdminDao;

public class AdminLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String workId = request.getParameter("workId");
		String workPwd = request.getParameter("workPwd");
		
		String url = "shop.do?command=admin"; // ������ �α���â
		
		AdminDao adao = AdminDao.getInstance();
		String adminUserPwd = adao.workerCheck(workId);
		
		if(adminUserPwd == null) request.setAttribute("message", "���̵� �����ϴ�.");
		else if(!adminUserPwd.equals(workPwd)) request.setAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		else if(adminUserPwd.equals(workPwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", workId);
			url = "shop.do?command=adminProductList";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
