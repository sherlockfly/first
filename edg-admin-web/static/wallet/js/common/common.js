
//翻页相关  hk 
function updatePageNo(pageNo){
	$("#pageNo").val(pageNo);
	$("#submit_form").submit();
}

function showFrameContent(url,navTitle){
	var iFrameElem = document.getElementById('mainFrame');
	iFrameElem.src = url;
	$('#navTitle').text(navTitle);

}
function showContent(url,obj){
	$("#menu .jgxx").each(function(i) {
        $(this).removeClass("jgxxBolder");
    });
	var className = $(obj).parent().attr("class");
	if(className == null || className == ''){
       	$(obj).parent().attr("class","listbgs");
		$(obj).parent().siblings().attr("class","");
	}
	var contentTitle = "";
	if(obj != null){
		var contentTitle= " > " + $(obj).text();
		if(url != null && url != ''){
			window.parent.showFrameContent(url,contentTitle);
		}
	}
}

//重置查询条件
$(document).ready(function(){
	$("#resetSelect").click(function(){
		$("input.resetInput").each(function(){
			$(this).val("");
		});
	});
});
