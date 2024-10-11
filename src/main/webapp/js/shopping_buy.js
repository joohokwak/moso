window.addEventListener('DOMContentLoaded', function() {
	const swiper = new Swiper('#shopping_buy .swiper', {
		// Optional parameters
		direction: 'horizontal',
		loop: true,
		slidesPerView: 5,
		// Navigation arrows
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
	});

	const images = document.querySelectorAll('#shopping_buy .goods_img .swiper-slide');
	if (images) {
		images.forEach((img) => {
			img.addEventListener('click', function() {
				document.querySelector('#shopping_buy .main_img img').src = img.children[0].src;
			});
		});
	}

	const sizes = document.querySelectorAll('#shopping_buy .select_detail .size a');
	const delivery = document.querySelectorAll('#shopping_buy .select_detail .delivery a');

	function noneBro(t) {
		let bro = t.parentElement.children;
		let broArr = [];
		
		for (let i = 0; i < bro.length; i++) {
			broArr.push(bro[i]);
		}
		
		return broArr.filter(function(e) {
			return e != t;
		});
	}
	
	if (sizes) {
		sizes.forEach((item, idx) => {
			item.addEventListener('click', function(e) {
				e.preventDefault();
				noneBro(e.currentTarget).forEach((i) => {
					i.classList.remove('active');
				});
				
				sizes[idx].classList.add('active');
				if (sizes[idx].classList.contains('active')) {
					detail.classList.add('on');
				} else if (detail.classList.contains('on')) {
					detail.classList.remove('on');
				}
			});
		});
	}
	
	if (delivery) {
		delivery.forEach((item, idx) => {
			item.addEventListener('click', function(e) {
				e.preventDefault();
				noneBro(e.currentTarget).forEach((i) => {
					i.classList.remove('active');
				});
				
				delivery[idx].classList.add('active');
				if (delivery[idx].classList.contains('active')) {
					detail.classList.add('view');
				}
			});
		});
	}

	const detail = document.querySelector('#shopping_buy .select_detail');
	const likeBtn = document.querySelector('#shopping_buy .buttons .like');
	if (likeBtn) {
		likeBtn.addEventListener('click', function() {
			let like = likeBtn.children[0].src;

			if (confirm('관심상품을 등록하시겠습니까?')) {
				this.children[0].src = like.replace('wish_off.png', 'wish_on.png');
			} else {
				if (like.indexOf('off') === -1) {
					this.children[0].src = like.replace('wish_on.png', 'wish_off.png');
				}
			}
		});
	}
	const showDetail = document.querySelector('#view_info .detail_more_box .table i');
	const detailBox = document.querySelector('#view_info .detail_more_box');
	const detailImage = document.querySelector('#view_info .detail_more_box .more_size');
	if (detailBox) {
		showDetail.addEventListener('click', function() {
			detailBox.classList.add('show_img');
		});
		
		detailImage.addEventListener('click', function() {
			detailBox.classList.remove('show_img');
		});
	}
});
