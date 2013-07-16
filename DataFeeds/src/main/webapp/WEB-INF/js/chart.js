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
function drawChart(value, name, shortN, projectId, feedId){	

	$.when(
			$.post("/DataFeeds/loadFile/convert/"+projectId+"/"+feedId",
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