let cartItem = {}; // 임시 이벤트 요소를 담기 위한 변수

window.addEventListener('DOMContentLoaded', function() {
    // 장바구니 페이지 인 경우
    if (location.pathname === '/Shop/cart') {
        // 목록 세팅
        setCartItem();
        
        // 전체 체크
		clickEvent('#shopping_cart thead .checkbox1_wrap', function() {
			// 전체 체크박스 상태 토글
	        const isChecked = this.classList.toggle('on');

	        // 하위 체크박스 선택
	        const tbodyCheck = document.querySelectorAll('#cartItemBody .checkbox1_wrap');
	        tbodyCheck.forEach(v => {
	            v.classList.toggle('on', isChecked); // 전체 체크 상태에 따라 하위 체크박스 상태 설정
	        });
		});
        
        // 옵션 관련 기능
        initOptionModal();
        
        // 수정 버튼 이벤트
        initUpdateButtons();
        
        // 확인 이벤트
		clickEvent('#shopping_cart .select_option .op_update', () => updateCartItem());
    }

    // 상품 상세페이지에서 장바구니에 담기 클릭
    const cartEl = document.querySelector('.shopping_goods .cart');
    if (cartEl) {
        cartEl.addEventListener('click', function(e) {
            e.preventDefault();
			
			const totalPriceElement = this.closest('.select_detail').querySelector('.total_price');
			// 이벤트 요소 상태를 확인하기 위해 window 객체의 getComputedStyle 함수 이용
			const isReady = window.getComputedStyle(totalPriceElement).display;
			// 옵션이 모두 선택 되었는지 체크
			if (isReady === 'none') {
				alert('가격 정보가 없거나 옵션이 선택되지 않았습니다!');
			} else {
				document.querySelector('#addCartLayer').style.display = 'block';
	            addToCart(this.dataset.item);
			}
        });
    }
});

// 상세페이지 팝업 닫기
function cartPopClose() {
	document.querySelector('#addCartLayer').style.display = 'none';
}

// 옵션 초기화
function initOptionModal() {
    const closeBtn = document.querySelector('#shopping_cart .select_option .top .close_btn');
    const showOption = document.querySelector('#shopping_cart .select_option');
    const cartModal = document.querySelector('.cart_modal_dim');

	// 닫기 버튼 이벤트
    if (closeBtn) {
        closeBtn.addEventListener('click', function() {
            showOption.classList.add('off');
            cartModal.classList.remove('on');
            document.body.classList.remove('on');
            cartItem = {};
        });
    }
	
	// 옵션 팝업 닫기 버튼
    const modalCancelBtn = document.querySelector('#shopping_cart .select_option .op_cancel');
    if (modalCancelBtn) {
        modalCancelBtn.addEventListener('click', () => closeBtn.click());
    }

	// 옵션 변경 팝업 내 선택 창 이벤트
	// 동적 생성된 태그 이므로 이벤트가 중첩될수 있으므로 기존 이벤트 삭제 후 이벤트 연결
    const selectOp = document.querySelectorAll('#shopping_cart .select_option .select_wrap1');
    if (selectOp) {
        selectOp.forEach(v => {
            v.addEventListener('click', function() {
                v.classList.toggle('on');
                v.querySelectorAll('.option1 li + li').forEach(el => {
                    el.removeEventListener('click', handleOptionClick);
                    el.addEventListener('click', handleOptionClick);
                });
            });
        });
    }
}

// 수정 버튼 이벤트
function initUpdateButtons() {
	// 동적으로 생성된 태그 이므로 정적 태그에서 부터 접근
    const cntUpdateBtns = document.querySelector('#cartItemBody');
    if (cntUpdateBtns) {
		cntUpdateBtns.addEventListener('click', function(e) {
			// 이벤트 버블링을 이용해 접근하는 방식
			const target = e.target.closest('.number_input button');
			if (target) {
				e.preventDefault();
				
				const countInput = target.previousElementSibling;
			    const countValue = parseInt(countInput.value);
			    
				// item 요소 얻기
			    cartItem = JSON.parse(target.dataset.item);
				// 개수 얻기
				cartItem.idx = target.dataset.idx;
				
				// 개수가 1개 이상인 경우에만
			    if (countValue > 0) {
					showLoading(true);
					
					setTimeout(() => {
				        cartItem.cnt = countValue;
				        document.querySelector('#shopping_cart .select_option .op_update').click();
						showLoading(false);
					}, 500);
			    } else {
			        alert('상품 개수는 1개 이상이어야 합니다.');
			        countInput.value = cartItem.cnt; // 원래 개수로 복원
			    }
			}
		});
    }
}

