<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="admin_list_wrap">
	<table id="adminCatalogList">
		<tr>
			<th width="50px"><input type="checkbox" id="chkAll"><label for="chkAll"><span class="blind">전체체크</span></label></th>
			<th width="50px">No.</th>
			<th width="150px">카테고리</th>
			<th>제목</th>
			<th width="150px">작성일</th>
		</tr>
		<c:forEach var="list" items="${list }">
		<tr>
			<td align="center"><label class="checkbox"><input type="checkbox" class="blind member_chk" data-no="${list.no }"></label></td>
			<td align="center">${list.no }</td>
			<td>${list.cate }</td>
			<td><a href="/Faq/update?no=${list.no }">${list.title }</a></td>
			<td align="center">${list.regdate }</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div class="btn_wrap">
	<button type="button" class="btn write_btn">글쓰기</button>
	<button type="button" class="btn delete_btn">선택삭제</button>
	<button type="button" class="btn down_btn">다운로드</button>
</div>
