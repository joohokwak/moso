<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="version" value="<%=Calendar.getInstance().getTimeInMillis() %>" />

<!-- 공통 -->
<script src="/js/quill.min.js"></script>
<script src="/js/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0c1b3017dd3d8dfe9308da71a1a5fd8b"></script>
<script src="/js/swiper-bundle.min.js"></script>
<script src="/js/sweetalert2.js"></script>

<script src="/js/common.js?v=${version }"></script>

<!-- 메인 -->
<script src="/js/ui-common.js?v=${version }"></script>

<!-- 멤버 -->
<script src="/js/login.js?v=${version }"></script>


<!-- 헤더/푸터 -->
<script src="/js/header.js?v=${version }"></script>

<!-- 쇼핑 -->
<script src="/js/shopping.js?v=${version }"></script>
<script src="/js/shopping_buy.js?v=${version }"></script>
<script src="/js/shopping_cart.js?v=${version }"></script>
<script src="/js/shopping_write.js?v=${version }"></script>

<!-- 매거진 -->
<script src="/js/brand.js?v=${version }"></script>

<!-- 공지사항 -->
<script src="/js/notice.js?v=${version }"></script>

<!-- 글쓰기 공통 -->
<script src="/js/inquiry.js?v=${version }"></script>

<!-- 고객서비스 -->
<script src="/js/faq.js?v=${version }"></script>
<script src="/js/materials.js?v=${version }"></script>
<script src="/js/catalog.js?v=${version }"></script>

<!-- 파트너 -->
<script src="/js/partner.js?v=${version }"></script>
