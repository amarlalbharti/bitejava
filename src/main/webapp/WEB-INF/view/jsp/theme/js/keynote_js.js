jQuery(document).ready(function() {
	
	
	$(document.body).on('change', '.my_keynotes #sid' ,function(){
		var sid = $(this).val();
		getKeynoteList(1);
		if(sid != "0")
		{
			$(".my_keynotes .add_keynote").attr("href", "adminAddKeynote?sid="+sid);
			$(".my_keynotes .add_keynote").removeClass("disabled");
			$('.my_keynotes .add_keynote').removeAttr("disabled");
		}
		else
		{
			$(".my_keynotes .add_keynote").addClass("disabled");
			$('.my_keynotes .add_keynote').attr("disabled","disabled");
		}
		
	});
	
	$(document.body).on('click', '.kn_view .unpublish_keynote' ,function(){
		var kid = $(this).parent().parent().attr("kid");
//		alert("Hello edit " + sid);
		if(confirm("Are you sure to UnPublish ?"))
		{
			$.ajax({
				type : "GET",
				url : "unpublishKeynote",
				data : {'kid':kid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getKeynoteList(1);
						
						Lobibox.notify('success', {
		                    size: 'mini',
		                    msg: 'Keynote has been unpublished !'
		                });
					}
					else
					{
						Lobibox.notify('error', {
		                    size: 'mini',
		                    msg: 'Oops, Something wrong !'
		                });
					}
					
					
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					alert(xhr.status);
				}
			}) ; 
		}
		
	});
	
	
	$(document.body).on('click', '.kn_view .publish_keynote' ,function(){
		var kid = $(this).parent().parent().attr("kid");
		if(confirm("Are you sure to Publish ?"))
		{
			$.ajax({
				type : "GET",
				url : "publishKeynote",
				data : {'kid':kid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getKeynoteList(1);
						
						Lobibox.notify('success', {
		                    size: 'mini',
		                    msg: 'Keynote has been published !'
		                });
					}
					else
					{
						Lobibox.notify('error', {
		                    size: 'mini',
		                    msg: 'Oops, Something wrong !'
		                });
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					alert(xhr.status);
				}
			}) ; 
		}
	});
	
	$(document.body).on('click', '.kn_view .delete_keynote' ,function(){
		var kid = $(this).parent().parent().attr("kid");
		if(confirm("Are you sure to delete subject ?"))
		{
			$.ajax({
				type : "GET",
				url : "adminDeleteKeynote",
				data : {'kid':kid},
				contentType : "application/json",
				success : function(data) {
					var obj = jQuery.parseJSON(data);
					if(obj.success)
					{
						getKeynoteList(1);
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					
					alert(xhr.status);
				}
			}) ; 
		}
		
	});
	
	
	
	
});


function getKeynoteList(pn)
{
	var sid = $("#sid").val();
	if(sid != "" &&  sid != "0" )
	{
		$.ajax({
			type : "GET",
			url : "getKeynoteList",
			data : {'pn':pn,'sid':sid},
			contentType : "application/json",
			success : function(data) {
				$(".my_keynotes .kn_view").html(data);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
			}
		}) ; 
	}
}

function getChildKeynoteList(pn, parent_kid)
{
	var sid = $("#sid").val();
	if(sid != "" &&  sid != "0" )
	{
		$.ajax({
			type : "GET",
			url : "getKeynoteList",
			data : {'pn':pn,'sid':sid,'parent_kid':parent_kid},
			contentType : "application/json",
			success : function(data) {
				$(".my_keynotes .kn_view").html(data);
			},
			error: function (xhr, ajaxOptions, thrownError) {
				alert(xhr.status);
			}
		}) ; 
	}
}

