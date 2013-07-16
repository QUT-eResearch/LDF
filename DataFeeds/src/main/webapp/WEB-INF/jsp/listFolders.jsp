<script>
$(document).ready(function() {
	
$.when(
		$.post("/DataFeeds/feed/admin/listFolders"
			)
	   ).done(function(data) {
		   $.each(data, function(i, data) {
				   $(".listFolders").append("<li>"+data.location + data.name + "<a href='/DataFeeds/feed/admin/listFolders/{"+data.folderId+"}'>  EDIT  </a></li>");
			  	});
		   }
);

});

</script>

<div >
<ul class="listFolders">

</ul>


</div>