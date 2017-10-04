$(document).ajaxComplete(function(event,xhr,options){
	var rspjson =  JSON.parse(xhr.responseText);
	if(rspjson.status==4400){
		jQuery.messager.confirm('系统提示', "由于长时间未操作，登录已失效，点击确定重新登录", function (data){
			if (data){
				top.location.href=rspjson.body;
			}
		})
	}
});



