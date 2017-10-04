 // 手机号码验证       
 jQuery.validator.addMethod("phone", function(value, element) {       
     var length = value.length;   
     var mobile = /^1[3|5|7|8|][0-9]{9}$/;   
     return this.optional(element) || (length == 11 && mobile.test(value));       
 }, "请正确填写您的手机号码");       
      
 // 用户名验证       5到25位 英文字母 数字 下划线组成
 jQuery.validator.addMethod("loginNo", function(value, element) {       
	 var loginNo =  /^[a-zA-Z0-9_]{5,25}$/;      
     return this.optional(element) || (loginNo.test(value));       
 }, "请输入正确格式的用户名"); 