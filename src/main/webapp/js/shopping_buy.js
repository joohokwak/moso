window.addEventListener('DOMContentLoaded', function() {
	// buy 이미지 하단 이미지 슬라이더
	new Swiper('#shopping_buy .swiper', {
		direction: 'horizontal',
		loop: true,
		slidesPerView: 5,
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		},
	});
	// 슬라이더 이미지 클릭 시 큰 이미지로 변경
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
	
	// jQuery siblings 대신 사용
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
	
	// 사이즈 선택 버튼
	if (sizes) {
		sizes.forEach((item, idx) => {
			item.addEventListener('click', function(e) {
				
				e.preventDefault();
				
				// 클릭된 요소만 active
				noneBro(e.currentTarget).forEach((i) => {
					i.classList.remove('active');
				});
				sizes[idx].classList.add('active');

				// 추가 가격 설정
	            const sizePriceMapping = {
	                'S': 0.8,
	                'SS': 0.9,
	                'Q': 1,
	                'K': 1.1,
	                'LK': 1.2
	            };
				
				// 추가 가격
	            addPr = sizePriceMapping[this.textContent] || 0; // 기본값 0 설정

				// 상세 정보 표시 관리
				detail.classList.toggle('on', sizes[idx].classList.contains('active'));
			});
		});
	}
	
	// 배송 지역 선택
	const detail = document.querySelector('#shopping_buy .select_detail');
	
	if (delivery.length) {
		// 가격 요소 내용
		let price = priceEm.innerText;
		
		delivery.forEach((item, idx) => {
			item.addEventListener('click', function(e) {
				e.preventDefault();
				
				// 선택된 요소만 active
				noneBro(e.currentTarget).forEach((i) => {
					i.classList.remove('active');
				});
				delivery[idx].classList.toggle('active');
				
				// 배송 여부 active동안 sizes값 변경 방지
				// 클릭 허용
				for (n = 0; n < sizes.length; n++) {
					sizes[n].style.cssText = 'pointer-events: auto';
				}
				// 총 가격 토글
				detail.classList.toggle('view');

				// 배송 선택 시  클릭 막기
				if (delivery[idx].classList.contains('active')) {
					// 총 가격 보기
					detail.classList.add('view');
					// 클릭막기
					for (n = 0; n < sizes.length; n++) {
						sizes[n].style.cssText = 'pointer-events: none';
					}
				}
				
				// 가격 더하기
				if (delivery[1].classList.contains('active')) {
					priceEm.innerText = comma(parseInt(price * addPr + 16000));
				} else if (delivery[2].classList.contains('active')) {
					priceEm.innerText = comma(parseInt(price * addPr + 30000));
				} else if (delivery[3].classList.contains('active')) {
					priceEm.innerText = comma(parseInt(price * addPr + 46000));
				} else {
					priceEm.innerText = comma(parseInt(price * addPr));
				}
			});
		});
	}


	// 좋아요 버튼
	const likeBtn = document.querySelector('#shopping_buy .buttons .like');
	if (likeBtn) {
		likeBtn.addEventListener('click', function(e) {
			e.preventDefault();

			let like = this.children[0].src;
			let msg = '관심상품을 등록하시겠습니까?';
			
			// like 버튼 img가 wish_on이(-1이 아니)면 메시지 변경
			if (like.indexOf('_on') !== -1) {
				msg = '관심상품을 취소하시겠습니까?';
			}
			
			// 메시지 띄우기
			confirm(msg, () => {
				// 로그인 시
				if (this.dataset.islogin === 'true') {
					// itemno 값을 가지고 like로 이동
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
				// 非비로그인 시
				} else {
					alert('로그인하셔야 본 서비스를 이용하실 수 있습니다.', () => {
						// 로그인 화면 띄우기
						document.querySelector('#loginbtn').click();
					});
				}
			});
		});
	}
	
	// "사이즈" 옆 (!) 클릭 시 사이즈 관련 상세보기
	const showDetail = document.querySelector('#view_info .detail_more_box .table i');
	const detailBox = document.querySelector('#view_info .detail_more_box');
	const detailImage = document.querySelector('#view_info .detail_more_box .more_size');
	if (detailBox) {
	    if (showDetail) { // null 체크 추가
	        showDetail.addEventListener('click', function() {
	            detailBox.classList.add('show_img');
	        });
	    }

	    if (detailImage) {
	        detailImage.addEventListener('click', function() {
	            detailBox.classList.remove('show_img');
	        });
	    }
	}
		
	
	// rvwrite 작성
	const gorvwrite = document.querySelector('#view_review .board_write');
	
	if (gorvwrite) {
		
		const user = gorvwrite.dataset.user;
		const itemno = gorvwrite.dataset.no;
		const url = '/Shop/rvwrite?itemno=' + itemno;
		const name = 'rvwirte';
		const option = 'width=1125, height=1000, left=500';
		
		// review write 팝업창
		gorvwrite.addEventListener('click', function() {
//			type 비교(typeof)
//			console.log(typeof user);
			if (user === '1') {
				window.open(url, name, option);				
			} else {
				alert('로그인하셔야 본 서비스를 이용하실 수 있습니다.', () => {
					document.querySelector('#loginbtn').click();
				});
			}
		});
	}
	
	// 리뷰 내용 보기
	const reviewView = document.querySelector('#view_review .board_body');
	
	if (reviewView) {
		
		let id = reviewView.dataset.id;
		let admin = reviewView.dataset.admin;
		
		reviewView.addEventListener('click', function(e) {
			
	        const target = e.target.closest('.display_view .board_content a');
			
	        if (target) {
				e.preventDefault();
				const secret = target.dataset.secret;
				const display = e.target.closest('.display_view').querySelector('tr + .display');
				const writer = e.target.closest('.display_view').querySelector('.review_writer').innerText;
				// 비밀글 X || 작성자 = 로그인 아이디 || 관리자 O 이면  리뷰 내용 보기
				if (secret == 0 || writer == id || admin == 'Y') display.classList.toggle('none');
				
				const  rvupdate = e.target.closest('.display_view').querySelector('.rv_btn .rvupdate');
				const  rvdelete = e.target.closest('.display_view').querySelector('.rv_btn .rvdelete');
				
				// 리뷰 업데이트 페이지 로딩
				if (rvupdate) {
					const no = rvupdate.dataset.no;
					const itemno = rvupdate.dataset.itemno;
					const url = '/Shop/rvupdate?itemno=' + itemno + '&rvno=' + no;
					const name = 'rvupdate';
					const option = 'width=1125, height=1000, left=500';
					rvupdate.addEventListener('click', function(e) {
						e.preventDefault();
						window.open(url, name, option);				
					});
				}
					
				// 리뷰 삭제
				if (rvdelete) {
					const no = rvdelete.dataset.no;
					const itemno = rvdelete.dataset.itemno;
					
					rvdelete.addEventListener('click', function(e) {
						e.preventDefault();
						location.href =`/Shop/rvdelete?itemno=${itemno}&rvno=${no}`;	
					});
				}
	        }	
		});
	}
	
	
	// 리뷰 페이징
    const reviewPaing = document.querySelector('#view_review');
    if (reviewPaing) {
       reviewPaing.addEventListener('click', function(e) {
         const target = e.target.closest('#view_review .pagination .page_num');
         if (target) {
	            e.preventDefault();
            if (!target.classList.contains('active')) {
               let pageNum = target.getAttribute('href').match(/pageNum=(\d+)/)[1];
               
               // 기본값 설정
               if (!pageNum) pageNum = 1;
               
               const params = {
                  'ITEMNO': reviewPaing.dataset.itemno,
                  'PAGENUM' : pageNum
               };
               
               post('/Shop/review', params, (data) => {
                  // 요소 추가
                  handleSetReview(data.reviewAll);
                  
                  // 페이징 처리
                  document.querySelector('#view_review .pagination').innerHTML = data.paging;
               });
            }
         }
         
      });
    }
	
	// Q&A 등록 버튼
	const qna = document.querySelector('.shopping_borad .board_top .board_btn .qna');
	if (qna) {
		qna.addEventListener('click', function() {
			location.href = `/Shop/write?itemno=${this.dataset.no}`;
		});
	}
	
	// Q&A 글 내용 토글
	const qnaView = document.querySelector('#view_question .board_body');
	if (qnaView) {
		qnaView.addEventListener('click', function(e) {
			// 동적 태그 (접근 방식) // tr:nth-of-type(1) : 토글 시 발생되는 dataset를 못찾는 오류 해결 
	        const target = e.target.closest('.display_view tr:nth-of-type(1) .board_content a');
			
	        if (target) {
	            e.preventDefault();
				const no = target.dataset.no;
				const admin = target.dataset.user;
				
				// 비밀글이 아닌 경우
				if (target.dataset.secret === 'false') {
					target.closest('.display_view').classList.toggle('on');
				
				//관리자인 경우
				} else if (admin == 'isadmintrue') {
					target.closest('.display_view').classList.toggle('on');
				
				// 비밀글인 경우 
				} else {
					prompt('글작성 시 설정한 비밀번호를 입력하세요.', (pass) => {
						// check로 pass 값을 보내서 비교
						post('/Shop/check', {no, pass}, data => {
							// check 기본 값은 char='0', pass 일치 시 '1'						
							if (data === '1') {
								target.closest('.display_view').classList.add('on');
							} else {
								alert('비밀번호가 일치하지 않습니다.');
							}
						});
					});
				}
				
				// + 바로 **뒤에** 나오는 인접한 형제 중 선택
				const display = e.target.closest('.display_view').querySelector('tr + .display');

				if (display) display.classList.toggle('on');
				
	        }
		});
	}
	
	// Q&A 수정/삭제 버튼(모든 사용자에게 버튼이 보임.) 비밀번호 체크 후 이동
	const qnaBtn = document.querySelectorAll('#view_question .board_body .display_view .qna_btn a');
	
	if (qnaBtn) {
		qnaBtn.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				
				const delBtn = e.target.closest('.qna_delete');
				const no = btn.dataset.no;
				const itemno = btn.dataset.itemno;
				
				prompt('글작성 시 설정한 비밀번호를 입력하세요.', (pass) => {
					if (pass) {
						post('/Shop/check', {no, pass}, (data) => {
							// 비밀번호 일치 시
							if (data === '1') {
								// 삭제
								if (delBtn) {
									post('/Shop/qnadelete', {no}, () => {
										location.reload();
									});
								// 수정
								} else {
									location.href = `/Shop/update?itemno=${itemno}&qnano=${no}`;
								}
							// 비밀번호 불일치
							} else {
								alert('비밀번호가 일치하지 않습니다.');
							}
						});
					}
				});
				
			});
		});
	}
	
	// Q&A 페이징
	const qnaPaing = document.querySelectorAll('#view_question .page_num');
	
	if (qnaPaing) {
		qnaPaing.forEach(v => {
			// page_num 클릭 시
			v.addEventListener('click', function(e) {
				e.preventDefault();
				// v에 active가 없을 시
				if (!v.classList.contains('active')) {
					
					const viewQuest = document.querySelector('#view_question');
					let pageNum = v.innerText;
					
					// 기본값 설정
					if (!pageNum) pageNum = 1;
					
					// 아이템 번호와 페이지 번호 전달
					const params = {
						'ITEMNO': viewQuest.dataset.itemno, 
						'PAGENUM' : pageNum
					};
					
					post('/Shop/qna', params, (data) => {
						
						// 요소 추가
						handleSetQna(data);
						
						// active 처리(siblings 유사)
						qnaPaing.forEach(rp => rp.classList.remove('active'));
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

// 리뷰 페이징 처리 후 요소 추가
function handleSetReview(data) {
	
	if (data) {
		let reviewTxt = '';
		const reviewView = document.querySelector('#view_review .board_body');
		let id = reviewView.dataset.id;
		let admin = reviewView.dataset.admin;
		
		for (const rv of data) {
			// 별점 생성
	        const star = createStarRating(rv.rating);
			
			let tmp = '';
			let file = '';
			let user = '';
			
			// 비밀글인 경우 아이콘 표시
			if (rv.secret > 0) {
				tmp = '<img class="secret_img" src="/images/shopping/icon_board_secret.png" alt="비밀글">';
			}
			// 이미지 파일 있는 경우 file아이콘 표시
			if (rv.content.indexOf('<img') != -1) {
				file = '<img src="/images/shopping/icon_board_attach_file.png"alt="file">';
			}
			// 본인이 작성한 글이거나 관리자인 경우 active
			if (rv.writer == id || admin == 'Y') {
				user = 'active';
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
						${tmp}
						<a href="#" data-secret="${rv.secret }">${rv.title }</a>
						<span>${file}</span>
					</td>
					<td width="112" align="center">
						<p class="review_writer">${rv.writer }</p>
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
					</td>
					<td></td>
					<td>
						<div class="rv_btn ${user}">
							<a href="#" class="rvupdate" data-no="${rv.no }" data-itemno="${rv.itemno }" >수정</a>
							<a href="#">삭제</a>
						</div>
					</td>
				</tr>
			</tbody>
			`;
		}
		
		// html에 요소 삽입
		const reviewBody = document.querySelector('#view_review .board_body');
		reviewBody.innerHTML = reviewTxt;
	}
}

// 별점 생성 함수
function createStarRating(rating) {
    let stars = '';
	
    // 별점 이미지 생성
    for (let i = 0; i < rating; i++) {
        stars += '<img src="/images/shopping/star-fill.png" alt="별점">';
    }

    for (let i = 0; i < (5 - rating); i++) {
        stars += '<img src="/images/shopping/star-bg.png" alt="별점">';
    }

    return stars;
}

// qna 페이징 후 요소 추가 함수
function handleSetQna(data) {
	if (data) {
		const qnaView = document.querySelector('#view_review .board_body');
		
		let qnaTxt = '';
		let user = qnaView.dataset.admin;
		
		for (const qna of data ) {
			
			let tmp = '';
			let admin ='';
			let isadmin = '';
			let answre = '&nbsp;';
			let answreYes = '';
			
			if (qna.secret > 0) {
				tmp = '<img src="/images/shopping/icon_board_secret.png" alt="비밀글">';
			}
			// 관리자인 경우
			if (user == 'Y') {
				admin = 'active';
				isadmin = 'isadmintrue';
			}
			// 답변이 있는 경우
			if (qna.answre) {
				// 답변 보이기
				answre = '${qna.answre}';
				// 답변완료 버튼 표시
				answreYes = '답변완료';
			}
			
			qnaTxt += `
				<tbody class="display_view">						
					<tr>
						<td class="board_content">
							<span>
							 	${tmp}
							</span>
							<a href="#" data-no="${qna.no }" data-secret='${qna.secret > 0}' data-user="${isadmin }">${qna.title }</a>
						</td>
						<td width="112">
							<p>${qna.writer }</p>
						</td>
						<td width="112">
							<p class="center">${qna.regdate }</p>
						</td>
						<td width="112">
							<p class="center">${answreYes }</p>
						</td>
					</tr>
					<tr>
						<td colspan='3' class="board_content display_none">
							<span><img src="/images/shopping/q.png" alt="질문"></span>
							<p>${qna.question }</p>
						</td>
						<td class="display_none" >
							<div class="qna_btn">
								<a href="#" data-itemno="${qna.itemno }" data-no="${qna.no }">수정</a>
								<a href="#">삭제</a>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan='4' class="board_content display_none">
							<span><img src="/images/shopping/a.png" alt="답변"></span>
							<p>${answre }</p>
							<div id="admin_ans" class="${admin }">
								<button onclick="location.href='/Shop/answre?itemno=${qna.itemno }&qnano=${qna.no }'" data-itemno="${qna.itemno }" data-no="${qna.no }" class="ansBtn">작성/수정</button>
								<button onclick="location.href='/Shop/ansdelete?itemno=${qna.itemno }&qnano=${qna.no }'" class="ansBtn">삭제</button>
							</div>
						</td>
					</tr>
				</tbody>
			`;
		}
		
		// html에 삽입
		const qnaBody = document.querySelector('#view_question .board_body');
		qnaBody.innerHTML = qnaTxt;
	}
}