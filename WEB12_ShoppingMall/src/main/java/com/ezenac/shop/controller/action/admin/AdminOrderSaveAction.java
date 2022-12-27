package com.ezenac.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.AdminDao;

public class AdminOrderSaveAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="shop.do?command=adminOrderList";
		HttpSession session = request.getSession();
		String avo =(String)session.getAttribute("loginAdmin");
		if(avo == null) {
			url="shop.do?command=admin";
		}else {
			String[]odseqs = request.getParameterValues("result");
			// üũ�ڽ��� üũ�� ��ǰ�� �ֹ� �� ��ȣ���� ���޵Ǳ� ������ �迭�� �޾Ƽ� �ϳ��� ó���մϴ�.
			
			AdminDao adao = AdminDao.getInstance();
			for(String odseq : odseqs) {
				adao.updateOrderResult(Integer.parseInt(odseq));
			}
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
