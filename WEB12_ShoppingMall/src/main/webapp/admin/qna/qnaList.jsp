<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp" %>
<article>
	<h1>Q&amp;A 게시글 리스트</h1>
	<form name="frm" method="post">
	<table style="float:right;">
		<tr><td>제목+내용 검색
		<input type="text" name="key" value="${key}">
		<input class="btn" type="button" value="검색" onclick="go_search('adminQnaList')">
		<input class="btn" type="button" name="btn_total" value="전체보기" onclick="go_total('adminQnaList')"></td>
	</table><br>
	</form>
	<table id="orderList">
		<tr><th>번호(답변여부)</th><th>제목</th><th>작성자</th><th>작성일</th></tr>
		<c:forEach items="${qnaList}" var="qnaVO">
			<tr><td>${qnaVO.qseq}
			<c:choose>
				<c:when test='${qnaVO.rep=="1"}'>(미처리)</c:when>	
				<c:otherwise>(답변처리완료)</c:otherwise>		
			</c:choose></td>
			<td><a href="#" onclick="javascript:go_view('${qnaVO.qseq}')">${qnaVO.subject}</a></td>
			<td>${qnaVO.id}</td><td><fmt:formatDate value="${qnaVO.indate}"/></td></tr>
		</c:forEach>
	</table><br>
	
	<jsp:include page="/admin/paging/paging.jsp">
	<jsp:param value="shop.do?command=adminQnaList" name="command"/>
	</jsp:include>

</article>





<%@ include file="/admin/footer.jsp" %>
