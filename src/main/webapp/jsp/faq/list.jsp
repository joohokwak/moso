<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="faq_wrap">
	<div class="inner">
		<div class="faq_top">
			<span><a href="/">Home</a></span>
			<span>고객서비스</span>
			<span>FAQ</span>
		</div>

		<div class="faq_contents">
			<h2>FAQ</h2>

			<form action="/Faq/list" method="post">
				<div class="faq_search">
					<input type="hidden" name="cate" value="${param.cate }">
					<input type="text" title="검색어 입력" name="title" class="faq_text" placeholder="FAQ 검색" value="${param.title }">
				</div>
			</form>

			<div class="faq_content">
				<div class="faq_content_left">
					<ul>
						<li><a href="#" class="${empty param.cate ? 'active' : ''}">전체</a></li>
						<li><a href="#" class="${param.cate eq '제품문의' 		? 'active' : ''}">제품문의</a></li>
						<li><a href="#" class="${param.cate eq '주문/배송' 		? 'active' : ''}">주문/배송</a></li>
						<li><a href="#" class="${param.cate eq '교환/반품' 		? 'active' : ''}">교환/반품</a></li>
						<li><a href="#" class="${param.cate eq '결제' 			? 'active' : ''}">결제</a></li>
						<li><a href="#" class="${param.cate eq '회원가입/탈퇴' 	? 'active' : ''}">회원가입/탈퇴</a></li>
						<li><a href="#" class="${param.cate eq 'A/S' 			? 'active' : ''}">A/S</a></li>
						<li><a href="#" class="${param.cate eq '기타' 			? 'active' : ''}">기타</a></li>
					</ul>
				</div>
				<div class="faq_content_right">
					<ul>
					<c:forEach var="list" items="${list }">
						<li>
							<a href="javascript:void(0)" class="faq_a">
								<span class="cate">${list.cate }</span>
								${list.title }
							</a>
							<div class="faq_q">
								${list.content }
								
								<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
									<button class="btn update_btn" data-no="${list.no }">수정</button>
									<button class="btn delete_btn" data-no="${list.no }">삭제</button>
								</div>
							</div>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="admin_btn_wrap" data-isadmin="${member.isadmin eq 'Y' }">
				<button class="btn write_btn">글쓰기</button>
			</div>
		</div>

		<!-- 페이징 -->
		<div class="pagination">
			${paging }
		</div>
	</div>
</div>
