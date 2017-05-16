function update() {
	$.ajax({
		url : "adminUpdateRole.do",
		data : {
			roleId : $("#roleId").val(),
			adminId : $("#adminId").val()
		},
		success : function(json) {
			var data = json.statue;
			if (data == "success") {
				window.location.href = "admin_list.jsp";
				alert("修改角色成功");
			} else {
				alert("修改角色失败");
			}
		}
	});
}
