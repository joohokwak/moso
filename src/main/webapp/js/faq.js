window.addEventListener('DOMContentLoaded', function () {
	// 제목 클릭 시 내용 보기
    const faqTitles = document.querySelectorAll('.faq_a');
    if (faqTitles) {
        faqTitles.forEach(title => {
            title.addEventListener('click', function(e) {
                e.preventDefault();
                this.classList.toggle('on'); // 내용 보이기/숨기기
            });
        });
    }
    
    // 카테고리 선택
    const faqCategories = document.querySelectorAll('.faq_content_left a');
    if (faqCategories) {
        faqCategories.forEach(category => {
            category.addEventListener('click', function(e) {
                e.preventDefault();
                const categoryText = this.innerText;
                const categoryInput = document.querySelector('.faq_search input[name=cate]');
                
                // 카테고리 입력값 설정
                categoryInput.value = (categoryText === '전체') ? '' : categoryText;
                document.querySelector('.faq_contents form').submit(); // 폼 제출
            });
        });
    }
    
    // 수정 버튼 클릭 시 페이지 이동
    const faqUpdateBtns = document.querySelectorAll('.faq_content_right .update_btn');
    if (faqUpdateBtns) {
        faqUpdateBtns.forEach(btn => {
            btn.addEventListener('click', function() {
                location.href = '/Faq/update?no=' + this.dataset.no;
            });
        });
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
