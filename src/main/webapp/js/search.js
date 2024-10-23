window.addEventListener('DOMContentLoaded', function() {
	// 검색 폼 select active
	const _select = document.querySelector('.search_input .select_type');
	if (_select) {
		_select.addEventListener('click', function() {
			_select.classList.toggle('on');
		});
	}

	// 위시리스트 active
	const _searchWish = document.querySelectorAll('#search_content .thumbnail .wish');
	if (_searchWish) {
		_searchWish.forEach((item) => {
			item.addEventListener('click', function(e) {
				e.preventDefault();

				if (this.dataset.islogin === 'true') {
					confirm('관심상품을 등록하시겠습니까?', () => {
//						fetch(`/Shop/like?like=${this.dataset.no}`).then(res => res.json()).then(data => {
//							this.classList.toggle('active');
//						}).catch(err => console.log(err));
						
						post(`/Shop/like?like=${this.dataset.no}`, {}, (data) => {
							this.classList.toggle('active');
						});
						
					});
					
					
					
					
				} else {
					alert("로그인하셔야 본 서비스를 이용하실 수 있습니다.", () => {
						const loginBtn = document.querySelector('#loginbtn');
						loginBtn.click();
					});
				}				
			});
		});
	}

	// 검색 페이지 내부 검색 버튼
	const _pageSearchBtn = document.querySelector('#search_content .search_input .search_btn');
	const _pageSearch = document.querySelector('#search_content .search_input input[type=text]');
	if (_pageSearch) {
		_pageSearchBtn.addEventListener('click', function(e) {
			if (_pageSearch.value.length === 0) {
				e.preventDefault();
				alert('검색어를 입력하세요.');
			};
		});
	}
	// 상품 결과 정렬 순서
	const _searchSort = document.querySelectorAll('#search_content .sort_wrap > li');
	if (_searchSort) {
		_searchSort.forEach((item) => {
			item.addEventListener('click', function(e){
				e.preventDefault();
				_searchSort.forEach(v => {
					v.classList.remove('on');
				});
				
				item.classList.toggle('on');
				item.querySelector('input[type=radio]').checked = true;
				
				document.sortFrm.submit();
			});
		})
	}
	
});








