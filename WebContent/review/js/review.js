/**
 * 
 */
function yes() {
	opener.reviewYes();
	window.close();
}

function no() {
	opener.reviewNo();
	window.close();
}

function purchase() {
	window.open("reviewCheck.jsp","리뷰확인","width=270,height=100, scrollbars=yes, resizable=yes");
}

function reviewYes() {
	location.href="/SOS/ReviewList.re?mem_num=1";
}
function reviewNo() {
	location.href="exit.jsp";
}