<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content_admin">
	<div class="inner">
		<div class="route">Home  /  비즈니스  /  공지사항/공시</div>
		<div class="admin_notice_wrap">
			<div class="admin_notice_header">
				<h2>공지사항/공시</h2>
				<ul class="tabs">
					<li>
						<a href="#">공지사항/공시</a>
					</li>
				</ul>
			</div>
			<form class="search_box" action="/Notice/list" method="post">
				<input type="text" class="text_search" placeholder="Search" name="title" title="검색어" value="${param.title }">
			</form>
			<table class="admin_notice_table">
					<thead>
						<tr>
							<th width="3%" class="delete_col" data-isadmin="${member.isadmin eq 'Y' }">
								<label class="checkbox">
									<input type="checkbox" class="blind" id="noticeDelAll" />
								</label>
							</th>
							<th width="10%">번호</th>
							<th>제목</th>
							<th width="10%">등록일</th>
							<th width="10%">조회</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list }">
						<tr>
							<td class="delete_col"  data-isadmin="${member.isadmin eq 'Y' }">
								<label class="checkbox">
									<input type="checkbox" class="blind selectedDelNo" name="delSelNo" data-no="${list.no }" />
								</label>
							</td>
							<td>${list.no }</td>
							<td class="visit_count">
								<a href="/Notice/view?no=${list.no }">
									${list.title }
									<c:if test="${not empty list.ofile }"><img src="/images/notice/icon_board_attach_file.png"></c:if>
								</a>
							</td>
							<td>${list.regdate }</td>
							<td>${list.visitcount }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				
				<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
					<button class="btn write_btn">글쓰기</button>
					<button class="btn delete_btn">글삭제</button>
				</div>
			
			
			<!-- 페이징 -->
			<div class="pagination">
				${paging }
			</div>
		</div>
	</div>
</div>
