<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
	
</script>


<section class="section">

	<div class="container">
	<br><br><br>
	<h2>가격표</h2>
		<div class="row">

			<!--col end-->
			<div class="col-md-6">
				<div class="box table-responsive">
					<h2 class="title">커트</h2>
					<table class="table cart-table order-details-table">
						<thead>
							<tr>
								<!-- <th>No</th> -->
								<th>성별</th>
								<th>스타일,기장</th>
								<th>스타일이름</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
						
							<c:forEach items="${priceList}" var="priceList"
								varStatus="status">
								
								<tr>
								 <c:if test="${priceList.priceType eq '커트'}">
									<!-- 	<th scope="row">1</th> -->
									<%-- <td><c:out value="${priceList.priceNo}" /></td> --%>
									<td><c:out value="${priceList.priceGender}" /></td>
									<%-- <td><c:out value="${selecttestList.img}" /></td> --%>
									<td><c:out value="${priceList.priceType}" /></td>
									<td><c:out value="${priceList.priceName}" /></td>
									<td><c:out value="${priceList.price}" /></td>
								<td><c:if test="${myId eq 'admin'}">
								<input type="hidden" name="priceNo" value="${priceList.priceNo}">
								<button class="btn-default" >수정</button>
								<button class="btn-default" onclick="document.location.href='deletePrice.do?&priceNo=${priceList.priceNo}'">삭제</button>
								</c:if></td>
								</c:if></tr>
							</c:forEach>
						</tbody>
					</table>
				</div></div>
				<div class="row">
				<div class="col-md-6">
				<div class="box table-responsive">
					<h2 class="title">염색</h2>
					<table class="table cart-table order-details-table">
						<thead>
							<tr>
								<!-- <th>No</th> -->
								<th>성별</th>
								<th>스타일,기장</th>
								<th>스타일이름</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${priceList}" var="priceList"
								varStatus="status">
								<tr>
								<c:if test="${priceList.priceType eq '염색'}">
									<!-- 	<th scope="row">1</th> -->
									<%-- <td><c:out value="${priceList.priceNo}" /></td> --%>
									<td><c:out value="${priceList.priceGender}" /></td>
									<%-- <td><c:out value="${selecttestList.img}" /></td> --%>
									<td><c:out value="${priceList.priceType}" /></td>
									<td><c:out value="${priceList.priceName}" /></td>
									<td><c:out value="${priceList.price}" /></td>
								</c:if></tr>
							</c:forEach>
						</tbody>
					</table>
				</div></div></div>
				<div class="row">
				<div class="col-md-6">
				<div class="box table-responsive">
					<h2 class="title">펌</h2>
					<table class="table cart-table order-details-table">
						<thead>
							<tr>
								<!-- <th>No</th> -->
								<th>성별</th>
								<th>스타일,기장</th>
								<th>스타일이름</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${priceList}" var="priceList"
								varStatus="status">
								<tr>
								<c:if test="${priceList.priceType eq '펌'}">
									<!-- 	<th scope="row">1</th> -->
									<%-- <td><c:out value="${priceList.priceNo}" /></td> --%>
									<td><c:out value="${priceList.priceGender}" /></td>
									<%-- <td><c:out value="${selecttestList.img}" /></td> --%>
									<td><c:out value="${priceList.priceType}" /></td>
									<td><c:out value="${priceList.priceName}" /></td>
									<td><c:out value="${priceList.price}" /></td>
								</c:if></tr>
							</c:forEach>
						</tbody>
					</table>
				</div></div>
				
				<div class="col-md-6">
				<div class="box table-responsive">
					<h2 class="title">클리닉</h2>
					<table class="table cart-table order-details-table">
						<thead>
							<tr>
								<!-- <th>No</th> -->
								<th>성별</th>
								<th>스타일,기장</th>
								<th>스타일이름</th>
								<th>가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${priceList}" var="priceList"
								varStatus="status">
								<tr>
								<c:if test="${priceList.priceType eq '클리닉'}">
									<!-- 	<th scope="row">1</th> -->
									<%-- <td><c:out value="${priceList.priceNo}" /></td> --%>
									<td><c:out value="${priceList.priceGender}" /></td>
									<%-- <td><c:out value="${selecttestList.img}" /></td> --%>
									<td><c:out value="${priceList.priceType}" /></td>
									<td><c:out value="${priceList.priceName}" /></td>
									<td><c:out value="${priceList.price}" /></td>
								</c:if></tr>
							</c:forEach>
						</tbody>
					</table>
				</div></div></div>
				<c:if test="${myId eq 'admin'}">
					<button type="button" class="btn-default"
						onclick="document.location.href='writePrice.do?'">가격표등록</button>
					<button type="button" class="btn-default"
						onclick="document.location.href='updatePrice.do?'">가격표수정</button>
				</c:if>
			
		</div>
		<!--col end-->

		<!--row end-->
	</div>
	
</section>