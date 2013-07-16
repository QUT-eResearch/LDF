$(document).ready(function() {
	
	
	var file1 = "../../static/slow_core_2.dat";
	var file2 = "../../static/slow_met_2.dat";
	
	getData(file1, file2, null);
	
});	

function getData(core, met, speedGauge){	

	$.when(
	   $.ajax({
		url: met, 
		cache: false,
		dataType: "text", 
		error: function (xhr, ajaxOptions, thrownError) {
			
	        	console.log("MET Message: ",xhr.status," ", thrownError);
			}
		}),
	   $.ajax({
		url: core, 
		cache: false,
		dataType: "text",
		error: function (xhr, ajaxOptions, thrownError) {
			
	        	console.log("CORE Message: ",xhr.status," ", thrownError);
			}
		})
	   ).done(function(met, core) {
		   processData(core[0]);
		   processMet(met[0], speedGauge);
		   
	   });
	   
	 
}
	
function processData(core) { 
	// convert data to array
	//console.log(core[0]);
	try {
		var array = csv2array(core);

	}catch (exception) {
		console.log("csv2array-CORE Message: ", exception);
		return;
	}
	
	// convert the array back to a string
	var arraySize = array.length;
	/*
	var arrayStr = "";
	for (row = 0; row < array.length; row++) {
	for (col = 0; col < array[row].length; col++) {
	if (col > 0) {
	arrayStr += ", ";
	}
	arrayStr += array[row][col];
	}
	arrayStr += "\n";
	}
	*/
	/*
	for (row = 1; row < 2; row++) {
	for (col = 0; col < array[row].length; col++) {
	if (col > 0) {
	}
		eval('var '+array[row][col]+'="'+array[arraySize-1][col]+'"');
		console.log('var '+array[row][col]+'="'+array[arraySize-1][col]+'"');
		}
	}
	*/
		$('.todayDate').html(array[arraySize-1][0]);
		$('.outsideTemp').html(roundValue(array[arraySize-1][19],100));
		$('.ruler.Temp').attr('val', array[arraySize-1][19]);
		$('.outsideHum').html(roundValue(array[arraySize-1][20] * 100,100));
		$('.ruler.Hum').attr('val', array[arraySize-1][20] * 100);
		$('.rain').html(array[arraySize-1][23]);
		$('.ruler.Rain').attr('val', array[arraySize-1][23]);
		$('.barometer').html(roundValue(array[arraySize-1][14]*10,100));
		$('.ruler.Barom').attr('val', array[arraySize-1][14]*10);
		
		/*
		$('#windSpeed').html(array[arraySize-1][14]);
		//Move Gauges to their correspondante percentage
		g2.move(array[arraySize-1][14])/3, true);
		$('#windDirection').html(array[arraySize-1][14]);
		if(today[0].wdr[i].windDirection.localeCompare('')!=0){
			g.move(convertDir(array[arraySize-1][14], true);
		}
		*/
		//Redraw ruler with new values

		tempChart(array);
		ruler();
}

function processMet(met, speedGauge) { 
	// convert data to array
	try {
		var array = csv2array(met);

	}catch (exception) {
		console.log("csv2array-met Message: ",exception);
		return;
	}
	// convert the array back to a string
		var arraySize = array.length;

	$('#windSpeed').html(roundValue(array[arraySize-1][5],100));
	//Move Gauges to their correspondante percentage
	speedGauge.move((array[arraySize-1][5]*180)/30, true);
	//speedGauge.move(array[arraySize-1][5]*10/3, true);
	/*
	$('#windDirection').html(array[arraySize-1][14]);
	if(today[0].wdr[i].windDirection.localeCompare('')!=0){
		g.move(convertDir(array[arraySize-1][14], true);
	}
	*/
ruler();
}

function tempData(){

var data = "[", i;

for(i=0;i<=10;i++){
  data = data + "{y: '"+ i +"', a: "+i*100+"}";
  if (i<=9){data = data + ",";}
}
data = data + "]";
return data;
}

function tempChart(array){

var arraySize = array.length;
var tempData = "[", i;
for(i=array.length-50;i<=array.length-1;i++){
  tempData = tempData + "[" + convertUTC(array[i][0])+ ", "+array[i][19]+"]";
  if(i<=array.length-2){tempData = tempData + ",";}
}
tempData = tempData + "]";

var d2n = "{\"color\":\"#ff0000\",\"data\":" + tempData + "}";
eval('var d2='+d2n);
//console.log(d2);
	$(function () {

		$.plot($("#tempChart"), [ d2 ], {
					xaxis: {
					mode: "time",
					timeformat: "%H:%M",
					min: convertUTC(array[arraySize-50][0]),
					max: convertUTC(array[arraySize-1][0])
				}
		}
		);
	});
	
var barData = "[", i;
for(i=array.length-50;i<=array.length-1;i++){
  barData = barData + "[" + convertUTC(array[i][0])+ ", "+array[i][14]*10+"]";
  if(i<=array.length-2){barData = barData + ",";}
}
barData = barData + "]";

var d2n = "{\"color\":\"#ff0000\",\"data\":" + barData + "}";
eval('var b2='+d2n);
//console.log(b2);

	$(function () {
		
		$.plot($("#barChart"), [ b2 ], {
					xaxis: {
					mode: "time",
					timeformat: "%H:%M",
					min: convertUTC(array[arraySize-50][0]),
					max: convertUTC(array[arraySize-1][0])
				}
		}
		);
	});

}

