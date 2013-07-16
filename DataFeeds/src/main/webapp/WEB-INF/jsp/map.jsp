<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
var map;
var markersArray = [];

function initialize() {
  var mapOptions = {
    center: new google.maps.LatLng(-35.274398, 133.775136),
    zoom: 4,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    mapTypeControl: false,
    scrollwheel: false,
    rotateControl: false,
    panControl: false,
    streetViewControl: false,
    zoomControl: true,
    zoomControlOptions: {
      style: google.maps.ZoomControlStyle.SMALL,
      position: google.maps.ControlPosition.RIGHT_TOP
    },
    overviewMapControl:false
  };
  map = new google.maps.Map(document.getElementById("map_canvas"),
      mapOptions);

  setFeeds();

}

function setFeeds(){
	$.when(
			$.post(CONTEXTPATH+"/map/loadProjects"
				)
		   ).done(function(data) {
			   $.each(data, function(i, data) {
				   markersArray.push(data);   
				  	});
			   listen();
			   }
	);
	
}
	
function listen(){
	var infowindow = new google.maps.InfoWindow();
	var marker, i;

	$.each(markersArray, function(i, markersArray) {
		//console.log(i, markersArray.name);
        marker = new google.maps.Marker({
        position: new google.maps.LatLng(markersArray.latitude, markersArray.longitude),
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
        	//showFeed(markersArray.feedId);
        	showFeedInfo(markersArray, map, marker);
          //infowindow.setContent(markersArray.name);
          //infowindow.open(map, marker);
        }
      })(marker, i));
    }); 

}	
	
function showFeed(id){
	$.when(
			$.post(CONTEXTPATH+"/map/loadProject?idProject="+id
				)
		   ).done(function(data) {
			   $('.feedInfo').css('display','block');
			   $.each(data, function(i, data) {
					   $(".feedDetails").html("<li> Feed Name:"+data.name + "<br/><!--<a href='/DataFeeds/charts/csvChart' >csvChart</a>--></li>");
				  	});

			   }
	);
}	

function showFeedInfo(markerArray, map, marker){
	///TO DO: Change Feed to ProjectFeeds to allow ProjectFeeds 0 to many feeds
	$.when(
			$.post(CONTEXTPATH+"/map/loadProject?idProject="+markerArray.idProject /*To obtain list of feeds it has*/
				)
		   ).done(function(data) {
				var infowindow = new google.maps.InfoWindow();
			   $('.feedInfo').css('display','block');
			   var content = "<div id='"+markerArray.idProject+"infoWindowContent'>";
			   content = content + "<p class='center title'><strong>" + markerArray.name + "</strong></p><ul class='projectInfo'>";
			   $.each(data, function(i, data) {
				   //TO DO: List all feed types in this FEED (Project/marker)
				   content = content + "<li> ";
				   content = content + "<table id='"+markerArray.idProject+"_"+data.idFeed+"_feedElements' class='listfeedElements'><tr>";
				   content = content + "<td>"+data.name+" </td> ";
				   //content = content +"<a href='javascript:buildContentInfoWindow(";
				   
				   //content = content + "&quot;";
				   //content = content + data.idFeed +","+markerArray.idProject+","+data.type+","+data.name;
				   //content = content + "&quot;)'> open feed "+data.name+" </a>";
				   
				   content = content +"<td><a class='mapButton' href='javascript:buildContentInfoWindow("+markerArray.idProject+", &quot;"+markerArray.name+"&quot;, "+data.idFeed+",&quot;"+data.type+"&quot;,&quot;"+data.name+"&quot;)'";
				   content = content + "> open </a></td></tr>";
				   content = content + "</table>";
				  	});
			   content = content + "</ul></div>";
			   infowindow.setContent(content);//$(#"+id+"-window-content).clone()[0]);
		       infowindow.open(map, marker);
			   }
	);
}	
function buildContentInfoWindow(idProject, projectName, idFeed, type, name){

	//console.log(idFeed, idProject, type, name);
	$.when(
			$.post(CONTEXTPATH+"/map/loadFeeds/"+idProject+"/"+idFeed) /*To obtain list of elements (charts,gauges,meter, etc) it has*/
				
		   ).done(function(data) {
			   var new_div = document.createElement('div');
				document.body.appendChild(new_div);
				className = idProject+"_"+ idFeed+ "_feedWindow";
				new_div.className = className;
				new_div.setAttribute('id', className);
				 var content = "<p>"+projectName + " > " + name+"</p><ul class='feedElements'>";
			   
				$.each(data, function(i, data) {
					if(data.type.localeCompare('image')==0){
					 	content = content + "<li> <a href='javascript:loadImage("+idProject+","+data.idFeed+","+data.idChart+",&quot;"+data.shortName+"&quot;)'>  "+data.shortName+"  </a></li>";					
					 }
					 else{
						 content = content + "<li> <a href='javascript:loadChart("+idProject+","+data.idFeed+","+data.idChart+",&quot;"+data.shortName+"&quot;)'>  "+data.shortName+"  </a></li>";					
					 }
					//$("#"+className).append("<li> <a href='javascript:buildDialogForFeedElements("+idProject+","+data.idFeed+","+data.idChart+",&quot;"+data.name+"&quot;)'>  "+data.shortName+"  </a></li>");
					//$("#"+idProject+"_"+idFeed+"_feedElements").append("<li> <a href='javascript:buildDialogForFeedElements("+idProject+","+data.idFeed+","+data.idChart+",&quot;"+data.name+"&quot;)'>  "+data.name+"  </a></li>");
				});
				content = content + "</ul>";
				//new_div.setContent(content);
				
				$("#"+className).html(content);
				
				$( "#"+className ).dialog({
					title: name,
		 	        close: function(event, ui)
			 	        {
			 	        	$(this).dialog('close').remove();//removed destroy instead of close so it can get called again byt the scheduller
			 	        },
			 	    width:'auto'
				});
			  
		});
	
}

