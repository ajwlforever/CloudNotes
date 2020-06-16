CONTEXT_PATH = "http://localhost:8080/cloud_notes";

$(function () {
    $("#changePassword").click(changePassword);
    $("#back").click(function () {
            window.location= CONTEXT_PATH+"/edit";
    });
});

function changePassword() {
    alert("change");
    var opassword = get('last_password').value;
    var npassword=get('new_password').value;
    var fpassword=get('final_password').value;
    $.ajax({
        url:CONTEXT_PATH+"/changePassword",
        type:"post",
        data:{
            "old_password":opassword,
            "new_password":npassword
        },
        dataType:"json",
        success:function (result) {

        },
        error:function () {
            alert("修改密码失败");
        }
        }
    );
}