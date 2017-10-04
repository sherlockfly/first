$(document).ready(function(){
	$("#submitExamine").click(function() {
		var status = $("#entiyStatus").val();
		if(!status){
			layer.alert('请选择操作！');
		}else{
			var merId = $("#entiyMerId").val();
			$.ajax({
	             type: "post",
	             url: "/walletAdmin/agent/updateStatus/"+merId+"/"+status+"",
	             dataType: "json",
	             success: function(data){
	            	 if(data && "success" == data.desc){
	            		 alert('操作成功！');
	            		 $("#submit_form",window.parent.document).submit();
	            		 parent.layer.closeAll();
	            	 }
	             }
	         });
		}
		
	}); 
});