function buildDialogForFeedElements(idProject, idFeed, idChart, name){	
	var new_div = document.createElement('div');
	document.body.appendChild(new_div);
	className = idChart+"infoWindowContentInner";
	new_div.className = className;
	//new_div.innerHTML = '<u>'+feedId+'</u>';
	//TO DO: get the necesary headers from the chart like chartTitle, columnIds and names to generate content
	var content = "<h3>"+name+"</h3>";
	content = content + "<div style='width:100%'>";
	content = content + "<div id='chartsPanel'>";
	content = content + "<div class='center'>";
	content = content + "<div class='chartBox'>";
	content = content + "<h2 class='chartBoxName'></h2>";
	content = content + "</div>";
	/*$( "."+className ).dialog({
 	        title: name,
 	        close: function(event, ui)
 	        {
 	        	$(this).dialog('destroy').remove();
 	        },
          minHeight: 400,
          minWidth: 500,
          maxWidth: 700,
          maxHeight: 600
	            		
	});*/
	content = content + "<div class='chart'>";
	content = content + '<div id='+idChart+'_Chart style=width:100%;height:300px;></div>';
	content = content + "</div>";
	content = content + "</div>";
	
	new_div.innerHTML = content;
	//console.log(content);
	//TODO: IF type of feed load appropiate function
	
	loadImage(idProject, idFeed, idChart, name);
	//loadChart(idProject, idFeed, idChart);
	new_div.style.cssText = 'display:none;';
}

function loadScript() {
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyB5z4Crv2MCH1n1e0Uvuzy2yVnvXyetURg&sensor=false&callback=initialize";
	document.body.appendChild(script);

}
function reCenter(){
	var center = map.getCenter(); 
    google.maps.event.trigger(map, 'resize'); 
    map.setCenter(center); 
}

window.onload = loadScript;
window.onresize = reCenter;

</script>
<div class="map">
<div id="map_canvas" style="width:100%; height:100%"></div>
<div class="feedInfo">
<div>
<ul  class="feedDetails"></ul>
</div>
</div>
</div>