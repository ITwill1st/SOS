<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int item_num=Integer.parseInt(request.getParameter("item_num")) ;
// 	int board_num=(Integer)request.getAttribute("board_num");
	String nowPage = request.getParameter("page");
//     String nowPage = (String)request.getAttribute("page");
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>MVC 게시판</title>
<style>

	#passForm{
		width:400px;
		margin:auto;
		border : 1px solid orange;
	}
	
</style>
</head>
<body>
<section id = "passForm">
<form action="ProductDeletePro.po?item_num=<%=item_num %>&page=<%=nowPage %>" method="post">
<%-- 			<input type="hidden" name="board_num" value="<%=board_num %>" /> --%>
<%-- 			<input type="hidden" name="page" value="<%=nowPage %>" /> --%>
			
			<table>

<tr>
	<td>
		<input type="submit" value = "삭제"/>
		&nbsp;&nbsp;
		<input type = "button" value = "돌아가기" onClick ="javascript:history.go(-1)"/>
	</td>
</tr>
</table>
</form>
</section>
</body>
</html>