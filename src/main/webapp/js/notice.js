window.addEventListener('DOMContentLoaded', function () {
	const noticeDels = document.querySelectorAll('.admin_notice_wrap tbody .delete_col input[type=checkbox]');
	let delSelectedNo = [];
	
	// 전체 선택
    const noticeDelAll = document.querySelector('.admin_notice_wrap #noticeDelAll');
    if (noticeDelAll) {
        noticeDelAll.addEventListener('click', function () {
            const isChecked = this.checked;
            delSelectedNo = []; // 초기화

            noticeDels.forEach(btn => {
                btn.checked = isChecked;
                if (isChecked) {
                    delSelectedNo.push(btn.dataset.no);
                }
            });
        });
    }
	
	// 글 선택
    const noticeDelSelect = document.querySelectorAll('.admin_notice_wrap .selectedDelNo');
    if (noticeDelSelect.length) {
        noticeDelSelect.forEach(select => {
            select.addEventListener('click', function () {
                const no = this.dataset.no;
                if (this.checked) {
                    delSelectedNo.push(no);
                } else {
                    delSelectedNo = delSelectedNo.filter(val => val !== no);
                }
            });
        });
    }
	
	// 글 삭제 버튼
    const noticeDelBtn = document.querySelector('.admin_notice_wrap .delete_btn');
    if (noticeDelBtn) {
        noticeDelBtn.addEventListener('click', function () {
            if (delSelectedNo.length) {
                confirm('삭제하시겠습니까?', () => {
                    location.href = '/Notice/delete?no=' + delSelectedNo.join();
                });
            } else {
                alert("삭제할 항목을 선택해 주세요.");
            }
        });
    }
	
	// view 페이지 글삭제
	const contentNo = document.querySelector('.notice_view_wrap .delete_btn');
	if (contentNo) {
		contentNo.addEventListener('click', function(){
			const cntNo = this.dataset.no;
			
			confirm('글을 삭제하시겠습니까?', () => {
				location.href = '/Notice/delete?no=' + cntNo;
			});
		});
	}
});
