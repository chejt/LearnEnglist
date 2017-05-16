<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*,org.english.form.*" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>英语学习软件</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Trade Zone Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--fonts-->
<link href="//fonts.googleapis.com/css?family=Noto+Sans:400,700" rel="stylesheet">
<link href="//fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
<link href="resource/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/pageView.js"></script>
    <script type="text/javascript" src="js/word_list.js"></script>
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/word_detail.js"></script>
<!--//fonts-->
</head>
<body>
<!--Header-->
	<div class="header" id="home">
		<!--Top-Bar-->
				<div class="top-bar">
				<div class="container-fluid">
					<div class="header-nav">
						<nav class="navbar navbar-default">
							<!-- Brand and toggle get grouped for better mobile display -->
							<div class="navbar-header">
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
									<span class="sr-only">Toggle navigation</span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
									<span class="icon-bar"></span>
								</button>
								<h1><a class="navbar-brand" href="word_list.jsp">英语 <span>学习</span></a></h1>
							</div>
							<!-- Collect the nav links, forms, and other content for toggling -->
							<div class="collapse navbar-collapse nav-wil" id="bs-example-navbar-collapse-1">
								<nav class="cl-effect-15" id="cl-effect-15">
								<ul>
									<!-- <li><a href="word_list.jsp">单词列表</a></li>
									<li><a href="student_word_list.jsp">单词本</a></li>
									<li><a href="student_word_detail.jsp">开始学习</a></li>
									<li><a href="student_word_test.jsp">开始测试</a></li>
									<li><a href="article_list.jsp">图文下载</a></li> -->
								</ul>
								</nav>
							</div>
							<div class="agileits-contact-info text-right">
							<ul>
								<li><span class="glyphicon glyphicon-earphone" aria-hidden="true"></span></li>
							</ul>
						</div>
						</nav>
					</div>
			</div>
			
			<div class="container">
		<!-- <select id="wordType" name="wordType">
				<option value="0">--选择分类 --</option>
				<option value="1">--四级 --</option>
				<option value="2">--六级 --</option>
				<option value="3">--雅思--</option>
		</select>
		<input id="wordName" name="wordName" style="width: 100px" type="text"
			placeholder="单词名称" />
		<input type="submit" class="btn btn-info btn-mini" value="查询"
			onclick="searchWord()"> -->
		
					<div class="bdata-infoitem-table centerData noheight">
					</div>
       <div class="mainlist_page"></div>
			<input type="hidden" id="currentpage" />
	<input type="hidden" id="totalpage" />
    </div> <!-- /container -->
			<div class="copy">
		        <p>Â© 2017 Trade Zone . All Rights Reserved | Design by</p>
		    </div>
		</div>
<!--/footer -->
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<!--light-box-files -->
<script src="js/jquery.chocolat.js"></script>
<!--//light-box-files -->
		<script type="text/javascript">
		$(function() {
			$('.gallery a').Chocolat();
		});
		</script>
<!-- //js -->
<script src="js/responsiveslides.min.js"></script>
		<script>
				$(function () {
					$("#slider").responsiveSlides({
						auto: true,
						pager: true,
						nav: true,
						speed: 1000,
						namespace: "callbacks",
						before: function () {
							$('.events').append("<li>before event fired.</li>");
						},
						after: function () {
							$('.events').append("<li>after event fired.</li>");
						}
					});
				});
			</script>
		<!--search-bar-->
		<script src="js/main.js"></script>	
	<a href="#home" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!-- //smooth scrolling -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
</body>
</html>