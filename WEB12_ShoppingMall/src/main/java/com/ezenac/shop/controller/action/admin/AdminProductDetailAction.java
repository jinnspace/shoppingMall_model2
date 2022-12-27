package com.ezenac.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.ProductDao;
import com.ezenac.shop.dto.ProductVO;

public class AdminProductDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String url="admin/product/productDetail.jsp";
		
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		
		HttpSession session = request.getSession();
		String avo =(String)session.getAttribute("loginAdmin");
		if(avo==null) {
			url ="shop.do?command=admin";
			
		}else {
			ProductDao pdao = ProductDao.getInstance();
			ProductVO pvo = pdao.getProduct(pseq);
			
			// ȭ�鿡 ǥ�õ� kind ������ �迭�� ����
			String [] kindList = {"0", "Heels", "Boots", "Sandals", "Sneakers", "Slipers", "On sale"};
			
			// ���� ��ǰ�� kind �� �����ؼ� index ������ ����
			int index = Integer.parseInt(pvo.getKind());
			
			// index ��°�� kindList ���� ������Ʈ�� ����
			request.setAttribute("kind", kindList[index]);
			request.setAttribute("productVO", pvo);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
