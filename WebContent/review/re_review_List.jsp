<%@page import="vo.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/SOS/js/jquery.js"></script>
<%
ArrayList<ReviewDTO> reviewList = (ArrayList<ReviewDTO>)request.getAttribute("reviewList");
%>
</head>
<body>
<script type="text/javascript">
// $(document).ready(function() {		
// 	$.ajax({
// 		url: "/SOS/AJAXRE_ReviewList.re",
// 		type:"POST",
// 		dataType: "json",
// 		success: function(data) {
// 			alert("aaa");
// 		},
// 		error: function(data) {
// 			console.log("에러!");
// 		}
// 	});
// 	$.getJSON("/SOS/AJAXRE_ReviewList.re", function(rdata) {
// 		alert("aaa2")
// 		$.each(rdata, function(index, item) {
// 			alert("aaa3")
// 			$('#div').html("aaa"+item_num);
// 		});
// 	});
// 	console(log);
// });
</script>
<h1>댓글 모아보기</h1>
<%for(ReviewDTO review : reviewList){%>
<script type="text/javascript">
$(document).ready(function() {	
	var star = "";
		for(var x = 0 ; x < <%=review.getReview_rating()%> ; x++) {
			star += "★";
		}
	$("#starRating_<%=review.getReview_comment_num()%>").html(star);

});
</script>
<div id="div">
	메뉴이름 <%=review.getItem_name()%> 평점 : <div id="starRating_<%=review.getReview_comment_num()%>"></div> <%=review.getReview_datetime() %><br>
	<%=review.getReview_comment()%><br>
</div>
<div>
		<textarea rows="3" cols="30" name="review_re_comment" placeholder="사장님 댓글을 입력해주세요." id="re_comment<%=review.getReview_comment_num()%>"></textarea><br>
		<input type="submit" value="등록" id="btnSubmit<%=review.getReview_comment_num()%>">
</div>
<script type="text/javascript">
	$("#btnSubmit<%=review.getReview_comment_num()%>").click(function() {
		
// 		btnSubmit 버튼 클릭시 ajax 작동
		var comment = document.getElementById("re_comment<%=review.getReview_comment_num()%>").value;
		// textarea에 있는 텍스트 가져오기
		$.ajax({
			url: "/SOS/RE_ReviewWritePro.re?review_comment_num=<%=review.getReview_comment_num()%>",
				//url 접근
			type:'GET',
			dataType: 'json',
				//ajax 비동기 방식으로 데이터를 넘겨줄때  json으로 넘겨줌 => action에서 getParameter로 가져 올 수 있음.
			data :{"review_re_comment" : comment},
			    // {키, 값} 형태로 데이토 전송 
			success: function(data) {
				
			},
			error: function(data) {
				console.log("에러!");
			}			
		});
		setTimeout(function(){
			location.reload();
			},500);
	});
</script>
		<%
}
%>
</body>
</html>