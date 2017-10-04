
$(document).ready(function() {

		var	contextPath = $("#contextPath").val();
		var list; // 根据支付类型查寻出来的通道list
			// 根据参数获取代理商的通道列表 mer_bus_info TODO 需要排除已经添加的通道
			function getTractInfo() {
				var select = " <option value=\"\">---请选择---</option>";
				$.post(contextPath + "/merBusInfo/getTractInfo", {
				    subMerId :$("#subMerId").val(),
					merSysId :$("#agentId").val(),
					busType : $("#busType").val(),
					gwType : $("#gwType").val()
				}, function(data) {
					if (data.code == 200) {

						list = data.response;

						if (list.length == 0) {
							return;
						}
						for ( var i = 0; i < list.length; i++) {
							select += "<option  value=\"" + list[i].tractId + "\">" + list[i].tractName + "</option>";
						}
//						 alert(select);
						$("#tractId").html(select);
					} else if (data.code == 500) {
						alert("服务器异常" + data.desc);
					} else if(data.code == 402){
						alert("该商户"+data.desc);
					}else{
						alert("商户的上级代理商没有配置该通道,请重新选择");
					}
				});
			}

			// 选择业务类型的时候，动态生成通道的下拉框
			$("#busType").change(function() {
				if($("#gwType").val() == "-1"){
					return;
				}
				getTractInfo();
			});
			$("#gwType").change(function() {
				if($("#busType").val() == "-1"){
					return;
				}
				getTractInfo();
			});
			
			//选择支付类型时过滤通道
			$("#payType").change(function() {
				var select = " <option value=\"\">---请选择---</option>";
				var payType = $("#payType").val();
				if (list.length == 0) {
					alert("该支付类型下暂时没有配置通道,请重新选择");
					return;
				}
				var flag = false;   //是否已经配置过该支付类型下的通道
				for ( var i = 0; i < list.length; i++) {
					if(payType == list[i].payType){
						select += "<option  value=\"" + list[i].tractId + "\">" + list[i].tractName + "</option>";
						flag = true;
					}
				}
				if(!flag){
					alert("已经配置过该通道,请重新选择");
				}else{
					$("#tractId").html(select);
				}
				
			});
			

			// 选择通道的时候给tractType赋值
			$("#tractId").change(function() {
				var tractId = $("#tractId").val();
//				alert("tractid:" + tractId);
				for ( var i = 0; i < list.length; i++) {
					if (list[i].tractId == tractId) {
						$("#tractType").val(list[i].tractType);
						break;
					}
				}
//				alert("tractType:" + $("#tractType").val());
				var tractId = $("#tractId").val();
//				alert(tractId + "  获取显示通道的相关信息");
			});

			// alert("ss");
			$("#addSubBusInfoForm").validate({
				onsubmit : true,// 是否在提交是验证
				onfocusout : false,// 是否在获取焦点时验证
				onkeyup : false,// 是否在敲击键盘时验证
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

				// 提交表单
				submitHandler : function(form) {
					var param = $("#addSubBusInfoForm").serialize();
					url = $("#addSubBusInfoForm").attr("action");
                    $("#addSubBusInfosubmit").removeAttr("onclick");  //防止重复提交
					$.ajax({
						url : url,
						type : "post",
						data : param,
						success : function(data) {

							if (data.code == 200) {
							    alert('操作成功');
							    $("#addSubBusInfosubmit").attr("onclick","submitForm();");
								layer.closeAll();
								$("#submit_form",window.parent.document).submit();
							} else {
								alert('添加失败,数据填写有误,请重新输入！');
								$("#addSubBusInfosubmit").attr("onclick","submitForm();");
							}
						}
					});
				} 
			}); 

		});

// 弹出添加商户通道窗口
function addSubBusInfo(url) {

	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex : -1,
		shade : [ 0.2, '#000' ],
		offset : [ '50px', '' ],
		closeBtn : [ 1, true ],
		area : [ '940px', '420px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}

//提交表单
function submitForm(){
	 $("#addSubBusInfoForm").submit();
}


function updateStatusById(id,status,tractId,merSysId){
	var	contextPath = $("#contextPath").val();
	$.post(
		contextPath + "/subBusInfo/updatestatus",
		{
			 status:status,
			 id:id,
			 merSysId:merSysId,
			 tractId:tractId
		},
		function(data){
//			alert(data);
			if (data.code == 200) {
				alert('操作成功');
				//parent.layer.closeAll();
				$("#submit_form").submit(); 
			} 	else if (data.code == 403) {
				alert(data.desc);
			} else {
				alert(data.desc);
			}
		}
	
	);
}

