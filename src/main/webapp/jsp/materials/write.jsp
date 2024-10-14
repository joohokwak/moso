<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="#">Home</a></span> <span>글쓰기</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>Materials</h2>
				</div>
				
				<form action="/Materials/writeOk" class="section_main" method="post" enctype="multipart/form-data">
					<ul>
						<li><span>Title</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" />
							</div>
						</li>
						<li>
							<span>Size</span>
							<input type="checkbox" id="S" class="field_tit" name="msize" value="S" />
							<label for="S">S</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="SS" class="field_tit" name="msize" value="SS" />
							<label for="SS">SS</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="Q" class="field_tit" name="msize" value="Q" />
							<label for="Q">Q</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="K" class="field_tit" name="msize" value="K" />
							<label for="K">K</label>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="LK" class="field_tit" name="msize" value="LK" />
							<label for="LK">LK</label>&nbsp;&nbsp;&nbsp;&nbsp;
						</li>
						<li>
							<span>Descript</span>
							<div class="main_field">
								<textarea id="writeEditor" name="txt" class="field_txt" data-editor></textarea>
							</div>
						</li>
						<li>
							<span>Poster</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="poster" />
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
