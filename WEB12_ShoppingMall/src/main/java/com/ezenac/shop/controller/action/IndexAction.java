package com.ezenac.shop.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.dao.ProductDao;
import com.ezenac.shop.dto.ProductVO;



public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// best��ǰ�� �Ż�ǰ�� �����ͺ��̽����� ��ȸ�ؼ� ����Ʈ�� ���Ϲް�, �̸� ������Ʈ�� �־� main.jsp�� �̵��մϴ�.
		ProductDao pdao = ProductDao.getInstance();
		
		ArrayList<ProductVO> blist = pdao.getBestList();
		ArrayList<ProductVO> nlist = pdao.getNewList();
		
		request.setAttribute("bestList", blist);
		request.setAttribute("newList", nlist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

}
