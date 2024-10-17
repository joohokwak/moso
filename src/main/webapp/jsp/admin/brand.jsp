<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="admin_list_wrap">
	<table>
		<tr>
			<th width="50px"><input type="checkbox" id="chkAll"><label for="chkAll"><span class="blind">전체체크</span></label></th>
			<th width="50px">No.</th>
			<th width="120px">포스터</th>
			<th>제목</th>
			<th width="120px">카테고리</th>
			<th width="150px">작성일</th>
		</tr>
		<c:forEach var="list" items="${list }">
		<tr>
			<td align="center"><label class="checkbox"><input type="checkbox" class="blind chks" data-no="${list.no }"></label></td>
			<td align="center">${list.no }</td>
			<td><img src="/images/${list.mtype }/${list.poster }" alt="매거진 포스터" width="100px"></td>
			<td><a href="/Magazine/update?no=${list.no }&isadmin=Y">${list.title }</a></td>
			<td>${list.mtype }</td>
			<td align="center">${list.regdate }</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div class="btn_wrap">
	<button type="button" class="btn write_btn">매거진 등록</button>
	<button type="button" class="btn delete_btn">선택삭제</button>
</div>
