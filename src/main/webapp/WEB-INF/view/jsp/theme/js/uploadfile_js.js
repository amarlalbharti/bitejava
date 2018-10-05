jQuery(document).ready(function() {
	
	$(document.body).on('click', '.file_view .btn_file_del' ,function(){
		var fid = $(this).attr("fid");
		if(confirm("Are you sure to delete this file ?"))
		{
			$.ajax({
				type : "GET",
				url : "adminDeleteFile",
				data : {'fid':fid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getUploadFileList(1);
						Lobibox.notify('success', {
		                    size: 'mini',
		                    msg: 'File has been deleted successfully!'
		                });
					}
					else
					{
						Lobibox.notify('error', {
		                    size: 'mini',
		                    msg: 'Oops, File has been deleted successfully !'
		                });
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					alert(xhr.status);
				}
			}) ; 
		}
		
	});
	
	
	
	
	
});


function getUploadFileList(pn)
{
//	alert("get List");
	$.ajax({
		type : "GET",
		url : "getUploadedFiles",
		data : {'pn':pn},
		contentType : "application/json",
		success : function(data) {
			$(".my_uploads .file_view").html(data);
		},
		error: function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);
	      }
	}); 
}


