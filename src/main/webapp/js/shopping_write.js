window.addEventListener('DOMContentLoaded', function() {
	let shopSelectBtn = document.querySelector('#shopping_write .container .select_wrap1 button');
	const selectWrap = document.querySelector('#shopping_write .container .select_wrap1');
	const selectOption = document.querySelectorAll('#shopping_write .container .select_wrap1 .option1 li');
	if (shopSelectBtn) {
		shopSelectBtn.addEventListener('click', function(e) {
			e.preventDefault();
			selectWrap.classList.toggle('on');
		});
		selectOption.forEach((item) => {
			item.addEventListener('click', function(){
				shopSelectBtn.innerHTML = item.innerText;
				selectWrap.classList.remove('on');
			})
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
