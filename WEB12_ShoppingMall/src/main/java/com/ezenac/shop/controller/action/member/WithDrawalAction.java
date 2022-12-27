package com.ezenac.shop.controller.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.MemberDao;
import com.ezenac.shop.dto.MemberVO;

public class WithDrawalAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "shop.do?command=loginForm";
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			request.setAttribute("message", "�α����� �ʿ��� �����Դϴ�.");
		}else {
			MemberDao mdao = MemberDao.getInstance();
			mdao.withDrawl(mvo.getId());
			request.setAttribute("message", 
				"Ż��Ǿ����ϴ�. Ż��ȸ���� ������ 3������ �����Ǹ�, �Ⱓ �ȿ� ������ ���Ծ��� ���������� �����մϴ�.");
			session.removeAttribute("loginUser");
		}
			request.getRequestDispatcher(url).forward(request, response);		
		
	}
		
}

