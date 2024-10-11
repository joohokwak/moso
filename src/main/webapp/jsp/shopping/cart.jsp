<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="shopping_cart">
	<div class="select_option off">
		<div class="top">
			<h3>옵션선택</h3>
			<span class="close_btn">
				<img src="/images/shopping/shopping_cart/layer-close.png" alt="">
			</span>
		</div>
		
		<div class="body">
			<div class="body_img">
				<img src="/images/shopping/1000000369_main_074.jpg" alt="이미지">
			</div>
			<div>
				<div>
					<strong>포레스트 워크S 하이브리드 스프링 매트리스 / 30cm</strong>
					<p>
						*항균효과가 뛰어난 Sanitized® 코퍼 메모리폼<br> *부드러운 퀼팅으로 마감된 타이트탑
					</p>
				</div>
				<div class="select_tab">
					<span>사이즈</span>
					<div class="select_wrap1">
						<button>Q</button>
						<ul class="option1">
							<li>= 사이즈 선택 =</li>
							<li>SS</li>
							<li>Q</li>
							<li>LK</li>
						</ul>
					</div>
					<span>설치배송여부</span>
					<div class="select_wrap1">
						<button>수도권 : +172,000 : 36개</button>
						<ul class="option1">
							<li>= 설치배송여부 선택 : 가격 : 재고 =</li>
							<li>미신청 : +150,000 : 150개</li>
							<li>수도권 : +172,000 : 36개</li>
							<li>지방 : +191,000 : 36개</li>
							<li>제주도 : +214,000 : 37개</li>
						</ul>
					</div>
				</div>
				<div class="num_btn">
					<button type="button">
						<img src="/images/shopping/shopping_cart/count-minus.png" alt="빼기">
					</button>
					<input type="text" value="1">
					<button type="button">
						<img src="/images/shopping/shopping_cart/count-plus.png" alt="더하기">
					</button>
				</div>
				<div class="cost">
					<div class="cost1">
						<p>
							<strong> 선택금액 </strong> <span>1,182,000</span> 원
						</p>
					</div>
					<div class="cost2">
						<p>
							<span>1,182,000</span> 원
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="bottom">
			<button type="button">취소</button>
			<button type="button">확인</button>
		</div>
	</div>
	
	<div class="inner">
		<h2 class="tit">장바구니</h2>
		<form>
			<table>
				<thead>
					<tr>
						<th>
							<div class="checkbox1_wrap">
								<label class="checkbox1"></label> <input type="checkbox">
							</div>
						</th>
						<th>상품정보</th>
						<th>수량</th>
						<th>상품금액</th>
						<th>혜택</th>
						<th>배송비</th>
						<th>합계금액</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div class="checkbox1_wrap">
								<label class="checkbox1"></label> <input type="checkbox">
							</div>
						</td>
						<td class="goods_info">
							<div class="info">
								<a href="#" class="goods_img">
									<img src="/images/shopping/1000000369_main_074.jpg" alt="이미지">
								</a>
								<ul>
									<li>
										<a href="#"><img src="/images/shopping/shopping_cart/coupon-apply.png" alt="쿠폰적용"></a>
									</li>
									<li class="goods_tit">
										<a href="#">포레스트 워크S 하이브리드 스프링 매트리스 / 30cm </a>
									</li>
									<li class="size">
										<p>
											사이즈 : <span>Q</span> 설치배송여부 : <span>수도권 (+172,000원)</span>
										</p>
									</li>
								</ul>
							</div>
							<button type="button" class="change_btn">옵션/수량변경</button>
						</td>
						<td class="number_input"><input type="text" value="1">
							<button type="button">수정</button></td>
						<td class="cost">1,182,000원</td>
						<td class="ben">0</td>
						<td class="del">기본 - 배송비무료<br>무료배송<br>(택배)</td>
						<td class="total_cost">1,182,000원</td>
					</tr>
					<tr>
						<td>
							<div class="checkbox1_wrap">
								<label class="checkbox1"></label>
								<input type="checkbox">
							</div>
						</td>
						<td class="goods_info">
							<div class="info">
								<a href="#" class="goods_img">
									<img src="/images/shopping/1000000369_main_074.jpg" alt="이미지">
								</a>
								<ul>
									<li>
										<a href="#"><img src="/images/shopping/shopping_cart/coupon-apply.png" alt="쿠폰적용"> </a>
									</li>
									<li class="goods_tit">
										<a href="#">포레스트 워크S 하이브리드 스프링 매트리스 / 30cm </a>
									</li>
									<li class="size">
										<p>
											사이즈 : <span>Q</span> 설치배송여부 : <span>수도권 (+172,000원)</span>
										</p>
									</li>
								</ul>
							</div>
							<button type="button" class="change_btn">옵션/수량변경</button>
						</td>
						<td class="number_input">
							<input type="text" value="1">
							<button type="button">수정</button>
						</td>
						<td class="cost">1,182,000원</td>
						<td class="ben">0</td>
						<td class="del">기본 - 배송비무료<br>무료배송<br>(택배)</td>
						<td class="total_cost">1,182,000원</td>
					</tr>
				</tbody>
			</table>
		</form>
		<button class="delete_btn" type="button">선택 상품 삭제</button>
		<div class="calc_cost">
			<div>
				<p>
					<strong>합계 금액</strong><br>( 상품금액 + 설치배송비 )
				</p>
				<span>1,182,000</span> <i></i>
			</div>
			<div>
				<p>할인 금액 합계</p>
				<span>0원</span> <i class="i2"></i>
			</div>
			<div>
				<p>배송비</p>
				<span>0</span> <i class="i3"></i>
			</div>
			<div>
				<p>결제 예정 금액</p>
				<span>1,182,000</span>
			</div>
		</div>
		<div class="button_tab">
			<button type="button">쇼핑 계속하기</button>
			<button type="button" class="btn2">주문하기</button>
		</div>
	</div>
</div>

