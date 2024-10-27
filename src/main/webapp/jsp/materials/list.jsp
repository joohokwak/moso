<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="materials_wrap">
	<div class="inner">
		<div class="materials_top">
			<span><a href="/">Home</a></span>
			<span>고객서비스</span>
			<span>조립설명서</span>
		</div>

		<div class="materials_contents">
			<h2>Materials</h2>

			<form action="/Materials/list" method="post">
				<div class="materials_search">
					<input type="text" title="검색어 입력" name="title" class="materials_text" placeholder="Search" value="${param.title }">
				</div>
			</form>

			<div class="materials_content">
				<ul>
				<c:forEach var="list" items="${list }">
					<li>
						<img src="/images/materials/${list.poster }" alt="설명서 이미지">
						<p>
							<strong>${list.title } (${list.msize })</strong><br>
							${list.txt } (${list.msize })
						</p>
						<button type="button" onclick="fileDownLoad('${list.ofile }', '${list.nfile }');">DOWNLOAD</button>
						<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
							<button class="btn update_btn" data-no="${list.no }">수정</button>
							<button class="btn delete_btn" data-no="${list.no }">삭제</button>
						</div>
					</li>
				</c:forEach>
				</ul>
			</div>
			
			<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
				<button class="btn write_btn">글쓰기</button>
			</div>
		</div>

		<!-- 페이징 -->
		<div class="pagination">
			${paging }
		</div>
	</div>
</div>
