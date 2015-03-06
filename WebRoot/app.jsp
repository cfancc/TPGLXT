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
		<script src="app/Application.js"></script>
	</head>
	<body>
	<input type="hidden" name="xm" id="xm" value='<%=request.getSession().getAttribute("xm")%>'>
	<input type="hidden" name="ryid" id="ryid" value='<%=request.getSession().getAttribute("ryid")%>'> 
	<input type="hidden" name="rylb" id="rylb" value='<%=request.getSession().getAttribute("rylb")%>'>
	<input type="hidden" name="sftp" id="sftp" value='<%=request.getSession().getAttribute("sftp")%>'>
	</body>
</html>
