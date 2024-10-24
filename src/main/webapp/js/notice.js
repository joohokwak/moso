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
	};
	
	// view 페이지에서 파일첨부 
	const fileInputView = document.getElementById('notice_file_input');
	const cancelBtnView = document.getElementById('cancel_btn');
	const fileNamDisplayEl = document.getElementById('file_name_display');
	if (fileInputView) {
		fileInputView.addEventListener('change', function(){
			if(fileInputView.files.length > 0) {
				fileNamDisplayEl.textContent = fileInputView.files[0].name; 
				cancelBtnView.style.display = 'inline-block';
			} else {
				console.log('파일이 선택되지 않았습니다.');
				cancelBtnView.style.display = 'none';
			}
		});
	};
	
	// view 페이지에서 파일삭제 (DB에서는 삭제되지 않음)
	if(cancelBtnView){
		cancelBtnView.addEventListener('click', function(e){
			e.preventDefault();
			
			fileNamDisplayEl.textContent = '';
			cancelBtnView.style.display = 'none';
		});
	};
	
	// 업데이트 페이지 접근 시, 파일삭제 버튼 생성 및 파일삭제
	const cancelBtnUpdate = document.getElementById('cancel_btn_update');
	const noticeUpdate = document.querySelector('.notice_file_update');
	const noticeUpdateId = document.getElementById('notice_file_update');
	if(cancelBtnUpdate){
		cancelBtnUpdate.addEventListener('click', function(e){
			e.preventDefault();
			noticeUpdate.value = '';
			cancelBtnUpdate.style.display = 'none';
		});
	}
	
	if(noticeUpdateId) {
		noticeUpdateId.addEventListener('change', function(){
			noticeUpdate.value = noticeUpdateId.files[0].name;
			cancelBtnUpdate.style.display = 'inline-block';
		});
	};
});
