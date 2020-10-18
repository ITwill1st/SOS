<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

//qr코드로 받아오는 table_num
int table_num = (int)session.getAttribute("table_num");



HttpSession session2= request.getSession(); 
session.setAttribute("table_num", table_num);
%>
<!DOCTYPE HTML>

<html>
	<head>
		<title>Silent OrderSystem</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	</head>
	<body class="is-preload">
		<div id="wrapper">
			<div id="bg"></div>
			<div id="overlay"></div>
			<div id="main">

				<!-- Header -->
					<header id="header">
						<h1>Silent Order System</h1>
						<p>Feel safe to eat our delicious food &nbsp;&bull;&nbsp; share a special experience &nbsp;&bull;&nbsp; spend time together</p>
						<nav>
							<ul>
								<li><a href="/SOS/MemberOrder.or?table_num=<%=table_num %>" class="icon brands fa-twitter">회원<span class="label"></span></a></li>
								<li><a href="/SOS/NonMemberOrder.or?table_num=<%=table_num %>" class="icon brands fa-facebook-f">비회원<span class="label"></span></a></li>
							</ul>
						</nav>
					</header>

				<!-- Footer -->
					<footer id="footer">
						<span class="copyright">&copy; Untitled. Design: <a href="http://html5up.net">HTML5 UP</a>.</span>
					</footer>

			</div>
		</div>
		<script>
			window.onload = function() { document.body.classList.remove('is-preload'); }
			window.ontouchmove = function() { return false; }
			window.onorientationchange = function() { document.body.scrollTop = 0; }
		</script>
	</body>
</html>