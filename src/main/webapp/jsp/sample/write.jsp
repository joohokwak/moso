<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="external">
	<div class="inquiry_wrap">
		<div class="inquiry_content">
			<div class="inquiry_admin">
				<span><a href="#">Home</a></span> <span>글쓰기</span>
			</div>
			
			<div class="inquiry_section">
				<div class="section_title">
					<h2>글쓰기</h2>
					<p>구분을 통해 게시글을 등록합니다.</p>
				</div>
				
				<form action="#" class="section_main" method="post" enctype="multipart/form-data">
					<ul>
						<li>
							<span>구분</span>
							<div class="main_field">
								<button type="button" class="division_btn">
									<p>회원/정보관리</p>
									<b></b>
								</button>
								<ul class="division">
									<li><a href="#">회원/정보관리</a></li>
									<li><a href="#">주문/결재</a></li>
									<li><a href="#">배송</a></li>
									<li><a href="#">반품/환불/교환/AS</a></li>
									<li><a href="#">영수증/증빙서류</a></li>
									<li><a href="#">상품/이벤트</a></li>
									<li><a href="#">기타</a></li>
								</ul>
							</div>
						</li>
						<li><span>제목</span>
							<div class="main_field">
								<input type="text" class="field_tit" name="title" />
							</div>
						</li>
						<li>
							<span>작성자</span>
							<div class="main_field">
								<input type="text" placeholder="홍길동" readonly value="${member.name }" />
							</div>
						</li>
						<li>
							<span>휴대폰</span>
							<div class="main_field">
								<input type="tel" class="field_box" placeholder="010-1111-2222" value="${member.phone }" />
								<p>※ 공백없이 숫자로만 입력하세요.</p>
							</div>
						</li>
						<li>
							<span>이메일</span>
							<div class="main_field">
								<input type="email" class="field_box" placeholder="hong@hong.com" value="${member.email }" />
								<p>※ 입력하신 이메일로 답변 메일이 발송됩니다.</p>
							</div>
						</li>
						<li>
							<span>내용</span>
							<div class="main_field">
								<p>해당글은 비밀글로만 작성이 됩니다. ※ 네이버 등 기타 온라인몰에서 구매하신 고객분들께서는 구매처에
									문의를 부탁드립니다.</p>
								<textarea id="writeEditor" class="field_txt" data-editor></textarea>
							</div>
						</li>
						<li>
							<span>파일</span>
							<div class="main_field">
								<input type="file" class="field_file" />
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
