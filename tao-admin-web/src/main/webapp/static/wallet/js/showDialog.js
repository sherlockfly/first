

function showDiolag(){

			//显示灰层
		 document.getElementById("bigDiv").style.display="block";  
          
        //设置透明度
        document.getElementById("bigDiv").style.filter="alpha(opacity=100)";  

			var _x = document.documentElement.clientWidth;  
			var _y = document.documentElement.clientHeight;  
			
			var diolag = document.getElementById("diolag");
			
			var a_h=250;
			var a_w=350;
			
			//计算弹出框的坐标
			var dleft = _x/2 - a_w/2;  
			var dtop = _y/2 - a_h/2;  
			
			document.getElementById("diolag").style.left=dleft+"px";
			document.getElementById("diolag").style.top=dtop+"px";
			document.getElementById("diolag").style.display="block";
			
		}

	
//关闭弹出框与灰层
function close_div(){  
        document.body.style.margin="";  
        document.getElementById("bigDiv").style.display="none";  
        document.getElementById("diolag").style.display="none";  
    }  

