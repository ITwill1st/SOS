<%@page import="vo.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="review/style/review.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/SOS/styles/bootstrap-4.1.2/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/main_styles.css">
<link rel="stylesheet" type="text/css" href="/SOS/order/style/order.css">
<link rel="stylesheet" type="text/css" href="/SOS/styles/responsive.css">
<link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/a372e2fae4.js" crossorigin="a372e2fae4"></script>
<script src="/SOS/js/jquery.js"></script>
<%
ArrayList<ReviewDTO> reviewList = (ArrayList<ReviewDTO>)request.getAttribute("reviewList");
%>
</head>
<body>
<div class="container">
			<div class="row">
				<div class="col">
					<div class="themenu_title_bar_container">
						<div class="themenu_stars text-center page_subtitle">Silent Order</div>
						<div class="themenu_rating text-center">
							<div class="rating_r rating_r_5"><i></i><i></i><i></i><i></i><i></i></div>
						</div>
						<div class="themenu_title_bar d-flex flex-column align-items-center justify-content-center">
							<div class="themenu_title">Review</div>
						</div>
					</div>
				</div>
			
			</div>
		</div>
<div id="reviewMain">
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
		<textarea rows="3" cols="30" name="review_re_comment" style="border: 1px solid;" 		
		placeholder="사장님 댓글을 입력해주세요." id="re_comment<%=review.getReview_comment_num()%>">
		</textarea><br>
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
			},300);
	});
</script>
		<%
}
%>
</div>
<div style="clear: both;"></div>
<jsp:include page="../inc/bottom.jsp"/>
</body>
</html>