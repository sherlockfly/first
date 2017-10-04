
//查看详情
function findMsgPushDetail(url) {
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset: ['40px',''],
		closeBtn : [ 1, true ],
		area : [ '800px', '450px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}
function findList(url){
	
}

function deletePushMsg(url){
	$.ajax({
		dataType:"json",
		url:url,
		type:"get",
		success:function(data){
			if(data.code==200){
				alert(data.response);
			}else{
				alert(data.response)
			}
			$("#submit_form",window.document).submit();
		}
	})	 
}
$(document).ready(function(){
	$("#domainsubmit").click(function() {
		var param = $("#addPushMsgForm").serialize();
		url = $("#addPushMsgForm").attr("action");
		var id = $("#id").val();
			$.ajax({
				 dataType: "json",
	             url : url,
	             type : "post",
	             data: param,
	             success: function(data){
	            	 if (data.code == 200) {
							alert(data.response);
							layer.closeAll();
							$("#submit_form",window.parent.document).submit();
							//window.location.href = '/dup/role/index?yfSessionId=' + yfSessionId;
						} else{
							layer.alert(data.response);
						}
	             }
	         });
		}); 
	$("#postSaveSubmit").click(function() {
			$.ajax({
				 dataType: "json",
	             url : $("#myForm").attr('action'),
	             type : "post",
	             data: $("#myForm").serialize(),
	             success: function(data){
							alert(data.data);
							$("#listFrm",window.parent.document).submit();
							parent.layer.closeAll();
						
	             },
	             error: function(XMLHttpRequest, textStatus, errorThrown) {
	            	 alert("请求对象XMLHttpRequest: "+XMLHttpRequest.status);
	            	 alert("错误类型textStatus: "+textStatus);
	            	 alert("异常对象errorThrown: "+errorThrown);
				}
	         });
			
			
		}); 

//福卡样式类型添加、更新
	$("#dosubmit").click(function() {
		var param = $("#addCardStyleTypeForm").serialize();
		url = $("#addCardStyleTypeForm").attr("action");
		var id = $("#id").val();
			$.ajax({
				 dataType: "json",
	             url : url,
	             type : "post",
	             data: param,
	             success: function(data){
	            	 if (data.code == 200) {
							alert('操作成功');
							$("#list_form",window.parent.document).submit();
							parent.layer.closeAll();
						} else{
							layer.alert('操作失败,数据填写有误,请重新输入！');
						}
	             }
	         });
		}); 
});
//跳转到添加、编辑福卡样式和样式类型
function toAddCardStyle(url) {
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset: ['40px',''],
		closeBtn : [ 1, true ],
		area : [ '800px', '450px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}
//福卡样式添加、更新
$(document).ready(function(){
	$("#do").click(function() {
		var param = $("#addCardStyleForm").serialize();
		url = $("#addCardStyleForm").attr("action");
		var id = $("#id").val();
		
			$.ajax({
				 dataType: "json",
	             url : url,
	             type : "post",
	             data: param,
	             success: function(data){
	            	 if (data.code == 200) {
							alert('操作成功');
							$("#list_form",window.parent.document).submit();
							parent.layer.closeAll();
						} else{
							layer.alert('操作失败,数据填写有误,请重新输入！');
						}
	             }
	         });
		}); 
});
//福卡样式添加、更新
//function upload(){
//	   var form = $("form[name=addCardStyleForm]");
//		url = $("#addCardStyleForm").attr("action");
//		
//			var fileName=document.getElementById("file").value;
//			var extname = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length);  
//		    extname = extname.toLowerCase();//处理了大小写  
//		    
//			var styleName=document.getElementById("styleName").value;
//			var styleTypeName=document.getElementById("styleTypeName").value;
//			alert(styleName);
//			 $.ajaxFileUpload  
//			    ({  
//			            url:url+"?styleName="+styleName+"&styleTypeName="+styleTypeName,  
//			            secureuri:false,  
//			            fileElementId:'file',  
//			            dataType: 'json',  
//			            success: function (data, status)  
//			            {  
//			            	$("#list_form",window.parent.document).submit();
//			            	parent.layer.closeAll();
//			            },  
//			            error: function (data, status, e)  
//			            {  
//			                alert("操作失败，请稍后再试");
//			            }  
//			        }  
//			    )  
//			    return false;  
//		}

//$(document).ready(function(){
//	$("#do").click(function() {
//		var param = $("#addCardStyleForm").serialize();
//		url = $("#addCardStyleForm").attr("action");
//		var id = $("#id").val();
//		alert(param);
//			$.ajax({
//				 dataType: "json",
//	             url : url,
//	             type : "post",
//	             data: param,
//	             success: function(data){
//	            	 if (data.code == 200) {
//							alert('操作成功');
//							$("#list_form",window.parent.document).submit();
//							parent.layer.closeAll();
//						} else{
//							layer.alert('操作失败失败,数据填写有误,请重新输入！');
//						}
//	             }
//	         });
//		}); 
//});


//福卡样式和样式类型删除
function deleteCardStyle(url){
	$.ajax({
		dataType:"json",
		url:url,
		type:"get",
		success:function(data){
			if(data.code==200){
				alert("删除成功");
			}else{
				alert("删除失败")
			}
			$("#list_form",window.document).submit();
		}
	})
}

//添加用户
function addUser(url) {
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset: ['40px',''],
		closeBtn : [ 1, true ],
		area : [ '800px', '450px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}