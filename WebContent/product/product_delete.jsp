<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	int item_num = Integer.parseInt(request.getParameter("item_num"));
// 	int board_num=(Integer)request.getAttribute("board_num");
String nowPage = request.getParameter("page");
//     String nowPage = (String)request.getAttribute("page");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE</title>
<link rel="stylesheet" href="product/style/product.css">

</head>
<body>
	<section id="passForm">
<!-- 	       상품삭제 -->
		<form
			action="ProductDeletePro.po?item_num=<%=item_num%>&page=<%=nowPage%>"
			method="post">

			<table>

				<tr>
					<td><input type="submit" value="삭제" /> &nbsp;&nbsp; <input
						type="button" value="돌아가기" onClick="javascript:history.go(-1)" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>