function add(){
	
	//显示灰层
 document.getElementById("bigDiv1").style.display="block";  
  
//设置透明度
document.getElementById("bigDiv1").style.filter="alpha(opacity=100)";  



	var _x = document.documentElement.clientWidth;  
	var _y = document.documentElement.clientHeight;  
	
	var diolag = document.getElementById("diolag1");
	
	var a_h=450;
	var a_w=550;
	
	//计算弹出框的坐标
	var dleft = _x/2 - a_w/2;  
	var dtop = _y/2 - a_h/2;  
	
	

	document.getElementById("diolag1").style.left=dleft+"px";
	document.getElementById("diolag1").style.top=dtop+"px";
	document.getElementById("diolag1").style.display="block";
	
	
}


//关闭弹出框与灰层
function close_div1(){  
document.body.style.margin="";  
document.getElementById("bigDiv1").style.display="none";  
document.getElementById("diolag1").style.display="none";  
}  
