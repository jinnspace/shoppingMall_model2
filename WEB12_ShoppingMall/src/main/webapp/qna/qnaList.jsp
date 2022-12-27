<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ include file="sub_image_menu.jsp"%>

<article>
<h2>1:1 고객 게시판</h2>
<h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
<form name="formm" method="post">
<table id="cartList">
	<tr><th>번호</th><th>제목</th><th>등록일</th><th>답변 여부</th></tr>
	<c:forEach items="${qnaList}" var="qnaVO">
		<tr><td>${qnaVO.qseq}</td>
			<td><a href="shop.do?command=qnaView&qseq=${qnaVO.qseq}">${qnaVO.subject}</a></td>
			<td><fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
			<td><c:choose>
				<c:when test="${qnaVO.rep==1}">no</c:when>
				<c:when test="${qnaVO.rep==2}">yes</c:when>
				</c:choose></td>
		</tr>
	</c:forEach>
</table><div class="clear"></div>
	<div id="paging" style="font-size:120%; font-weight:bold; margin-left:300px">
	<!-- 페이지를 클릭했을 때 이동할 url을 변수에 저장 -->
	<c:url var="action" value="shop.do?command=qnaList"/>
	
	<!-- 리퀘스트로 전달된 paging 객체들을 이용 -->
	<!-- 이전 버튼 표시  -->
	<c:if test="${paging.prev}">
		<a href="${action}&page=${paging.beginPage-1}">◀</a>&nbsp;
	</c:if>
	
	<!-- 이동할 페이지 표시 beginPage부터 endPage까지 -->
	<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="index">
		<c:choose>
			<c:when test="${paging.page==index}">
				<span style="color:red">${index}&nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="${action}&page=${index}">${index}</a>&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<!-- 반복실행문중 지금 표시하려는 페이지와 현재 보려는 페이지번호가 같다면 링크 없음  -->
	<!-- 같지 않다면 파라미터로 표시하려는 페이지를 붙여서 링크 설정 -->
	
	
	<!-- 다음 버튼 표시  -->
	<c:if test="${paging.next}">
		<a href="${action}&page=${paging.endPage+1}">▶</a>&nbsp;
	</c:if>
	</div>
	
	<div class="clear"></div><br>
	<div id="buttons" style="float:right">
		<input type="button" value="질문하기" class="submit" onclick="location.href='shop.do?command=qnaWriteForm'">
		<input type="button" value="쇼핑 계속하기" class="cancel" onclick="location.href='shop.do?command=index'">
	</div>
</form>
</article>

<%@ include file="../footer.jsp" %>
