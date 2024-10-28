<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="b_external">
	<div class="brand_wrap">
		<div class="brand_content">
			<div class="brand_menu">
				<ul class="tabs">
					<li><a href="#">이벤트</a></li>
					<li><a href="/Magazine/magazine" class="on">매거진</a></li>
					<li><a href="#">지누스 스토리</a></li>
					<li><a href="#">Mattress in a Box</a></li>
					<li><a href="#">제품안전인증</a></li>
					<li><a href="/Quiz/home">내게 맞는 매트리스 찾기</a></li>
					<li><a href="#">현대백화점 그룹</a></li>
				</ul>
			</div>
			<div class="brand_admin">
				<span><a href="/">Home</a></span>
				<span>브랜드</span>
				<span>매거진</span>
			</div>
			<div class="brand_section">
				<div class="section_header">
					<ul>
						<li class="${magazine.mtype eq 'magazine' ? 'on' : '' }"><a href="/Magazine/magazine">매거진</a></li>
						<li class="${magazine.mtype eq 'review'   ? 'on' : '' }"><a href="/Magazine/review">월간 리뷰</a></li>
					</ul>
				</div>
				<div class="section_main">
					<div class="viewer">
						${magazine.content }
					</div>
					<div class="list_btn">
						<a href="/Magazine/magazine">목록</a>
						<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
							<a href="/Magazine/update?no=${magazine.no }">수정</a>
							<a href="#" class="delete_btn" data-no=${magazine.no }>삭제</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
