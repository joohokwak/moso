window.addEventListener('DOMContentLoaded', function() {
	new Swiper('#shopping_buy .swiper', {
		direction: 'horizontal',
		loop: true,
		slidesPerView: 5,
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
	const priceEm = document.querySelector('#shopping_buy .goods_select .total_price em');

	let addPr = 0;

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

				if (this.textContent === 'S') {
					addPr = 0.8;
				} else if (this.textContent === 'SS') {
					addPr = 0.9;
				} else if (this.textContent === 'Q') {
					addPr = 1;
				} else if (this.textContent === 'K') {
					addPr = 1.1;
				} else if (this.textContent === 'LK') {
					addPr = 1.2;
				} else {
					addPr = 0;
				}

				if (sizes[idx].classList.contains('active')) {
					detail.classList.add('on');
				} else if (detail.classList.contains('on')) {
					detail.classList.remove('on');
				}
			});
		});
	}

	if (delivery.length) {
		let price = priceEm.innerText;
		
		delivery.forEach((item, idx) => {
			item.addEventListener('click', function(e) {
				e.preventDefault();

				noneBro(e.currentTarget).forEach((i) => {
					i.classList.remove('active');
				});

				// active동안 sizes값 변경 방지
				delivery[idx].classList.toggle('active');
				
				for (n = 0; n < sizes.length; n++) {
					sizes[n].style.cssText = 'pointer-events: auto';
				}
				detail.classList.toggle('view');

				// 가격보기
				if (delivery[idx].classList.contains('active')) {
					detail.classList.add('view');
					// 클릭막기
					for (n = 0; n < sizes.length; n++) {
						sizes[n].style.cssText = 'pointer-events: none';
					}
				}
				
				// 가격 더하기
				if (delivery[1].classList.contains('active')) {
					priceEm.innerText = comma(Number(price) * addPr + 16000);
				} else if (delivery[2].classList.contains('active')) {
					priceEm.innerText = comma(Number(price) * addPr + 30000);
				} else if (delivery[3].classList.contains('active')) {
					priceEm.innerText = comma(Number(price) * addPr + 46000);
				} else {
					priceEm.innerText = comma(Number(price) * addPr);
				}
			});
		});
	}


	// 좋아요 버튼
	const detail = document.querySelector('#shopping_buy .select_detail');
	const likeBtn = document.querySelector('#shopping_buy .buttons .like');
	if (likeBtn) {
		likeBtn.addEventListener('click', function(e) {
			e.preventDefault();

			let like = this.children[0].src;
			let msg = '관심상품을 등록하시겠습니까?';

			if (like.indexOf('_on') !== -1) {
				msg = '관심상품을 취소하시겠습니까?';
			}

			confirm(msg, () => {
				if (this.dataset.islogin === 'true') {
					fetch(`/Shop/like?like=${this.dataset.no}`)
						.then((res) => res.json())
						.then((data) => {
							if (data != 0) {
								if (like.indexOf('off') !== -1) {
									this.children[0].src = like.replace('wish_off.png', 'wish_on.png');
								} else {
									this.children[0].src = like.replace('wish_on.png', 'wish_off.png');
								}
							}
						})
						.catch((err) => console.log(err));
						
				} else {
					alert('로그인하셔야 본 서비스를 이용하실 수 있습니다.', () => {
						document.querySelector('#loginbtn').click();
					});
				}
			});
		});
	}

	// 리뷰 내용 보기
	const reviewView = document.querySelector('#view_review .board_body');
	if (reviewView) {
		reviewView.addEventListener('click', function(e) {
			e.preventDefault();
			const displayViews = reviewView.querySelectorAll('.display_view .board_content a');
			
			displayViews.forEach(v => {
				v.addEventListener('click', handleReviewView);
			});
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
	
	const qna = document.querySelector('.shopping_borad .board_top .board_btn .qna');
	if(qna) {
		qna.addEventListener('click', function() {
			window.location.href = `/Shop/write?itemno=${this.dataset.no}`;
		});
		console.log(qna);
	}

	// 리뷰 페이징
	const reviewPaing = document.querySelectorAll('#view_review .page_num');
	if (reviewPaing) {
		reviewPaing.forEach(v => {
			v.addEventListener('click', function(e) {
				e.preventDefault();
				
				if (!v.classList.contains('active')) {
					const viewReview = document.querySelector('#view_review');
					const pageNum = v.innerText;
					if (!pageNum) pageNum = 1;
					
					const params = {'ITEMNO': viewReview.dataset.itemno, 'PAGENUM' : pageNum};
					
					post('/Shop/review', params, (data) => {
						// 요소 추가
						handleSetReview(data);
						
						// active 처리
						reviewPaing.forEach(rp => rp.classList.remove('active'));
						v.classList.add('active');
					});
				}
			});
		});
	}
	
});

// 콤마 변환 함수
function comma(str) {
	return str.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

function handleReviewView(e) {
	console.log(e);
	const display = e.target.closest('.display_view').children[1];
	
	if (display.classList.contains('none')) {
		display.classList.remove('none');
	} else {
		display.classList.add('none');
	}
}

// 리뷰 페이징 처리
function handleSetReview(data) {
	if (data) {
		let reviewTxt = '';
		
		for (const rv of data) {
			// 별점
			let star = '';
			for (let i = 0; i < rv.rating; i++) {
				star += '<img src="/images/shopping/star-fill.png" alt="별점">';
			}
			
			for (let i = 0; i < (5 - rv.rating); i++) {
				star += '<img src="/images/shopping/star-bg.png" alt="별점">';
			}
			
			reviewTxt += `
				<tbody class="display_view">
					<tr>
						<td width="110">
							<span class="rating">
								${star}
							</span>
						</td>
						<td class="board_content">
							<a href="#">${rv.title }</a>
							<span><img src="/images/shopping/icon_board_attach_file.png"alt="file"></span>
						</td>
						<td width="112" align="center">
							<p>${rv.writer }</p>
						</td>
						<td width="112">
							<p class="center">${rv.regdate }</p>
						</td>
					</tr>
					<tr class="display none">
						<td></td>
						<td class="board_content">
							<div class="board_main_content">
								${rv.content }
							</div>
							<div>
								<p><span><img src="/images/shopping/icon_board_attach_file.png"alt="file"></span><strong>첨부파일</strong><i class="file_name">jpg</i></p>
							</div>
						</td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			`;
		}
		
		const reviewBody = document.querySelector('#view_review .board_body');
		reviewBody.innerHTML = reviewTxt;
	}
	
}
