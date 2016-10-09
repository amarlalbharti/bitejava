jQuery(document).ready(function() {
	
	$(document.body).on('click', '.sub_view .unpublish_subject' ,function(){
		var sid = $(this).parent().parent().attr("sid");
//		alert("Hello edit " + sid);
		if(confirm("Are you sure to UnPublish ?"))
		{
			$.ajax({
				type : "GET",
				url : "unpublishSubject",
				data : {'sid':sid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getSubjectList(1);
						Lobibox.notify('success', {
		                    size: 'mini',
		                    msg: 'Subject has been unpublished !'
		                });
					}
					else
					{
						Lobibox.notify('error', {
		                    size: 'mini',
		                    msg: 'Oops, Subject has not been unpublished !'
		                });
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					alert(xhr.status);
				}
			}) ; 
		}
		
	});
	
	
	$(document.body).on('click', '.sub_view .publish_subject' ,function(){
		var sid = $(this).parent().parent().attr("sid");
		if(confirm("Are you sure to Publish ?"))
		{
			$.ajax({
				type : "GET",
				url : "publishSubject",
				data : {'sid':sid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getSubjectList(1);
						Lobibox.notify('success', {
		                    size: 'mini',
		                    msg: 'Subject has been published !'
		                });
					}
					else
					{
						Lobibox.notify('error', {
		                    size: 'mini',
		                    msg: 'Oops, Subject has not been unpublished !'
		                });
					}
					
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					alert(xhr.status);
				}
			}) ; 
		}
		
	});
	

	$(document.body).on('click', '.sub_view .delete_subject' ,function(){
		var sid = $(this).parent().parent().attr("sid");
		if(confirm("Are you sure to delete subject ?"))
		{
			$.ajax({
				type : "GET",
				url : "adminDeleteSubject",
				data : {'sid':sid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getSubjectList(1);
						Lobibox.notify('success', {
		                    size: 'mini',
		                    msg: 'Subject has been deleted successfully !'
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


function getSubjectList(pn)
{
//	alert("get List")
	$.ajax({
		type : "GET",
		url : "getSubjectList",
		data : {'pn':pn},
		contentType : "application/json",
		success : function(data) {
			$(".my_subjects .sub_view").html(data);
		},
		error: function (xhr, ajaxOptions, thrownError) {
	        alert(xhr.status);
	      }
	}) ; 
}


