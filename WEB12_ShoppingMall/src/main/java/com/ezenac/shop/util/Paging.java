package com.ezenac.shop.util;

public class Paging {
	private int page = 1;// ���� ������ �ϴ� ��������ȣ
	private int totalCount; // �Խù��� �� ����
	private int displayRow = 5; // �� ȭ�鿡 ǥ���� �Խù� ����
	private int displayPage = 5; // �� ȭ�鿡 ǥ���� ������ ���� 
	private int beginPage; // ���� ȭ�鿡 ǥ�õǴ� �������߿� ���� ó�� ������ 1 2 3 4 5 �� �� 1�� �ش��ϴ� ����
	private int endPage; // ���� ȭ�鿡 ǥ�õǴ� �������߿� ���� ó�� ������ 1 2 3 4 5 �� �� 5�� �ش��ϴ� ����
	private boolean prev; // ���� ��ư ǥ�� ����
	private boolean next; // ���� ��ư ǥ�� ����
	private int startNum; // ���� ȭ�鿡 ǥ�õ� �Խù����� ���۹�ȣ(�Խù���ȣ, num�̳� qseq�� �ƴϰ� �˻��� �Խù��� ����)
	private int endNum; // ���� ȭ�鿡 ǥ�õ� �Խù��� �� ��ȣ 
	
	private void paging(){
		double temp = page /(double) displayPage;	
		temp = Math.ceil(temp);	
		endPage = (int)(temp * displayPage);	
		beginPage = endPage - (displayPage - 1);	
		// endPage = ((int)Math.ceil( page /(double) displayPage)) * displayPage;	
		// beginPage = endPage - (displayPage - 1);	
		
		int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
		if(totalPage < endPage) {
			endPage = totalPage;
			next = false;
		}else {
			next = true;
		}		
		prev = (beginPage==1)? false : true; 
		startNum = (page-1)*displayRow + 1;
		endNum = page*displayRow;	
		System.out.println( beginPage + " " + endPage + " " + startNum + " " + endNum);
		// �ܼ�â�� ǥ�� 
	} // setTotalCount �ż����� �Ű������� �� �Խù� ������ �ԷµǸ� paging() �ż��嵵 ȣ��Ǿ �� �������� ���˴ϴ�.
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paging(); // ��� �ż��� ȣ�� 
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	

	
	
}
