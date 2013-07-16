
function loadImage(idProject, idFeed, idChart, name){	
	$.when(
			$.post(CONTEXTPATH+"/image/getImage/"+idProject+"/"+idFeed+"/"+idChart)
		   ).done(function(data) {
			   var dataInfo = new Object();
			    dataInfo.idProject = idProject;
			    dataInfo.idFeed = idFeed;
			    dataInfo.idChart = idChart;
			    dataInfo.name = name;
			    generateImageDiv(data, dataInfo);

			   }
	);
		   
}

function generateImageDiv(imageLocation, dataInfo){
	
	var new_div = document.createElement('div');
	document.body.appendChild(new_div);
	className = dataInfo.idProject+"_"+ dataInfo.idFeed+ "_"+ dataInfo.idChart + "_ImageWindow";
	new_div.className = className;
	new_div.setAttribute('id', className);
	$("#"+className).html("<img src='"+CONTEXTPATH+imageLocation+"'/>");
	
	$( "#"+className ).dialog({
		title: dataInfo.name,
		width:'auto',
		resizable: false,
	        close: function(event, ui)
 	        {
 	        	$(this).dialog('destroy').remove();
 	        },
	});
	
}