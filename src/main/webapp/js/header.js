window.addEventListener('DOMContentLoaded', function () {
	const navEl = document.querySelector('header .top_nav');
	const dep2bg = navEl.querySelectorAll('.depth2_bg');
	const navs = document.querySelectorAll('header .top_nav>li');
	const dLis = document.querySelectorAll('header .depth2>li');
	const dep3s = document.querySelectorAll('header .depth3>li');
	const banners = document.querySelectorAll('header .banner');
	const titles = document.querySelectorAll('header .depth3_tit1');
	
	// header depth2_bg
	if (navEl) {
		navEl.addEventListener('mouseenter', function() {
			dep2bg.forEach((bg) => {
				bg.style.height = '480px';
			});
		});
		
		navEl.addEventListener('mouseleave', function() {
			dep2bg.forEach((bg) => {
				bg.style.height = 0;
			});
		});
	}
	
	// header .top_nav>li
	if (navs) {
		navs.forEach((nav) => {
			nav.addEventListener('mouseenter', function() {
				// 다른 항목에 on 클래스 제거
				navs.forEach((i) => i.classList.remove('on'));
				// 현재 항목에 'on' 클래스 추가
				nav.classList.add('on');
		    });
		
			nav.addEventListener('mouseleave', function() {
				nav.classList.remove('on'); // 마우스가 떠나면 'on' 클래스 제거
			});
		});
		  
		navs.forEach((nav) => {
			const dLis = nav.querySelectorAll('header .depth2>li');
		
			nav.addEventListener('mouseenter', function() {
				// 모든 자식 요소에서 'on' 클래스 제거
				dLis.forEach((dLi) => dLi.classList.remove('on'));
		
				// 첫 번째 자식 요소에 'on' 클래스 추가
				if (dLis.length > 0) {
					dLis[0].classList.add('on');
				}
		    });
		
			nav.addEventListener('mouseleave', function() {
				// 모든 자식 요소에서 'on' 클래스 제거
				dLis.forEach((dLi) => dLi.classList.remove('on'));
		    });
		});
	}
	
	// .depth3_tit 영역에서만 depth2>li에 on 걸리게하고
	// .depth2>li 영역을 벗어나야 mouseleave 됨
	if (dLis) {
		dLis.forEach((dLi) => {
			const depTits = dLi.querySelectorAll('header .depth3_tit');
		
			depTits.forEach((depTit) => {
				depTit.addEventListener('mouseenter', function() {
					dLi.classList.add('on');
				});
		
				dLi.addEventListener('mouseleave', function() {
					dLi.classList.remove('on');
				});
			});
		});
	}
	
	if (dep3s) {
		dep3s.forEach((dep3) => {
			dep3.addEventListener('mouseenter', function() {
				dep3.classList.add('on');
			});
		
			dep3.addEventListener('mouseleave', function() {
				dep3.classList.remove('on');
			});
		});
	}
	
	if (banners) {
		banners[0].classList.add('on');
		titles.forEach((title, index) => {
			title.addEventListener('mouseenter', function() {
				// 모든 배너 숨기기
				banners.forEach((banner) => banner.classList.remove('on'));
		
				// mouseenter된 제목에 맞는 배너 표시
				banners[index].classList.add('on');
			});
		});
	}
	
	// 준비중 페이지
	const navLink = document.querySelectorAll('header a[href="#"]');
	if (navLink) {
		navLink.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				if (this.getAttribute('id')) return false;
				
				const prepareCommonImg = document.querySelector('#prepareCommonWrap');
				prepareCommonImg.style.display = 'flex';
				document.body.classList.add('on');
				setTimeout(() => {
					prepareCommonImg.style.display = 'none';
					document.body.classList.remove('on');
				}, 3000);
			});
		});
	}
	
}); // DOMContentLoaded
