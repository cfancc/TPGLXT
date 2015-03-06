<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>投票管理系统</title>
		<!-- 样式表 -->
		<link rel="stylesheet"
			href="ext/resources/ext-theme-neptune/ext-theme-neptune-all.css">
		<!-- 入口script -->
		<script src="ext/bootstrap.js"></script>
		<script src="ext/locale/ext-lang-zh_CN.js"></script>
		<script src="login.js"></script>
		<script src="app/view/register.js"></script>
	</head>
	<body>
	</body>
</html>
