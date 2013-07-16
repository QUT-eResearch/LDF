<%@ page language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="success" value="${param.success}"/>

<div class="listTable">
<p><a class="grayButton" href="${contextPath }/admin/listProjects">Projects</a><span class="grayButton">></span></a><a class="grayButton" href="${contextPath }/admin/feeds/listFeeds?idProject=${idProject }">Feeds</a></p>
<c:if  test="${!empty feedsList}">
<table class="">
<tr>
	<td>Type</td>
	<td>Name</td>
	<td>Num Columns</td>
	<td>Start Row</td>
	<td>Num Rows</td>
	<td>Header Row</td>
	<td>Location</td>
	<td>&nbsp;</td>
</tr>
<c:forEach items="${feedsList}" var="feed">
	<tr>
		<td>${feed.type}</td>
		<td>${feed.name}</td>
		<td>${feed.numColumns}</td>
		<td>${feed.startRow}</td>
		<td>${feed.numRows}</td>
		<td>${feed.headerRows}</td>
		<td>${feed.idLocation}</td>
		<td class="center"><a class='bButton' href="javascript:editFeed(${feed.idProject},${feed.idFeed})"><spring:message code="label.app.edit"/></a></td>
	</tr>
	<tr>
		<td>Feed Elements</td>
		<td colspan="6"><div ><table class="subTable" id="${feed.idProject}_${feed.idFeed}_Charts">
		<script>$(function() {loadChartsList(${feed.idProject}, ${feed.idFeed});});</script>
		</table></div>
		<div class="center"><a class="bButton" href="javascript:dialog('addChart', ${feed.idProject}, ${feed.idFeed}, null)"><spring:message code="label.app.add"/></a></div>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td colspan="7"></td>
		
		<td class="center"><a class='bButton' href="deleteFeed/${feed.idProject}/${feed.idFeed}"><spring:message code="label.app.delete"/></a></td>
	</tr>
</c:forEach>
</table>
</c:if>
</div>

<div class="center">
<a class="aButton" href="javascript:dialog('addFeed',${idProject}, null)"><spring:message code="label.feed.addfeed"/></a>
</div>
<div id="addFeed" style="display:none">
<form:form class="currentFormAddFeed" method="post" action="" commandName="feed">
	<form:input class="currentiDAddFeed" path="idFeed" type="hidden" value=""/>
	
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.feed.type"/></form:label></td>
        <td><form:input path="type" /></td> 
    </tr>
    <tr>
        <td><form:label path="name"><spring:message code="label.feed.name"/></form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="numColumns"><spring:message code="label.feed.numColumns"/></form:label></td>
        <td><form:input path="numColumns" /></td>
    </tr>
    <tr>
        <td><form:label path="startRow"><spring:message code="label.feed.startRow"/></form:label></td>
        <td><form:input path="startRow" /></td>
    </tr>    
    <tr>
        <td><form:label path="numRows"><spring:message code="label.feed.numRows"/></form:label></td>
        <td><form:input path="numRows" /></td>
    </tr>
    <tr>
        <td><form:label path="headerRows"><spring:message code="label.feed.headerRows"/></form:label></td>
        <td><form:input path="headerRows" /></td>
    </tr>
    <tr>
	<td>
        <form:label path="idLocation"><spring:message code="label.feed.location"/></form:label></td>
         <td>
        <form:select path="idLocation">
        	<c:forEach  items="${locationsList }" var="location">
			<form:option value="${location.idLocation }">${location.name }</form:option>
			</c:forEach>
        </form:select>
               <!--<form:input path="idLocation" />-->
        </td>
    </tr>
    <tr>
        <td><form:label path="dateFormat"><spring:message code="label.feed.dateFormat"/></form:label></td>
        <td><form:input path="dateFormat" /></td>
    </tr>     
    <tr>
        <td><form:label path="dateColumn"><spring:message code="label.feed.dateColumn"/></form:label></td>
        <td><form:input path="dateColumn" /></td>
    </tr>  
    <tr>
        <td><form:label path="timeFormat"><spring:message code="label.feed.timeFormat"/></form:label></td>
        <td><form:input path="timeFormat" /></td>
    </tr>  
    <tr>
        <td><form:label path="timeColumn"><spring:message code="label.feed.timeColumn"/></form:label></td>
        <td><form:input path="timeColumn" /></td>
    </tr>    
    <tr>
        <td class="center" colspan="2">
            <input  class="aButton"  type="submit" value="<spring:message code="label.app.add"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
