
var recordRate;
//根据select列表选中的当前option的value来获取对应的汇率值
function getRateById(obj){
	
	var index=obj.selectedIndex;
	var id=obj.options[index].value;
//	alert(id);
	if(id==0){
		document.getElementById("other").value=0;
		return;
	}
	getRate(id);
}

function getRate(id){
	
	 $.ajax({  
	        dataType:"json",
	        type:"GET",  
	        url:"getRate?id="+id,  
	        error:function(data){  
	              
	        },  
	        success:function(data){  
	           
	        	
	        	document.getElementById("other").value=data.rate.rate;
	        	recordRate=data.rate.rate;//记录rate与更新时的rate进行比较，若相同，则返回
	        }  
	        });  
}


function updateRate(){
	
	
	var index=document.getElementById("rates").selectedIndex;
	var id=document.getElementById("rates").options[index].value;
	
	var rate=document.getElementById("other").value;
	if(id==0)return;
	
	if(checkRate(rate)!=true){
		
		alert("汇率输入不合法");
		return;
	}
	if(recordRate==rate)return;
	 $.ajax({  
	        dataType:"json",
	        type:"GET",  
	        url:"updateRate?id="+id+"&rate="+rate,  
	        error:function(data){  
	              
	        },  
	        success:function(data){  
	           
	        	
	        	alert(data.result);
	        }  
	        });  
}

//正则不正确，需改正
function checkRate(rate){
	
	 return /^\d+(\.\d+)?$/.test(rate);
}
function checkNumberAndPoint(keyCode) {
	//小数点
	if(keyCode==190) return true;
	if(keyCode==46) return true;
    // 数字
    if (keyCode >= 48 && keyCode <= 57 ) return true;
    // 小数字键盘
    if (keyCode >= 96 && keyCode <= 105) return true;
    // Backspace键
    if (keyCode == 8) return true;
    
    return false;
}
function preventInput(e) {
    var keyCode = e.keyCode
    if ( !checkNumberAndPoint(keyCode) ) return false;           
}
