<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="shopping_write">
	<div class="goods">
		<h4>상품문의 쓰기</h4>
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
		<form action="/Shop/rvwriteOk" method="post" enctype="multipart/form-data">
			<input type="hidden" name="itemno" value="${param.itemno}">
			
			<div class="container">
				<table>
					<tr>
						<th>작성자</th>
						<td class="input_txt">
							<input type="text" name="writer" value="${member.id }" readonly>
						</td>
					</tr>
					<tr>
						<th>평가</th>
						<td class="input_txt">
							<div class="mradio">
								<c:forEach begin="1" end="5" varStatus="vs">
									<div class="inradio ${vs.first ? 'active' : '' }" data-rating="${6 - vs.count}">
										<span><i class="circle"></i></span>
										<div class="rating">
											<c:forEach begin="${vs.count}" end="5">
												<img src="/images/shopping/star-fill.png" alt="별점">
											</c:forEach>
											<c:forEach begin="2" end="${vs.count }">
												<img src="/images/shopping/star-bg.png" alt="별점">
											</c:forEach>
										</div>
									</div>
									<input type="hidden" name="ratings" value="${6 - vs.count }"></input>
								</c:forEach>
							</div>
						</td>
					</tr>
					<tr>
						<th>제목</th>
						<td class="input_txt">
							<input type="text" placeholder="제목 입력" name="title">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="content">
							<div class="checkbox1_wrap">
								<label class="checkbox1" for="check1">비밀글</label>
								<input type="hidden" id="check1" name="secret" value="0">
							</div>
							<p>
								※ 네이버 등 기타 온라인몰에서 구매하신 고객분들께서는 구매처에 문의를 부탁드립니다. <br>
								※ 올려주신 상품후기는 사진과 함께 마케팅 용도로 활용될 수 있습니다. <br>
								※ 상품후기를 올리실 경우 이에 동의하시는 걸로 간주됩니다.
							</p>
							<textarea id="content" name="content" data-editor></textarea>
						</td>
					</tr>
				</table>
			</div>
			
			<!-- 버튼 -->
			<div class="bottom" style="padding: 20px 0;">
				<button onclick="window.close()" class="sub_btn">취소</button>
				<button id="openpopup_btn" type="button">등록</button>
			</div>
		</form>
	</div>
</div>