var name = "";
function searchArticle(){
	name = $("#name").val();
	setCenterListData();
}
function setCenterListData(){
	$.ajax({
        url: "allArticlePaging.do",
        data: {
            page: 1,
            pageSize: 10,
            name:name
        },
        success: function (json) {
            var data = json.info2;
            //console.log(JSON.stringify(data))<a href="word_list.html"
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>图文标题</th><th scope='col'>内容</th>";
            if(json.adminRole==2||json.adminRole==1){
            	centerData += "<th scope='col'>操作</th>";
            }
            centerData += "</tr>";
            for (var i = 0; i < data.length; i++) {
                centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td><a href='../" + data[i].filepath + "'>图文内容</a></td>"
                if(json.adminRole==2||json.adminRole==1){
                	centerData += "<td> <a href='javascript:void(0);' onclick='delOrg(" + data[i].id + ")'>删除</a></td>";
                }
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
        url: "allArticlePaging.do",
        data: {
            page: page,
            pageSize: 10,
            name:name
        },
        success: function (json) {
            var data = json.info2;
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>图文标题</th><th scope='col'>内容</th><th scope='col'>操作</th></tr>";
            for (var i = 0; i < data.length; i++) {
                centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td><a href='../" + data[i].filepath + "'>图文内容</a></td><td> <a href='javascript:void(0);' onclick='delOrg(" + data[i].id + ")'>删除</a></td></tr>"
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
function delOrg(id){
	$.ajax({
        url: "deleteArticle.do",
        data: {
        	articleId: id,
        },
        success: function (json) {
            var data = json.statue;
            if(data=="success"){
            	alert("delete success");
            }else{
            	alert("delete fail");
            }
            window.location.href="article_list.html";
        }
    })
}
