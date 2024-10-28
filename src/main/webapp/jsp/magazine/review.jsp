<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<span>월간 리뷰</span>
			</div>
			<div class="brand_section">
				<div class="section_header">
					<ul>
						<li><a href="/Magazine/magazine">매거진</a></li>
						<li class="on"><a href="/Magazine/review">월간 리뷰</a></li>
					</ul>
				</div>
				<div class="section_search">
					<form action="/Magazine/review" method="post">
						<input class="search" type="text" name="title" placeholder="Search" title="검색어입력" value="${param.title }">
					</form>
				</div>
				<div class="section_main">
					<div class="category">
						<span><a href="#" class="on">전체(${count })</a></span>
						<span><a href="#">진행중 이벤트(0)</a></span>
						<span><a href="#">종료된 이벤트(14)</a></span>
					</div>
					<div class="review">
						<ul>
							<c:forEach var="m" items="${magazine }">
								<li>
									<a href="/Magazine/view?no=${m.no }"><img src="/images/review/${m.poster }"></a>
									<div>
										<h3><a href="/Magazine/view?no=${m.no }">${m.title }</a></h3>
										<p>${m.text }</p>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
				<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
					<button class="btn write_btn">글쓰기</button>
				</div>
			</div>
			
			<!-- 페이징 -->
			<div class="pagination">${paging }</div>
		</div>
	</div>
</div>
