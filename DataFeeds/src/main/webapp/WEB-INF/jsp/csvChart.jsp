<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	 <script type="text/javascript" src="/DataFeeds/js/lib/jquery.js"></script>
	 <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/excanvas.min.js"></script><![endif]-->
	<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>-->
	<script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.js"></script>
    <script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.resize.js"></script>
	<script language="javascript" type="text/javascript" src="/DataFeeds/js/lib/jquery.flot.time.js"></script>
	<script type="text/javascript" src="/DataFeeds/js/lib/csv2array.js"></script>
	<script type="text/javascript" src="/DataFeeds/js/lib/csvChart.js"></script>
    <link rel="stylesheet" href="/DataFeeds/css/csvCharts.css">
	
	<title>cr800</title>

</head>
<body>
<div class="chartDisplay">
	<h3>Argonaut</h3>
	<div style="width:90%">
	<div id="chartsPanel">
		<div class="center">
			<div class="chartBox">
				<h2 class="chartBoxName"></h2>
			</div>
			<div class="chart">
			<div id="Chart" style="width:100%;height:300px;"></div>
			</div>
		</div>
	<div id="menu">
	 <select id="chartSelect">
	  <option name="T" value="3">Temperature</option>
	  <option name="L" value="4">Level</option>
	  <option name="V1" value="5">Velocity1</option>
	  <option name="V2" value="6">Velocity2</option>
	  <option name="Vx" value="7">Velocity_X</option>
	  <option name="F" value="8">Flow</option>
	  <option name="F" value="9">Flow</option>
	  <option name="F" value="10">Flow</option>
	  <option name="F" value="11">Flow</option>
	  <option name="F" value="12">Flow</option>
	</select>
	</div>
	<!--
		<p>
			<label><input id="enablePosition" type="checkbox"></input>Show mouse position</label>
			<span id="hoverdata"></span>
			<span id="clickdata"></span>
		</p>
		<p>
			<label><input id="enableTooltip" type="checkbox"></input>Enable tooltip</label>
		</p>
	-->
</div>
</div>
<script type="text/javascript">
$(document).ready(function() {

	drawChart(1, "Temperature", "T");
	$('.chartBoxName').html("Temperature");
	
	$('#chartSelect').on('change', function() {
		
		var fieldName = $("#chartSelect option:selected").text();
		$('.chartBoxName').html(fieldName);
		var shortN = $("#chartSelect option:selected").attr("name");
		drawChart( this.value, fieldName, shortN ); // or $(this).val()
		});
	
});	
/*
function loadCharts(value, name, shortN){
	
	$.post("/DataFeeds/feed/loadFile/convert",
			{ 	field:  value,
		  		fieldName:  name,
		  		shortName: shortN},
				function(data){
		  			//console.log(data);
					generateChart(data);
			});
	
}
*/
//used	
function drawChart(value, name, shortN){	

	$.when(
			$.post("/DataFeeds/feed/loadFile/convert",
				{ 	field:  value,
			 		fieldName:  name,
				  	shortName: shortN
				})
		   ).done(function(data) {
		  			//console.log(data);
					generateChart(data);
			   }
	);
		   
}
	
function generateChart(data){
	
	var parseMe = "{\"color\":\"#ff0000\",\"label\":\""+data.shortName+"\" , \"data\":" + data.value + "}";
	
	var chartData = $.parseJSON(parseMe);
	//console.log(chartData);
		$(function () {
			var chart = $("#Chart");
			var plot = $.plot(chart, [ chartData ], {
						series: {
							lines: {
								show: true
							},
							points: {
								how: true
							}
						},
						grid: {
							hoverable: true,
							clickable: true
						},
						xaxis: {
							mode: "time",
							timeformat: "%y %d %H:%M",
							min: data.xMin,
							max: data.xMax,
							/*tickSize: [1, "hour"]*/ 
						}

					}
			
			);
			
		});
		
}
function convertUTC(input){
	  var parts = input.match(/(\d+)/g);
	  // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
	  var date = Date.UTC(parts[0], parts[1]-1, parts[2], parts[3], parts[4]); // months are 0-based
	  return date;
	  //return Date.UTC(date);
}
function dateTimeToUTC(date,time){
	  var dateParts = input.match(/(\d+)/g);
	  // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
	  var timeParts = input.match(/(\d+)/g);
	  console.log(dateParts, timeParts);
	  var date = Date.UTC(parts[0], parts[1]-1, parts[2], parts[3], parts[4]); // months are 0-based
	  return date;
	  //return Date.UTC(date);
}
$(function() {
	
	function showTooltip(x, y, contents) {
		$("<div id='tooltip'>" + contents + "</div>").css({
			position: "absolute",
			display: "none",
			top: y + 5,
			left: x + 5,
			border: "1px solid #fdd",
			padding: "2px",
			"background-color": "#fee",
			opacity: 0.80
		}).appendTo("body").fadeIn(200);
	}

	var previousPoint = null;
	$("#Chart").bind("plothover", function (event, pos, item) {
	
		if ($("#enablePosition:checked").length > 0) {
			var str = "(" + pos.x.toFixed(2) + ", " + pos.y.toFixed(2) + ")";

			$("#hoverdata").text(str);
		}
	
		//if ($("#enableTooltip:checked").length > 0) {
			if(1){
			if (item) {
				if (previousPoint != item.dataIndex) {
	
					previousPoint = item.dataIndex;
	
					$("#tooltip").remove();
					var x = item.datapoint[0].toFixed(2),
					y = item.datapoint[1].toFixed(2);
	
					showTooltip(item.pageX, item.pageY,
					    item.series.label + " = " + y);
				}
			} else {
				$("#tooltip").remove();
				previousPoint = null;            
			}
		}
	});

	$("#Chart").bind("plotclick", function (event, pos, item) {
		if (item) {
			$("#clickdata").text(" - click point " + item.dataIndex + " in " + item.series.label);
			plot.highlight(item.series, item.datapoint);
		}
	});

});
</script>
</body>
</html>