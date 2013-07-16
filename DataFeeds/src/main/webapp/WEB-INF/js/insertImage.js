
function loadImage(idProject, idFeed, idChart, name){	
   var dataInfo = new Object();
    dataInfo.idProject = idProject;
    dataInfo.idFeed = idFeed;
    dataInfo.idChart = idChart;
    dataInfo.name = name;
	
	//Init
	$.when(
			$.post(CONTEXTPATH+"/image/getImage/"+idProject+"/"+idFeed+"/"+idChart)
		   ).done(function(data) {
			    generateImageDiv(data, dataInfo);    
			   }
	);
	
	setInterval(function() {
		$.when(
				$.post(CONTEXTPATH+"/image/getImage/"+idProject+"/"+idFeed+"/"+idChart)
			   ).done(function(data) {
				    generateImageDiv(data, dataInfo);    
				   }
		);
	}, 200000);
	
		   
}

function generateImageDiv(imageLocation, dataInfo){
	
	className = dataInfo.idProject+"_"+ dataInfo.idFeed+ "_"+ dataInfo.idChart + "_ImageWindow";

	var new_div = document.createElement('div');
	//document.body.appendChild(new_div);
	new_div.className = className;
	new_div.setAttribute('id', className);
	new_div.setAttribute('type', dataInfo.name);
	//var new_div = "<div id='"+className+"' class='"+className+"'><img src='"+CONTEXTPATH+imageLocation+"'/></div>";
	
	if(dataInfo.name.localeCompare('date')==0){
		var datePosition = "#"+dataInfo.idProject+"_"+dataInfo.idFeed+"_feedWindow > .feedDate"; 
		$(datePosition).html("<img src='"+CONTEXTPATH+imageLocation+"'/>");
	}else{
		$("#"+className).html("<img src='"+CONTEXTPATH+imageLocation+"'/>");
	}

}

