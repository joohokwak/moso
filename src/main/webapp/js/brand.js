window.addEventListener('DOMContentLoaded', function () {
	// 세부메뉴 화면상단 고정
	const content = document.querySelector('.brand_menu');
	const admin = document.querySelector('.brand_admin');
	const con = document.querySelector('.brand_wrap');

	if (content) {
		window.addEventListener('scroll', function () {
			const isScrolled = window.scrollY > 0;
			content.classList.toggle('fixed', isScrolled);
			if (admin) admin.classList.toggle('on', isScrolled);
			if (con) con.classList.toggle('on', isScrolled);
		});
	}
	
	// 게시글 삭제
	const magazinedelete = document.querySelectorAll('.brand_section .delete_btn');
	if(magazinedelete) {
		magazinedelete.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				confirm('삭제하시겠습니까?', () => {
					location.href = '/Magazine/delete?no=' + this.dataset.no;
				});
			});
		});
	}
	
});
