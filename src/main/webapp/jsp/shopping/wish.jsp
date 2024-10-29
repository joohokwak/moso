<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="w_external">
	<div class="wish_wrap">
		<div class="wish_content">
			<div class="wish_admin">
				<span><a href="/">Home</a></span>
				<span>마이페이지</span>
				<span>관심상품</span>
			</div>
			
			<div class="section">
				<div class="section_header">
					<h2>관심상품</h2>
				</div>
				<div class="section_body">
					<div class="catalog_table">
						<table class="news_title">
							<thead>
								<tr>
									<th width="5%">
										<div class="checkbox_wrap">
											<label class="checkbox1"></label>
											<input type="checkbox">
										</div>
									</th>
									<th>상품명/옵션</th>
									<th width="15%">상품금액/수량</th>
									<th width="15%">혜택</th>
									<th width="15%">담기/삭제</th>
								</tr>
							</thead>
						</table>
						<table class="news_body">
							<c:forEach var="wish" items="${wishList }">
							<tr>
								<th class="wish_ct" width="5%">
									<div class="checkbox_wrap">
										<label class="checkbox1"></label>
										<input type="checkbox">
									</div>
								</th>
								<th class="wish_ct">
									<div class="img">
										<a href="/Shop/buy?itemno=${wish.no }">
											<img src="/images/shopping/${wish.poster }" alt="포스터 이미지">
										</a>
									</div>
									<div>
										<div class="name">
											<a href="/Shop/buy?itemno=${wish.no }"><span>${wish.name }</span></a>
										</div>
										<div class="option">
											<p>사이즈 : Q</p>
											<p>설치배송여부 : 미신청(0원)</p>
										</div>
									</div>
								</th>
								<th class="wish_ct" width="15%">
									<span class="price"><fmt:formatNumber value="${wish.price }" pattern="#,###" /> 원 /</span>
									<span class="num"> 1개</span><br>
									<a href="#" class="option_btn">옵션/수량변경</a>
								</th>
								<th class="wish_ct" width="15%"></th>
								<th class="wish_ct" width="15%">
									<a href="#" class="button">장바구니</a>
									<a href="#" class="button">삭제하기</a>
								</th>
							</tr>
							</c:forEach>
							<c:if test="${empty wishList }">
							<tr>
								<td colspan="5" align="center" style="height: 50px;">등록된 관심 상품이 없습니다.</td>
							</tr>
							</c:if>
						</table>
					</div>
				</div>
				
				<!-- 페이징 -->
				<div class="pagination">${paging }</div>
				
				<div class="section_footer">
					<div class="wish_btn">
						<button type="button">선택 상품 삭제</button>
						<button type="button">선택 상품 장바구니</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
