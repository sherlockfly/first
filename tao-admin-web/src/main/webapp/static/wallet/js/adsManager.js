function reloadPage() {

	window.location.href = "/wallet/admin/loopPicList";
}
//点击重置按钮重置表单
function resetForm() {

	document.getElementById("type").value = "0";
	document.getElementById("status").value = "-1";
	document.getElementById("remark").value = "";
	document.getElementById("belongTo").value = "";
}

var myid;
//修改广告的状态
function change(id) {
	//获取当前当前广告信息的操作名称
	var a = document.getElementById(id + "a");
	var value = $.trim(a.innerHTML);

	if (value == "禁用") {
		var mystatus = 0;
	} else {
		var mystatus = 1;
	}

	$.ajax({
		dataType : "json",
		type : "GET",
		url : "loopPicList/change?id=" + id + "&status=" + mystatus,
		error : function(data) {

		},
		success : function(data) {

			var vstatus = document.getElementById(id + "status");
			if (value == "禁用") {
				a.innerHTML = "启用";
				vstatus.innerHTML = "不可用";
			} else {
				a.innerHTML = "禁用";
				vstatus.innerHTML = "可用";
			}
		}
	});

}

//点击确定后从面板上获得数据异步修改，返回修改后的对象信息，将信息重新显示出来
function gotoChange() {

	var status = document.getElementById("status1").value;
	var type = document.getElementById("type1").value;
	var remark = document.getElementById("remark1").value;

	$.ajax({
		dataType : "json",
		type : "GET",
		url : "loopPicList/change?id=" + myid + "&status=" + status,
		error : function(data) {

		},
		success : function(data) {

			resetInfo(status, type, remark)
			close_div();
		}
	});
}

//根据用户添加的信息修改页面数据
function resetInfo(status, type, remark) {

	var vtype = document.getElementById(myid + "type");
	var vstatus = document.getElementById(myid + "status");
	var vremark = document.getElementById(myid + "remark");

	if (status == "0") {
		vstatus.innerHTML = "不可用";
	} else {
		vstatus.innerHTML = "可用";
	}

	if (type == "1") {
		vtype.innerHTML = "首页";
	} else {
		vtype.innerHTML = "头部";
	}

	vremark.innerHTML = remark;
}

//删除信息

function deleteInfo(id) {

	$.ajax({
		dataType : "json",
		type : "GET",
		url : "loopPicList/delete?id=" + id,
		error : function(data) {

		},
		success : function(data) {

			//removeNode(id);
			reloadPage();//删除后更新页面
		}
	});
}
//删除成功后删除该记录对应的节点
function removeNode(id) {

	var table = document.getElementById("father");
	var son = document.getElementById(id);

	son.remove();
}

function reloadPage() {

	document.getElementById("submit_form").submit();
}