// 장바구니에 상품 추가
function addToCart(itemData) {
	// 기존 스토리지 데이터
    let itemList = JSON.parse(localStorage.getItem('itemList')) || [];
    
	// 상품 정보 파싱
    const item = parseItemData(itemData);
	
    // 사이즈
    const _size = document.querySelector('.size .active');
    item.size = _size.textContent;
    
	// 설치/배송
    const _shipping = document.querySelector('.delivery .active');
    item.loc = _shipping.textContent;
    item.plusPrice = getShippingPrice(item.loc);
	
	let itemFound = false;
	// 동일 제품이 장바구니에 있는지 체크해서 수량만 업데이트
	for (let _item of itemList) {
		// 제품코드, 사이즈, 설치/배송 여부가 동일한지 체크
		if (_item.no === item.no && _item.size === item.size && _item.loc === item.loc) {
			itemFound = true;
			_item.cnt += 1;
			break;
		}
	}
	
	// 동일 제품이 없는 경우에만 다시 담기
    if (!itemFound) {
		// 기본 상품 개수
	    item.cnt = 1;
		
		// 상품 총액
	    item.totalPrice = document.querySelector('.total_price em').textContent.replaceAll(',', '');
	
		// 배열에 담기
	    itemList.push(item);
	}
	
	// 스토리지에 저장
    localStorage.setItem('itemList', JSON.stringify(itemList));
}

// 상품 정보 파싱
function parseItemData(itemStr) {
    const item = {};
    const _tmp1 = itemStr.substring(itemStr.indexOf("(") + 1, itemStr.length - 1).split(", ");
	
    _tmp1.forEach(pair => {
        const [key, value] = pair.split("=");
        item[key] = value;
    });
	
    return item;
}

// 배송비 계산
function getShippingPrice(location) {
	const locations = ['미신청', '수도권', '지방', '제주도'];
	
	// 설치배송 여부 세팅
	for (const loc of locations) {
	    if (location.includes(loc)) {
			switch(location) {
	        case '수도권': return 16000;
	        case '지방': return 30000;
	        case '제주도': return 46000;
	        default: return 0;
		    }
	    }
	}
}

