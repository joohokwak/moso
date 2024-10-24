<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="search_content">
	<div class="inner">
		<strong>
			<span>"${param.keyword}${param.name }"</span>
			검색결과 
			<c:if test="${not empty search }">${search[0].totalCount}개</c:if> 
			<c:if test="${empty search }">0개</c:if>
		</strong>
		<div class="search_input">
			<form action="/Quiz/search" method="post">
				<fieldset>
					<legend>검색 폼</legend>
						<span class="research_form">
							<input type="checkbox" id="rescan" name="reSearch" class="checkbox" value="c">
							<label for="rescan">결과 내 재검색</label>
						</span>
						<select class="select_type" name="key" id="check-s">
							<option value="goodsName" ${param.key eq 'goodsName' ? 'selected' : '' }>상품명</option>
							<option value="goodsNo"   ${param.key eq 'goodsNo' 	 ? 'selected' : '' }>상품코드</option>
							<option value="goodsText" ${param.key eq 'goodsText' ? 'selected' : '' }>상품설명</option>
						</select>
						<div class="txt_field">
							<input type="text" name="keyword" class="text_keyword" autocomplete="off" value="${param.keyword }">
						</div>
						<button type="submit" class="search_btn">
							검색
							<img src="/images/shopping/search.png" alt="상품 검색 버튼">
						</button>
				</fieldset>
			</form>
		</div>
		<div class="order_sort">
			<form action="/Quiz/search" method="post" name="sortFrm">
				<ul class="sort_wrap">
					<li class="${param.sort eq 'visit_desc' ? 'on' : '' }">
						<input type="radio" id="sort1" name="sort" value="visit_desc" checked>
						<label for="sort1">인기순</label>
					</li>
					<li class="${param.sort eq 'regdate_desc' ? 'on' : '' }">
						<input type="radio" id="sort2" name="sort" value="regdate_desc">
						<label for="sort2">신상품순</label>
					</li>
					<li class="${param.sort eq 'price_desc' ? 'on' : '' }">
						<input type="radio" id="sort3" name="sort" value="price_desc">
						<label for="sort3">가격순</label>
					</li>
					<li class="${param.sort eq 'review_desc' ? 'on' : '' }">
						<input type="radio" id="sort4" name="sort" value="review_desc">
						<label for="sort4">상품평순</label>
					</li>
				</ul>
			</form>
		</div>
		<div class="search_result">
			<ul>
			<c:forEach var="item" items="${search }">
				<li>
					<div class="goods_space">
						<div class="thumbnail">
							<a href="#" class="wish" data-no="${item.no }" data-islogin="${not empty member }">
							<c:set var="mids" value="${fn:split(item.memberid, ',') }"/>
	                        <c:set var="isInWishlist" value="false"/>
	                        <c:forEach var="mid" items="${mids}">
	                            <c:if test="${mid eq member.id }">
	                               <c:set var="isInWishlist" value="true"/>
	                              <img alt="위시리스트" src="/images/shopping/wish_on.png">
	                            </c:if>
	                        </c:forEach>
	                        <c:if test="${not isInWishlist}">
	                            <img alt="위시리스트" src="/images/shopping/wish_off.png">
	                        </c:if>
							</a>
							<a href="/Shop/buy?itemno=${item.no }">
								<img src="/images/shopping/${item.poster }" alt="${item.name }">
							</a>
						</div>
						<div class="txt_wrap">
							<a href="/Shop/buy?itemno=${item.no }">
								<strong>${item.name }</strong>
								<span>${item.text }</span>
							</a>
						</div>
						<div class="lowest_price">
							<span>최저 <strong><fmt:formatNumber value="${item.price }" pattern="#,###"/></strong>원</span>
						</div>
						<div class="type">
							<img src="/images/shopping/S_03.png" alt="이곳에 사이즈를 넣어주세요">
							<img src="/images/shopping/S_04.png" alt="이곳에 사이즈를 넣어주세요">
						</div>
					</div>
				</li>
			</c:forEach>
			</ul>
		</div>
		
		<!-- 페이징 -->
		<div class="pagination">${paging }</div>
	</div>
</div>
