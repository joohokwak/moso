window.addEventListener('DOMContentLoaded', function() {
	let shopSelectBtn = document.querySelector('#shopping_write .container .select_wrap1 button');
	const selectWrap = document.querySelector('#shopping_write .container .select_wrap1');
	const cateBtn = document.querySelector('#cateBtn');
	const selectOption = document.querySelectorAll('#shopping_write .container .select_wrap1 .option1 li');
	if (shopSelectBtn) {
		shopSelectBtn.addEventListener('click', function(e) {
			e.preventDefault();
			selectWrap.classList.toggle('on');
		});
		selectOption.forEach((item) => {
			item.addEventListener('click', function(){
				shopSelectBtn.innerHTML = item.innerText;
				cateBtn.value = this.textContent;
				selectWrap.classList.remove('on');
			})
		});
	}


	
	const checkBox1 = document.querySelector('#shopping_write .content .checkbox1_wrap');
	const checkBox2 = document.querySelector('#shopping_write .allow .checkbox1_wrap');
	const check1 = document.querySelector('#check1');
	const check2 = document.querySelector('#check2');
	const reqBtn = document.querySelector('#req_btn');	
 	if (checkBox1) {
		checkBox1.addEventListener('click', function() {
				this.classList.toggle('on');
				if(checkBox1.classList.contains('on')) {
					check1.setAttribute('checked', true);
					check1.value = 1;
				} else {
					check1.setAttribute('checked', false);
					check1.value = 0;
				}
		});
		checkBox2.addEventListener('click', function() {
				this.classList.toggle('on');
				if(checkBox2.classList.contains('on')) {
					reqBtn.removeAttribute('disabled');
					check2.setAttribute('checked', true);
				} else {
					check2.setAttribute('checked', false);
				}
		});
		reqBtn.addEventListener('click', function() {			
			if (!checkBox2.classList.contains('on')) {
				alert('개인정보 수집 및 이용동의를 체크해주세요.')
			    reqBtn.setAttribute('disabled', 'disabled');
			}
			
		});

	}
});
