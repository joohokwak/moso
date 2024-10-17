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
			<div class="notice_view_wrap">
				<div class="notice_view_head">
					<div class="notice_view_tit">
						<strong>제목</strong>
						<h2>${dto.title }</h2>
					</div>
					<div class="notice_view_info">
						<strong>등록일</strong>
						<h2>${dto.regdate }</h2>
					</div>
					<div class="notice_view_info">
						<strong>조회수</strong>
						<h2>${dto.visitcount }</h2>
					</div>
				</div>
			</div>
			<div class="notice_view_body">${dto.content }</div>
		</div>
		<div class="back_to_list">
			<a href="/Notice/list">목록보기</a>
		</div>
		<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
			<button class="btn update_btn" onclick="location.href='/Notice/update?no=${dto.no}'" >수정</button>
			<button class="btn delete_btn" data-no="${dto.no }">삭제</button>
		</div>
	</div>
</div>
