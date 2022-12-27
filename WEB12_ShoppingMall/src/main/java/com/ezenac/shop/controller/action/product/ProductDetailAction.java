package com.ezenac.shop.controller.action.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.ProductDao;
import com.ezenac.shop.dto.ProductVO;

public class ProductDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// pseq를 전달받아서 상품하나를 조회하고, 조회한 상품을 request에 넣어서 productDetail.jsp로 이동합니다.
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		
		ProductDao pdao = ProductDao.getInstance();
		ProductVO pvo = pdao.getProduct(pseq);
		
		request.setAttribute("productVO", pvo);
		
		request.getRequestDispatcher("product/productDetail.jsp").forward(request, response);

	}

}
