window.addEventListener('DOMContentLoaded', function() {
	// 장바구니 페이지 인 경우
	if (location.pathname === '/Shop/cart') {
		// 스토리지 데이터 얻기
		const storageItem = JSON.parse(localStorage.getItem('itemList'));
		// 목록 세팅
		setCartItem(storageItem);
		
		
		// 상품 삭제버튼
		const cartDelBtn = document.querySelector('#shopping_cart .delete_btn');
		if (cartDelBtn) {
			cartDelBtn.addEventListener('click', function() {
				const cartChkBtns = document.querySelectorAll('#shopping_cart td .checkbox1_wrap');
				cartChkBtns.forEach((v, i) => {
					if (v.classList.contains('on')) {
						storageItem.splice(i, 1);
						localStorage.setItem('itemList', JSON.stringify(storageItem));
					}
				});
				
				// 목록 다시 세팅
				setCartItem(storageItem);
			});
		}
	}
	
	const theadCheck = document.querySelector('#shopping_cart thead .checkbox1_wrap');
	const tbodyCheck = document.querySelectorAll('#shopping_cart tbody .checkbox1_wrap');

	if (theadCheck) {
		theadCheck.addEventListener('click', function() {
			theadCheck.classList.toggle('on');
			
			let allOn = theadCheck.classList.contains('on');
			if (allOn) {
				tbodyCheck.forEach((element) => {
					element.classList.add('on');
				});
			} else {
				tbodyCheck.forEach((element) => {
					element.classList.remove('on');
				});
			}
		});
	}
	
	if (tbodyCheck) {
		tbodyCheck.forEach((item) => {
			item.addEventListener('click', function() {
				item.classList.toggle('on');
			});
		});
	}
	
	const showOption = document.querySelector('#shopping_cart .select_option');
	const changBtn = document.querySelectorAll('#shopping_cart .goods_info .change_btn ');
	const closeBtn = document.querySelector('#shopping_cart .select_option .top .close_btn');
	
	if (changBtn) {
		changBtn.forEach((item) => {
			item.addEventListener('click', function() {
				showOption.classList.remove('off');
			});
		});
	}
	
	if (closeBtn) {
		closeBtn.addEventListener('click', function() {
			showOption.classList.add('off');
		});
	}
	
	const selectOp = document.querySelectorAll('#shopping_cart .select_option .select_wrap1');
	if (selectOp) {
		selectOp.forEach((item) => {
			item.addEventListener('click', function() {
				item.classList.toggle('on');
			});
		});
	}
	
	
	// 상품 상세페이지에서 장바구니에 담기 클릭
	const cartEl = document.querySelector('.shopping_goods .cart');
	if (cartEl) {
		cartEl.addEventListener('click', function(e) {
			e.preventDefault();
			let itemList = [];
			
			// 기존 스토리지 정보 얻기
			const _storageItem = localStorage.getItem('itemList');
			// 스토리지 정보가 있다면 배열에 추가
			if (_storageItem) itemList = JSON.parse(_storageItem);
			
			// 상품 정보 얻기
			let _item = this.dataset.item;
			_item = _item.substring(_item.indexOf("(") + 1);
			_item = _item.substring(0, _item.length - 1);
			
			// 상품 정보 객체로 변환
			const item = {};
			const _tmp1 = _item.split(", ");
			for (let i = 0, len = _tmp1.length; i < len; i++) {
				const _tmp2 = _tmp1[i].split("=");
				item[_tmp2[0]] = _tmp2[1];
			}
			
			// 사이즈
			const _size = document.querySelector('.size .active');
			item.size = _size.textContent;
			
			// 설치배송
			const _shipping	= document.querySelector('.delivery .active');
			let loc = _shipping.textContent;
			item.loc = loc;
			
			// 추가금액
			let tmpPrice = 0;
			if (loc === '수도권') tmpPrice += 16000;
			else if (loc === '지방') tmpPrice += 30000;
			else if (loc === '제주도') tmpPrice += 46000;
			
			item.plusPrice = tmpPrice;
			
			// 총액
			const _totalPrice = document.querySelector('.total_price em');
			console.log(_totalPrice.textContent.replaceAll(',', ''));
			item.totalPrice = _totalPrice.textContent.replaceAll(',', '');
			
			// 상품이 있다면 배열에 추가
			if (item) itemList.push(item);
			
			// 스토리지에 추가
			localStorage.setItem('itemList', JSON.stringify(itemList));
			
			// 장바구니 페이지 이동
			location.href = "/Shop/cart";
		});
	}
	
});

// 장바구니 아이템 세팅
function setCartItem(itemList) {
	let data = '';
	let sumPrice = 0;

	if (itemList) {
		for (const item of itemList) {
			sumPrice += parseInt(item.totalPrice);
			data += `
				<tr>
					<td>
						<div class="checkbox1_wrap">
							<label class="checkbox1"></label><input type="checkbox">
						</div>
					</td>
					<td class="goods_info">
						<div class="info">
							<a href="#" class="goods_img">
								<img src="/images/shopping/${item.poster}" alt="이미지">
							</a>
							<ul>
								<li>
									<a href="#"><img src="/images/shopping/shopping_cart/coupon-apply.png" alt="쿠폰적용"></a>
								</li>
								<li class="goods_tit">
									<a href="#">${item.name}</a>
								</li>
								<li class="size">
									<p>
										사이즈 : <span>${item.size}</span> 설치배송여부 : <span>${item.loc} (+${comma(item.plusPrice)}원)</span>
									</p>
								</li>
							</ul>
						</div>
						<button type="button" class="change_btn">옵션/수량변경</button>
					</td>
					<td class="number_input"><input type="text" value="1">
						<button type="button">수정</button>
					</td>
					<td class="cost">${comma(parseInt(item.totalPrice))}원</td>
					<td class="ben">0</td>
					<td class="del">기본 - 배송비무료<br>무료배송<br>(택배)</td>
					<td class="total_cost">${comma(parseInt(item.totalPrice))}원</td>
				</tr>
			`;
		}
		
		const sumPriceFirstEl = document.querySelector('.calc_cost div:first-child span');
		const sumPriceLastEl = document.querySelector('.calc_cost div:last-child span');
		sumPriceFirstEl.innerText = comma(sumPrice);
		sumPriceLastEl.innerText = comma(sumPrice);
		
	} else {
		data += '<tr><td colspan="7" align="center" height="86">장바구니에 담겨있는 상품이 없습니다.</td></tr>';
	}
	
	const cartItemBody = document.querySelector('#cartItemBody');
	cartItemBody.replaceChildren();
	cartItemBody.innerHTML = data;
}