<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function yes() {
	opener.reviewYes();
	window.close();
}

function no() {
	opener.reviewNo();
	window.close();
}
</script>
</head>
<body>
<div>
<h3 style="margin: 0 0 10px 15%">리뷰를 작성하시겠습니까?</h3>
<div style="margin: 0 0 0 37%">
<input type="button" value="네" onclick="yes()"><input type="button" value="아니오" onclick="no()">
</div>
</div>
</body>
</html>