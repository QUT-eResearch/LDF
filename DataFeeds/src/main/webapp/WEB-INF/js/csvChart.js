
function loadChart(idProject, idFeed, idChart, name){
	
	var dataInfo = new Object();
    dataInfo.idProject = idProject;
    dataInfo.idFeed = idFeed;
    dataInfo.idChart = idChart;
    dataInfo.name = name;
	
	generateChartDiv(null, dataInfo);
	//drawChart(1, "Temperature", "T", projectId, feedId, chartId);
	//$('.'+feedId+'_chartBoxName').html("Temperature");
	
	/*$('#'+feedId+'_chartSelect').on('change', function() {
		
		var fieldName = $('#'+feedId+'_chartSelect option:selected').text();
		$('.'+feedId+'_chartBoxName').html(fieldName);
		var shortN = $('#'+feedId+'_chartSelect option:selected').attr("name");
		drawChart( this.value, fieldName, shortN, feedId ); // or $(this).val()
		});
	*/
}
function generateChartDiv(imageLocation, dataInfo){
	
	var new_div = document.createElement('div');
	document.body.appendChild(new_div);
	className = dataInfo.idProject+"_"+ dataInfo.idFeed+ "_"+ dataInfo.idChart + "_ChartWindow";
	new_div.className = className;
	new_div.setAttribute('id', className);
	//$("#"+className).html("<img src='"+CONTEXTPATH+imageLocation+"'/>");
	drawChart(1, dataInfo.name, " - ", dataInfo.idProject, dataInfo.idFeed, dataInfo.idChart, className);

	$( "#"+className ).dialog({
		title: dataInfo.name,
		width:'500',
		height:'300',
		resizable: true,
	        close: function(event, ui)
 	        {
 	        	$(this).dialog('close').remove();
 	        },
	});
	
}
/*
function loadCharts(value, name, shortN){
	
	$.post(CONTEXTPATH+"/feed/loadFile/convert",
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
/*
function drawChart(value, name, shortN, idProject, idFeed, idChart, className) {
	
	$.when(
			$.post(CONTEXTPATH+"/charts/convert/"+idProject+"/"+idFeed+"/"+idChart,
				{ 	field:  value,
			 		fieldName:  name,
				  	shortName: shortN
				})
		   ).done(function(data) {
		  			//console.log(data);
					generateChart(data, idFeed, className);
			   }
	);
}
*/

function drawChart(value, name, shortN, idProject, idFeed, idChart, className){	
	
	//Init
	$.when(
			$.post(CONTEXTPATH+"/charts/convert/"+idProject+"/"+idFeed+"/"+idChart,
				{ 	field:  value,
			 		fieldName:  name,
				  	shortName: shortN
				})
		   ).done(function(data) {
		  			//console.log(data);
					generateChart(data, idFeed, className);
			   }
	);
	
	//Set interval for next
	setInterval(function() {
		$.when(
				$.post(CONTEXTPATH+"/charts/convert/"+idProject+"/"+idFeed+"/"+idChart,
					{ 	field:  value,
				 		fieldName:  name,
					  	shortName: shortN
					})
			   ).done(function(data) {
			  			//console.log(data);
						generateChart(data, idFeed, className);
				   }
		);
	}, 200000); 
		
		   
};

	
function generateChart(data, feedId, className){
	
	var parseMe = "{\"color\":\"#ff0000\",\"label\":\""+data.shortName+"\" , \"data\":" + data.value + "}";
	
	var chartData = $.parseJSON(parseMe);
	//console.log(chartData);
		$(function () {
			var chart = $('#'+className);
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
		loadInteractive(className); 
		
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
function loadInteractive(className) {
	
	function showTooltip(x, y, contents) {
		$("<div id='"+className+"_tooltip'>" + contents + "</div>").css({
			position: "fixed",
			display: "none",
			top: y + 5,
			left: x + 5,
			border: "1px solid #fdd",
			padding: "2px",
			"background-color": "#fee",
			opacity: 0.80
		}).appendTo("."+className).fadeIn(200);
	}

	var previousPoint = null;
	$('#'+className).bind("plothover", function (event, pos, item) {
	
		if ($('#'+className+'_enablePosition:checked').length > 0) {
			var str = "(" + pos.x.toFixed(2) + ", " + pos.y.toFixed(2) + ")";

			$('#'+className+'_hoverdata').text(str);
		}
	
		//if ($("#enableTooltip:checked").length > 0) {
			if(1){
			if (item) {
				if (previousPoint != item.dataIndex) {
	
					previousPoint = item.dataIndex;
	
					$("#"+className+"_tooltip").remove();
					var x = item.datapoint[0].toFixed(2),
					y = item.datapoint[1].toFixed(2);
	
					showTooltip(item.pageX, item.pageY,
					    item.series.label + " = " + y);
				}
			} else {
				$("#"+className+"_tooltip").remove();
				previousPoint = null;            
			}
		}
	});

	$('#'+className+'_Chart').bind("plotclick", function (event, pos, item) {
		if (item) {
			$('#'+className+'_clickdata').text(" - click point " + item.dataIndex + " in " + item.series.label);
			plot.highlight(item.series, item.datapoint);
		}
	});

}