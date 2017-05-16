function searchArticle(){
	setCenterListData();
}
function setCenterListData(){
	$.ajax({
        url: "allAdminPaging.do",
        data: {
            page: 1,
            pageSize: 10,
        },
        success: function (json) {
            var data = json.info2;
            //console.log(JSON.stringify(data))<a href="word_list.html"
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>管理员名称</th><th scope='col'>当前角色</th>";
            centerData += "<th scope='col'>修改角色</th>";
            centerData += "</tr>";
            for (var i = 0; i < data.length; i++) {
            	var roleStr = "老师";
            	if(data[i].role_id==1){
            		roleStr = "总管理员"
            	}else if(data[i].role_id==2){
            		roleStr = "管理员";
            	}else if(data[i].role_id==3){
            		roleStr="老师";
            	}
                centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td>" + roleStr + "</td>"
                centerData += "<td> <a href='javascript:void(0);' onclick='updateRole(" + data[i].id + ")'>修改角色</a></td>";
                centerData+="</tr>";
            }
            $(".centerData").html(centerData);
            document.getElementById("currentpage").value = json.currentpage;
            document.getElementById("totalpage").value = json.totalpage;
            currentpage = parseInt($("#currentpage").val());
            totalpage = parseInt($("#totalpage").val());
            initPage(currentpage, totalpage);
        }
    })
}
function topage(page) {
	$.ajax({
        type: "POST",
        url: "allAdminPaging.do",
        data: {
            page: page,
            pageSize: 10,
        },
        success: function (json) {
            var data = json.info2;
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>管理员名称</th><th scope='col'>当前角色</th>";
            centerData += "<th scope='col'>修改角色</th>";
            centerData += "</tr>";
            for (var i = 0; i < data.length; i++) {
            	var roleStr = "老师";
            	if(data[i].role_id==1){
            		roleStr = "总管理员"
            	}else if(data[i].role_id==2){
            		roleStr = "管理员";
            	}else if(data[i].role_id==3){
            		roleStr="老师";
            	}
                centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td>" + roleStr + "</td>"
                centerData += "<td> <a href='javascript:void(0);' onclick='updateRole(" + data[i].id + ")'>修改角色</a></td>";
                centerData+="</tr>";
            }
            $(".centerData").html(centerData);
            document.getElementById("currentpage").value = json.currentpage;
            document.getElementById("totalpage").value = json.totalpage;
            currentpage = parseInt($("#currentpage").val());
            totalpage = parseInt($("#totalpage").val());
            initPage(currentpage, totalpage);
        }
    });
}
$(document).ready(function()
{
	setCenterListData()
});
function updateRole(id){
	window.location.href="updateRolePre.do?adminId="+id;
}
