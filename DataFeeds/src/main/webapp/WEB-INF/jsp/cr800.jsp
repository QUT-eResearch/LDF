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
	
	<title>cr800</title>

</head>
<body>

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
  <option value="Temperature">Temperature</option>
  <option value="Level">Level</option>
  <option value="Velocity1">Velocity1</option>
  <option value="Velocity2">Velocity2</option>
  <option value="Velocity_X">Velocity_X</option>
  <option value="Flow">Flow</option>
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
<style type="text/css">
#chartSelect{
   background: transparent;
   width: 268px;
   padding: 5px;
   font-size: 16px;
   line-height: 1;
   border: 1;
   border-radius: 0;
   height: 34px;
   -webkit-appearance: none;
}

#menu { text-align:center;
margin:20px;
 }
 
.chartBox{
 	text-align:center;
 	color:#ff0000;
 }

</style>
</style>
<script type="text/javascript"> 
$(document).ready(function() {
	
	/*
	var file1 = "../../static/slow_core_2.dat";
	var file2 = "../../static/slow_met_2.dat";
	var file3 = "../../static/argonaut-u.csv";
	*/
	//getData(file3);
	loadCharts("Temperature");
	$('.chartBoxName').html("Temperature");
	
	$('#chartSelect').on('change', function() {
		loadCharts( this.value ); // or $(this).val()
		$('.chartBoxName').html(this.value);
		});
	
});	
function loadCharts(value){
	
	$.post("/DataFeeds/feed/loadFile/bean",
			{ 	field:  value,
		  		fieldName:  value},
				function(data){
					generateChart(data);
			});
	
}
function getData(file){	

	$.when(
		   $.ajax({
			url: file, 
			cache: false,
			dataType: "text", 
			error: function (xhr, ajaxOptions, thrownError) {
				
		        	console.log("CORE Message: ",xhr.status," ", thrownError);
				}
			})
		   ).done(function(core) {

			   processData(core);  
			   }
		   );
		   
}

function processData(core) { 

	try {
		var array = csv2array(core);

	}catch (exception) {
		console.log("csv2array-CORE Message: ", exception);
		return;
	}
	
	generateChart(array);
}

	
function generateChart(data){
	//console.log(tempData);
/*
	var tempData = "";
	
	var tempData = "[", i;
	for(i=array.length-50;i<=array.length-1;i++){
		//var time = convertUTC(array[i][0]);
		console.log(array[i][5],array[i][6]);
		var time = dateTimeToUTC(array[i][0],array[i][1]);
		
		var value = array[i][5];
	  tempData = tempData + "[" + time+ ", "+ value +"]";
	  if(i<=array.length-2){tempData = tempData + ",";}
	}
	tempData = tempData + "]";
*/
	
	var parseMe = "{\"color\":\"#ff0000\",\"label\":\""+data.name+"\" , \"data\":" + data.value + "}";
	//console.log(parseMe);
	var chartData = $.parseJSON(parseMe);
	
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
							timeformat: "%H:%M",
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