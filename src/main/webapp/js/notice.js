window.addEventListener('DOMContentLoaded', function () {
	
	// 삭제 버튼
	const noticeDelBtn = document.querySelector('.admin_notice_wrap #noticeDelAllBtn');
	if(noticeDelBtn) {
		noticeDelBtn.addEventListener('click', function(){
			const noticeDels = document.querySelectorAll('.admin_notice_wrap tbody .delete_col input[type=checkbox]');
			
			noticeDels.forEach(btn => {
				btn.checked = this.checked;
			})
		});
		
	}
	
	
	const noticeDeleteBtn = document.querySelectorAll('.admin_notice_wrap .delete_btn');
	if (noticeDeleteBtn) {
		noticeDeleteBtn.forEach(btn => {
			btn.addEventListener('click', function() {
				confirm('삭제하시겠습니까?', () => {
					location.href = '/Notice/delete?no=' + this.dataset.no;
				});
			});
		})
	}
});
