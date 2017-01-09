<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./view/color.jspf"%>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<br><br><br><br><br><br><br><br>
<div class="container" style="margin: auto;width:80%">

<H2 text align="center">시술 후기 게시판(전체 글:${count})</H2>

<c:if test="${count == 0}">
<table>
  <tr>
    <td>
      게시판에 저장된 글이 없습니다.
    </td>
  </tr>
</table>
</c:if>

<c:if test="${count > 0}">
<table class="table table-hover">
    <tr>
      <td>번 호</td>
      <td>제   목</td>
      <td>작성자</td>
      <td>작성일</td>
      <td>조 회</td>
     
    </tr>
    

   <c:forEach var="article" items="${articleList}">
   <tr>
    <td>
  <c:out value="${number}"/>
  <c:set var="number" value="${number - 1}"/>
</td>
    <td>
  <c:if test="${article.re_level > 0}">
  <img src="images/level.gif" width="${5 * article.re_level}" height="16">
    <img src="images/re.gif">
  </c:if>
  <c:if test="${article.re_level == 0}">
    <img src="images/level.gif" width="${5 * article.re_level}" height="16">
  </c:if>
          
      <a href="pdqContent.do?pdqNo=${article.pdqNo}&pageNum=${currentPage}">
          ${article.pdqTitle}</a>
         <c:if test="${article.pdqHits >= 20}">
            <img src="images/hot.gif" border="0"  height="16">
  </c:if>  
</td>
     <td>
     ${article.writer}
</td> 
    <td>${article.pdqDate}
</td>
      <td>${article.pdqHits}</td>  
  </tr>
  </c:forEach>
<!--    <tr>
   <td><td><td><td>
    <td align="right">
       <a href="writeForm.do"><h6>글쓰기</h6></a>
    </td>
  </tr> -->
</table>
</c:if>

<c:if test="${count > 0}">
   <c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}"/>
   <c:set var="pageBlock" value="${10}"/>
   <fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
   <c:set var="startPage" value="${result * 10 + 1}" />
   <c:set var="endPage" value="${startPage + pageBlock-1}"/>
   <c:if test="${endPage > pageCount}">
        <c:set var="endPage" value="${pageCount}"/>
   </c:if>
         
   <c:if test="${startPage > 10}">
        <a href="pdqlist.do?pageNum=${startPage - 10 }">[이전]</a>
   </c:if>

   <c:forEach var="i" begin="${startPage}" end="${endPage}">
       <a href="pdqlist.do?pageNum=${i}">[${i}]</a>
   </c:forEach>

   <c:if test="${endPage < pageCount}">
        <a href="pdqlist.do?pageNum=${startPage + 10}">[다음]</a>
   </c:if>
</c:if>

<div align="right">
<a class="btn-default" href="pdqwriteForm.do?pageNum=${pageNum}">글쓰기</a></div>
<!-- <button type="button" class="btn-default" href="noticewriteForm.do">글쓰기</button> -->

  <!--      <a align ="right" href="noticewriteForm.do"><h6>글쓰기</h6></a> -->

<form colspan=2 bgcolor="${value_c}" align="center">
<select name="searchn">
<option value="0">작성자</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>
 
<input type="text" name="search" size="15" maxlength="50" /> <input type="submit" value="검색" />
</form>
<br></br>
</div>
</body>
</html>