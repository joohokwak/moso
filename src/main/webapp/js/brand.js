window.addEventListener('DOMContentLoaded', function () {
	// 세부메뉴 화면상단 고정
	const content = document.querySelector('.brand_menu');
	const admin = document.querySelector('.brand_admin');
	const con = document.querySelector('.brand_wrap');

	if (content) {
		window.addEventListener('scroll', function () {
			const scrollY = window.scrollY;
	
			if (scrollY > 0) {
				content.classList.add('fixed');
				if (admin) admin.classList.add('on');
				if (con) con.classList.add('on');
			} else {
				content.classList.remove('fixed');
				if (admin) admin.classList.remove('on');
				if (con) con.classList.remove('on');
			}
		});
	}
	
	const magazinedelete = document.querySelectorAll('.brand_section .delete_btn');
	
	if(magazinedelete) {
		magazinedelete.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				confirm('삭제하시겠습니까?', () => {
					location.href='/Magazine/delete?no=' + this.dataset.no;
				});
			});
		});
	}
	
});
