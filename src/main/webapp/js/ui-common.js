window.addEventListener('DOMContentLoaded', function () {
	// 슬라이더 초기화 함수
	function initSwiper(selector, options) {
		return new Swiper(selector, options);
	}
	
	// 메인 슬라이더
	const mainSlider = initSwiper('.main_slider .swiper', {
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
	clickEvent('.main_slider .toggle_btn', function() {
		this.classList.toggle('on');
		
		if (mainSlider.autoplay.running) {
			mainSlider.autoplay.stop();
		} else {
			mainSlider.autoplay.start();
		}
	});
	
	// 매거진 슬라이더
	initSwiper('.magazine_slider .swiper', {
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
	initSwiper('.reivew_slider .swiper', {
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
  	
	// TOP 버튼 (스크롤)
  	const float_btn = document.querySelector('.float_btn');
	if (float_btn) {
		window.addEventListener('scroll', function () {
			// 스크롤y 값 = 510
			float_btn.classList.toggle('fixed', window.scrollY > 510);
		});
	}
	
	// TOP 버튼 (클릭)
	clickEvent('.go_top', function() {
		window.scrollTo({ top: 0, behavior: 'smooth' });
	});
	
	// 공통 버튼 (글쓰기)
	const writeBtns = document.querySelectorAll('.admin_btn_wrap .write_btn');
	if (writeBtns) {
		writeBtns.forEach(writeBtn => {
			writeBtn.addEventListener('click', function() {
				const path = location.pathname.substring(0, location.pathname.lastIndexOf("/"));
				location.href = `${path}/write`;
			});
		});
	}
	
}); // DOMContentLoaded