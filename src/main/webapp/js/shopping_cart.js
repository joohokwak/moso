let cartItem = {};

window.addEventListener('DOMContentLoaded', function() {
	// 장바구니 페이지 인 경우
	if (location.pathname === '/Shop/cart') {
		// 목록 세팅
		setCartItem();
		
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
		const closeBtn = document.querySelector('#shopping_cart .select_option .top .close_btn');
		const showOption = document.querySelector('#shopping_cart .select_option');
		const cartModal = document.querySelector('.cart_modal_dim');
		
		// 닫기(X) 버튼
		if (closeBtn) {
			closeBtn.addEventListener('click', function() {
				showOption.classList.add('off');
				cartModal.classList.remove('on');
				document.body.classList.remove('on');
				
				cartItem = {};
			});
		}
		
		// 옵션 내부 취소 버튼
		const modalCancelBtn = document.querySelector('#shopping_cart .select_option .op_cancel');
		if (modalCancelBtn) {
			modalCancelBtn.addEventListener('click', function() {
				closeBtn.click();
			});
		}
		
		/* 
			동적 생성 태그 이므로 이벤트를 정적 태그를 통해서 접근할수 있도록 설정해야 하며
			다시 만들어 지는 태그 및 이벤트 이므로 기존 이벤트리스터를 제거 해야 여러번
			호출되는 이슈를 해결할 수 있음.!!
	 	*/
		// 옵션 내부 선택 이벤트
		const selectOp = document.querySelectorAll('#shopping_cart .select_option .select_wrap1');
		if (selectOp) {
			selectOp.forEach(v => {
				v.addEventListener('click', function() {
					v.classList.toggle('on');
					
		            v.querySelectorAll('.option1 li + li').forEach(el => {
						// 이미 존재하는 이벤트 리스너 제거
		                el.removeEventListener('click', handleOptionClick);
		                el.addEventListener('click', handleOptionClick);
		            });
				});
			});
		}
		
 		// 수정 버튼 이벤트
		const cntUpdateBtns = document.querySelector('#cartItemBody');
		if (cntUpdateBtns) {
			cntUpdateBtns.addEventListener('click', function() {
				this.querySelectorAll('.number_input button').forEach(v => {
					// 이미 존재하는 이벤트 리스너 제거
					v.removeEventListener('click', itemCntChange);
					v.addEventListener('click', itemCntChange);
				});
			});
		}
		
		// 확인 이벤트
		const modalOkBtn = document.querySelector('#shopping_cart .select_option .op_update');
		if (modalOkBtn) {
			modalOkBtn.addEventListener('click', function() {
				const itemList = JSON.parse(localStorage.getItem('itemList'));
				itemList[cartItem.idx] = cartItem;
				
				// 스토리지에 변경된 옵션 세팅
				localStorage.setItem('itemList', JSON.stringify(itemList));
				
				setCartItem();
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
			
			// 상품개수
			item.cnt = 1;
			
			// 총액
			const _totalPrice = document.querySelector('.total_price em');
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

function handleSizeOption(txt) {
	let result = -1;
	
	if (txt === 'S') 		result = 0.8;
	else if (txt === 'SS') 	result = 0.9;
	else if (txt === 'Q') 	result = 1;
	else if (txt === 'K') 	result = 1.1;
	else if (txt === 'LK') 	result = 1.2;
	
	// 사이즈명 세팅
	cartItem.size = txt;
	
	return result;
}

function handleShipOption(txt) {
	let result = -1;
	let setTxt = '';
	
	if (txt.includes('미신청')) {
		result = 0;
		setTxt = '미신청'
	} else if (txt.includes('수도권')) {
		result = 16000;
		setTxt = '수도권'
	} else if (txt.includes('지방')) {
		result = 30000;
		setTxt = '지방'
	} else if (txt.includes('제주도')) {
		result = 46000;
		setTxt = '제주도';
	}
	
	// 설치배송 여부 세팅
	cartItem.loc = setTxt;
	
	return result;
}

function handleOptionClick(e) {
	let price = cartItem.price;
	
	// 선택 옵션
	const txtData = e.currentTarget.innerText;
	e.target.closest('.select_wrap1').children[0].innerText = txtData;
	
	const orgSize = document.querySelector('.select_option .size_names > button').innerText;
	const orgShip = document.querySelector('.select_option .shippings > button').innerText;
	
	// 사이즈 계산
	let sizePrice = handleSizeOption(txtData);
	// 선택하지 않았다면 기존 옵션으로 계산
	if (sizePrice === -1) {
		sizePrice = handleSizeOption(orgSize);
	}
	price = parseInt(price * sizePrice);
	
	// 설치배송 계산
	let shipPrice = handleShipOption(txtData);
	// 선택하지 않았다면 기존 옵션으로 계산
	if (shipPrice === -1) {
		shipPrice = handleShipOption(orgShip);
	}
	price = parseInt(price + shipPrice);
	
	// 임시 변수에 세팅 (수량변경시 사용을 위해)
	cartItem.totalPrice = price;
	
	// 상품개수 확인
	const itemCnt = document.querySelector('.select_option .num_btn .item_cnt').value;
	price *= itemCnt;
	
	// 금액 세팅
	document.querySelector('.select_option .cost1 span').innerHTML = comma(price);
	document.querySelector('.select_option .cost2 span').innerHTML = comma(price);
}

// 장바구니 아이템 세팅
function setCartItem() {
	// 스토리지 데이터 얻기
	const itemList = JSON.parse(localStorage.getItem('itemList'));
	
	let data = '';		// 동적으로 생성될 요소 구성 변수
	let sumPrice = 0;	// 합계 금액 처리 변수
	let idx = 0;

	if (itemList && itemList.length) {
		for (const item of itemList) {
			sumPrice += parseInt(item.totalPrice * item.cnt);
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
							<a href="/Shop/buy?itemno=${item.no}" class="goods_img">
								<img src="/images/shopping/${item.poster}" alt="이미지">
							</a>
							<ul>
								<li>
									<a href="#"><img src="/images/shopping/coupon-apply.png" alt="쿠폰적용"></a>
								</li>
								<li class="goods_tit">
									<a href="/Shop/buy?itemno=${item.no}">${item.name}</a>
								</li>
								<li class="size">
									<p>
										사이즈 : <span>${item.size}</span> 설치배송여부 : <span>${item.loc} (+${comma(item.plusPrice)}원)</span>
									</p>
								</li>
							</ul>
						</div>
						<button type="button" class="change_btn" onclick='changeCartItem(${_op}, ${idx})'>옵션/수량변경</button>
					</td>
					<td class="number_input">
						<input type="text" value="${item.cnt}">
						<button type="button" data-item='${_op}'>수정</button>
					</td>
					<td class="cost">${comma(parseInt(item.totalPrice))}원</td>
					<td class="ben">0</td>
					<td class="del">기본 - 배송비무료<br>무료배송<br>(택배)</td>
					<td class="total_cost">${comma(parseInt(item.totalPrice * item.cnt))}원</td>
				</tr>
			`;
			
			idx++;
		}
		
	} else {
		// 장바구니에 담긴 상품이 없는 경우
		data += '<tr><td colspan="7" align="center" height="86">장바구니에 담겨있는 상품이 없습니다.</td></tr>';
	}
	
	// 합계 금액
	const sumPriceFirstEl = document.querySelector('.calc_cost div:first-child span');
	sumPriceFirstEl.innerText = comma(sumPrice);
	
	// 결재 예정 금액
	const sumPriceLastEl = document.querySelector('.calc_cost div:last-child span');
	sumPriceLastEl.innerText = comma(sumPrice);
	
	const cartItemBody = document.querySelector('#cartItemBody');
	// 하위 목록 삭제 후 재구성
	cartItemBody.replaceChildren();
	cartItemBody.innerHTML = data;
}

// 상품 수량 수정
function itemCntChange(e) {
	const cntEl = e.target.closest('.number_input').children[0];
	const cntVal = parseInt(cntEl.value);
	
	cartItem = JSON.parse(e.target.dataset.item);
	
	if (cntVal > 0) {
		cartItem.cnt = cntVal;
		document.querySelector('#shopping_cart .select_option .op_update').click();
	} else {
		alert('상품 개수는 1개 이상이어야 합니다.');
		cntEl.value = cartItem.cnt
	}
}

// 상품삭제
function deleteCartItem() {
	let itemList = JSON.parse(localStorage.getItem('itemList'));
	
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
			confirm('선택하신 상품을 장바구니에서 삭제 하시겠습니까?', () => {
				// 체크 목록 필터링하여 기존 변수에 담기
				itemList = itemList.filter((_, i) => !delArr.includes(i));
				
				// 스토리지에 다시 세팅
				localStorage.setItem('itemList', JSON.stringify(itemList));
				
				// 목록 다시 구성
				setCartItem();
			});
		} else {
			alert('선택하신 상품이 없습니다.');
		}
	}
}

// 옵션/수량변경 버튼 클릭
function changeCartItem(item, idx) {
	// 원본데이터 보존 및 전역에서 사용하기 위해 대입
	cartItem = item;
	cartItem.idx = idx;
	
	// 옵션 모달창 열기 / 딤처리
	document.querySelector('#shopping_cart .select_option').classList.remove('off');
	document.querySelector('.cart_modal_dim').classList.add('on');
	document.body.classList.add('on');
	
	// 포스터
	const cartBodyPoster = document.querySelector('.select_option .body_img img');
	cartBodyPoster.src = "/images/shopping/" + cartItem.poster;
	
	// 제품명
	const cartBodyName = document.querySelector('.select_option .body_name');
	cartBodyName.innerHTML = cartItem.name;
	
	// 제품설명
	const cartBodyText = document.querySelector('.select_option .body_text');
	cartBodyText.innerHTML = cartItem.text;
	
	// 사이즈
	const modalSizeName = document.querySelector('.select_option .size_names');
	modalSizeName.replaceChildren();
	
	let cartSizeData = `<button>${cartItem.size}</button>`;
	cartSizeData += `<ul class="option1">`;
	cartSizeData += `<li>= 사이즈 선택 =</li>`;
	const _sizeArr = cartItem.sizename.split(',');
	for (const _s of _sizeArr) {
		cartSizeData += `<li>${_s}</li>`;
	}
	cartSizeData += `</ul>`;
	modalSizeName.innerHTML = cartSizeData;
	
	// 설치배송 여부
	const modalShippings = document.querySelector('.select_option .shippings > button');
	modalShippings.innerHTML = cartItem.loc + " : +" + comma(cartItem.plusPrice) + "원";
	
	// 상품개수
	const modalItemCnt = document.querySelector('.select_option .num_btn .item_cnt');
	modalItemCnt.value = cartItem.cnt;
	
	// 선택금액 세팅
	const _cost1 = document.querySelector('.select_option .cost1 span');
	_cost1.innerHTML = comma(cartItem.totalPrice);
	
	// 최종금액 세팅
	const _cost2 = document.querySelector('.select_option .cost2 span');
	_cost2.innerHTML = comma(cartItem.totalPrice);
}

// 옵션 - 상품개수 변경
function plusCartItemCnt(op) {
	const modalItemCnt = document.querySelector('.select_option .num_btn .item_cnt');
	
	if (op === 'minus' && modalItemCnt.value > 1) {
		modalItemCnt.value = parseInt(modalItemCnt.value) - 1;
	} else if (op === 'plus') {
		modalItemCnt.value = parseInt(modalItemCnt.value) + 1;
	}
	
	// 변경수량 세팅
	cartItem.cnt = parseInt(modalItemCnt.value);
	
	// 금액 세팅
	document.querySelector('.select_option .cost1 span').innerHTML = comma(modalItemCnt.value * parseInt(cartItem.totalPrice));
	document.querySelector('.select_option .cost2 span').innerHTML = comma(modalItemCnt.value * parseInt(cartItem.totalPrice));
}

// 주문하기 버튼
function orderItem() {
	const prepareCommonImg = document.querySelector('#prepareCommonWrap');
	prepareCommonImg.style.display = 'flex';
	document.body.classList.add('on');
	setTimeout(() => {
		prepareCommonImg.style.display = 'none';
		document.body.classList.remove('on');
	}, 3000);
}
