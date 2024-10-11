<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		<div id="container" style="padding-top: 60px;">
			<jsp:include page="/jsp${layout }.jsp" />
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