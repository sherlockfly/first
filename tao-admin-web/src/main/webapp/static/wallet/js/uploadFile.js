function validate(){
	
	var file=document.getElementById("fileToUpload");
	if(file.value==null||file.value==""){
		
		return false;
	}
	var remark=document.getElementById("remark2");
	if(remark.value==null||remark.value==""){
		
		return false;
	}
	var fileName =file.value;
	
	var extname = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length);  
    extname = extname.toLowerCase();//处理了大小写  
    if(extname!= "jpeg"&&extname!= "jpg"&&extname!= "gif"&&extname!= "png"){  
      
   
     return false;
     }  
    return true;
}


function upload(){
	if(!validate()){
		
		alert("请输入完整信息");
		return;
	}
	var fileName=document.getElementById("fileToUpload").value;
	var extname = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length);  
    extname = extname.toLowerCase();//处理了大小写  
    
	var status=document.getElementById("status2").value;
	var type=document.getElementById("type2").value;
	var remark=document.getElementById("remark2").value;
    var belongTo=document.getElementById("belongTo1").value;
    var linkAddress=document.getElementById("linkAddress1").value;
    
	var newremark=encodeURI("remark="+remark);
	 $.ajaxFileUpload  
	    ({  
	           
	    		url:"loopPicList/add?status="+status+"&type="+type+"&belongTo="+belongTo+"&linkAddress="+linkAddress+"&"+newremark,
	            secureuri:false,  
	            fileElementId:'fileToUpload',  
	            dataType: 'json',  
	            success: function (data, status)  
	            {  
	            	
	               alert(1); 
	              close_div1();
	              reloadPage();//增加后更新页面
	            },  
	            error: function (data, status, e)  
	            {  
	                
	               
	            }  
	              
	        }  
	    )  
	    return false;  
}