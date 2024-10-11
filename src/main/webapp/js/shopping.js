window.addEventListener('DOMContentLoaded', function() {
	const toggle = document.querySelector('#shopping .select_toggle');
	const active = document.querySelectorAll('#shopping .rank li');
	
	if (toggle) {
		toggle.addEventListener('click', function() {
			toggle.classList.toggle('on');
		});
	}

	// js에서 siblings() 함수
	function siblings(t) {
		let children = t.parentElement.children;
		let tempArr = [];
		
		for (var i = 0; i < children.length; i++) {
			tempArr.push(children[i]);
		}
		
		return tempArr.filter(function(e) {
			return e != t;
		});
	}
	
	if (active) {
		active.forEach((item, idx) => {
			item.addEventListener('click', function(e) {
				e.preventDefault();
				siblings(e.currentTarget).forEach((i) => {
					// e.currentTarget: 클릭된 현재 타겟
					i.classList.remove('active');
				});
				active[idx].classList.add('active');
			});
		});
	}

	const wishList = document.querySelectorAll('#shopping .wish');
	if (wishList) {
		wishList.forEach((wish) => {
			wish.addEventListener('click', function(e) {
				e.preventDefault();
				let src = this.children[0].src;
				if (src.indexOf('off') != -1) {
					this.children[0].src = src.replace('wish_off.png', 'wish_on.png');
				} else {
					this.children[0].src = src.replace('wish_on.png', 'wish_off.png');
				}
			});
		});
	}
});
