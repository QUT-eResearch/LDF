<%@ page language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
 <c:set var="success" value="${param.success}"/>
<div class="listTable">
<p><a class="grayButton" href="${contextPath }/admin/listScripts">Scripts</a></p>
<c:if  test="${!empty scriptList}">
<table class="">
<tr>
	<td>Location</td>
	<td>Input File</td>
	<td>Output File</td>
	<td>Options</td>
	<td>Enabled</td>
	<td>&nbsp;</td>
	<td>&nbsp;</td>
</tr>
<c:forEach items="${scriptList}" var="script">
	<tr>
		<td>${script.scriptLoc}</td>
		<td>${script.inputFile}</td>
		<td>${script.outputFile}</td>
		<td>${script.options}</td>
		<td>${script.status}</td>
		<td class="center"><a class="bButton" href="javascript:editScript(${script.idScript}, 'editScript')"><spring:message code="label.app.edit"/></a></td>		
		<td class="center"><a class="bButton" href="deleteScript/${script.idScript}"><spring:message code="label.app.delete"/></a></td>
	</tr>
</c:forEach>
</table>
</c:if>
</div>
<div class="center">
<a class="aButton" href="javascript:dialog('addScript')"><spring:message code="label.script.addScript"/> </a>
</div>
<div id="addScript" style="display:none">
<form:form method="post" action="addScript" commandName="script">
 
    <table>
    <tr>
        <td><form:label path="scriptLoc"><spring:message code="label.script.scriptLoc"/></form:label></td>
        <td><form:input path="scriptLoc" /></td> 
    </tr>
    <tr>
        <td><form:label path="inputFile"><spring:message code="label.script.inputFile"/></form:label></td>
        <td><form:input path="inputFile" /></td>
    </tr>
    <tr>
        <td><form:label path="outputFile"><spring:message code="label.script.outputFile"/></form:label></td>
        <td><form:input path="outputFile" /></td>
    </tr>
    <tr>
        <td><form:label path="options"><spring:message code="label.script.options"/></form:label></td>
        <td><form:input path="options" /></td>
    </tr> 
    <tr>
        <td><form:label path="status"><spring:message code="label.script.status"/></form:label></td>
        <td><form:input path="status" /></td>
    </tr>      
    <tr>
        <td  class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.script.addScript"/>"/>
        </td>
    </tr>
</table>  
</form:form>
</div>
<div id="editScript" style="display:none">
<c:if  test="${!empty locationU}">Empty</c:if>
<form:form method="post" action="updateScript" commandName="scriptU">
 	<form:input path="idScript" type="hidden"/>
    <table>
	<tr>
        <td><form:label path="scriptLoc"><spring:message code="label.script.scriptLoc"/></form:label></td>
        <td><form:input path="scriptLoc" /></td> 
    </tr>
    <tr>
        <td><form:label path="inputFile"><spring:message code="label.script.inputFile"/></form:label></td>
        <td><form:input path="inputFile" /></td>
    </tr>
    <tr>
        <td><form:label path="outputFile"><spring:message code="label.script.outputFile"/></form:label></td>
        <td><form:input path="outputFile" /></td>
    </tr>
    <tr>
        <td><form:label path="options"><spring:message code="label.script.options"/></form:label></td>
        <td><form:input path="options" /></td>
    </tr> 
    <tr>
        <td><form:label path="status"><spring:message code="label.script.status"/></form:label></td>
        <td><form:input path="status" /></td>
    </tr>      
    <tr>
        <td  class="center" colspan="2">
            <input class="aButton" type="submit" value="<spring:message code="label.script.editScript"/>"/>
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
	$('.currentFormAddScript').attr("action", CONTEXTPATH+'/admin/'+idDiv);
	if(data){
		populate('#editScript', data);
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

function editScript(idScript, idDiv){	
	$.when(
			$.post(CONTEXTPATH+"/admin/editScripts/"+idScript)
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