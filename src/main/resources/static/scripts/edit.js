CONTEXT_PATH = "";

$(function () {
    loadUserBooks();
     $("#add_note").on("click",function () {
         alertAddWindow("/alert/alert_note.html")
     });  //点击 显示 添加笔记的框
     $("#note_ul").on("click","li",showNotesContent);
    $("#note_ul").on("click",".btn_slide_down",showDownMenu);  //每个笔记点击 下拉菜单

    $("#book_ul").on("click","li",loadBookNotes);  //点击 笔记本 显示笔记

    $("#add_notebook").on("click",function () {
        alertAddWindow("/alert/alert_notebook.html");
    });   //点击显示添加笔记本的框
    $("#can").on("click",".close,.cancle",closeAlertWindow);  //为框动态绑定关闭时事件
    $("#can").on("click","#createNotebook",createABook);  //
    $("#can").on("click","#createNote",createANote);  //创建

    $("#save_note").click(saveNote);
 

    $("#rollback_button").on("click",function () {
        alertAddWindow("/alert/alert_delete_notebook.html");
    });
    $("#can").on("click","#sure_recyclenotebook",sureRecycleNotebook);
      //点击笔记菜单的x按钮，弹出确认框
     $("#note_ul").on("click",".btn_delete",function () {
            alertAddWindow("/alert/alert_delete_note.html");
     });
     //单击确认框中"删除"按钮
     $("#can").on("click","#sure_recyclenote",sureRecycleNote);
    // //点击笔记菜单的"转移"按钮,弹出转移对话框
    // $("#note_ul").on("click",".btn_move",alertMoveNoteWindow);
    // //确定转移笔记操作
    // $("#can").on("click","#sure_movenote",sureMoveNote);
    // //笔记分享操作
    // $("#note_ul").on("click",".btn_share",sureShareNote);
    // //搜索分享笔记
    // $("#search_note").keydown(searchShareNote);
    // //分享笔记查看
    // $("#search_ul").on("click","li",loadShareContent);
    // //收藏笔记
    // $("#search_ul").on("click","button",function(){
    //     //获取请求参数
    //     var $li = $(this).parents("li");
    //     var shareId = $li.data("shareId");
    //     //发送Ajax请求
    //     $.ajax({
    //         url:"http://localhost:8088/cloud_note_learn/share/favor.do",
    //         type:"post",
    //         data:{"shareId":shareId,"userId":userId},
    //         dataType:"json",
    //         success:function(result){
    //             if(result.status==0){
    //                 alert(result.msg);
    //             }else if(result.status==1){
    //                 alert(result.msg);
    //             }else if(result.status==2){
    //                 alert(result.msg);
    //             }
    //         },
    //         error:function(){
    //             alert("收藏笔记失败");
    //         }
    //     });
    // });


});

//删除笔记本
function  sureRecycleNotebook() {
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");//笔记本ID
    console.log("sureRecycleNotebook:"+bookId);
    $.ajax(
        {
            url:CONTEXT_PATH+"/recycleNotebook",
            type:"post",
            data:{"bookId":bookId},
            dataType:"json",
            success:function (result) {
                if(result.status==0)
                {
                    closeAlertWindow();
                    $li.empty();
                    console.log(bookId+result.msg);
                }
            },
            error:function () {
                    alert("笔记本彻底删除失败");
            }
        }
    );
}
//删除笔记
function  sureRecycleNote() {
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");

    $.ajax(
        {
            url:CONTEXT_PATH+"/recycleNote",
            type:"post",
            data:{"noteId":noteId},
            dataType:"json",
            success:function (result) {
                if(result.status==0)
                {
                    closeAlertWindow();
                    $li.empty();
                    console.log(noteId+result.msg);
                }
            },
            error:function () {
                alert("笔记回收失败");
            }
        }
    );

}
function saveNote() {
//获取请求参数
    var body = um.getContent();//笔记内容
    var title = $("#input_note_title").val();//笔记标题
    var $li = $("#note_ul a.checked").parent();
    var noteId = $li.data("noteId");//笔记ID
    //发送Ajax请求
    $.ajax({
        url:CONTEXT_PATH+"/saveNote",
        type:"post",
        data:{"noteId":noteId,
            "noteTitle":title,"noteBody":body},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                //更新列表标题名称
                var sli='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
                sli+= title;
                sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
                $li.find("a").html(sli);//替换<a>元素中的内容
                //提示保存完成
                alert(result.msg);
            }
        },
        error:function(){
            alert("保存笔记失败");
        }
    });

}
function showNotesContent() {

    //收起所有笔记x
    $(this).siblings().find(".note_menu").hide();
    //将点击的笔记设置为选中
    $("#note_ul a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取请求参数
    var noteId = $(this).data("noteId");
    //发送Ajax请求
    $.ajax({
        url:CONTEXT_PATH+"/loadNoteContent",
        type:"post",
        data:{"noteId":noteId},
        dataType:"json",
        success:function(result){
            if(result.status==0){
                var title =
                    result.data.cnNoteTitle;//标题
                var body =
                    result.data.cnNoteBody;//内容
                //显示到编辑区
                $("#input_note_title").val(title);
                um.setContent(body);//获取使用um.getContent();
                //显示编辑区,隐藏预览区
                $("#pc_part_3").show();
                $("#pc_part_5").hide();
            }
        },
        error:function(){
            alert("加载笔记内容失败");
        }});
}
function showDownMenu() {
    $(this).parent().next().slideToggle();
}
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
    console.log("click li:"+bookId);
    //发送Ajax请求加载笔记
    $.ajax({
        url:CONTEXT_PATH+"/loadNotes",
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
                        notes[i].cnNoteTitle;//笔记标题
                    var noteId = notes[i].cnNoteId;//笔记ID
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
function createANote() {
    var noteName = $("#input_note").val().trim();
    var $li = $("#book_ul a.checked").parent();
    var bookId = $li.data("bookId");//笔记本ID

    $.ajax({
        url:CONTEXT_PATH+"/addNote",
        type:"post",
        data:{
       "noteName":noteName,
        "bookId":bookId
    } ,
         dataType:"json",
        success:function (result) {
        if(result.status==0) {

             closeAlertWindow();
             var  noteId= result.data.cnNoteId;
             var noteTitle= result.data.cnNoteTitle;

     console.log(noteId +"::"+noteTitle);
    var sli = "";
    sli += '<li class="online">';
    sli += '	<a>';
    sli += '		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
    sli += noteTitle;
    sli += '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
    sli += '	</a>';
    sli += '	<div class="note_menu" tabindex="-1">';
    sli += '		<dl>';
    sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
    sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
    sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
    sli += '		</dl>';
    sli += '	</div>';
    sli += '</li>';
    var $li = $(sli);
    $li.data("noteId", noteId);//给li元素绑定笔记id值
    //添加到笔记列表ul元素中
    $("#note_ul").prepend($li);

}
        },
        error:function () {
            alert("创建笔记失败");
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
                 closeAlertWindow();
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
                $("#book_ul li:first").trigger("click");
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
    console.log( $li.data("bookId"));
    $("#book_ul").append($li);
}


function closeAlertWindow(){
    //将id为can的div清空
    $("#can").empty();//html("")也可以
    //隐藏背景
    $(".opacity_bg").hide();
}


//显示添加  框
function alertAddWindow(htmlUrl)
{
    //弹出出对话框
    $("#can").load(CONTEXT_PATH+htmlUrl ,function () {
        $(".opacity_bg").show();
        $(".close").click(function () {
            $("#can").html("");
            //隐藏背景色
            $(".opacity_bg").hide();
        });
    } );
    //显示背景色


}

