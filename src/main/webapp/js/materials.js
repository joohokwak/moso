window.addEventListener('DOMContentLoaded', function() {
	// 수정 버튼
	const materialUpdateBtns = document.querySelectorAll('.materials_content .update_btn');
	if (materialUpdateBtns.length) {
		materialUpdateBtns.forEach(btn => {
			btn.addEventListener('click', function() {
				location.href = '/Materials/update?no=' + this.dataset.no;
			});
		})
	}
	
	// 삭제 버튼
	const materialDeleteBtns = document.querySelectorAll('.materials_content .delete_btn');
	if (materialDeleteBtns.length) {
		materialDeleteBtns.forEach(btn => {
			btn.addEventListener('click', function() {
				confirm('삭제하시겠습니까?', () => {
					location.href = '/Materials/delete?no=' + this.dataset.no;
				});
			});
		})
	}
});

// 조립설명서 (파일다운로드)
function fileDownLoad(ofile, nfile) {
	location.href = `/Materials/download?ofile=${ofile}&nfile=${nfile}`;
}