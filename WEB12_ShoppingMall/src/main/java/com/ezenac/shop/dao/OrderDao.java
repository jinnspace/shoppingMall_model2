package com.ezenac.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.OrderVO;
import com.ezenac.shop.util.Dbman;

public class OrderDao {

	private OrderDao(){}
	private static OrderDao itc = new OrderDao();
	public static OrderDao getInstance() {return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int insertOrder(ArrayList<CartVO> cartList, String id) {
		int oseq = 0;
		con = Dbman.getConnection();
		
		try {
			// 1. ������ ���̵�� �������� �̿��� orders ���̺� ���ڵ� �߰�
			String sql = "insert into orders(oseq, id) values(orders_seq.nextVal,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
			
			// 2. orders ���̺� ��� �߰��� ���ڵ��� oseq �ʵ尪�� ��ȸ
			// -> 1�� �þ�� ������ ������ oseq�� �ԷµǾ��� ���̹Ƿ�, ���� ū oseq ���� ��� �߰��� ���� �˴ϴ�.
			sql = "select max(oseq) as max_oseq from orders";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())oseq = rs.getInt("max_oseq");
			
			// 3. īƮ��ϰ� ��� ��ȸ�� ���� ū oseq�� �̿��� oder_detail�� ���ڵ带 īƮ ��ǰ ������ �ϳ��� �߰��մϴ�.
			sql = "insert into order_detail(odseq, oseq, pseq, quantity)"
					+ "values(order_detail_seq.nextVal, ?, ?, ?)";
			
			for(CartVO cvo : cartList) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, oseq);
				pstmt.setInt(2, cvo.getPseq());
				pstmt.setInt(3, cvo.getQuantity());
				pstmt.executeUpdate();
			}
			
			// 4. order_detail�� �߰��� ī�� ������ cart ���̺��� ó���Ǿ����Ƿ� ���� �Ǵ� result�� 2�� ���� �Ǵ� ���� �մϴ�.
			sql = "delete from cart where cseq=?";
			// sql = "update cart set result ='2' where cseq=?";
			for(CartVO cvo : cartList) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cvo.getCseq());
				pstmt.executeUpdate();
			}
		
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
		return oseq;	
	}

	public ArrayList<OrderVO> selectOrdersByOseq(int oseq) {
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		String sql = "select * from order_view where oseq=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOdseq(rs.getInt("odseq"));			ovo.setOseq(rs.getInt("oseq"));
				ovo.setId(rs.getString("id"));				ovo.setIndate(rs.getTimestamp("indate"));
				ovo.setMname(rs.getString("mname"));		ovo.setZip_num(rs.getString("zip_num"));
				ovo.setAddress1(rs.getString("address1"));	ovo.setAddress2(rs.getString("address2"));
				ovo.setPhone(rs.getString("phone"));		ovo.setPname(rs.getString("pname"));
				ovo.setPrice2(rs.getInt("price2"));			ovo.setPseq(rs.getInt("pseq"));
				ovo.setQuantity(rs.getInt("quantity"));		ovo.setResult(rs.getString("result"));
				list.add(ovo);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
		return list;	
	}

	public ArrayList<Integer> selectOseqOrderIng(String id) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		String sql = "select distinct oseq from order_view where id=? and result='1'order by oseq desc";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("oseq")); // �ֹ���ȣ�� list�� ����ϴ�. 
			}
		} catch (SQLException e) { e.printStackTrace();
		}	finally {Dbman.close(con, pstmt, rs);}				
		
		return list;
	}

	public ArrayList<Integer> selectOseqAll(String id) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		String sql = "select distinct oseq from order_view where id=? order by oseq desc";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("oseq")); // �ֹ���ȣ�� list�� ����ϴ�. 
			}
		} catch (SQLException e) { e.printStackTrace();
		}	finally {Dbman.close(con, pstmt, rs);}				
		
		return list;
	}
	
}
