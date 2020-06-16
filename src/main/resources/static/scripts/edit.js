CONTEXT_PATH = "http://localhost:8080/cloud_notes";

$(function () {
    loadUserBooks();
    $("#book_ul").on("click","li",loadBookNotes);
    $("#add_notebook").click(alertAddBookWindow);   //点击显示添加笔记本的框
    $("#can").on("click",".close,.cancle",closeAlertWindow);  //为框动态绑定关闭时事件
    $("#can").on("click",".sure",createABook);  //为框动态绑定关闭时事件

});

//点击bookul是点亮li并改写笔记列表
function loadBookNotes() {
//切换列表显示区
    $("#pc_part_6").hide();
    $("#pc_part_2").show();
    $("#pc_part_4").hide();
    $("#pc_part_7").hide();
    $("#pc_part_8").hide();
    //将点击的笔记本设置为选中状态
    $("#book_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取点击的笔记本li的ID值
    var bookId = $(this).data("bookId");
    //发送Ajax请求加载笔记
    $.ajax({
        url:CONTEXT_PATH+"/notes",
        type:"post",
        data:{"bookId":bookId},
        dataType:"json",
        success:function(result){
            if(result.status==0){//成功
                var notes = result.data;//获取返回的笔记集合
                //清除原有列表li
                $("#note_ul").empty();
                //循环生成笔记列表li
                for(var i=0;i<notes.length;i++){
                    var noteTitle =
                        notes[i].cn_note_title;//笔记标题
                    var noteId = notes[i].cn_note_id;//笔记ID
                    //创建一个笔记li元素
                    createNoteLi(noteId,noteTitle);
                }
            }
        },
        error:function(){
            alert("加载笔记列表失败");
        }
    });
}
//创建一个笔记li
function createNoteLi(noteId,noteTitle){
    var sli = "";
    sli+='<li class="online">';
    sli+='	<a>';
    sli+='		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
    sli+= noteTitle;
    sli+='		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli+='	</a>';
    sli+='	<div class="note_menu" tabindex="-1">';
    sli+='		<dl>';
    sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli+='			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli+='		</dl>';
    sli+='	</div>';
    sli+='</li>';
    var $li = $(sli);
    $li.data("noteId",noteId);//给li元素绑定笔记id值
    //添加到笔记列表ul元素中
    $("#note_ul").append($li);
}


function createABook() {

    var bookName = $("#input_notebook").val().trim();
    //TODO检查格式
    //发送Ajax请求
    $.ajax({
        url:CONTEXT_PATH+"/book/add",
        type:"post",
        data:{"bookName":bookName},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                //关闭对话框
                //closeAlertWindow();
                //添加笔记本li
                var bookId = result.data;//笔记本ID
                var sli = '<li class="online">';
                sli+= '	<a>';
                sli+= '		<i class="fa fa-book" title="online" rel="tooltip-bottom">';
                sli+= '		</i>';
                sli+= bookName;
                sli+= '	</a>';
                sli+= '</li>';
                //将bookId绑定到li元素上
                var $li = $(sli);
                $li.data("bookId",bookId);
                //添加到笔记本列表ul元素中
                console.log(sli);
                $("#book_ul").prepend($li);
                //提示成功
                alert(result.msg);
            }
        },
        error:function(){
            alert("创建笔记本失败");
        }
    });
}
//加载笔记本列表
function loadUserBooks(){


    $.ajax({
        url:CONTEXT_PATH+"/book/loadbooks",
        type:"post",
        data:{ },
        dataType:"json",
        success:function(result){
            console.log(result);
            if(result.status==0){//成功

                var books = result.data;//获取服务器返回的笔记本信息
                //循环笔记本数组
                for(var i=0;i<books.length;i++){
                    var bookName =
                        books[i].cnNotebookName;//获取笔记本名
                    var bookId = books[i].cnNotebookId;//获取笔记本ID
                    //生成笔记本列表li元素
                    createBookLi(bookId,bookName);
                }
            }
        },
        error:function(){
            alert("加载用户笔记本列表失败");
        }
    });
}
//创建笔记本列表li
function createBookLi(bookId,bookName){

    var sli = '<li class="online">';
    sli+= '	<a>';
    sli+= '		<i class="fa fa-book" title="online" rel="tooltip-bottom">';
    sli+= '		</i>';
    sli+= bookName;
    sli+= '	</a>';
    sli+= '</li>';
    //将bookId绑定到li元素上
    var $li = $(sli);
    $li.data("bookId",bookId);
    //添加到笔记本列表ul元素中
    console.log(sli);
    $("#book_ul").append($li);
}


function closeAlertWindow(){
    //将id为can的div清空
    $("#can").empty();//html("")也可以
    //隐藏背景
    $(".opacity_bg").hide();
}


function alertAddBookWindow()
{
    //弹出出对话框
    $("#can").load(CONTEXT_PATH+"/alert/alert_notebook.html" ,function () {
        $(".opacity_bg").show();
        $(".close").click(function () {
            $("#can").html("");
            //隐藏背景色
            $(".opacity_bg").hide();
        });
    } );
    //显示背景色


}

