var wordName = "";
var wordType = "0";
function searchWord(){
	wordName = $("#wordName").val();
	wordType = $("#wordType").val();
	setCenterListData();
}
function setCenterListData(){
	$.ajax({
        url: "allStudentWordPaging.do",
        data: {
            page: 1,
            pageSize: 10,
            wordName:wordName,
            wordType:wordType
        },
        success: function (json) {
            var data = json.info2;
            //console.log(JSON.stringify(data))
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>单词</th><th scope='col'>释义</th><th scope='col'>学习时长</th><th scope='col'>添加日期</th><th scope='col'>状态</th></tr>";
            for (var i = 0; i < data.length; i++) {
                centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td>" + data[i].meaning + "</td><td>" + data[i].time + "s</td><td>"+data[i].addDate+"</td><td>" + (data[i].flag?"正确":"错误") + "</td>";
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
        type: "post",
        url: "allStudentWordPaging.do",
        data: {
            page: page,
            pageSize: 10,
            wordName:wordName,
            wordType:wordType
        },
        success: function (json) {
            var data = json.info2;
            var centerData = "<tr id='alldata_id'><th scope='col'>序号</th><th scope='col'>单词</th><th scope='col'>释义</th><th scope='col'>学习时间</th><th scope='col'>状态</th></tr>";
            for (var i = 0; i < data.length; i++) {
                centerData += "<tr id='org-item-" + data[i].id + "'><td>" + (i + 1) + "</td><td>" + data[i].name + "</td><td>" + data[i].meaning + "</td><td>" + data[i].time + "s</td><td>" + (data[i].flag?"正确":"错误") + "</td>";
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
function addOrg(id){
	$.ajax({
        url: "student/addWord.do",
        data: {
        	wordId: id,
        },
        success: function (json) {
            var data = json.statue;
            if(data=="success"){
            	alert("add success");
            }else{
            	alert("add fail");
            }
            window.location.href="word_list.jsp";
        }
    })
}
function delOrg(id){
	$.ajax({
        url: "student/deleteStudentWord.do",
        data: {
        	wordid: id,
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
            window.location.href="word_list.jsp";
        }
    })
}
function his(id){
	window.location.href="wordDetail.do?wordId="+id;
}
