<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="admin_list_wrap">
	<table>
		<tr>
			<th width="50px"><input type="checkbox" id="chkAll"><label for="chkAll"><span class="blind">전체체크</span></label></th>
			<th width="50px">No.</th>
			<th width="100px">IMG.</th>
			<th>제목</th>
			<th width="150px">텍스트</th>
			<th width="120px">사이즈</th>
			<th width="120px">파일</th>
		</tr>
		<c:forEach var="list" items="${list }">
		<tr>
			<td align="center"><label class="checkbox"><input type="checkbox" class="blind chks" data-no="${list.no }"></label></td>
			<td align="center">${list.no }</td>
			<td><img src="/images/materials/${list.poster }" alt="설명서 이미지" width="80px"></td>
			<td><a href="/Materials/update?no=${list.no }&isadmin=Y">${list.title }</a></td>
			<td>${list.txt }</td>
			<td>${list.msize }</td>
			<td>${list.ofile }</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div class="btn_wrap">
	<button type="button" class="btn write_btn">설명서 등록</button>
	<button type="button" class="btn delete_btn">선택삭제</button>
</div>