window.addEventListener('DOMContentLoaded', function() {
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
});
