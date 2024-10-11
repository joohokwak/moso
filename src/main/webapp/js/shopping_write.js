window.addEventListener('DOMContentLoaded', function() {
	const shopSelectBtn = document.querySelector('#shopping_write .container .select_wrap1 button');
	const selectWrap = document.querySelector('#shopping_write .container .select_wrap1');
	
	if (shopSelectBtn) {
		shopSelectBtn.addEventListener('click', function() {
			selectWrap.classList.toggle('on');
		});
	}
	
	const shopCheckBoxs = document.querySelectorAll('#shopping_write .checkbox1_wrap');
	if (shopCheckBoxs) {
		shopCheckBoxs.forEach((ele) => {
			ele.addEventListener('click', function() {
				ele.classList.toggle('on');
			});
		});
	}
});
