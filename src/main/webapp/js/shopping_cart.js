window.addEventListener('DOMContentLoaded', function() {
	// 장바구니 페이지 인 경우
	if (location.pathname === '/Shop/cart') {
		// 스토리지 데이터 얻기
		let storageItem = JSON.parse(localStorage.getItem('itemList'));
		// 목록 세팅
		setCartItem(storageItem);
		
		// 상품 삭제버튼
		const cartDelBtn = document.querySelector('#shopping_cart .delete_btn');
		if (cartDelBtn) {
			cartDelBtn.addEventListener('click', function() {
				storageItem = JSON.parse(localStorage.getItem('itemList'));
				deleteCartItem(storageItem);
			});
		}
		
		// 전체 체크
		const theadCheck = document.querySelector('#shopping_cart thead .checkbox1_wrap');
		if (theadCheck) {
			theadCheck.addEventListener('click', function() {
				theadCheck.classList.toggle('on');
				
				// 하위 체크박스
				const tbodyCheck = document.querySelectorAll('#cartItemBody .checkbox1_wrap');
				if (this.classList.contains('on'))
					tbodyCheck.forEach(v => v.classList.add('on'));
				else 
					tbodyCheck.forEach(v => v.classList.remove('on'));
			});
		}
		
		// 옵션
		const showOption = document.querySelector('#shopping_cart .select_option');
		const changBtn = document.querySelectorAll('#shopping_cart .goods_info .change_btn ');
		const closeBtn = document.querySelector('#shopping_cart .select_option .top .close_btn');
		const cartModal = document.querySelector('.cart_modal_dim');
		
		// 옵션/수량변경 버튼 클릭시 옵션 모달 열기
		if (changBtn) {
			changBtn.forEach((item) => {
				item.addEventListener('click', function() {
					showOption.classList.remove('off');
					cartModal.classList.add('on');
					
					const $item = JSON.parse(this.dataset.option);
					const $idx = this.dataset.idx;
					
					// 모달창 옵션 세팅
					const modalSizeName = document.querySelector('.select_tab .size_names');
					modalSizeName.replaceChildren();
					let _sizeData = `<button data-idx='${$idx}'>${$item.size}</button>`;
					_sizeData += `<ul class="option1">`;
					_sizeData += `<li>= 사이즈 선택 =</li>`;
					
					const _sizeArr = $item.sizename.split(',');
					for (const _s of _sizeArr) {
						_sizeData += `<li>${_s}</li>`;
					}
					_sizeData += `</ul>`;
					modalSizeName.innerHTML = _sizeData;
					
					// 배송설치 옵션 세팅
					const modalShippings = document.querySelector('.select_tab .shippings');
					modalShippings.replaceChildren();
					let _shippingData = `<button data-idx='${$idx}'>${$item.loc} : +${$item.plusPrice} : 36개</button>`;
					_shippingData += `<ul class="option1">`;
					_shippingData += `<li>= 설치배송여부 선택 : 가격 : 재고 =</li>`;
					_shippingData += `<li><span>미신청</span> : <span>0</span> : 150개</li>`;
					_shippingData += `<li><span>수도권</span> : <span>+16,000원</span> : 36개</li>`;
					_shippingData += `<li><span>지방</span> : <span>+30,000원</span> : 36개</li>`;
					_shippingData += `<li><span>제주도</span> : <span>+46,000원</span> : 37개</li>`;
					_shippingData += `</ul>`;
					modalShippings.innerHTML = _shippingData;
					
					// 선택금액 세팅
					const _cost1 = document.querySelector('#shopping_cart .cost1 span');
					_cost1.innerHTML = comma($item.totalPrice);
					
					// 최종금액 세팅
					const _cost2 = document.querySelector('#shopping_cart .cost2 span');
					_cost2.innerHTML = comma($item.totalPrice);
					
				});
			});
		}
		
		// 옵션/수량 변경 모달 닫기
		if (closeBtn) {
			closeBtn.addEventListener('click', function() {
				showOption.classList.add('off');
				cartModal.classList.remove('on');
			});
		}
		
		// 옵션 내부 선택 창
		const selectOp = document.querySelectorAll('#shopping_cart .select_option .select_wrap1');
		if (selectOp) {
			selectOp.forEach((item) => {
				item.addEventListener('click', function() {
					item.classList.toggle('on');
				});
			});
		}
		
		// 옵션 내부 확인 버튼
		const modalOkBtn = document.querySelector('#shopping_cart .op_update');
		if (modalOkBtn) {
			modalOkBtn.addEventListener('click', function() {
				const $size = selectOp[0].children[0];
				const $sipp = selectOp[1].children[0];
				const $idxs = $size.dataset.idx;
				
				$item.size = $size.textContent;
				$item.loc = $sipp.textContent.split(":")[0].trim();
				$item.plusPrice = parseInt($sipp.textContent.split(":")[1].trim());
				
				// TODO: 아씨 머리가 안돌아 간다!!!!!!!!!!!!!!!!
				
				storageItem[$idxs] = $item;
				setCartItem(storageItem);
				closeBtn.click();
			});
		}
		
		// 옵션 선택
		const _sizeOpSelected = document.querySelectorAll('#shopping_cart .select_option .select_wrap1 li');
		if (_sizeOpSelected) {
			_sizeOpSelected.forEach(v => {
				v.addEventListener('click', function() {
					console.log(11);
					v.closest('.select_wrap1').children[0].innerText = v.textContent;
				});
			});
		}
		
		// 옵션 내부 취소 버튼
		const modalCancelBtn = document.querySelector('#shopping_cart .op_cancel');
		if (modalCancelBtn) {
			modalCancelBtn.addEventListener('click', function() {
				closeBtn.click();
			});
		}
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
	let data = '';		// 동적으로 생성될 요소 구성 변수
	let sumPrice = 0;	// 합계 금액 처리 변수
	let idx = 0;

	if (itemList && itemList.length) {
		for (const item of itemList) {
			sumPrice += parseInt(item.totalPrice);
			const _op = JSON.stringify(item);
			
			data += `
				<tr>
					<td align="center">
						<div class="checkbox1_wrap" onclick="this.classList.toggle('on');">
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
									<a href="#"><img src="/images/shopping/coupon-apply.png" alt="쿠폰적용"></a>
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
						<button type="button" class="change_btn" data-idx='${idx}' data-option='${_op}'>옵션/수량변경</button>
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
			
			idx++;
		}
		
		// 합계 금액
		const sumPriceFirstEl = document.querySelector('.calc_cost div:first-child span');
		sumPriceFirstEl.innerText = comma(sumPrice);
		
		// 결재 예정 금액
		const sumPriceLastEl = document.querySelector('.calc_cost div:last-child span');
		sumPriceLastEl.innerText = comma(sumPrice);
		
	} else {
		// 장바구니에 담긴 상품이 없는 경우
		data += '<tr><td colspan="7" align="center" height="86">장바구니에 담겨있는 상품이 없습니다.</td></tr>';
	}
	
	const cartItemBody = document.querySelector('#cartItemBody');
	// 하위 목록 삭제 후 재구성
	cartItemBody.replaceChildren();
	cartItemBody.innerHTML = data;
}

// 상품삭제
function deleteCartItem(itemList) {
	if (itemList && itemList.length) {
		// 하위 체크 박스의 체크 여부 확인
		const cartChkBtns = document.querySelectorAll('#shopping_cart td .checkbox1_wrap');
		
		const delArr = [];
		// 체크된 목록의 인덱스값 얻기
		cartChkBtns.forEach((v, i) => {
			if (v.classList.contains('on')) {
				delArr.push(i);
			}
		});
		
		// 체크된 값이 있다면
		if (delArr.length) {
			// 체크 목록 필터링하여 기존 변수에 담기
			itemList = itemList.filter((_, i) => !delArr.includes(i));
			
			// 스토리지에 다시 세팅
			localStorage.setItem('itemList', JSON.stringify(itemList));
		
			// 목록 다시 구성
			setCartItem(itemList);
		}
	}
}