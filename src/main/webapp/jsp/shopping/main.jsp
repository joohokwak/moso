<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="shopping">
	<div class="view">
		<img class="main_img" src="/images/shopping/mattress.jpg" alt="mattress">
		<div class="title">
			<h2>Mattress</h2>
			<p>완벽한 숙면을 위한 선택</p>
			<a href="#">제품 바로보기</a>
		</div>
	</div>
	
	<!-- 메뉴 -->
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
	
	<!-- 리스트 -->
	<div class="shopping_view" id="shoppingView">
		<div class="inner">
			<div id="location" class="location">
				<span>Home</span> <span class="slash">/</span>
				<div class="show_more">
					<a href="#shoppingView" class="current_active">매트리스</a>
					<ul class="category">
						<li><a href="#shoppingView" class="active">매트리스</a></li>
						<li><a href="#shoppingView">침대프레임</a></li>
						<li><a href="#shoppingView">토퍼</a></li>
						<li><a href="#shoppingView">필로우</a></li>
						<li><a href="#shoppingView">베딩</a></li>
						<li><a href="#shoppingView">Sale</a></li>
					</ul>
				</div>
			</div>
			<div class="filter">
				<div class="select_toggle">
					<span class="current_select">
						<c:choose>
							<c:when test="${type eq 'memory'	}">메모리폼 매트리스</c:when>
							<c:when test="${type eq 'spring'	}">스프링 매트리스</c:when>
							<c:when test="${type eq 'air'		}">에어 매트리스</c:when>
							<c:when test="${type eq 'luxe'		}">Luxe S collection</c:when>
							<c:when test="${type eq 'forest'	}">ForestWalk</c:when>
							<c:otherwise>타입 선택</c:otherwise>
						</c:choose>
					</span>
					<ul class="type_select">
						<li><a href="/Shop/main?type=all&ordered=${orderBy}#shoppingView">타입 선택</a></li>
						<li><a href="/Shop/main?type=memory&ordered=${orderBy}#shoppingView">메모리폼 매트리스</a></li>
						<li><a href="/Shop/main?type=spring&ordered=${orderBy}#shoppingView">스프링 매트리스</a></li>
						<li><a href="/Shop/main?type=air&ordered=${orderBy}#shoppingView">에어 매트리스</a></li>
						<li><a href="/Shop/main?type=luxe&ordered=${orderBy}#shoppingView">Luxe S collection</a></li>
						<li><a href="/Shop/main?type=forest&ordered=${orderBy}#shoppingView">ForestWalk</a></li>
					</ul>
				</div>

				<ul class="rank">
					<li class="${orderBy eq 'pop' ? 'active' : '' }">
						<a href="/Shop/main?type=${type}&ordered=pop#shoppingView" class="sort">인기순</a>
					</li>
					<li class="${orderBy eq 'new' ? 'active' : '' }">
						<a href="/Shop/main?type=${type}&ordered=new#shoppingView" class="sort"> 신상품순 </a>
					</li>
					<li class="${orderBy eq 'price' ? 'active' : '' }">
						<a href="/Shop/main?type=${type}&ordered=price#shoppingView" class="sort"> 가격순 </a>
					</li>
					<li class="${orderBy eq 'review' ? 'active' : '' }">
						<a href="/Shop/main?type=${type}&ordered=review#shoppingView" class="sort"> 상품평순 </a>
					</li>
				</ul>
			</div>
			
			<!-- 결과 -->
			<div class="list">
				<c:forEach var="list" items="${list }">
					<ul class="product">
						<li class="product_img">
							<a href="/Shop/buy?itemno=${list.no }">
								<img src="/images/shopping/${list.poster }" alt="포레스트 워크S 하이브리드 스프링 매트리스 30cm">
							</a>
							<a href="#" class="wish" data-no="${list.no }" data-islogin="${not empty member }">
								<c:set var="mids" value="${fn:split(list.id, ',') }" />
								<c:set var="isInWishlist" value="false" />
								<c:forEach var="mid" items="${mids}">
									<c:if test="${mid eq member.id }">
										<c:set var="isInWishlist" value="true" />
										<img alt="위시리스트" src="/images/shopping/wish_on.png">
									</c:if>
								</c:forEach>
								<c:if test="${not isInWishlist}">
									<img alt="위시리스트" src="/images/shopping/wish_off.png">
								</c:if>
							</a>
						</li>
						<li class="discription">
							<a href="/Shop/buy?itemno=${list.no }">
								<strong>${list.name}</strong>
								<em> ${list.id } ${list.text } </em>
							</a>
						</li>
						<li>
							<p class="price">
								최저 <strong><fmt:formatNumber value="${list.price }" pattern="#,###" /></strong> 원
							</p>
							<div class="type">
								<c:if test="${list.point eq 'H'  }"><img src="/images/shopping/H_01.png" alt="단단한"></c:if>
								<c:if test="${list.point eq 'MH' }"><img src="/images/shopping/H_02.png" alt="적당히 단단한"></c:if>
								<c:if test="${list.point eq 'M'  }"><img src="/images/shopping/H_03.png" alt="중간"></c:if>
								<c:if test="${list.point eq 'MS' }"><img src="/images/shopping/H_05.png" alt="적당히 푹신한"></c:if>
								<c:set var="size" value="${fn:split(list.sizename, ',') }" />
								<c:forEach var="s" items="${size }">
									<c:choose>
										<c:when test="${s eq 'LK' }"><img src="/images/shopping/S_01.png" alt="LK"></c:when>
										<c:when test="${s eq 'K'  }"><img src="/images/shopping/S_02.png" alt="K" ></c:when>
										<c:when test="${s eq 'Q'  }"><img src="/images/shopping/S_03.png" alt="Q" ></c:when>
										<c:when test="${s eq 'SS' }"><img src="/images/shopping/S_04.png" alt="SS"></c:when>
										<c:when test="${s eq 'S'  }"><img src="/images/shopping/S_05.png" alt="S" ></c:when>
										<c:otherwise></c:otherwise>
									</c:choose>
								</c:forEach>
							</div>
						</li>
					</ul>
				</c:forEach>
			</div>
		</div>
	</div>
	
	<!-- 페이징 -->
	<div class="pagination">${paging }</div>
</div>