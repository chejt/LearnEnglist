<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.*,java.util.*,org.english.form.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <!-- Bootstrap core CSS -->
    <link href="../resource/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/admin_update.js"></script>
</head>
<body>
<div class="container">
      <form class="form-signin">
        <h2 class="form-signin-heading">修改角色</h2>
        <label for="inputEmail" class="sr-only">姓名</label>
        <input type="text" id="name" class="form-control" placeholder="name" readonly value="${admin.name }" required autofocus>
        <label for="inputPassword" class="sr-only">角色</label>
        <select id="roleId" name="roleId" class="form-control">
				<option value="2">--管理员 --</option>
				<option value="3">--老师--</option>
		</select>
        <input type="hidden" id="adminId" name="adminId" value="${admin.id}">
         <button class="btn btn-lg btn-primary btn-block" type="button" onclick="update()">提交</button>
      </form>
    </div> <!-- /container -->
</body>
</html>