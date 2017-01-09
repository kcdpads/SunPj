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
<form method="post" name="writeform" action="writePrice.do">
<input type="hidden" name="priceName" value="${price.priceName}">
<input type="hidden" name="price" value="${price.price}">
<input type="hidden" name="priceType" value="${price.priceType}">
<input type="hidden" name="priceGender" value="${price.priceGender}">
<table class="table-bordered">
   <tr>
  <!--   <td>
    <a href="list.do" class="btn"> 글목록</a>
   </td> -->
   </tr>
   <tr>
    <td>이 름</td>
    <td>
    <td><input type="text" size="130" maxlength="50" name="priceName"></td>
       
       </td>
  </tr>
  
  <tr>
   <td><input type="text" size="130" maxlength="50" name="priceType"></td>
    <td>타입</td>

<td><input type="text" size="130" maxlength="50" name="price"></td>
 <td>가격</td>
  </tr>
  <tr>
    <td>성별</td>
    <td><input type="text" size="130" maxlength="50" name="priceGender"></td>
  </tr>
  <tr>
<%--   <c:if test="${myId}">
    <td>비밀번호</td>
    <td>
     <input type="password" size="8" maxlength="12" name="passwd">
</td></c:if> --%>

<!--     <td>비밀번호</td>
    <td>
     <input type="password" size="8" maxlength="12" name="passwd">
	</td>
 -->
  </tr>
<tr>     
<td colspan=2 align="center">
  <input type="submit" value="글쓰기"> 
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" OnClick="window.location='list.do'">
</td></tr></table>   
</form>     
</section>