window.addEventListener('DOMContentLoaded', function () {
	
});

function quizValidate() {
	const quizFrm = document.quizFrm;
	const q1 = quizFrm.Q1.value;
	const q2 = quizFrm.Q2.value;
	const q3 = quizFrm.Q3.value;
	const q4 = quizFrm.Q4.value;
	const sumQ = q1 + q2 + q3 + q4;
	
	if (sumQ.length != 4) {
		alert('항목을 모두 체크해주세요.');
		return false;
	}
	
	return true;
}