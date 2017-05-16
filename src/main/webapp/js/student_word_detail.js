/**
 * 分页入口
 * @param currentpage  当前页
 * @param totalpage  总页数
 */
function initPage(currentpage, totalpage){
	if(totalpage == 0) return;
	var sbf = new StringBuffer();
	if(currentpage == 1){
		sbf.append("<a href=\"javascript:void(0);\" id=\"lastpage\">上一个</a>");
	}
	else{
		sbf.append("<a href=\"javascript:topage(");
		sbf.append(currentpage-1);
		sbf.append(");\" id=\"lastpage\">上一个</a>");
	}
	/** 遍历页数---结束 */
	if(currentpage == totalpage){
		sbf.append("<a href=\"javascript:void(0);\" id=\"nextpage\">下一个</a>");
	}else{
		sbf.append("<a href=\"javascript:topage(");
		sbf.append(currentpage+1);
		sbf.append(");\" id=\"nextpage\">下一个</a>");
		sbf.append("<a href=\"javascript:topage(");
		sbf.append(totalpage);
		sbf.append(");\" id=\"endpage\"></a>");
	}
	if(totalpage == 1) {
		$(".mainlist_page").html("<a href=\"javascript:void(0);\">1</a>");
	} else {
		$(".mainlist_page").html(sbf.toString());
	}

}

/**
 * 是当前页
 * @param index 页号
 * @returns {String}
 */
function current_Y(index){
	var str = "<a href=\"javascript:void(0);\" class=\"page_current\">" + index + "</a>";
	return str;
}

/**
 * 不是当前页
 * @param index 页号
 * @returns {String}
 */
function current_N(index){
	var str = "<a href=\"javascript:topage(" + index + ");\">" + index + "</a>";
	return str;
}

/** 构建一个StringBuffer类-----开始 */
function StringBuffer(){ 
	this.content = new Array; 
}
StringBuffer.prototype.append = function(str){ 
	this.content.push(str);
}
StringBuffer.prototype.toString = function(){
	return this.content.join("");
}
StringBuffer.prototype.clear = function(){
    this.content = [];
};

var wordName = "";
var wordType = "0";
function searchWord(){
	wordName = $("#wordName").val();
	wordType = $("#wordType").val();
	setCenterListData();
}
var startTime = new Date();
function setCenterListData(){
	$.ajax({
        url: "allStudentWordPaging.do",
        data: {
            page: 1,
            pageSize: 1,
        },
        success: function (json) {
            var data = json.info2;
            var centerData = "";
            for (var i = 0; i < data.length; i++) {
                centerData += data[i].name + "<br/>" + data[i].soundmark+ "<br/>" + "<audio src='"+data[i].pronunciation+"' controls='controls'>Your browser does not support the audio element.</audio>" + "<br/>" + data[i].meaning+"<input type='hidden' id='student_word_id' value='"+data[i].student_word_id+"'>";
            }
            $(".centerData").html(centerData);
            document.getElementById("currentpage").value = json.currentpage;
            document.getElementById("totalpage").value = json.totalpage;
            $(".centerDataCount").html("1");
            $(".centerDataTotal").html($("#totalpage").val());
            currentpage = parseInt($("#currentpage").val());
            totalpage = parseInt($("#totalpage").val());
            initPage(currentpage, totalpage);
        }
    })
}
function topage(page) {
	var endTime = new Date();
	var time = endTime-startTime;
	var student_word_id = $("#student_word_id").val();
	//更新阅读时间
	$.ajax({
        type: "POST",
        url: "updateWordTime.do",
        data: {
            time: time,
            student_word_id:student_word_id
        },
        success: function (json) {
        }
    });
	$.ajax({
        type: "POST",
        url: "allStudentWordPaging.do",
        data: {
            page: page,
            pageSize: 1,
        },
        success: function (json) {
            var data = json.info2;
            var centerData = "";
            for (var i = 0; i < data.length; i++) {
                centerData += data[i].name + "<br/>" + data[i].soundmark+ "<br/>" + "<audio src='"+data[i].pronunciation+"' controls='controls'>Your browser does not support the audio element.</audio>"  + "<br/>" + data[i].meaning +"<input type='hidden' id='student_word_id' value='"+data[i].student_word_id+"'>";
            }
            $(".centerData").html(centerData);
            $(".centerDataCount").html(""+page);
            $(".centerDataTotal").html($("#totalpage").val());
            document.getElementById("currentpage").value = json.currentpage;
            document.getElementById("totalpage").value = json.totalpage;
            currentpage = parseInt($("#currentpage").val());
            totalpage = parseInt($("#totalpage").val());
            initPage(currentpage, totalpage);
        }
    });
	startTime = new Date();
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
    			/*window.location.href="actroControll.jsp";*/
            }else{
            	alert("add fail");
    			/*window.location.href="actroControll.jsp";*/
            }
        }
    })
}
function delOrg(id){
	$.ajax({
        url: "student/deleteWord.do",
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
        }
    })
}
function his(id){
	window.location.href="wordDetail.do?wordId="+id;
}
