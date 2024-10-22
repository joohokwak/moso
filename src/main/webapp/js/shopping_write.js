window.addEventListener('DOMContentLoaded', function() {
	let shopSelectBtn = document.querySelector('#shopping_write .container .select_wrap1 .cateBtn');
	const selectWrap = document.querySelector('#shopping_write .container .select_wrap1');
	const cateBtn = document.querySelector('#cateBtn');
	
	// 글쓰기 구분 버튼
	if (shopSelectBtn) {
		shopSelectBtn.addEventListener('click', function() {
			selectWrap.classList.toggle('on');
		});
	}
	
	// 구분 목록 선택
	const selectOption = document.querySelectorAll('#shopping_write .container .select_wrap1 .option1 li');
	selectOption.forEach((item) => {
		item.addEventListener('click', function(){
			shopSelectBtn.innerHTML = item.innerText;
			cateBtn.value = item.innerText;
			selectWrap.classList.remove('on');
		})
	});

	// 비밀글
	const checkBox1 = document.querySelector('#shopping_write .content .checkbox1_wrap');
	const check1 = document.querySelector('#check1');
	const check2 = document.querySelector('#check2');
	if (checkBox1) {
		// 비밀글 토글 온/오프
		checkBox1.addEventListener('click', function() {
			this.classList.toggle('on');
			if(this.classList.contains('on')) {

				check1.value = 1;
			} else {

				check1.value = 0;
			}
		});
	}
	
	// 수집동의
	const checkBox2 = document.querySelector('#shopping_write .allow .checkbox1_wrap');
	if (checkBox2) {
		// 개인정보 수집동의
		checkBox2.addEventListener('click', function() {
			this.classList.toggle('on');
			if(checkBox2.classList.contains('on')) {
				reqBtn.removeAttribute('disabled');
				check2.setAttribute('checked', true);
			} else {
				check2.setAttribute('checked', false);
			}
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

			if(wrtext.value == '') {
				alert('작성자를 입력해 주세요.');
			} else if(wrpass.value == '') {
				alert('비밀번호를 입력해주세요.');	
			} else if(wrtitle.value == '') { 
				alert('제목을 입력해주세요.');	
			} else if(wrquestion.textContent.trim().length == 0) {
				alert('내용을 입력해주세요.');	
			} else if (!checkBox2.classList.contains('on')){				
				alert('개인정보 수집 및 이용동의를 체크해주세요.');
			} else {
				reqBtn.setAttribute('type', 'submit');
			}
		});
	}
});
