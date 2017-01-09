<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<!DOCTYPE html>
<link href="style.css" rel="stylesheet" type="text/css">
<section class="section collections no-padding-bottom"
	id="home-collections">

	<script type="text/javascript">
	/* $(function(){
		var s = $("#tdId")
		for(var i=0; i<s.length; i++){
			s.html(i);
		}; */
		$(function(){
		 
		var s = $("#trId #tdId");
		var f = s.length;
		s.each(function(i,e){
			$(this).html(f-i);
		}); 
		
		});
		function deleteCheck() {
			check = confirm("정말 삭제하시겠습니까?");
			if (check == true) {
				alert("삭제합니다.");

				location.href = "deleteStyleForm.do?styleNum=" + $
				{
					style.styleNum
				}
				+"&styleType=" + '${style.styleType}';
			} else {
				alert("취소합니다.");
			}
		}
		

	</script>
	
	<div class="page-head content-top-margin">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-sm-7">
					<ol class="breadcrumb">
						<li><a href="main.do">Home</a></li>

						<c:if test="${style.styleType eq 'cut' }">
							<li><a href="mCutList.do">Man Cut Styles</a></li>
						</c:if>
						<c:if test="${style.styleType eq 'perm' }">
							<li><a href="mPermList.do">Man Perm Styles</a></li>
						</c:if>
						<c:if test="${style.styleType eq 'color' }">
							<li><a href="mColorList.do">Man Color Styles</a></li>
						</c:if>
						<c:if test="${style.styleType eq 'long' }">
							<li><a href="wLongList.do">Woman Long Styles</a></li>
						</c:if>
						<c:if test="${style.styleType eq 'medium' }">
							<li><a href="wMediumList.do">Woman Medium Styles</a></li>
						</c:if>
						<c:if test="${style.styleType eq 'short' }">
							<li><a href="wShortList.do">Woman Short Styles</a></li>
						</c:if>


						<li class="active">${style.styleName }</li>
					</ol>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container -->
	</div>
	<!-- /.page-head -->

	<section class="section single-product-wrapper">
		<div class="container">

			<div class="row">
				<div class="col-sm-5">
					<div class="product-images">
						<div class="product-thumbnail">
							<img src="styleImages/${style.stylePhoto }"
								class="img-responsive"
								style="width: 320px; height: 450px; margin-left: 230px;">

						</div>
					</div>
					<!-- /.product-images -->
				</div>
				<div class="col-sm-6 col-sm-offset-1"
					style="margin-left: 180px; width: 380px; height: 450px;">
					<div class="product-details">
						<div class="rating">
							<span class="rating-text" style="margin-left: 0px;">조회수 :
								${style.viewCount }</span>
							<!-- <span class="pull-right">게시글 번호 <span>2</span></span> -->
						</div>
						<p>
						<div class="product-title">
							<h3 class="product-name">스타일 : ${style.styleName }</h3>
							<hr>
						</div>
						<div class="product-title">
							<h3 class="product-name">담당 디자이너 : ${style.designer }</h3>
							<hr>
						</div>

						<p class="price">
							가격 :
							<!-- <del>
								<span class="amount">15,000원</span>
							</del> -->
							<ins>
								<span class="amount">${style.price }원</span>
							</ins>
						</p>
						<c:if test="${myId eq 'admin' }">
							<input type="button" value="update" class="btn btn-default"
								style="margin-top: 100px;"
								Onclick="window.location='updateStyleForm.do?styleNum=${style.styleNum}'" />
							<input type="button" value="delete" class="btn btn-default"
								style="margin-top: 100px;" Onclick="deleteCheck()" />
						</c:if>
					</div>
					<!-- /.product-details -->
				</div>

			</div>
			<!-- /.row -->
			<p>
			<div class="section-title text-center">
				<h3>
					<i class="line"></i>Related Information<i class="line"></i>
				</h3>
			</div>
			<p>
				<c:if test="${videoList != null }">
					<c:forEach var="video" items="${videoList }">
						<p>
							<iframe src="${video }" frameborder="0" allowfullscreen
								style="width: 700px; height: 500px; margin-left: 230px;"></iframe>
						</p>
					</c:forEach>
				</c:if>
			<p>
				<c:forEach var="photo" items="${photoList }">
					<div class="col-sm-12">
						<div class="product-thumbnail">
							<img src="styleImages/${photo }" class="img-responsive" alt=""
								style="width: 700px; height: 500px; margin-left: 215px;">
						</div>
					</div>
				</c:forEach>
			</p>
<br><br>
<div class="container" style="margin: auto;width:80%">

<center><H2>시술 후기 게시판</H2>

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
<table class="table table-hover" id="styletable">
    <tr>
      <td>번 호</td>
      <td>제   목</td>
      <td>작성자</td>
      <td>작성일</td>
      <td>조 회</td>
     
    </tr>
    

   <c:forEach var="article" items="${articleList}">
   <c:if test="${style.styleName eq article.styleName}">
  
   <tr id="trId">
    <td id="tdId">
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
          
      <a href="pdqContent.do?pdqNo=${article.pdqNo}&pageNum=${currentPage}&styleNum=${style.styleNum}">
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
  </tr></c:if>
  </c:forEach>
<!--    <tr>
   <td><td><td><td>
    <td align="right">
       <a href="writeForm.do"><h6>글쓰기</h6></a>
    </td>
  </tr> -->
</table>
</c:if>
<div class="row">
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
 
        <a href="pdqlist.do?pageNum=${startPage - 10 }&styleNum=${style.styleNum }">[이전]</a>
   </c:if>

   <c:forEach var="i" begin="${startPage}" end="${endPage}">
         <a href="pdqlist.do?pageNum=${i}&styleNum=${style.styleNum }">[${i}]</a>
   </c:forEach>

   <c:if test="${endPage < pageCount}">
        <a href="pdqlist.do?pageNum=${startPage + 10}&styleNum=${style.styleNum }">[다음]</a>
   </c:if>
</c:if>

</div>
<table>
 
</table>
<br><br>
<form colspan=2 bgcolor="${value_c}" align="center">
<select name="searchn">
<option value="0">작성자</option>
<option value="1">제목</option>
<option value="2">내용</option>
</select>
       <a href="pdqwriteForm.do?styleNum=${style.styleNum }&styleName=${style.styleName }"><h6>글쓰기</h6></a>
<input type="text" name="search" size="15" maxlength="50" /> <input type="submit" value="검색" />
</form>
<br></br>

</div>
		</div>
		<!-- /.container -->
	</section>
	<!-- /.products -->
</section>