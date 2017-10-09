 $(function(){
	 
	 var url = window.location.href
	 alert(url);
	 var sessionId = $.cookie("yfwxSessionId"); 
	 if(sessionId == null||sessionId == ''){
		 window.location.href="../user/login.html?rt="+url;
	 }
 })


