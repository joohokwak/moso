<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="external">
	<div class="brand_wrap">
		<div class="brand_content">
			<div class="brand_menu">
				<ul class="tabs">
					<li><a href="#">이벤트</a></li>
					<li><a href="/Magazine/magazine" class="on">매거진</a></li>
					<li><a href="#">지누스 스토리</a></li>
					<li><a href="#">Mattress in a Box</a></li>
					<li><a href="#">제품안전인증</a></li>
					<li><a href="#">내게 맞는 매트리스 찾기</a></li>
					<li><a href="#">현대백화점 그룹</a></li>
				</ul>
			</div>
			<div class="brand_admin">
				<span><a href="#">Home</a></span>
				<span>브랜드</span>
				<span>매거진</span>
			</div>
			<div class="brand_section">
				<div class="section_header">
					<ul>
						<li class="on"><a href="/Magazine/magazine">매거진</a></li>
						<li><a href="/Magazine/review">월간 리뷰</a></li>
					</ul>
				</div>
				<div class="section_search">
					<input class="search" type="text" placeholder="Search" title="검색어입력">
				</div>
				<div class="section_main">
					<div class="category">
						<span><a href="#" class="on">전체(73)</a></span>
						<span><a href="#">진행중 이벤트(2)</a></span>
						<span><a href="#">종료된 이벤트(71)</a></span>
					</div>
					<div class="board">
						<ul>
							<c:forEach var="m" items="${magazine }">
								<li>
									<a href="/Magazine/view"><img src="/images/magazine/${m.poster }"></a>
									<div>
										<h3><a href="/Magazine/view?no=${m.no }">${m.title }</a></h3>
										<p>${m.text }</p>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			
			<!-- 페이징 -->
			<div class="pagination">
				<span class="page_num active">1</span>
				<span class="page_num"><a href="#">2</a></span>
				<span class="page_num"><a href="#">3</a></span>
				<span class="page_num"><a href="#">4</a></span>
			</div>
		</div>
	</div>
</div>
