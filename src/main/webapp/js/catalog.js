window.addEventListener('DOMContentLoaded', function () {
	// 수정 버튼
	const catalogUpdateBtns = document.querySelectorAll('.catalog_list_btn .update_btn');
	if (catalogUpdateBtns) {
		catalogUpdateBtns.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				location.href = '/Catalog/update?no=' + this.dataset.no;
			});
		})
	}
	
	// 삭제 버튼
	const catalogDeleteBtns = document.querySelectorAll('.catalog_list_btn .delete_btn');
	if (catalogDeleteBtns) {
		catalogDeleteBtns.forEach(btn => {
			btn.addEventListener('click', function(e) {
				e.preventDefault();
				confirm('삭제하시겠습니까?', () => {
					location.href = '/Catalog/deleteOk?no=' + this.dataset.no;
				});
			});
		})
	}
	
	
});
