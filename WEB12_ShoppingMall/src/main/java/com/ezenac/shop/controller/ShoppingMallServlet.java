package com.ezenac.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.controller.action.Action;

@WebServlet("/shop.do")
public class ShoppingMallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoppingMallServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		System.out.println(command);
		
		ActionFactory af = ActionFactory.getInstance();
		Action ac = af.getAction(command);
		
		if(ac!= null) ac.execute(request,response);
		else System.out.println("command가 null입니다. command를 확인하세요.");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
