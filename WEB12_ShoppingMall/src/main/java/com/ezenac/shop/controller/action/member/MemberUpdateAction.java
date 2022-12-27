package com.ezenac.shop.controller.action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.MemberDao;
import com.ezenac.shop.dto.MemberVO;

public class MemberUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���޵� �Ķ���͵�� VO ��ü�� ä���, updateMember�ż���� ȸ�������� ������ �� 
		// ���ǰ��� �����ϼ���. ������������ main.jsp �Դϴ�.
		MemberVO mvo = new MemberVO();
		
		mvo.setId(request.getParameter("id"));
		mvo.setPwd(request.getParameter("pwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setZip_num(request.getParameter("zip_num"));
		mvo.setAddress1(request.getParameter("address1"));
		mvo.setAddress2(request.getParameter("address2"));
		mvo.setPhone(request.getParameter("phone"));
		
		MemberDao mdao = MemberDao.getInstance();
		mdao.updateMember(mvo);		
	
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
		
			String url ="shop.do?command=index";
			RequestDispatcher dp = request.getRequestDispatcher(url);
			dp.forward(request, response);
		
	}

}
