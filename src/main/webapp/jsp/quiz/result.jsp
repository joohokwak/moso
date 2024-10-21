<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="quiz_container">
	<div id="quiz_content">
		<div class="brand_menu">
			<ul class="tabs">
				<li><a href="#">이벤트</a></li>
				<li><a href="#">매거진</a></li>
				<li><a href="#">지누스 스토리</a></li>
				<li><a href="#">Mattress in a Box</a></li>
				<li><a href="#">제품안전인증</a></li>
				<li><a href="#" class="on">내게 맞는 매트리스 찾기</a></li>
				<li><a href="#">현대백화점 그룹</a></li>
			</ul>
		</div>
		<div class="inner">
			<div class="route">
				<span>
					<a href="/">Home</a>
				</span>
				<span>브랜드</span>
				<span>내게 맞는 매트리스 찾기</span>
			</div>
			<div class="content_wrap">
				<div class="result">
					<div class="result_tit">
						<h2>당신을 위한 추천 매트리스</h2>
						<p>퀴즈를 모두 푸셨군요. 당신의 인생 매트리스를 추천해 드립니다.</p>
						<a href="/Shop/main">매트리스 전 제품 보러가기</a>
					</div>
					<div class="result_box">
						<h2>Your Mattress Fit</h2>
						<div class="recommend_wrap">
							<c:forEach var="m" items="${quiz }">
							<div class="recommend">
								<a href="/Shop/buy?itemno=${m.no }">
									<span class="img_wrap">
										<img src="/images/shopping/${m.poster }" alt="얼티마 하이브리드 스프링 매트리스" title="얼티마 하이브리드 스프링 매트리스">
									</span>
									<b class="info">
									${m.name }
										<em class="summary">
											${m.text }
										</em>
										<em class="bar"></em>
										<em class="price">
											최저
											<span><fmt:formatNumber value="${m.price }" pattern="#,###" /></span>
											원
										</em>
									</b>
								</a>
							</div>
							</c:forEach>
<!-- 							<div class="recommend"> -->
<!-- 								<a href="#"> -->
<!-- 									<div class="img_wrap"> -->
<!-- 										<img src="/images/quiz/1000000034_detail_050.jpg" alt="얼티마 하이브리드 스프링 매트리스" title="얼티마 하이브리드 스프링 매트리스"> -->
<!-- 									</div> -->
<!-- 									<div class="info"> -->
<!-- 										<h3>그린티 하이브리드 스프링 매트리스</h3> -->
<!-- 										<p class="summary"> -->
<!-- 											*아이코일®기술로 안정감 있게 지지<br>*지누스 스테디셀러 -->
<!-- 										</p> -->
<!-- 										<span class="bar"></span> -->
<!-- 										<p class="price"> -->
<!-- 											최저 -->
<!-- 											<span>154,000</span> -->
<!-- 											원 -->
<!-- 										</p> -->
<!-- 									</div> -->
<!-- 								</a> -->
<!-- 							</div> -->
						</div>
					</div>
				</div>
				<a href="/Quiz/home" class="again_btn">퀴즈 다시풀기</a>
			</div>
		</div>
	</div>
</div>