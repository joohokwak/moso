<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="admin_list_wrap">
	<table id="adminMemberList">
		<tr>
			<th width="50px"><input type="checkbox" id="chkAll"><label for="chkAll"><span class="blind">전체체크</span></label></th>
			<th width="100px">ID</th>
			<th width="100px">Name</th>
			<th width="200px">Email</th>
			<th width="130px">Phone</th>
			<th width="130px">Tel</th>
			<th width="100px">Zipcode</th>
			<th width="400px">Address</th>
			<th width="200px">Detail Addr</th>
			<th width="100px">Gender</th>
			<th width="100px">Birth</th>
			<th width="100px">IsAdmin</th>
		</tr>
		<c:forEach var="list" items="${list }">
		<tr>
			<td align="center"><label class="checkbox"><input type="checkbox" class="blind chks" data-no="${list.id }"></label></td>
			<td><a href="#" class="mem_id">${list.id }</a></td>
			<td>${list.name }</td>
			<td>${list.email }</td>
			<td>${list.phone }</td>
			<td>${list.tel }</td>
			<td>${list.zipcode }</td>
			<td>${list.address }</td>
			<td>${list.addr_detail }</td>
			<td align="center">${list.gender eq 'M' ? '남' : '여' }</td>
			<td>${list.birth }</td>
			<td align="center">${list.isadmin }</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div class="btn_wrap">
	<button type="button" class="btn" onclick="location.href='/Member/join?isadmin=Y'">회원등록</button>
	<button type="button" class="btn delete_btn">선택 회원탈퇴</button>
</div>
