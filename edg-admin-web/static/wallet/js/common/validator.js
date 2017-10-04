//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉子
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobileOfCom: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '手机号码格式不准确.'
    },
  
    telePhone : {// 验证电话号码
    	        validator : function(value) {
    	        	var reg = /(^(\(0\d{2,3}\)|0\d{2,3}-)?\d{6,10}$)|(^\d{2,5}-\d{6,10}$)|(^\(\d{2,5}\)\d{6,10}$)|(^1[3|4|5|8|9]\d{9}$)/;
    	        	//var reg = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?\d{6,10}$/;
    	            return reg.test(value);
    	        },
    	        message : "电话号码格式不正确."
   },
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
    //验证邮箱
    email: {
    	validator: function (value) {
    		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    		return reg.test(value);
    	},
    	message: '邮箱格式不正确.'
    },
    //验证QQ
    qq: {
    	validator: function (value) {
    		var reg = /^\d{5,20}$/;    
    		return reg.test(value);
    	},
    	message: 'QQ格式不正确.'
    },
    //用户账号验证(只能包括 _ 数字 字母) 
    account: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    },
    
    faxNo:{
    	validator:function(value){
    		var reg = /^((\(\d{2,3}\))|(\d{3}\-)|(\(0\d{2,3}\))|(0\d{2,3}-))?\d{6,12}$/;
    		return reg.test(value);
    	},
    	message:'传真格式不正确.'
    	
    },
    //身份证号码
    IdCardNo:{
    	validator: function (value) {
    		var reg =  /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    		 return reg.test(value);
    	},
    	message:'身份证格式不正确.'
    },
    //卡号
	bankaccount:{
		validator : function(value,param){
			return /^\d{15,22}$/i.test(value);
		},
		message : '银行卡号格式错误，请输入长度为15~22位之间的数字！'
	} ,
   
    //信用卡有效期交易
	validDebit:{
		validator : function(value,param){
			return /^\d{4}$/i.test(value);
		},
		message : '有效期格式错误'
	},
 
	//短信验证码校验
	validateCode:{
		validator : function(value,param){
			return /^\d{6}$/i.test(value);
		},
		message : '短信验证码格式错误'
	},
 
	userValidateCode:{
		validator : function(value,param){
			return /^\d{4}$/i.test(value);
		},
		message : '短信验证码格式错误'
	},
	
	//福卡卡号校验
	fukaCardNo:{
		validator : function(value,param){
			reg =  /(^\d{12}$)|(^\d{16}$)/;
			return reg.test(value);
		},
		message : '福卡卡号格式错误'
	}
 
});