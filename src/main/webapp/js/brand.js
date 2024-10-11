window.addEventListener('DOMContentLoaded', function () {
	// 세부메뉴 화면상단 고정
	const content = document.querySelector('.brand_menu');
	const admin = document.querySelector('.brand_admin');
	const con = document.querySelector('.brand_wrap');

	if (content) {
		window.addEventListener('scroll', function () {
			const scrollY = window.scrollY;
	
			if (scrollY > 50) {
				content.classList.add('fixed');
				admin.classList.add('on');
				con.classList.add('on');
			} else {
				content.classList.remove('fixed');
				admin.classList.remove('on');
				con.classList.remove('on');
			}
		});
	}
});
