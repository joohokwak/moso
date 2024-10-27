<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="/">Home</a></span>
				<span>수정하기</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>NOTICE</h2>
				</div>
				
				<form action="/Notice/updateOk" class="section_main" method="post" enctype="multipart/form-data">
					<input type="hidden" name="no" value="${dto.no }"/>
					<input type="hidden" name="isadmin" value="${param.isadmin }">
					
					<ul>
						<li><span>제목</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" value="${dto.title }"/>
							</div>
						</li>
						<li>
							<span>내용</span>
							<div class="main_field">
								<textarea id="writeEditor" name="content" class="field_txt" data-editor='${dto.content }'>${dto.content }</textarea>
							</div>
						</li>
						<li>
							<span>파일</span>
							<div class="main_field_notice">
								<label for="notice_file_update">파일선택</label>
								<input class="blind" name="nfile" />
								<input class="notice_file_update" name="ofile" value="${dto.ofile }" placeholder="첨부파일" />
								<input type="file" name="file" id="notice_file_update" style="display: none" />
								<c:if test="${fn: length(dto.ofile) > 0 }">
									<button type="button" id="cancel_btn_update" class="notice_cancel_btn">X</button>
								</c:if>
							</div>
							
						</li>
					</ul>
					
					<div class="section_bottom">
						<a href="javascript:history.back();" class="return">이전</a>
						<button class="save" >수정완료</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
