
function addSubBusInfo(url) {
    
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset : [ '50px', '' ],
		closeBtn : [ 1, true ],
		area : [ '920px', '380px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		} 
	});
}

//查看代理商详情
function findMerInfoDetail(url) {
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset: ['40px',''],
		closeBtn : [ 1, true ],
		area : [ '800px', '530px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}
//添加,编辑代理商
function findMerInfoDetail(url) {
	var laySum = $.layer({
		type : 2,
		title : false,
		zIndex: -1,
		shade : [ 0.2, '#000' ],
		offset: ['40px',''],
		closeBtn : [ 1, true ],
		area : [ '800px', '550px' ],
		iframe : {
			src : url+'?isLayerRequest=true'
		}
	});
}

function containSpecial(str,special){
	for(i=0;i<str.length;i++){
		if(special.indexOf(str.charAt(i))>=0){
			return false;
		}
	}
	return true;
}
$(document).ready(function(){
//	alert("ss");
	jQuery.validator.addMethod("merRegno", function(value, element) {
		var chrnum = /^([0-9]{15})$/;
		return this.optional(element) || (chrnum.test(value));
		}, "工商注册号长度为15位"); 
	jQuery.validator.addMethod("merTaxno", function(value, element) {
		var chrnum = /^([0-9]{15})$/;
		return this.optional(element) || (chrnum.test(value));
		}, "税务登记号长度为15位");
	jQuery.validator.addMethod("isCardNo", function(value, element) {
		var chrnum = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		return this.optional(element) || (chrnum.test(value));
		}, "身份证格式不正确");
	jQuery.validator.addMethod("bankCard", function(value, element) {
		var chrnum = /^\d{16}|\d{19}$/;
		return this.optional(element) || (chrnum.test(value));
		}, "结算账号应为16或19位数字");
	jQuery.validator.addMethod("specialFonts", function(value, element) {
		var chrnum = "/^[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]$/";
		return this.optional(element) || (containSpecial(value,chrnum));
		}, "不能含有特殊字符");
	jQuery.validator.addMethod("busLicNo", function(value, element) {
		var chrnum = /^([0-9]{15})$/;
		return this.optional(element) || (chrnum.test(value));
		}, "营业执照号长度为15位");
	
	$("#addMerchantForm").validate({
		  onsubmit:true,// 是否在提交是验证
		  onfocusout:false,// 是否在获取焦点时验证
		  onkeyup :false,// 是否在敲击键盘时验证
		  rules: {
			  agentId :{
					required:true,
					minlength:1,
					agentIdTest:true
			  },
			  merShortName: {
				required:true,
				specialFonts:true,
				minlength: 2,
				maxlength: 25
			  },
			  merName: {
				  required:true,
				  specialFonts:true,
				  minlength: 2,
				  maxlength: 50
			  },
			  merRegno: {
				  required:true,
				  specialFonts:true,
				  digits:true,
				  merRegno:true
			  },
			  merRegaddr: {
				  required:true,
				  specialFonts:true,
				  maxlength: 80
			  },
			  merTaxno: {
				  required:true,
				  specialFonts:true,
				  digits:true,
				  merTaxno:true
			  },
			  merLegalPerson: {
				  required:true,
				  specialFonts:true,
				  maxlength: 15
			  },
			  merLegalIdcard: {
				  required:true,
				  specialFonts:true,
				  isCardNo:true
			  },
			  regCapital: {
				  required:true,
				  number:true,
				  min:0,
				  maxlength: 10
			  },
			  merKind: {
				  required:true,
				  specialFonts:true
			  },
			  billCycle: {
				  required:true
			  },
			  contacter: {
				  required:true,
				  specialFonts:true
			  },
			  contacterPhone: {
				  required:true,
				  specialFonts:true
			  },
			  contacter: {
				  required:true,
				  specialFonts:true
			  },
			  settAccountName:{
				  specialFonts:true,
				  specialFonts:true
			  },
			  settAccountNo:{
				  required:true,
				  bankCard:true,
				  specialFonts:true
			  },
			  lowestSettAmt:{
				  required:true,
				  number:true,
				  min:1
			  },
			  busLicNo: {
				  required:true,
				  specialFonts:true,
				  digits:true,
				  busLicNo:true
			  }
		  },
				/* 设置错误信息 */  
			 messages: {
				 lowestSettAmt: {       
					   number: "请输入有效的数字",
					   min:"最低结算金额必须大于0"
				   }
			},
		//提交表单
		submitHandler:function(form) {
			
			var agentId=$("#agentId").val();  //validate无法验证改变了结构的元素，单独验证
			if(agentId==""){	
				layer.alert("请选择代理商简称");
				return false;
			}
			var  publicKey=$('input[name="publicKey"]').val();
			if(publicKey==""){
				layer.alert("请上传公钥文件");
				return false;
			}
			
			var busLicBeginTime=$("#busLicBeginTime").val();
			if(busLicBeginTime!=''){	//这里加下营业执照的时间判断
				
				var curDate = new Date();			//营业执照开始日期不能大于当前日期
				var curMonth=curDate.getMonth()+1;
				var curDay=curDate.getDate();
				if(curMonth<10){
					curMonth="0"+curMonth;
				}
				if(curDay<10){
					curDay="0"+curDay;
				}
				var curDateStr=curDate.getFullYear()+"-"+curMonth+"-"+curDay;
				if(busLicBeginTime>curDateStr){	//日期不能大于当前日期
					layer.alert("营业执照有效期不能大于当前日期");
					return false;
				}
				var busLicEndTime=$("#busLicEndTime").val();
				if(busLicEndTime==''){
					var longTerm=$("#longTerm").val();
					if(longTerm!="1"){
						layer.alert("必须为长期");
						return false;
					}
				}else{
					
					if(busLicBeginTime>curDateStr){
						layer.alert("营业执照有效期开始时间不能大于当前时间");
						return false;
					}
					
					if(busLicBeginTime >= busLicEndTime){
						layer.alert("营业执照有效期开始时间不能大于等于结束时间");
						return false;
					}
				}
			}
			
			var param = $("#addMerchantForm").serialize();
			url = $("#addMerchantForm").attr("action");
			$.ajax({
//				url : url + "?yfSessionId=" + yfSessionId,
				url : url,
				type : "post",
				data: param,
				success : function(data) {
//					alert(data);
					
					if (data.code == 200) {
						alert('操作成功');
						layer.closeAll();
						$("#submit_form",window.parent.document).submit();
						//window.location.href = '/dup/role/index?yfSessionId=' + yfSessionId;
					} else{
						layer.alert('添加失败,数据填写有误,请重新输入！');
					}
				}
			});
		} //submitHandler:function(form) {
	}); //validate
});
		
$(document).ready(function(){
	$("#submitExamine").click(function() {
		var status = $("#entiyStatus").val();
		if(!status){
			layer.alert('请选择操作！');
		}else{
			var merId = $("#entiyMerId").val();
			$.ajax({
	             type: "post",
	             url: "/walletAdmin/wallet/updateStatus/"+merId+"/"+status+"",
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
	$("#submit_form").validate({
		rules: {
			merId: {
				number:true
			}
		},
		messages: {
			merId: "代理商或商户号应为整数"
		},
       showErrors : function(errorMap, errorList) {  
       		var msg = "";  
        	$.each(errorList, function(i, v) {  
         		msg += (v.message + "\r\n");  
        	});  
        	if (msg != "")  
         		layer.alert(msg); 
       		}  
    	});
});



