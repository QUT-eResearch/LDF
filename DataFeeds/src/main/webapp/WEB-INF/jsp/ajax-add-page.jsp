<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!-- <script type="text/javascript" src="/DataFeeds/js/jquery/jquery-1.4.4.min.js"></script>-->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	    var jq = jQuery.noConflict();
	</script>
	
	<title>Spring MVC - jQuery Integration Tutorial</title>

</head>
<body>

<h3>Spring MVC - jQuery Integration Tutorial</h3>
<h4>AJAX version</h4>

Demo 1
<div style="border: 1px solid #ccc; width: 250px;">
	Add Two Numbers: <br/>
	<input id="inputNumber1" type="text" size="5"> +
	<input id="inputNumber2" type="text" size="5">
	<input type="submit" value="Add" onclick="add()" /> <br/>
	Sum: <span id="sum">(Result will be shown here)</span>
</div>


<script type="text/javascript"> 

function add() {
	jq(function() {
		// Call a URL and pass two arguments
		// Also pass a call back function
		// See http://api.jquery.com/jQuery.post/
		// See http://api.jquery.com/jQuery.ajax/
		// You might find a warning in Firefox: Warning: Unexpected token in attribute selector: '!' 
		// See http://bugs.jquery.com/ticket/7535
		jq.post("/DataFeeds/feed/ajax/add",
					{ 	inputNumber1:  jq("#inputNumber1").val(),
				  		inputNumber2:  jq("#inputNumber2").val() },
						function(data){
							// data contains the result
							// Assign result to the sum id
							jq("#sum").replaceWith('<span id="sum">'+ data + '</span>');
					});
	});
}

</script>
</body>
</html>