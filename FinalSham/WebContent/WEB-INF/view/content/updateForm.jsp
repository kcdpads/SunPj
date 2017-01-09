<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<section class="section">
	<b>글수정</b>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">

		<form method="post" name="writeform" action="price.do?">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>No</th>
						<th>성별</th>
						<th>스타일,기장</th>
						<th>스타일이름</th>
						<th>가격</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${article}" var="priceList" varStatus="status">
						<tr>
							<td><input type="text" name="priceGender"
								value="${article.priceGender}"> <input type="hidden"
								name="priceNo" value="${article.priceNo}"></td>
							<td><input type="text" name="priceType"
								value="${article.priceType}"></td>
							<td><input type="text" name="priceName"
								value="${article.priceName}"></td>
							<td><input type="text" name="price"
								value="${article.price}"></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=2><input
							type="submit" value="글수정"> <input type="reset"
							value="다시작성"> <input type="button" value="목록보기"
							onclick="document.location.href='noticelist.do?pageNum=${pageNum}'">
						</td>
					</tr>
			</table>
		</form>
	</div></section>
</body>
</html>
