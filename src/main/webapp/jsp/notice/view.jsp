<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="notice_view">
	<div class="inner">
		<div class="route">Home / 비즈니스 / 뉴스</div>
		<div class="notice_view_wrap">
			<div class="notice_view_header">
				<h2>공지사항/공시</h2>
				<ul class="tabs">
					<li><a href="#">공지사항/공시</a></li>
				</ul>
			</div>
			<table class="notice_view_table">
				<tbody>
					<tr>
						<td class="tit" width="15%">제목</td>
						<td width="80%">${dto.title }</td>
					</tr>
					<tr>
						<td class="tit" width="15%">등록일</td>
						<td width="80%">${dto.regdate }</td>
					</tr>
					<tr>
						<td class="tit" width="15%">조회수</td>
						<td width="80%">${dto.visitcount }</td>
					</tr>
				</tbody>
			</table>
					<div class="view_content">${dto.content }</div>

			<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
				<button class="btn write_btn">수정</button>
				<button class="btn delete_btn" data-no="${dto.no }">삭제</button>
			</div>
		</div>
	</div>
</div>
