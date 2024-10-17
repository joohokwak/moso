<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="#">Home</a></span>
				<span>글수정</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>MAGAZINE</h2>
				</div>
				
				<form action="/Magazine/updateOk" class="section_main" method="post" enctype="multipart/form-data">
					<ul>
						<li>
							<span>구분</span>
							<div class="main_field">
								<button type="button" class="division_btn">
									<p>
										<c:if test="${magazine.mtype eq 'magazine' }">매거진</c:if>
										<c:if test="${magazine.mtype eq 'review' }">월간 리뷰</c:if>
									</p>
									<b></b>
								</button>
								<ul class="division">
									<li><a href="#">매거진</a></li>
									<li><a href="#">월간 리뷰</a></li>
								</ul>
								<input type="hidden" name="mtype" value="${magazine.mtype }">
								<input type="hidden" name="no" value="${magazine.no }">
								<input type="hidden" name="isadmin" value="${param.isadmin }">
							</div>
						</li>
						<li><span>제목</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" value="${magazine.title }" />
							</div>
						</li>
						<li>
							<span>설명</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="text" value="${magazine.text }" />
							</div>
						</li>
						<li>
							<span>내용</span>
							<div class="main_field">
								<textarea id="writeEditor" name="content" class="field_txt" data-editor='${magazine.content }'>${magazine.content }</textarea>
							</div>
						</li>
						<li>
							<span>POSTER</span>
							<div class="main_field">
								<input type="file" class="field_file" name="poster" />
							</div>
						</li>
					</ul>
					
					<div class="section_bottom">
						<a href="javascript:history.back();" class="return">이전</a>
						<button class="save">확인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
