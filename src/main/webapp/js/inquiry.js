window.addEventListener('DOMContentLoaded', function() {
	// 구분 토글
	const divisionBtn = document.querySelector('.main_field .division_btn');
	if (divisionBtn) {
		divisionBtn.addEventListener('click', function() {
			this.parentElement.classList.toggle('on');
		});
	}
	
	// 구분 하위목록 선택
	const divisionEl = document.querySelectorAll('.inquiry_section .division a');
	if (divisionEl.length) {
		divisionEl.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				
				const $txt = this.innerText;
				divisionBtn.querySelector('p').innerText = $txt;
				this.closest('.main_field').querySelector('input[type=hidden]').value = $txt;
				
				divisionBtn.click();
			});
		});
	}
	
});
