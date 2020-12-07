<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원목록</h1>

<table border="1">
	<tr>
		<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td>
	</tr>
	<c:forEach items="${list }" var="member">
	<tr>
		<td><a href="/member/detail?id=${member.id }">${member.id }</a></td>
		<td>${member.pw }</td>
		<td>${member.name }</td>
		<td>${member.email }</td>
		<td>${member.joindate }</td>
	</tr>
	</c:forEach>
</table>

	<c:if test="${pageMaker.prev }">
		<a href="/member/list?pageNum=${pageMaker.startPage-1 }&amount=${pageMaker.pcdto.amount}">
		이전</a></c:if> 
		<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage}">
			<a href="/member/list?pageNum=${num }&amount=${pageMaker.pcdto.amount}">${num }</a>
		</c:forEach>
		<c:if test="${pageMaker.next }">
		<a href="/member/list?pageNum=${pageMaker.endPage+1 }&amount=${pageMaker.pcdto.amount}">
		다음</a></c:if><br> 
		<form action="/member/list" method="get">
		<input type="hidden" name="pageNum" value="${pageMaker.pcdto.pageNum }">
		<input type="hidden" name="amount" value="${pageMaker.pcdto.amount }">
		<select name="type">
			<option value="id">아이디</option>
			<option value="pw">비밀번호</option>
			<option value="name">이름</option>
			<option value="iname">아이디+이름</option>
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
		</form>
</body>
</html>