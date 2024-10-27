<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="/">Home</a></span>
				<span>수정하기</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>Materials</h2>
				</div>
				
				<form action="/Materials/updateOk" class="section_main" method="post" enctype="multipart/form-data">
					<input type="hidden" name="no" value="${data.no }" />
					<input type="hidden" name="isadmin" value="${param.isadmin }">
					
					<ul>
						<li><span>Title</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" value="${data.title }" />
							</div>
						</li>
						<li>
							<span>Size</span>
							<c:set var="msize" value="${fn:split(data.msize, ', ') }" />
							<input type="checkbox" id="S" class="field_tit" name="msize" value="S" <c:forEach var="ms" items="${msize }">${ms eq 'S' ? 'checked' : '' }</c:forEach> />
							<label for="S">S</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="SS" class="field_tit" name="msize" value="SS" <c:forEach var="ms" items="${msize }">${ms eq 'SS' ? 'checked' : '' }</c:forEach> />
							<label for="SS">SS</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="Q" class="field_tit" name="msize" value="Q" <c:forEach var="ms" items="${msize }">${ms eq 'Q' ? 'checked' : '' }</c:forEach> />
							<label for="Q">Q</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="K" class="field_tit" name="msize" value="K" <c:forEach var="ms" items="${msize }">${ms eq 'K' ? 'checked' : '' }</c:forEach> />
							<label for="K">K</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="LK" class="field_tit" name="msize" value="LK" <c:forEach var="ms" items="${msize }">${ms eq 'LK' ? 'checked' : '' }</c:forEach> />
							<label for="LK">LK</label>&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						<li>
							<span>Descript</span>
							<div class="main_field">
								<textarea id="writeEditor" name="txt" class="field_txt" data-editor='${data.txt }'>${data.txt }</textarea>
							</div>
						</li>
						<li>
							<span>Poster</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="poster" value="${data.poster }" />
							</div>
						</li>
						<li>
							<span>File</span>
							<div class="main_field">
								<input type="file" class="field_file" name="file" />
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
