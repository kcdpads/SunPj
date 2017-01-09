<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./view/color.jspf"%>

<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>

<body> 
<b>글수정</b>
<br><br><br><br><br><br><br><br>
<div class="container">

<form method="post" name="writeform" action="noticeupdatePro.do?pageNum=${pageNum}" onsubmit="return writeSave()">
<input type="hidden" name="writer" value="${article.writer}">
<table class="table table-bordered">

  <tr>
    <td>이 름</td>
    <td>
     ${article.writer}
   <input type="hidden" name="noticeNo" value="${article.noticeNo}"></td>
  </tr>
  <tr>
    <td>제 목</td>
    <td>
       <input type="text" size="128" maxlength="50" name="noticeTitle" value="${article.noticeTitle}"></td>
  </tr>
  <tr>
    <td>내 용</td>
    <td>
     <textarea name="noticeContent" rows="13" cols="130">${article.noticeContent}</textarea></td>
  </tr>
  <tr>
    
    <td>비밀번호</td>
    <td>
     <input type="password" size="8" maxlength="12" name="passwd">
     
</td>
  </tr>
  <tr>     
   <td colspan=2 bgcolor="${value_c}" align="center">
     <input type="submit" value="글수정" > 
     <input type="reset" value="다시작성">
     <input type="button" value="목록보기"
       onclick="document.location.href='noticelist.do?pageNum=${pageNum}'">
   </td>
</tr>

</table>
</form>
</div>
</body>
</html>  