<%@ page language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <c:set var="success" value="${param.success}"/>
<div class="listTable">
<p><a class="grayButton" href="${contextPath }/admin/locations/listLocations">Locations</a></p>
<c:if  test="${!empty locationsList}">
<table class="">
<tr>
	<td>Name</td>
	<td>Type</td>
	<td>URI</td>
	<td>baseUri</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<c:forEach items="${locationsList}" var="location">
	<tr>
		<td>${location.name}</td>
		<td>${location.type}</td>
		<td>${location.uri}</td>
		<td>${location.baseUri}</td>
		<td class="center"><a class="bButton" href="javascript:editLocation(${location.idLocation}, 'editLocation')"><spring:message code="label.app.edit"/></a></td>		
		<td class="center"><a class="bButton" href="deleteLocation/${location.idLocation}"><spring:message code="label.app.delete"/></a></td>
	</tr>
</c:forEach>
</table>
</c:if>
</div>
<div class="center">
<a class="aButton" href="javascript:dialog('addLocation')"><spring:message code="label.location.addLocation"/> </a>
</div>
<div id="addLocation" style="display:none">
<form:form method="post" action="addLocation" commandName="location">
 
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.location.type"/></form:label></td>
        <td><form:input path="type" /></td> 
    </tr>
    <tr>
        <td><form:label path="name"><spring:message code="label.location.name"/></form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="uri"><spring:message code="label.location.uri"/></form:label></td>
        <td><form:input path="uri" /></td>
    </tr>
    <tr>
        <td><form:label path="baseUri"><spring:message code="label.location.baseUri"/></form:label></td>
        <td><form:input path="baseUri" /></td>
    </tr>  
    <tr>
        <td  class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.location.addLocation"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
<div id="editLocation" style="display:none">
<c:if  test="${!empty locationU}">Empty</c:if>
<form:form method="post" action="updateLocation" commandName="locationU">
 	<form:input path="idLocation" type="hidden"/>
    <table>
    <tr>
        <td><form:label path="type"><spring:message code="label.location.type"/></form:label></td>
        <td><form:input path="type"/></td> 
    </tr>
    <tr>
        <td><form:label path="name"><spring:message code="label.location.name"/></form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="uri"><spring:message code="label.location.uri"/></form:label></td>
        <td><form:input path="uri" /></td>
    </tr>
    <tr>
        <td><form:label path="baseUri"><spring:message code="label.location.baseUri"/></form:label></td>
        <td><form:input path="baseUri"/></td>
    </tr>  
    <tr>
        <td  class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.location.addLocation"/>"/>
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
		dialog('error', null);
	}
	
}

function dialog(idDiv, data){
	$('.currentFormAddLoation').attr("action", CONTEXTPATH+'/admin/'+idDiv);
	if(data){
		populate('#editLocation', data);
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

function editLocation(idLocation, idDiv){	
	$.when(
			$.post(CONTEXTPATH+"/admin/locations/editLocation/"+idLocation)
		   ).done(function(data) {

			   dialog(idDiv, data);
			   }
	);
		   
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