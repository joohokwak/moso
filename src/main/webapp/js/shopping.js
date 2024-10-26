window.addEventListener('DOMContentLoaded', function() {
	// 타입선택
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
				
				if(this.dataset.islogin === "true"){
					
					fetch(`/Shop/like?like=${this.dataset.no}`).then(res => res.json()).then(data => {
						if(data != 0) {
							let src = this.children[0].src;
							if (src.indexOf('off') != -1) {
								this.children[0].src = src.replace('wish_off.png', 'wish_on.png');
							} else {
								this.children[0].src = src.replace('wish_on.png', 'wish_off.png');
							}
						}
					}).catch(err => console.log(err));
					
				} else {
					alert("로그인하셔야 본 서비스를 이용하실 수 있습니다.", () => {
						const loginBtn = document.querySelector('#loginbtn');
						loginBtn.click();
					});
				}
				
			});
		});
	}
});
