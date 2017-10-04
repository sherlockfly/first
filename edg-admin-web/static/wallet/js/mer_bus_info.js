

function addMerBusInfo(url) {
    
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset : [ '50px', '' ],
		closeBtn : [ 1, true ],
		area : [ '940px', '420px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}

function editRiskRelationConfig(url) {
	var offset = 50;
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset: [offset+'px',''],
		closeBtn : [ 1, true ],
		area : [ '920px', ($(window).height()-offset*2)+'px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}

$(document).ready(function(){
	
	var contextPath = $("#contextPath").val();
	
	var list; //根据支付类型查寻出来的通道list
	//选择支付类型的时候，动态生成通道的下拉框
	$("#payType").change(function(){
//		alert("支付类型："+ $("#payType").val());
		
		$.post(
				contextPath + "/tractInfo/list",
				{
					payType: $("#payType").val(),
					merId: $("#merSysId").val(),
					busType : $("#busType").val(),
					gwType : $("#gwType").val()
				}, 
				function(data){
					if(data.code==200){
						$("#tractId").empty();
						var select = " <option value=\"\">---请选择---</option>";
						
					   list = data.response;
					   if(list.length==0){
						   alert("该支付类型下暂时没有配置通道,请重新选择");
						   return;
					   }
						for(var i =0;i<list.length; i++){
							select += "<option  value=\"" + list[i].tractId + "\">" + list[i].tractName + "</option>";
						}
//						alert(select);
						$("#tractId").html(select);
					}else if(data.code==500){
						$("#tractId").empty();
						alert("服务器异常" + data.desc);
					}else if(data.code == 401){
						$("#tractId").empty();
						alert("该支付类型下暂时没有通道,请选择其他类型");
					}
				}
			);
	});
	
	
	//选择通道的时候给tractType赋值
	$("#tractId").change(function(){
		var tractId = $("#tractId").val();
		for(var i=0;i<list.length;i++){
			if(list[i].tractId == tractId){
				$("#tractType").val(list[i].tractType);
				break;
			}
		}
	});
	
	
//	alert("ss");
	$("#addMerchantForm").validate({
		  onsubmit:true,// 是否在提交是验证
		  onfocusout:false,// 是否在获取焦点时验证
		  onkeyup :false,// 是否在敲击键盘时验证
		  rules: {
			busType :{
					required:true,
					minlength:1
			},
			gwType :{
				required:true,
				minlength:1
			},
			tractId :{
				required:true,
				minlength:1
			},
			feeType :{
				required:true,
				minlength:1
			},
			status :{
				required:true,
				minlength:1
			},
		    merFee :{
			   required:true,
			   number:true	
			},
			creditCost :{
				number:true	
			},
			debitCost :{
				number:true	
			},
			perLowestFee :{
				number:true	
			},
			perHighestFee :{
				number:true	
			}
		  },
		  /* 设置错误信息 */  
		  messages: {   
			   merFee: {
				   required:"请输入商户费率",
				   number: "请输入有效的数字"  
			   },
			   creditCost: {       
				   number: "请输入有效的数字"  
			   },
			   debitCost: {       
				   number: "请输入有效的数字"  
			   },
			   perLowestFee: {       
				   number: "请输入有效的数字"  
			   },
			   perHighestFee: {       
				   number: "请输入有效的数字"  
			   },
			   busType :{
					required:"请选择业务类型"
			   },
			   gwType :{
					required:"请选择网关类型"
			   },
			   tractId :{
				   required:"请选择通道名称"
			   },
			   feeType :{
				   required:"请选择支付类型"
			   },
			   status :{
				   required:"请选择状态"
			   }
			   
		 },

		//提交表单
		submitHandler:function(form) {
			var param = $("#addMerchantForm").serialize();
			 url = $("#addMerchantForm").attr("action");
			 $("#addMerchantForm").removeAttr("onclick");  //防止重复提交
			$.ajax({
				url : url ,
				type : "post",
				data: param,
				success : function(data) {
					if (data.code == 200) {
						alert('操作成功');
						$("#addMerchantForm").attr("onclick","submitForm();");
						$("#submit_form",window.parent.document).submit();
						parent.layer.closeAll();
						
					} else if(data.code == 402){
						$("#addMerchantForm").attr("onclick","submitForm();");
						alert('已经配置该通道请重新选择');
					} else{
						$("#addMerchantForm").attr("onclick","submitForm();");
						alert('添加失败,数据填写有误,请重新输入！');
					}
				}
			});
		} 
	}); 
	
});

//提交表单
function submitForm(){
	 $("#addMerchantForm").submit();
}

//放到$(document).ready(function(){}之前IE会报错
function updateStatusById(id,status,tractType,merSysId){
	var	contextPath = $("#contextPath").val();
	$.post(contextPath + "/merBusInfo/updatestatus",
		{
			 id:id,
			 status:status,
			 tractType:tractType,
			 merSysId:merSysId
		},
		function(data){
			if (data.code == 200) {
				alert('修改成功');
				//parent.layer.closeAll();
				$("#submit_form").submit();
				
			} 	else if (data.code == 404) {
				alert(data.desc);
				
			} else {
				alert(data.code);
				alert('添加失败,数据填写有误,请重新输入！');
			}
		}
	
	);
}		





