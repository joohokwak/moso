<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<div class="search_frm">
					<form action="/Catalog/list" method="post">
						<fieldset>
							<legend><span class="blind">검색</span></legend>
							<input type="text" placeholder="Search" name="title" title="검색" value="${param.title }">
						</fieldset>
					</form>
				</div>
				<div class="section_body">
					<div class="catalog_table">
						<table class="news">
							<colgroup>
								<col style="width: 10%;">
								<col>
								<col style="width: 10%;">
								<col style="width: 10%;">
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>등록일</th>
									<th>조회</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="ct" items="${catalog }">
								<tr>
									<td class="col_no">${ct.no }</td>
									<td class="col_tit">
										<a href="/Catalog/view?no=${ct.no }">${ct.title }</a>
										<c:if test="${not empty ct.ofile }">
											<img src="/images/catalog/icon_board_attach_file.png" alt="파일첨부 있음">
										</c:if>
										<c:if test="${ct.visitcount ge 10 }">
											<img src="/images/catalog/icon_board_hot.png" alt="인기글">
										</c:if>	
									</td>
									<td class="col_postdate">${ct.regdate }</td>
									<td class="col_visicount">${ct.visitcount }</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
						<button class="btn write_btn">글쓰기</button>
					</div>
					
					<!-- 페이징 -->
					<div class="pagination">${paging }</div>
				</div>
			</div>
		</div>
	</div>
</div>
