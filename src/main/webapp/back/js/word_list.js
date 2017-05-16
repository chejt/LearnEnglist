var wordName = "";
var wordType = "0";
function searchWord(){
	wordName = $("#wordName").val();
	wordType = $("#wordType").val();
	setCenterListData();
}
function setCenterListData(){
	$.ajax({
        url: "../allWordPaging.do",
        data: {
            page: 1,
            pageSize: 20,
            wordName:wordName,
            wordType:wordType
        },
        success: function (json) {
            var data = json.info2;
            //console.log(JSON.stringify(data))
            //在此处控制用户的权限(如果是老师，就不让删除)
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>单词</th><th scope='col'>释义</th><th scope='col'>详细</th>";
            if(json.adminRole==2||json.adminRole==1){
            	centerData += "<th scope='col'>操作</th>";
            }
            centerData += "</tr>";
            for (var i = 0; i < data.length; i++) {
            	centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td>" + data[i].meaning + "</td><td><a href='javascript:;' onclick='his(\"" + data[i].id + "\")'>详细</a></td>";
            	if(json.adminRole==2||json.adminRole==1){
            		centerData += "<td> <a href='javascript:void(0);' onclick='delOrg(" + data[i].id + ")'>删除</a></td>";
            	}
                centerData += "</tr>";
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
        type: "get",
        url: "../allWordPaging.do",
        data: {
            page: page,
            pageSize: 20,
            wordName:wordName,
            wordType:wordType
        },
        success: function (json) {
            var data = json.info2;
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>单词</th><th scope='col'>释义</th><th scope='col'>详细</th>";
            if(json.adminRole==2||json.adminRole==1){
            	centerData += "<th scope='col'>操作</th>";
            }
            centerData += "</tr>";
            for (var i = 0; i < data.length; i++) {
            	centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td>" + data[i].meaning + "</td><td><a href='javascript:;' onclick='his(\"" + data[i].id + "\")'>详细</a></td>";
            	if(json.adminRole==2||json.adminRole==1){
            		centerData += "<td> <a href='javascript:void(0);' onclick='delOrg(" + data[i].id + ")'>删除</a></td>";
            	}
                centerData += "</tr>";
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
        url: "deleteWord.do",
        data: {
        	wordId: id,
        },
        success: function (json) {
            var data = json.statue;
            if(data=="success"){
            	alert("delete success");
    			/*window.location.href="actroControll.jsp";*/
            }else{
            	alert("delete fail");
    			/*window.location.href="actroControll.jsp";*/
            }
            window.location.href="word_list.html";
        }
    })
}
function his(id){
	window.location.href="../wordDetail.do?wordId="+id;
}
