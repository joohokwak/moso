window.addEventListener('DOMContentLoaded', function () {
	// 메인 슬라이더
	let mainSlider = new Swiper('.main_slider .swiper', {
		simulateTouch: true,
		loop: true,
		speed: 800,
		autoplay: {
			delay: 5000,
			disableOnInteraction: false,
		},
		
		pagination: {
			el: '.swiper-pagination',
			clickable: true,
		},
		
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
	});

	// 재생 / 정지 버튼
	const toggleBtn = document.querySelector('.main_slider .toggle_btn');
	if (toggleBtn) {
		toggleBtn.addEventListener('click', function(e) {
			e.preventDefault();
			this.classList.toggle('on');

			if (this.classList.contains('on')) {
				mainSlider.autoplay.stop();
			} else {
				mainSlider.autoplay.start();
			}
		});
	}

	// 매거진 슬라이더
	let magazineSlider = new Swiper('.magazine_slider .swiper', {
		simulateTouch: true,
		loop: true,
		slidesPerView: 3,
		spaceBetween: 50,

		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
	});

	// 리뷰 슬라이더
	let reviewSlider = new Swiper('.reivew_slider .swiper', {
		simulateTouch: true,
		loop: true,
		slidesPerView: 1.7,
		spaceBetween: 50,

		pagination: {
			el: '.review_wrap .swiper_btn .swiper-pagination',
			clickable: true,
			type: 'fraction',
			formatFractionCurrent: function (num) {
				return num < 10 ? '0' + num : num;
			},
			formatFractionTotal: function (num) {
				return num < 10 ? '0' + num : num;
			},
			renderFraction: function (currentClass, totalClass) {
				return (
					'<span class="' + currentClass + '"></span>' +
					'/&nbsp;' +
					'<span class="' + totalClass + '"></span>'
				);
			},
		},
		
		navigation: {
			nextEl: '.review_wrap .swiper_btn .swiper-button-next',
			prevEl: '.review_wrap .swiper_btn .swiper-button-prev',
		},
	});
  
	// TOP 버튼
  	const float_btn = document.querySelector('.float_btn');
	if (float_btn) {
		window.addEventListener('scroll', function () {
			const scrollY = window.scrollY;
	
			// 스크롤y 값 = 510
			if (scrollY > 510) {
				float_btn.classList.add('fixed');
			} else {
				float_btn.classList.remove('fixed');
			}
		});
	
		// 위로가기 버튼
		const goTopBtn = document.querySelector('.go_top');
		if (goTopBtn) {
			goTopBtn.addEventListener('click', function(e) {
				e.preventDefault();
				window.scrollTo({ top: 0, behavior: 'smooth' });
			});
		}
	}
	
	// 공통 버튼박스 (관리자인경우 노출되도록 설정)
	const adminBtnWraps = document.querySelectorAll('[data-isadmin]');
	if (adminBtnWraps) {
		adminBtnWraps.forEach(divWrap => {
			if (divWrap.dataset.isadmin === 'true') {
				divWrap.style.display = 'flex';
			}
		});
	}
	
	// 공통 버튼 (글쓰기)
	const writeBtns = document.querySelectorAll('.admin_btn_wrap .write_btn');
	if (writeBtns) {
		writeBtns.forEach(writeBtn => {
			writeBtn.addEventListener('click', function() {
				let $path = location.pathname;
				$path = $path.substring(0, $path.lastIndexOf("/"));
				location.href = $path + "/write";
			});
		});
	}
	
}); // DOMContentLoaded