<div id="updateFeed" style="display:none">
<form:form class="currentFormEditFeed" method="post" action="" commandName="feed">
	<form:input class="currentiDEditFeed" path="idFeed" type="hidden" value=""/>
	<form:input class="currentiDEditFeedProject" path="idProject" type="hidden" value=""/>
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.feed.type"/></form:label></td>
        <td><form:input path="type" /></td> 
    </tr>
    <tr>
        <td><form:label path="name"><spring:message code="label.feed.name"/></form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="numColumns"><spring:message code="label.feed.numColumns"/></form:label></td>
        <td><form:input path="numColumns" /></td>
    </tr>
    <tr>
        <td><form:label path="startRow"><spring:message code="label.feed.startRow"/></form:label></td>
        <td><form:input path="startRow" /></td>
    </tr>    
    <tr>
        <td><form:label path="numRows"><spring:message code="label.feed.numRows"/></form:label></td>
        <td><form:input path="numRows" /></td>
    </tr>
    <tr>
        <td><form:label path="headerRows"><spring:message code="label.feed.headerRows"/></form:label></td>
        <td><form:input path="headerRows" /></td>
    </tr>
    <tr>
	<td>
        <form:label path="idLocation"><spring:message code="label.feed.location"/></form:label></td>
         <td>
        <form:select path="idLocation">
        	<c:forEach  items="${locationsList }" var="location">
			<form:option value="${location.idLocation }">${location.name }</form:option>
			</c:forEach>
        </form:select>
               <!--<form:input path="idLocation" />-->
        </td>
    </tr>  
        <tr>
        <td><form:label path="dateFormat"><spring:message code="label.feed.dateFormat"/></form:label></td>
        <td><form:input path="dateFormat" /></td>
    </tr>    
    <tr>
        <td><form:label path="dateColumn"><spring:message code="label.feed.dateColumn"/></form:label></td>
        <td><form:input path="dateColumn" /></td>
    </tr>  
    <tr>
        <td><form:label path="timeFormat"><spring:message code="label.feed.timeFormat"/></form:label></td>
        <td><form:input path="timeFormat" /></td>
    </tr>  
    <tr>
        <td><form:label path="timeColumn"><spring:message code="label.feed.timeColumn"/></form:label></td>
        <td><form:input path="timeColumn" /></td>
    </tr>    
    <tr>
        <td class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.app.update"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>

 
<div id="addChart" style="display:none">
<h3>Chart</h3>
<form:form class="currentFormAddChart" method="post" action="" commandName="chart">
	<form:input class="currentiDAddChart" path="idFeed" type="hidden" value=""/>
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.chart.type"/></form:label></td>
        <td>
            <form:select path="type">
				<form:option value="image">image</form:option>
				<form:option value="csv">csv</form:option>			
            </form:select>
        </td> 
    </tr>
    <tr>
        <td><form:label path="uriName"><spring:message code="label.chart.uriName"/></form:label></td>
        <td><form:input path="uriName" /></td>
    </tr>
    <tr>
        <td><form:label path="shortName"><spring:message code="label.chart.shortName"/></form:label></td>
        <td><form:input path="shortName" /></td>
    </tr>
    <tr>
        <td><form:label path="xMin"><spring:message code="label.chart.xMin"/></form:label></td>
        <td><form:input path="xMin" /></td>
    </tr>
    <tr>
        <td><form:label path="xMax"><spring:message code="label.chart.xMax"/></form:label></td>
        <td><form:input path="xMax" /></td>
    </tr>
    <tr>
        <td><form:label path="time"><spring:message code="label.chart.time"/></form:label></td>
        <td><form:input path="time" /></td>
    </tr>
    <tr>
        <td><form:label path="minTime"><spring:message code="label.chart.minTime"/></form:label></td>
        <td><form:input path="minTime" /></td>
    </tr> 
    <tr>
        <td><form:label path="maxTime"><spring:message code="label.chart.maxTime"/></form:label></td>
        <td><form:input path="maxTime" /></td>
    </tr> 
    <tr>
        <td><form:label path="numValues"><spring:message code="label.chart.numValues"/></form:label></td>
        <td><form:input path="numValues" /></td>
    </tr>    
    <tr>
        <td><form:label path="feedColumn"><spring:message code="label.chart.feedColumn"/></form:label></td>
        <td><form:input path="feedColumn" /></td>
    </tr>    
    <tr>
        <td class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.app.add"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
 
<div id="updateChart" style="display:none;">
<form:form method="post" action="/DataFeeds/admin/charts/updateChart/${idProject}" commandName="chart">

<form:input path="idFeed" type="hidden" value="${chart.idFeed}"/>
<form:input path="idChart"type="hidden" value="${chart.idChart}"/>
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.chart.type"/></form:label></td>
        <td><form:input path="type" value="${chart.type}"/></td> 
    </tr>
    <tr>
        <td><form:label path="uriName"><spring:message code="label.chart.uriName"/></form:label></td>
        <td><form:input path="uriName" value="${chart.uriName}"/></td>
    </tr>
    <tr>
        <td><form:label path="shortName"><spring:message code="label.chart.shortName"/></form:label></td>
        <td><form:input path="shortName" value="${chart.shortName}"/></td>
    </tr>
    <tr>
        <td><form:label path="xMin"><spring:message code="label.chart.xMin"/></form:label></td>
        <td><form:input path="xMin" value="${chart.xMin}"/></td>
    </tr>
    <tr>
        <td><form:label path="xMax"><spring:message code="label.chart.xMax"/></form:label></td>
        <td><form:input path="xMax" value="${chart.xMax}"/></td>
    </tr>
    <tr>
        <td><form:label path="time"><spring:message code="label.chart.time"/></form:label></td>
        <td><form:input path="time" value="${chart.time}"/></td>
    </tr>
    <tr>
        <td><form:label path="minTime"><spring:message code="label.chart.minTime"/></form:label></td>
        <td><form:input path="minTime" value="${chart.minTime}"/></td>
    </tr> 
    <tr>
        <td><form:label path="maxTime"><spring:message code="label.chart.maxTime"/></form:label></td>
        <td><form:input path="maxTime" value="${chart.maxTime}"/></td>
    </tr> 
    <tr>
        <td><form:label path="numValues"><spring:message code="label.chart.numValues"/></form:label></td>
        <td><form:input path="numValues" value="${chart.numValues}"/></td>
    </tr>    
    <tr>
        <td><form:label path="feedColumn"><spring:message code="label.chart.feedColumn"/></form:label></td>
        <td><form:input path="feedColumn" value="${chart.feedColumn}"/></td>
    </tr>    
    <tr>
        <td class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.app.update"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
