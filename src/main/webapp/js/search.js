window.addEventListener('DOMContentLoaded', function() {
	// 검색 폼 select active
	const _select = document.querySelector('.search_input .select_type');
	if (_select) {
		_select.addEventListener('click', function() {
			_select.classList.toggle('on');
		});
	}
	
	// 위시리스트 active
	const _wish = document.querySelector('.thumbnail .wish');
	if (_wish) {
		_wish.addEventListener('click', function() {
			_wish.classList.toggle('active');
		});	
	}	
	
	// 검색 영역
	const _topInput = document.querySelector('.txt_field .text_keyword');
	const _topSearch = document.querySelector('.txt_field .search_button'); // 검색 버튼 선택
	if (_topSearch && _topInput) {
		_topSearch.addEventListener('click', function(e) {
			if (_topInput.value.length === 0) {
				e.preventDefault();
				alert('검색어를 입력하세요.');
			}
		});
	}
});