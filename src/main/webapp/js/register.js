function register() {
	if($("#inputPassword").val()!=$("#inputRePassword").val()){
		alert("两次输入的密码不一致");
		return;
	}
	$.ajax({
		url : "registerAction.do",
		data : {
			email : $("#inputEmail").val(),
			password : $("#inputPassword").val(),
			name:$("#name").val()
		},
		success : function(json) {
			var data = json.statue;
			if (data == "success") {
				window.location.href = "login.html";
				alert("注册成功");
			} else {
				alert("注册失败");
			}
		}
	});
}
function checkPassword(){
	if($("#inputPassword").val()!=$("#inputRePassword").val()){
		alert("两次输入的密码不一致");
	}
}