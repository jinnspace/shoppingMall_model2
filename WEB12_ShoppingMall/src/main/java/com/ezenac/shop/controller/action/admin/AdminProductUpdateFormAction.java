package com.ezenac.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.ProductDao;
import com.ezenac.shop.dto.ProductVO;

public class AdminProductUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String url="admin/product/productUpdate.jsp";
		HttpSession session = request.getSession();
		String avo =(String)session.getAttribute("loginAdmin");
		if(avo==null) {
			url ="shop.do?command=admin";
			
		}else {
			// ���޵� ��ǰ��ȣ�� ��ǰ�󼼳����� ��ȸ�ϰ� ������Ʈ�� �����մϴ�. kind�鵵 �迭�� �ְ� ������Ʈ�� �����մϴ�.
			int pseq=Integer.parseInt(request.getParameter("pseq"));
			
			ProductDao pdao = ProductDao.getInstance();
			ProductVO pvo = pdao.getProduct(pseq);
			
			request.setAttribute("productVO", pvo);
			
			String [] kindList = {"Heels", "Boots", "Sandals", "Sneakers", "Slipers", "On sale"};
			request.setAttribute("kindList", kindList);
			
		}
		request.getRequestDispatcher(url).forward(request, response);		

	}

}
