<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div id="shopping_cart">
	<div class="cart_modal_dim"></div>
	<div class="select_option off">
		<div class="top">
			<h3>옵션선택</h3>
			<span class="close_btn">
				<img src="/images/shopping/layer-close.png" alt="닫기버튼">
			</span>
		</div>
		
		<div class="body">
			<div class="body_img">
				<img src="/images/shopping/1000000369_main_074.jpg" alt="이미지">
			</div>
			<div>
				<div>
					<strong class="body_name">포레스트 워크S 하이브리드 스프링 매트리스 / 30cm</strong>
					<p class="body_text">
						*항균효과가 뛰어난 Sanitized® 코퍼 메모리폼<br> *부드러운 퀼팅으로 마감된 타이트탑
					</p>
				</div>
				<div class="select_tab">
					<span>사이즈</span>
					<div class="select_wrap1 size_names">
						<button>Q</button>
						<ul class="option1">
							<li>= 사이즈 선택 =</li>
							<li>SS</li>
							<li>Q</li>
							<li>LK</li>
						</ul>
					</div>
					<span>설치배송여부</span>
					<div class="select_wrap1 shippings">
						<button>수도권 : +172,000</button>
						<ul class="option1">
							<li>= 설치배송여부 선택 : 가격 =</li>
							<li><span>미신청</span> : <span>0</span></li>
							<li><span>수도권</span> : <span>+16,000원</span></li>
							<li><span>지방</span> 	: <span>+30,000원</span></li>
							<li><span>제주도</span> : <span>+46,000원</span></li>
						</ul>
					</div>
				</div>
				<div class="num_btn">
					<button type="button" onclick="plusCartItemCnt('minus')">
						<img src="/images/shopping/count-minus.png" alt="빼기">
					</button>
					<input type="text" value="1" class="item_cnt">
					<button type="button" onclick="plusCartItemCnt('plus')">
						<img src="/images/shopping/count-plus.png" alt="더하기">
					</button>
				</div>
				<div class="cost">
					<div class="cost1">
						<p>
							<strong> 선택금액 </strong>
							<span>0</span> 원
						</p>
					</div>
					<div class="cost2">
						<p>
							<span>0</span> 원
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="bottom">
			<button type="button" class="op_cancel">취소</button>
			<button type="button" class="op_update">확인</button>
		</div>
	</div>
	
	<div class="inner">
		<h2 class="tit">장바구니</h2>
		<table>
			<thead>
				<tr>
					<th>
						<div class="checkbox1_wrap">
							<label class="checkbox1"></label>
							<input type="checkbox">
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
			<tbody id="cartItemBody"></tbody>
		</table>
		
		<button class="delete_btn" type="button" onclick="deleteCartItem();">선택 상품 삭제</button>
		
		<div class="calc_cost">
			<div>
				<p>
					<strong>합계 금액</strong><br>( 상품금액 + 설치배송비 )
				</p>
				<span>0</span> <i></i>
			</div>
			<div>
				<p>할인 금액 합계</p>
				<span>0원</span> <i class="i2"></i>
			</div>
			<div>
				<p>배송비</p>
				<span>0원</span> <i class="i3"></i>
			</div>
			<div>
				<p>결제 예정 금액</p>
				<span>0</span>
			</div>
		</div>
		<div class="button_tab">
			<button type="button">쇼핑 계속하기</button>
			<button type="button" class="btn2">주문하기</button>
		</div>
	</div>
</div>