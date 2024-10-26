<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="shopping_write">
	<div class="goods">
		<h4>상품문의 답변</h4>
		<div class="goods_info">
			<div class="goods_img">
				<img src="/images/shopping/${item.poster }" alt="이미지">
			</div>
			<div>
				<strong>${item.name }</strong>
				<p>${item.text }</p>
			</div>
		</div>
	</div>
	
	<div class="contain">
		<form action="/Shop/answreOk" method="post" enctype="multipart/form-data">
			<input type="hidden" name="itemno" value="${param.itemno}">
			<input type="hidden" name="no" value="${qna.no}">
			
			<div class="container">
				<table>
					<tr>
						<th>구분</th>
						<td class="select_wrap1">
							<button class="cateBtn" type="button">상품</button>
							<ul class="option1">
								<li>상품</li>
								<li>배송</li>
								<li>반품/환불</li>
								<li>교환/변경</li>
								<li>기타</li>
							</ul>
							<input type="hidden" name="cate" id="cateBtn" value="상품">
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td class="input_txt">
							<input type="text" name="writer" value="${qna.writer}">
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td class="input_txt">
							<input type="text" placeholder="제목 입력" name="title" value="${qna.title}">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="content">
							<input type="hidden" id="check1" name="secret" value="${qna.secret}" class="blind">
							<textarea name="question">${qna.question}</textarea>
						</td>
					</tr>
					<tr>
						<th>답변 달기</th>
						<td style="padding-top: 20px;">
							<textarea id="answre" name="answre" data-editor="${qna.answre}">${qna.answre }</textarea>
						</td>
					</tr>
				</table>
			</div>
			<div class="bottom" style="padding: 20px 0;">
				<button onclick="javascript:history.go(-1);">취소</button>
				<button>등록</button>
			</div>
		</form>
	</div>
</div>
