window.addEventListener('DOMContentLoaded', function () {
	// 제목 클릭시 내용보기
	const faqA = document.querySelectorAll('.faq_a');
	if (faqA) {
		faqA.forEach((b) => {
			b.addEventListener('click', function(e) {
				e.preventDefault();
				this.classList.toggle('on');
			});
		});
	}
	
	// 카테고리 선택
	const faqCate = document.querySelectorAll('.faq_content_left a');
	if (faqCate) {
		faqCate.forEach(cate => {
			cate.addEventListener('click', function(e) {
				e.preventDefault();
				
				const _text = this.innerText;
				const cateEl = document.querySelector('.faq_search input[name=cate]');
				if (_text === '전체') cateEl.value = '';
				else cateEl.value = _text;
				
				document.querySelector('.faq_contents form').submit();
			});
		});
	}
	
	// 수정 버튼
	const faqUpdateBtns = document.querySelectorAll('.faq_content_right .update_btn');
	if (faqUpdateBtns) {
		faqUpdateBtns.forEach(btn => {
			btn.addEventListener('click', function() {
				location.href = '/Faq/update?no=' + this.dataset.no;
			});
		})
	}
	
	// 삭제 버튼
	const faqDeleteBtns = document.querySelectorAll('.faq_content_right .delete_btn');
	if (faqDeleteBtns) {
		faqDeleteBtns.forEach(btn => {
			btn.addEventListener('click', function() {
				confirm('삭제하시겠습니까?', () => {
					location.href = '/Faq/delete?no=' + this.dataset.no;
				});
			});
		})
	}
	
	
});

// 조립설명서 (파일다운로드)
function fileDownLoad(ofile) {
	location.href = "/Materials/download?ofile=" + ofile;
}