<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="#">Home</a></span>
				<span>글쓰기</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>FAQ</h2>
				</div>
				
				<form action="/Faq/updateOk" class="section_main" method="post">
					<ul>
						<li>
							<span>구분</span>
							<div class="main_field">
								<button type="button" class="division_btn">
									<p>${faq.cate }</p>
									<b></b>
								</button>
								<ul class="division">
									<li><a href="#">제품문의</a></li>
									<li><a href="#">주문/배송</a></li>
									<li><a href="#">교환/반품</a></li>
									<li><a href="#">결제</a></li>
									<li><a href="#">회원가입/탈퇴</a></li>
									<li><a href="#">A/S</a></li>
									<li><a href="#">기타</a></li>
								</ul>
								<input type="hidden" name="no" value="${faq.no }">
								<input type="hidden" name="cate" value="${faq.cate }">
								<input type="hidden" name="isadmin" value="${param.isadmin }">
							</div>
						</li>
						<li><span>제목</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" value="${faq.title }" />
							</div>
						</li>
						<li>
							<span>내용</span>
							<div class="main_field">
								<textarea id="writeEditor" name="content" class="field_txt" data-editor='${faq.content }'>${faq.content }</textarea>
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
