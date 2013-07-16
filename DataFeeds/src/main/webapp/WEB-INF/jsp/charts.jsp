<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!-- <script type="text/javascript" src="/DataFeeds/js/jquery/jquery-1.4.4.min.js"></script>-->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
	<!-- <script type="text/javascript">
	
	    var jq = jQuery.noConflict();
	</script>
	-->
	  <script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.min.js"></script>
  <script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.resize.min.js"></script>


	<title>Charts</title>

</head>
<body>
<h2></h2>
<div style="width:90%">
<div class="chartsPanel off">
	<div class="left">
		<div class="chartBox">
			OUTSIDE TEMPERATURE (<sup>o</sup>C)
			<div><span class="yesterdayDate"></span> - <span class="todayDate"></span></div>
		</div>
		<div class="chart">
		<div id="tempChart" style="width:100%;height:300px;"></div>
		</div>
	</div>
	<div class="clearright">
		<div class="chartBox">
		BAROMETER (hPa)
		<div><span class="yesterdayDate"></span> - <span class="todayDate"></span></div>
		</div>
		<div class="chart ">
		<div id="barChart" style="width:100%;height:300px;">></div>
		</div>
	</div>
</div>					

</body>
</html>
					