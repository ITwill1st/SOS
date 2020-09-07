<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function purchase() {
	window.open("reviewCheck.jsp","리뷰확인","width=270,height=100, scrollbars=yes, resizable=yes");
}

function reviewYes() {
	location.href="/SOS/ReviewList.re?mem_num=1";
}
function reviewNo() {
	location.href="exit.jsp";
}
</script>
</head>
<body>
<h1>리뷰 임시 페이지</h1>
<input type="button" value="리뷰하기"  onclick="purchase()">
</body>
</html>