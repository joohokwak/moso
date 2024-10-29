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
		<!-- 폼 데이터 -->
		<form action="/Shop/answerOk" method="post" enctype="multipart/form-data">
			<input type="hidden" name="itemno" value="${param.itemno}">
			<!-- 질문을 찾기 위한 Q&A 번호 찾기 -->
			<input type="hidden" name="no" value="${qna.no}">
			<div class="container">
				<table>
					<tr>
						<th>구분</th>
						<td class="content">
							<div class="text_readonly">${qna.cate}</div>
						</td>
					</tr>
					<tr>
						<th>작성자</th>
						<td class="content">
							<div class="text_readonly">${qna.writer}</div>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td class="content">
							<div class="text_readonly">${qna.title}</div>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="content">
							<div class="text_readonly">${qna.question}</div>
						</td>
					</tr>
					<tr>
						<th>답변 달기</th>
						<td style="padding-top: 20px;">
							<textarea id="answer" name="answer" data-editor="${qna.answer}">${qna.answer }</textarea>
						</td>
					</tr>
				</table>
			</div>
			<!-- 버튼 -->
			<div class="bottom" style="padding: 20px 0;">
				<button onclick="javascript:history.go(-1);">취소</button>
				<button>등록</button>
			</div>
		</form>
	</div>
</div>
