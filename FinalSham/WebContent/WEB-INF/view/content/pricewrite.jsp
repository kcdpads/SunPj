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
		<form method="post" name="writeform" action="writePricePro.do">
			<%-- <input type="hidden" name="priceName" value="${price.priceName}">
<input type="hidden" name="price" value="${price.price}">
<input type="hidden" name="priceType" value="${price.priceType}">
<input type="hidden" name="priceGender" value="${price.priceGender}"> --%>
			<center><table class="table-bordered">
				<br>
				<br>
				<br>
				<br>
				<br>

				<!--   <td>
    <a href="list.do" class="btn"> 글목록</a>
   </td> -->
				<thead>
					<tr>
						<td>성별</td>
						<td>스타일,기장</td>
						<td>스타일이름</td>
						<td>가격</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" name="priceGender"></td>
					
						<td><input type="text" name="priceType"></td>
				<td><input type="text" name="priceName"></td>
					
					<td><input type="text" name="price"></td>
					
					</tr>
				</tbody>
					<%--   <c:if test="${myId}">
    <td>비밀번호</td>
    <td>
     <input type="password" size="8" maxlength="12" name="passwd">
</td></c:if> --%>

					<!--     <td>비밀번호</td>
    <td>
     <input type="password" size="8" maxlength="12" name="passwd">
	</td>
 --></table>
					<tr>
						<td><input type="submit" value="글쓰기">
							<input type="reset" value="다시작성"> <input type="button"
							value="목록보기" OnClick="window.location='price.do'"></td>
					</tr></center>
			
		</form>
	</div>
</section>