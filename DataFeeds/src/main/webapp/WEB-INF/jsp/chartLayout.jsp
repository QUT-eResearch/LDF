<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/DataFeeds/js/lib/jquery.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/excanvas.min.js"></script><![endif]-->
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>-->
<script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.js"></script>
<script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.resize.js"></script>
<script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.time.js"></script>
<script language="javascript" type="text/javascript" src="/DataFeeds/js/chart.js"></script>
<link type="text/css" href="/DataFeeds/css/styles.css" rel="stylesheet"> 
<link type="text/css" href="/DataFeeds/css/chart.css" rel="stylesheet">

<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<div class="header">
	<tiles:insertAttribute name="header" />
</div>
<div class="chartContent">
	<tiles:insertAttribute name="body" />
</div>
<div class="footer">
	<tiles:insertAttribute name="footer" />
</div>
</body>
</html>