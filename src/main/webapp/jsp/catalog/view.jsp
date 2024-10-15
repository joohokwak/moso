<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="catalog_container">
	<div id="catalog_content">
		<div class="inner">
			<div class="route">
				<span>
					<a href="/">Home</a>
				</span>
				<span>고객서비스</span>
				<span>카탈로그</span>
			</div>
			<div class="section">
				<div class="section_header">
					<h2>카탈로그</h2>
				</div>
				<div class="catalog_view_header">
					<div class="catalog_view_title">
						<strong>제목</strong>
						<h3>${view.title }</h3>
					</div>
					<div class="catalog_view_info">
						<strong>등록일</strong>
						<span class="text1">${view.regdate }</span>
					</div>
					<div class="catalog_view_info">
						<strong>조회수</strong>
						<span class="text1">${view.visitcount }</span>
					</div>
					<div class="catalog_view_info">
						<img src="/images/catalog/icon-clip.png" alt="첨부파일 있음">
						<strong>첨부파일</strong>
						<c:if test="${not empty view.ofile }">
							<a href="/Catalog/download?ofile=${view.ofile }&nfile=${view.nfile }" class="file_name">${view.ofile }</a>
						</c:if>
					</div>
				</div>
				<div class="catalog_view_body">
					<div class="text_field">
						<p>${view.content }</p>
					</div>
				</div>
				<div class="catalog_list_btn">
					<div data-isadmin="${member.isadmin eq 'Y' }">
						<a href="#" class="update_btn" data-no="${view.no }">수정</a>
						<a href="#" class="delete_btn" data-no="${view.no }">삭제</a>
					</div>
					<a href="javascript:history.back();">목록</a>
				</div>
			</div>
		</div>
	</div>
</div>