<div id="error" style="display:none"><p>Could not perform operation, please delete nested elements </p></div>

<script>

var SUCCESS = '<c:out value="${success}"/>';

document.onLoad = getErrors();

function getErrors(){
	if(SUCCESS.localeCompare('false')==0){
		console.log(SUCCESS);
		dialog('error', null, null, null);
	}
	
}

$(function() {
	$("#addButtonForm").click(function () {
		$('#addButton').toggle();
		$('#addButtonForm').toggle();
		});
	});
	
function loadChartsList(idProject, idFeed){
	
$.when(
		$.post("${contextPath}/admin/charts/listCharts/"+idFeed
			)
	   ).done(function(data) {
		   if(data.length>0){
			   $("#"+idProject+"_"+idFeed+"_Charts").append("<tr><td>#</td><td>Name</td><td>Type</td><td></td><td></td></tr>");
			   $.each(data, function(i, data) {
				   //javascript:dialog('addChart',  /viewChart/{idProject}/{idFeed}/{idChart}
					var url = "javascript:editChart("+idProject+","+idFeed+","+data.idChart+")";
					$("#"+idProject+"_"+idFeed+"_Charts").append("<tr> <td> "+ (i+1) +" </td><td> "+ data.shortName + "</td><td> "+data.type +"</td><td class='center'><a class='cButton' href="+url+">  edit  </a></td><td class='center'><a class='cButton' href='../charts/deleteChart/"+idProject+"/"+idFeed+"/"+data.idChart+"'>  delete  </a></td></tr>");
					//$("#"+idProject+"_"+idFeed+"_Charts").append("<tr> <td>"+ (i+1) +"</td><td> "+ data.shortName + "</td><td> "+data.type +"</td><td><a href='../charts/editChart/"+idProject+"/"+idFeed+"/"+data.idChart+"'>  EDIT  </a></td><td><a href='../charts/deleteChart/"+idProject+"/"+idFeed+"/"+data.idChart+"'>  DELETE  </a></td></tr>");
				});
			}
	   }
);

}

function editChart(idProject, idFeed, idChart){
	$.when(
			$.post(CONTEXTPATH+"/admin/charts/viewChart/"+idProject+"/"+idFeed+"/"+idChart)
		   ).done(function(data) {

			   dialog('updateChart', idProject, idFeed, data);
			   }
	);
	
}
function editFeed(idProject, idFeed){

		$.when(
				$.post(CONTEXTPATH+"/admin/feeds/editFeed/"+idProject+"/"+idFeed)
			   ).done(function(data) {

				   dialog('updateFeed', idProject, idFeed, data);
				   }
		);
	
}

function dialog(idDiv, idProject, idFeed, data){

	$('.currentiDEditFeed').attr("value", idFeed);
	$('.currentiDEditFeedProject').attr("value", idProject);
	$('.currentFormEditFeed').attr("action", CONTEXTPATH+"/admin/feeds/"+idDiv+"/"+idProject);
	
	$('.currentiDAddFeed').attr("value", idProject);
	$('.currentFormAddFeed').attr("action", CONTEXTPATH+"/admin/feeds/"+idDiv+"/"+idProject);
	$('.currentiDAddChart').attr("value", idFeed);
	$('.currentFormAddChart').attr("action", CONTEXTPATH+"/admin/charts/"+idDiv+"/"+idProject+"/"+idFeed);
	
	if(data){
		populate('#'+idDiv, data);
	}
	
	$( "#"+idDiv ).dialog({
	        title: idDiv,
	        modal: true,
	        close: function(event, ui)
	        {
	        	//$(this).dialog('destroy').remove();
	        },
	        width:'auto'
            		
	});
	
}

function populate(frm, data) {   
    $.each(data, function(key, value){  
    var $ctrl = $('[name='+key+']', frm);  
    switch($ctrl.attr("type"))  
    {  
        case "text" :   
        case "hidden":  
        case "textarea":  
        $ctrl.val(value);   
        break;   
        case "radio" : case "checkbox":   
        $ctrl.each(function(){
           if($(this).attr('value') == value) {  $(this).attr("checked",value); } });   
        break;  
    }  
    });  
}


</script>

