<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="#">Home</a></span> <span>글쓰기</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>NOTICE</h2>
				</div>
				
				<form action="/Notice/writeOk" class="section_main" method="post" enctype="multipart/form-data">
					<ul>
						<li><span>제목</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" />
							</div>
						</li>
						<li>
							<span>내용</span>
							<div class="main_field">
								<textarea id="writeEditor" name="content" class="field_txt" data-editor></textarea>
							</div>
						</li>
						<li>
							<span>파일</span>
							<div class="main_field">
								<input type="file" class="field_file" name="file" />
							</div>
						</li>
					</ul>
					
					<div class="section_bottom">
						<a href="javascript:history.back();" class="return">이전</a>
						<button type="submit" class="save" onclick="location.href='/Notice/writeOk'" >확인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
