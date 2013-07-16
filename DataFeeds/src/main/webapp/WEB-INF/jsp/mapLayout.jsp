<%@ page language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${contextPath}/js/lib/jquery.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="${contextPath}/js/lib/excanvas.min.js"></script><![endif]-->
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>-->
<script language="javascript" type="text/javascript" src="${contextPath}/js/lib/jquery.flot.js"></script>
<script language="javascript" type="text/javascript" src="${contextPath}/js/lib/jquery.flot.resize.js"></script>
<script language="javascript" type="text/javascript" src="${contextPath}/js/lib/jquery.flot.time.js"></script>
<link type="text/css" href="${contextPath}/css/home.css" rel="stylesheet">
<link type="text/css" href="${contextPath}/css/map.css" rel="stylesheet">

	<link rel="stylesheet" href="${contextPath}/css/jquery-ui.css">
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.core.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.widget.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.mouse.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.draggable.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.position.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.resizable.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.button.min.js"></script>
	<script src="${contextPath}/js/jQuery-UI/jquery.ui.dialog.min.js"></script>
	<!--removed demo css-->

    <script language="javascript" type="text/javascript" src="${contextPath}/js/lib/jquery.flot.js"></script>
    <script language="javascript" type="text/javascript" src="${contextPath}/js/lib/jquery.flot.resize.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/js/lib/jquery.flot.time.js"></script>
	
	<script type="text/javascript" src="${contextPath}/js/csvChart.js"></script>
	<script type="text/javascript" src="${contextPath}/js/image.js"></script>
    <link rel="stylesheet" href="${contextPath}/css/csvChart.css">
     
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<script>
var CONTEXTPATH = '<c:out value="${contextPath}"/>';
</script>
<div class="main">
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="content">
		<tiles:insertAttribute name="body" />

	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
		</div>
</div>
</body>
</html>