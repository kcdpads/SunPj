<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#btnTd button").click(function() {

			var num = $(this).attr("id");
			var trname = "div#" + num;
			$(trname).remove();
			return false;
		});
	});
</script>


<div class="page-head content-top-margin">
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-sm-7">
				<ol class="breadcrumb">
					<li><a href="main.do">Home</a></li>
					<li class="active">디자이너 정보</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
</div>
<section class="section" id="page-about">
	<div class="container">
		<form name="addForm" id="addForm" method="post"
			enctype="multipart/form-data" runat="server" action="designerSubmit.do">
			<div class="block clearfix">
				<div class="col-sm-12 section-title text-center">
					<h3>
						<i class="line"></i>디자이너 정보<i class="line"></i>
					</h3>
				</div>
				<div id="our-team">
					<div class="row">
						<c:forEach items="${designerList}" var="designerList"
							varStatus="status">
							<div class="col-sm-3" style="margin-bottom: 20px;"
								id="${status.count}">
								<div class="author" id="author">
									<img src="${designerList.designerPhoto}">
									<h3>${designerList.designer}</h3>
									<div align="left" style="margin-left: 30px;">
										<p>경력 - ${designerList.spec}</p>
										<p>특기 - ${designerList.favorite}</p>
										<p>직급 - ${designerList.position}</p>
									</div>
									<input type="hidden" name="designerNo" id="designerNo"
										value="${designerList.designerNo}">
									<c:if test="${myId==admin}">
										<div class="row" style="margin: auto;" id="btnDiv">
											<div class="col-sm-5">
												<a href="updateDesignerForm.do?designerNo=${designerList.designerNo}" class="btn btn-warning">수정</a>
											</div>
											<div id="btnTd" class="col-sm-5">
												<button class="btn btn-primary" id="${status.count}">삭제</button>
											</div>
										</div>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- /#our-team -->
			</div>
			<c:if test="${myId==admin}">
				<div class="row" style="margin: auto; width: 400px">
					<input type="button" value="베너추가" class="btn btn-primary"
						onclick="javascript:location.href='addDesignerForm.do'" /> <input
						type="submit" id="submit" value="확인" class="btn btn-default" /> <input
						type="button" value="취소" class="btn btn-primary"
						onclick="javascript:location.href='designer.do'">
				</div>
			</c:if>

		</form>
		<!-- /.block -->
	</div>
	<!-- /.container -->
</section>