// 장바구니 아이템 세팅
function setCartItem() {
	//	장바구니에 담긴 상품이 없는 경우 기본값을 배열로 세팅하기 위해
    const itemList = JSON.parse(localStorage.getItem('itemList')) || [];
    
    let data = '';
	// 총액을 구하기 위해 reduce 누산 함수 사용
    let sumPrice = itemList.reduce((sum, item) => sum + parseInt(item.totalPrice * item.cnt), 0);
	
    if (itemList.length) {
        itemList.forEach((item, idx) => {
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
                        <button type="button" data-item='${_op}' data-idx="${idx}">수정</button>
                    </td>
                    <td class="cost">${comma(parseInt(item.totalPrice))}원</td>
                    <td class="ben">0</td>
                    <td class="del">기본 - 배송비무료<br>무료배송<br>(택배)</td>
                    <td class="total_cost">${comma(parseInt(item.totalPrice * item.cnt))}원</td>
                </tr>
            `;
        });
		
    } else {
        data += '<tr><td colspan="7" align="center" height="86">장바구니에 담겨있는 상품이 없습니다.</td></tr>';
    }
    
    document.querySelector('#cartItemBody').innerHTML = data;
    document.querySelector('.calc_cost div:first-child span').innerText = comma(sumPrice);
    document.querySelector('.calc_cost div:last-child span').innerText = comma(sumPrice);
}


// 사이즈별 금액 변경
function handleSizeOption(size) {
    const sizeMultiplier = {
        'S': 0.8,
        'SS': 0.9,
        'Q': 1,
        'K': 1.1,
        'LK': 1.2
    };
    
	// 변경된 사이즈 정보 세팅
    cartItem.size = size;
	// 기본값 1 반환
    return sizeMultiplier[size] || 1;
}

// 지역별 설치배송 금액 변경
function handleShipOption(location) {
    const shippingCosts = {
        '미신청': 0,
        '수도권': 16000,
        '지방': 30000,
        '제주도': 46000
    };
	
	const locations = ['미신청', '수도권', '지방', '제주도'];

	// 설치배송 여부 세팅
	for (const loc of locations) {
	    if (location.includes(loc)) {
	        cartItem.loc = loc;
	        break;
	    }
	}
	
	// 변경된 금액 정보 세팅
	cartItem.plusPrice = shippingCosts[cartItem.loc];
	// 기본값 0 반환
    return shippingCosts[cartItem.loc] || 0;
}

// 옵션/수량 변경 모달에서 사이즈/설치배송 옵션 변경 클릭
function handleOptionClick(e) {
    const selectedText = e.currentTarget.innerText;
    const optionWrap = e.target.closest('.select_wrap1');
	// 선택한 옵션 텍스트 설정
    optionWrap.children[0].innerText = selectedText;

    const originalSize = document.querySelector('.select_option .size_names > button').innerText;
    const originalShipping = document.querySelector('.select_option .shippings > button').innerText;

    // 사이즈 계산
    let price = calculatePrice(selectedText, originalSize, originalShipping);
    
    // 임시 변수에 세팅 (수량변경시 사용을 위해)
    cartItem.totalPrice = price;

    // 상품개수 확인
    const itemCount = document.querySelector('.select_option .num_btn .item_cnt').value;
    price *= itemCount;

    // 금액 세팅
    updatePriceDisplay(price);
}

// 가격 계산 함수
function calculatePrice(selectedText, originalSize, originalShipping) {
    let price = cartItem.price;

    let sizePrice = handleSizeOption(selectedText);
	// 선택하지 않았다면 기존 옵션으로 계산
    if (sizePrice === 1) {
        sizePrice = handleSizeOption(originalSize);
    }
    price = parseInt(price * sizePrice);

    let shippingPrice = handleShipOption(selectedText);
	// 선택하지 않았다면 기존 옵션으로 계산
    if (shippingPrice === 0) {
        shippingPrice = handleShipOption(originalShipping);
    }
    price = parseInt(price + shippingPrice);

    return price;
}

// 금액 세팅 함수
function updatePriceDisplay(price) {
    document.querySelector('.select_option .cost1 span').innerHTML = comma(price);
    document.querySelector('.select_option .cost2 span').innerHTML = comma(price);
}

// 상품삭제
function deleteCartItem() {
	let itemList = JSON.parse(localStorage.getItem('itemList')) || [];
	    
    if (itemList.length === 0) {
        alert('장바구니에 담긴 상품이 없습니다.');
        return;
    }

    // 체크된 목록의 인덱스값 얻기
    const cartCheckBoxes = document.querySelectorAll('#shopping_cart td .checkbox1_wrap');
    const deleteIndices = Array.from(cartCheckBoxes).reduce((indices, checkbox, index) => {
        if (checkbox.classList.contains('on')) {
            indices.push(index);
        }
        return indices;
    }, []);
	
    // 체크된 값이 있다면
    if (deleteIndices.length) {
		confirm('선택하신 상품을 장바구니에서 삭제 하시겠습니까?', () => {
            // 체크 목록 필터링하여 기존 변수에 담기
            itemList = itemList.filter((_, index) => !deleteIndices.includes(index));
            
            // 스토리지에 다시 세팅
            localStorage.setItem('itemList', JSON.stringify(itemList));
            
            // 목록 다시 구성
            setCartItem();
			
			// 체크박스 상태 업데이트
			updateCheckboxState();
		});
		
    } else {
        alert('선택하신 상품이 없습니다.');
    }
}

// 옵션/수량변경 버튼 클릭
function changeCartItem(item, index) {
	// 원본데이터 보존 및 전역에서 사용하기 위해 대입
	// 스프레드로 추가 하면 오류 발생함?? (기능은 정상동작됨!)
    // cartItem = { ...item, idx: index };
	cartItem = item;
	cartItem.idx = index;

    const selectOption = document.querySelector('#shopping_cart .select_option');
    selectOption.classList.remove('off');
    document.querySelector('.cart_modal_dim').classList.add('on');
    document.body.classList.add('on');

    // 포스터
    document.querySelector('.select_option .body_img img').src = "/images/shopping/" + cartItem.poster;
    
    // 제품명 및 설명
    document.querySelector('.select_option .body_name').innerHTML = cartItem.name;
    document.querySelector('.select_option .body_text').innerHTML = cartItem.text;

    // 사이즈
    updateSizeOptions(cartItem.sizename);
    
    // 설치배송 여부
    document.querySelector('.select_option .shippings > button').innerHTML = `${cartItem.loc} : +${comma(cartItem.plusPrice)}원`;
    
    // 상품개수
    document.querySelector('.select_option .num_btn .item_cnt').value = cartItem.cnt;
    
    // 선택금액 세팅
    updatePriceDisplay(cartItem.totalPrice);
}

// 사이즈 옵션 세팅
function updateSizeOptions(sizeNames) {
    const modalSizeName = document.querySelector('.select_option .size_names');
    modalSizeName.innerHTML = '';

    let sizeData = `<button>${cartItem.size}</button><ul class="option1"><li>= 사이즈 선택 =</li>`;
    const sizeArray = sizeNames.split(',');
    
    sizeArray.forEach(size => {
        sizeData += `<li>${size.trim()}</li>`;
    });
    sizeData += `</ul>`;
	
    modalSizeName.innerHTML = sizeData;
}

// 옵션 - 상품개수 변경
function plusCartItemCnt(operation) {
    const modalItemCnt = document.querySelector('.select_option .num_btn .item_cnt');
    
    if (operation === 'minus' && modalItemCnt.value > 1) {
        modalItemCnt.value = parseInt(modalItemCnt.value) - 1;
    } else if (operation === 'plus') {
        modalItemCnt.value = parseInt(modalItemCnt.value) + 1;
    }
    
    cartItem.cnt = parseInt(modalItemCnt.value);
    updatePriceDisplay(modalItemCnt.value * parseInt(cartItem.totalPrice));
}

// 옵션 변경 확인버튼
function updateCartItem() {
	showLoading(true);
	
	setTimeout(() => {
	    const itemList = JSON.parse(localStorage.getItem('itemList')) || [];
	    
	    // 기존 상품 정보를 업데이트
	    // itemList[cartItem.idx] = { ...cartItem };
		itemList[cartItem.idx] = cartItem;
	
	    // 로컬 스토리지에 변경된 옵션 세팅
	    localStorage.setItem('itemList', JSON.stringify(itemList));
	
	    // 장바구니 아이템 목록을 다시 세팅
	    setCartItem();
	
	    // 모달 닫기
	    document.querySelector('#shopping_cart .select_option .top .close_btn').click();
		
		showLoading(false);
	}, 500);
}

// 체크박스 상태 업데이트
function updateCheckboxState() {
    const theadCheck = document.querySelector('#shopping_cart thead .checkbox1_wrap');
    const tbodyCheck = document.querySelectorAll('#cartItemBody .checkbox1_wrap');

    // 상품이 남아있다면 체크박스 상태를 업데이트
    if (tbodyCheck.length > 0) {
        const allChecked = Array.from(tbodyCheck).every(v => v.classList.contains('on'));
        theadCheck.classList.toggle('on', allChecked);
    } else {
        // 상품이 없을 경우 전체 체크박스 상태 초기화
        theadCheck.classList.remove('on');
    }
}