function convertDir(dir)
{
	var a = "-";
	dir = $.trim(dir);
		switch(dir){
			case"N":a=25;break;case"NNE":a=31.25;break;case"NE":a=37.5;break;case"ENE":a=43.75;break;case"E":a=50;break;case"ESE":a=56.25;break;case"SE":a=62.5;break;case"SSE":a=68.75;break;case"S":a=75;break;case"SSW":a=81.25;break;case"SW":a=87.5;break;case"WSW":a=93.75;break;case"W":a=0;break;case"WNW":a=6.25;break;case"NW":a=12.5;break;case"NNW":a=18.75;break;
		}

	return a;
}

function calculateStormRain(today,yesterday){
	var stormRain = 0;
	var stormCount = 0;
	var storm = 0;
	var tR = today.wdrRecords-3;
	var yR = yesterday.wdrRecords-3;
	var hour24 = 60/today.wdr[tR].archiveInterval*24;
	
	var stormStore = [];
	var j=0;
	
	for(i=1;i<=tR;i++){
	j++;
		stormStore[j] = today.wdr[i].rain;
	}
	for(i=1;i<=yR;i++){
	j++;
		stormStore[j] = yesterday.wdr[i].rain;
	}
	for(j=0;j<stormStore.length;j++){
		if(stormStore[j] > 0.05){
			storm += parseFloat(stormStore[j]);
			stormCount++;
		}else{
			storm = 0;
			stormCount = 0;
		}
	}
	
	if(stormCount > (hour24) ){
		return storm;
	}	
	return storm;
}

//utilities
Date.prototype.toUTCArray= function(){
    var D= this;
    return [D.getUTCFullYear(), D.getUTCMonth(), D.getUTCDate(), D.getUTCHours(),
    D.getUTCMinutes(), D.getUTCSeconds()];
}

function convertUTC(input){
  var parts = input.match(/(\d+)/g);
  // new Date(year, month [, date [, hours[, minutes[, seconds[, ms]]]]])
  var date = Date.UTC(parts[0], parts[1]-1, parts[2], parts[3], parts[4]); // months are 0-based
  return date;
  //return Date.UTC(date);
}

Number.prototype.roundTo = function(num) {
    var resto = this%num;
    if (resto <= (num/2)) { 
        return this-resto;
    } else {
        return this+num-resto;
    }
}
function roundValue(val,round){

	return Math.round(val*round)/round;
}
//Views
function changeView(currentPage, item){
	$(".temp, .wind, .charts, .rainS").removeClass('active').addClass('inactive');
	$(item).removeClass('inactive').addClass('active');
	$(".tempPanel, .windPanel, .chartsPanel, .rainPanel").removeClass('on').addClass('off');
    $(currentPage).removeClass('off').addClass('on');
}
function ruler(){
$(function() {
    // Build "dynamic" rulers by adding items
    $(".ruler[data-items]").each(function() {
        var ruler = $(this).empty(),
            len = Number(ruler.attr("data-items")) || 0,
            item = $(document.createElement("li")),
            i;
			var h = ruler.attr("height-item");
			var val = parseInt(ruler.attr("val"));
			
			var intItem = parseInt(ruler.attr("int-item"));
			var min = parseInt(ruler.attr("min"));
			var max =  parseInt( ruler.attr("max"));
			var factor = parseInt( ruler.attr("factor"));
        for (i = max; i >= min; i--) {		
			var itemText = 0;
			if(i % intItem == 0){
				itemText = (i + len) - max;
				if(i==val){
					ruler.append(item.clone().html("<span class='grade'>"+i*factor+"</span><span class='itemBorder fifth'/><span class='valueBar'><img src='images/triangleLeft.png'/></span>").css('height',h));
				}else{
				if(i==parseInt(val/factor)){
					ruler.append(item.clone().html("<span class='grade'>"+i*factor+"</span><span class='itemBorder fifth'/><span class='valueBar'><img src='images/triangleLeft.png'/></span>").css('height',h));
				}else{
					ruler.append(item.clone().html("<span class='grade'>"+i*factor+"</span><span class='itemBorder fifth'/>").css('height',h));
					}
				}
			}else{
				if(i==parseInt(val/factor)){
					ruler.append(item.clone().html("<span class='grade'/><span class='itemBorder'/><span class='valueBar'><img src='images/triangleLeft.png'/></span>").css('height',h));
				}
				else{
					ruler.append(item.clone().html("<span class='grade'/><span class='itemBorder'/>").css('height',h));
				}
			}
        }
    });
    // Change the spacing Not Used
    function changeRulerSpacing() {
        $(".ruler").
          find("li").
            css("height", this.attr("height-item"));
    }
});
}