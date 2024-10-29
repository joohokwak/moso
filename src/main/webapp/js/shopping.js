window.addEventListener('DOMContentLoaded', function() {
	// 타입 선택 토글
	const toggle = document.querySelector('#shopping .select_toggle');
	if (toggle) {
		toggle.addEventListener('click', function() {
			toggle.classList.toggle('on');
		});
	}
	
	// 좋아요 버튼
	const wishList = document.querySelectorAll('#shopping .wish');
	if (wishList) {
		wishList.forEach((wish) => {
			wish.addEventListener('click', function(e) {
				e.preventDefault();
				// 로그인 여부 확인
				if (this.dataset.islogin === "true") {
					post(`/Shop/like?like=${this.dataset.no}`, {}, (data) => {
						if (data !== 0) {
							//	wish 클래스 img 태그 src
							let src = this.children[0].src;
							// off가 없을 시 on으로 변경
							if (src.indexOf('off') != -1) {
								this.children[0].src = src.replace('wish_off.png', 'wish_on.png');
							} else {
								this.children[0].src = src.replace('wish_on.png', 'wish_off.png');
							}
						}
					});
				// 非로그인 시 alert	
				} else {
					alert("로그인하셔야 본 서비스를 이용하실 수 있습니다.", () => {
						const loginBtn = document.querySelector('#loginbtn');
						// 로그인 화면 열기
						loginBtn.click();
					});
				}
				
			});
		});
	}
});
