package com.ezenac.shop.controller.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.controller.action.Action;

public class LoginFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/login.jsp";
		
		// login�� ������ ȭ��� logout�� ������ ȭ���� �����ϹǷ� �α��λ��¸� üũ���� �ʰ�
		// header.jsp���� �α��� ���¿� ���� �޴��� �޸� �����ִ� �ɷ� ������ �˴ϴ�. 
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
