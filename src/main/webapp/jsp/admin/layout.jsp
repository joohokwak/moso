<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/template/meta.jsp" />
	<title>MOSO::ZINUS</title>
	<jsp:include page="/template/link.jsp" />
	<jsp:include page="/template/script.jsp" />
</head>
<body>
	<header>
		<jsp:include page="/jsp/layout/header.jsp" />
	</header>
	
	<main>
		<div id="skip_navi">
			<a href="#container">본문바로가기</a>
		</div>
		
		<div id="container">
			<div class="admin_wrap">
				<div class="inner">
					<div class="admin_contents">
						<h2>관리자님 <span>환영합니다.</span></h2>
			
						<form action="#" method="post">
							<div class="admin_search">
								<input type="hidden" name="searchField" value="all">
								<input type="text" title="검색어 입력" name="searchWord" class="admin_text" placeholder="Search">
							</div>
						</form>
			
						<div class="admin_content">
							<c:set var="menu">${fn:substring(layout, 1, fn:length(layout)) }</c:set>
							<div class="admin_content_left">
								<ul>
									<li><a href="#" data-tit="home" 	class="${menu eq 'home' 	? 'active' : '' }">관리자 홈</a></li>
									<li><a href="#" data-tit="member" 	class="${menu eq 'member' 	? 'active' : '' }">회원</a></li>
									<li><a href="#" data-tit="shop" 	class="${menu eq 'shop' 	? 'active' : '' }">쇼핑</a></li>
									<li><a href="#" data-tit="magazine"	class="${menu eq 'magazine'	? 'active' : '' }">매거진</a></li>
									<li><a href="#" data-tit="faq" 		class="${menu eq 'faq' 		? 'active' : '' }">FAQ</a></li>
									<li><a href="#" data-tit="materials"class="${menu eq 'materials'? 'active' : '' }">조립설명서</a></li>
									<li><a href="#" data-tit="catalog" 	class="${menu eq 'catalog' 	? 'active' : '' }">카탈로그</a></li>
									<li><a href="#" data-tit="notice" 	class="${menu eq 'notice' 	? 'active' : '' }">공지사항</a></li>
								</ul>
							</div>
							<div class="admin_content_right">
								<div class="content">
									<jsp:include page="/jsp/admin/${layout }.jsp" />
								</div>
			
								<c:if test="${menu ne 'home' }">
									<div class="pagination">
										${paging }
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		
			<jsp:include page="/jsp/member/login.jsp"/>
		</div>
		
		<!-- 탑버튼 공통 -->
		<div class="float_btn">
			<a href="#" class="go_top">TOP</a>
		</div>
		
		<div id="prepareCommonWrap">
			<img alt="준비중 페이지" src="/images/common/img-blank-page.png">
		</div>
	</main>
	
	<footer>
		<jsp:include page="/jsp/layout/footer.jsp" />
	</footer>
</body>
</html>