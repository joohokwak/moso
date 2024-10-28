<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="external catalog">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="/">Home</a></span> <span>수정하기</span>
			</div>
			<div class="inquiry_section">
				<div class="section_title">
					<h2>CATALOG</h2>
				</div>
				<form action="/Catalog/updateOk" class="section_main" method="post" enctype="multipart/form-data">
					<input type="hidden" name="no" value="${update.no }" />
					<input type="hidden" name="isadmin" value="${param.isadmin }" />
					<ul>
						<li><span>제목</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" value="${update.title }"/>
							</div>
						</li>
						<li>
							<span>내용</span>
							<div class="main_field">
								<textarea id="updateEditor" name="content" class="field_txt" data-editor='${update.content }'>${update.content }</textarea>
							</div>
						</li>
						<li>
							<span>파일</span>
							<div class="main_field catalog_file_field">
								<input type="file" class="field_file" id="file" name="file"/>
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
