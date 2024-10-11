<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <div id="shopping">
   <div class="view">
     <img class="main_img" src="/images/shopping/mattress.jpg" alt="mattress">
	<div class="title">
		<h2>Mattress</h2>
		<p>완벽한 숙면을 위한 선택</p>
		<a href="#">제품 바로보기</a>
	</div>
   </div>
   <div class="go_menu">
     <ul class="inner">
		<li class="box">
			<div class="box_inner">
				<h4>Memory Foam<br>Mattress</h4>
				<h5>메모리폼 매트릭스</h5>
				<p>어떤 수면 자세에도<br>몸 전체를 빈틈없이 받쳐주는 편안함</p>
				<a href="#">SHOP</a>
			</div>
		</li>
		<li class="box">
			<div class="box_inner">
				<h4>Spring<br>Mattress</h4>
				<h5>스프링 매트릭스</h5>
				<p>아이코일* 기술 적용,<br>흔들림을 최소화한 안정적인 지지력</p>
				<a href="#">SHOP</a>
			</div>
		</li>
		<li class="box">
			<div class="box_inner">
				<h4>Topper</h4>
				<h5>토퍼</h5>
				<p>매트리스 위에서도, 바닥에서도<br>오래도록 느낄 수 있는 포근함</p>
				<a href="#">SHOP</a>
			</div>
		</li>
     </ul>
   </div>
<div class="shopping_view">
	<div class="inner">	
		<div id="location" class="location">
			<span>Home</span>
			<span class="slash">/</span>
			<div class="show_more">
				<a href="#" class="current_active">매트리스</a>
				<ul class="category">
					<li>
						<a href="#" class="active">매트리스</a>
					</li>
					<li>
						<a href="#">침대프레임</a>
					</li>
					<li>
						<a href="#">토퍼</a>
					</li>
					<li>
						<a href="#">필로우</a>
					</li>
					<li>
						<a href="#">베딩</a>
					</li>
					<li>
						<a href="#">Sale</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="filter">
			<div class="select_toggle">
				<span class="current_select">타입선택</span>
				<ul class="type_select">
						<li><a >타입 선택</a></li>
						<li><a href="/Shop/main?type=memory">메모리폼 매트리스</a></li>
						<li><a href="/Shop/main?type=spring">스프링 매트리스</a></li>
						<li><a href="/Shop/main?type=air">에어 매트리스</a></li>
						<li><a href="/Shop/main?type=luxe">Luxe S collection</a></li>
						<li><a href="/Shop/main?type=forest">ForestWalk</a></li>
				</ul>
			</div>
			
			<ul class="rank">
				<li class="${param.ordered eq 'pop' ? 'active' : '' }">
					<a href="/Shop/main?ordered=pop${typecheck }" class="sort">
						인기순
					</a>
				</li>
				<li class="${param.ordered eq 'new' ? 'active' : '' }">
					<a href="/Shop/main?ordered=new${typecheck }" class="sort">
						신상품순
					</a>
				</li>
				<li class="${param.ordered eq 'price' ? 'active' : '' }">
					<a href="/Shop/main?ordered=price${typecheck }" class="sort">
						가격순
					</a>
				</li>
				<li class="${param.ordered eq 'review' ? 'active' : '' }">
					<a href="/Shop/main?ordered=review${typecheck }" class="sort">
						상품평순
					</a>
				</li>
			</ul>
		</div>
		<div class="list">
			<c:forEach var="list" items="${list }">
			<ul class="product">
				<li class="product_img">
					<a href="/Shop/buy?num=${list.no }">
						<img src="/images/shopping/${list.poster }" alt="포레스트 워크S 하이브리드 스프링 매트리스 30cm">
					</a>
					<a href="#" class="wish" data-no="${list.no }"><img src="/images/shopping/wish_off.png" alt="좋아요"></a>
				</li>
				<li class="discription">
					<a href="/Shop/buy?num=${list.no }">
						<strong>
							${list.name} 
						</strong>
						<em>
							${list.text }
						</em>
					</a>
				</li>
				<li>
					<p class="price">최저 <strong><fmt:formatNumber value="${list.price }" pattern="#,###" /></strong> 원</p>
					<div class="type">
						<c:set var="size" value="${fn:split(list.sizename, ',') }" />
						<c:forEach var="s" items="${size }">
							<c:choose>
								<c:when test="${s eq 'Q' }"><img src="/images/shopping/S_03.png" alt="Q"></c:when>
								<c:when test="${s eq 'LK' }"><img src="/images/shopping/S_01.png" alt="LK"></c:when>
								<c:when test="${s eq 'SS' }"><img src="/images/shopping/S_04.png" alt="SS"></c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${list.point eq '중간' }"><img src="/images/shopping/H_03.png" alt="중간"></c:if>
					</div>
				</li>
			</ul>
			</c:forEach>
		</div>
	</div>
</div>
<div class="pagination">
	<span class="page_num active">1</span>
	<span class="page_num"><a href="#">2</a></span>
	<span class="page_num"><a href="#">3</a></span>
	<span class="page_num"><a href="#">4</a></span>
</div>
 </div>
