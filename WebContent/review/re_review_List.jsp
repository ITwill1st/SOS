<%@page import="vo.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="review/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {	
	alert("aaa");
	$.getJSON("review/JSONRE_reviewListInfo.jsp", function(data) {
		$.each(data,function(index, rdata) {
			alert("aaa2");
			$('#div').append(item_num);
		});
	});
	console(log);
});
</script>
<%
ArrayList<ReviewDTO> reviewList = (ArrayList<ReviewDTO>)request.getAttribute("reviewList");
%>
</head>
<body>
<h1>댓글 모아보기</h1>
<%for(ReviewDTO review : reviewList){%>
<div id="div">
	메뉴이름 <%=review.getItem_num()%> 평점 : <%=review.getReview_rating()%> <%=review.getReview_datetime() %><br>
	<%=review.getReview_comment()%><br>
</div>
<div>
<%-- 	<%if(review.getReview_re_checker() == 0){%> --%>
		<textarea rows="3" cols="30" name="review_re_comment" placeholder="사장님 댓글을 입력해주세요." id="re_comment<%=review.getReview_comment_num()%>"></textarea><br>
		<input type="submit" value="등록" id="btnSubmit<%=review.getReview_comment_num()%>">
<%-- 	<%}%> --%>
</div>
<script type="text/javascript">
	$('#btnSubmit<%=review.getReview_comment_num()%>').click(function() {
		// btnSubmit 버튼 클릭시 ajax 작동
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
				location.reload(true);
			},
			error: function(data) {
				console.log("에러!");
			}
			
		})
	});
</script>
		<%
}
%>
</body>
</html>