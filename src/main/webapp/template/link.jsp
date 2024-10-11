<%@page import="java.util.Calendar"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="version" value="<%=Calendar.getInstance().getTimeInMillis() %>" />

<!-- 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

<!-- 공통 -->
<link rel="shortcut icon" href="/images/common/favicon.png">
<link rel="stylesheet" href="/css/swiper-bundle.min.css">
<link rel="stylesheet" href="/css/quill.snow.css">
<link rel="stylesheet" href="/css/common.css?v=${version }">


<!-- 메인 -->
<link rel="stylesheet" href="/css/main.css?v=${version }">

<!-- 멤버 -->
<link rel="stylesheet" href="/css/login.css?v=${version }">

<!-- 헤더/푸터 -->
<link rel="stylesheet" href="/css/header.css?v=${version }">
<link rel="stylesheet" href="/css/footer.css?v=${version }">

<!-- 쇼핑 -->
<link rel="stylesheet" href="/css/shopping.css?v=${version }">
<link rel="stylesheet" href="/css/shopping_buy.css?v=${version }">
<link rel="stylesheet" href="/css/shopping_cart.css?v=${version }">
<link rel="stylesheet" href="/css/shopping_write.css?v=${version }">

<!-- 매거진 -->
<link rel="stylesheet" href="/css/brand.css?v=${version }">

<!-- 문의 공통 -->
<link rel="stylesheet" href="/css/inquiry.css?v=${version }">

<!-- FAQ -->
<link rel="stylesheet" href="/css/faq.css?v=${version }">
<link rel="stylesheet" href="/css/catalog.css?v=${version }">
<link rel="stylesheet" href="/css/catalog_view.css?v=${version }">

<!-- 비즈니스 -->
<link rel="stylesheet" href="/css/notice.css?v=${version }">

<!-- 파트너 -->
<link rel="stylesheet" href="/css/partner.css?v=${version }">