window.addEventListener('DOMContentLoaded', function() {
	
	// 상품/배송 등 질문 유형 구분 버튼
	let shopSelectBtn = document.querySelector('#shopping_write .container .select_wrap1 .cateBtn');
	const selectWrap = document.querySelector('#shopping_write .container .select_wrap1');
	const cateBtn = document.querySelector('#cateBtn');
	
	if (shopSelectBtn) {
		shopSelectBtn.addEventListener('click', function() {
			selectWrap.classList.toggle('on');
		});
	}
	
	// 구분 목록 선택
	const selectOption = document.querySelectorAll('#shopping_write .container .select_wrap1 .option1 li');
	
	if (selectOption) {
		selectOption.forEach((item) => {
			item.addEventListener('click', function(){
				shopSelectBtn.innerHTML = item.innerText;
				cateBtn.value = item.innerText;
				selectWrap.classList.remove('on');
			});
		});
	}

	// 비밀글
	const checkBox1 = document.querySelector('#shopping_write .content .checkbox1_wrap');
	const check1 = document.querySelector('#check1');
	const check2 = document.querySelector('#check2');
	
	// 비밀글 토글 온/오프
	if (checkBox1) {
		checkBox1.addEventListener('click', function() {
			this.classList.toggle('on');
			check1.value = this.classList.contains('on') ? 1 : 0;
		});
	}
	
	// 수집동의
	const checkBox2 = document.querySelector('#shopping_write .allow .checkbox1_wrap');
	if (checkBox2) {
		// 개인정보 수집동의
		checkBox2.addEventListener('click', function(e) {
			// wrap 클릭 시 토글 可
			e.preventDefault();
			this.classList.toggle('on');
			check2.checked = this.classList.contains('on');
			reqBtn.disabled = !this.classList.contains('on');
		});
	}
	
	// 등록버튼
	const reqBtn = document.querySelector('#req_btn');
	if (reqBtn) {
		// 비동의 시 버튼 잠금
		reqBtn.addEventListener('click', function() {
			const wrtext = document.querySelector('#shopping_write input[name=writer]');
			const wrpass = document.querySelector('#shopping_write input[name=pass]');
			const wrtitle = document.querySelector('#shopping_write input[name=title]');
			const wrquestion = document.querySelector('#shopping_write .ql-editor p');
			// 내용 未작성 시 focus이동 및 submit 방지
			if (wrtext.value === '') {
				alert('작성자를 입력해 주세요.', () => wrtext.focus());
				return;
			}
			
			if (wrpass.value === '') {
				alert('비밀번호를 입력해주세요.', () => wrpass.focus());
				return;
			}
			
			if (wrtitle.value === '') { 
				alert('제목을 입력해주세요.', () => wrtitle.focus());
				return;
			}
			
			if (wrquestion.textContent.trim().length === 0) {
				alert('내용을 입력해주세요.');
				return;
			}
			
			if (!checkBox2.classList.contains('on')) {				
				alert('개인정보 수집 및 이용동의를 체크해주세요.');
				return;
			}
			// 버튼 속성을 button => submit으로 변경
			reqBtn.setAttribute('type', 'submit');
		});
	}
	
	// rvwrite - radio관련
	const radioInput = document.querySelector('#shopping_write .mradio .rating_input');
	if (radioInput) {
		const srradio = document.querySelectorAll('#shopping_write .inradio');
		
		if (srradio) {
			// radio value 적용
			srradio.forEach((item, idx) => {
				item.addEventListener('click', function() {
					// 전체 remove 후 선택한 것만 토글
					srradio.forEach(v => v.classList.remove('active'));
					item.classList.toggle('active');
					// input name=rating에 value 지정
					radioInput.setAttribute('value', 5 - idx);
				});
			});
		}
	}
	
	// submit 후 닫기
	const subBtn = document.querySelector('#openpopup_btn');
	if (subBtn) {		
		subBtn.addEventListener('click', function() {
			const rvtitle = document.querySelector('#shopping_write input[name=title]');
			const rvcontent =  document.querySelector('#shopping_write .ql-editor p');
			const ratingNum = radioInput.dataset.rating;
			
			// 내용 未선택/공백문자만 존재 시 submit 방지
			if (rvtitle.value === '') { 
				alert('제목을 입력해주세요.', () => rvtitle.focus());
				return;
			}
			if (rvcontent.innerHTML.trim().length === 0) {
				alert('내용을 입력해주세요.');
				return;
			}
			// DB에서 review 값이 있는지(대표로 rating 값을 활용)
			const url = ratingNum ? '/Shop/rvupdateOk' : '/Shop/rvwriteOk';
	        fetch(url, {
	            method: 'POST',
				// form 데이터 전달 시 new FormData
	            body: new FormData(document.querySelector('#shopping_write form'))
	        }).then(() => {
				// 부모 페이지 리로드 / 현재 창 닫기
				if (window.opener) {
		            window.opener.location.reload();
		        }
				window.close();
			});
		});
	}	
});
