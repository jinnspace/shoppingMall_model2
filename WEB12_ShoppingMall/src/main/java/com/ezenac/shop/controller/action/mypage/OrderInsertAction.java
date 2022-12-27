package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.CartDao;
import com.ezenac.shop.dao.OrderDao;
import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.MemberVO;

public class OrderInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url="";
		
			HttpSession session = request.getSession();
			MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
			if(mvo==null) {
				url="shop.do?command=loginForm";
			}else {
				// �ֹ��� ���̵�� �˻��� īƮ ���(���� �ֹ� ó����)����� ���� ��ȸ�մϴ�.(cdao ���� �ʿ�)
				CartDao cdao = CartDao.getInstance();
				ArrayList<CartVO> cartList = cdao.selectCart(mvo.getId());
				
				// ��ȸ�� list�� �ֹ����� ���̵� ���� orderDao�� insertOrder�ż���� 
				// orders�� order_detail ���̺� �����͸� �߰��մϴ�. 
				OrderDao odao = OrderDao.getInstance();
				int oseq = odao.insertOrder(cartList, mvo.getId());
				
				// ��� �ֹ��� ������ �ֹ���ȣ�� ���� ���� ����Ʈ�� �̵��� �ֹ���ȣ�� �ֹ� ������ �ٽ� ��ȸ�ϰ� jsp�� �̵�
				url = "shop.do?command=orderList&oseq="+ oseq;
			}
			response.sendRedirect(url);
		}
	}


