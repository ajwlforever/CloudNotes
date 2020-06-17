CONTEXT_PATH = "http://localhost:8080/cloud_notes";

$(function () {
    $("#changePassword").click(changePassword);
    $("#back").click(function () {
            window.location= CONTEXT_PATH+"/edit";
    });
});

function changePassword() {

    var opassword = get('last_password').value;
    var npassword=get('new_password').value;
    var fpassword=get('final_password').value;
    if(npassword!=fpassword)
    {
        alert("两次密码不一样！");
    }
    else
        if(opassword.length<6||npassword.length<6||fpassword.length<6||
            opassword.length>12||npassword.length>12||fpassword.length>12)
        {
            alert("密码长度大于6小于16！");
        }else
    $.ajax({
        url:CONTEXT_PATH+"/changePassword",
        type:"post",
        data:{
            "oldPassword":opassword,
            "newPassword":npassword
        },
        dataType:"json",
        success:function (result) {

           alert(result.msg);
            if(result.code==0) window.location=CONTEXT_PATH+"/";
        },
        error:function () {
            alert("修改密码失败");
        }
        }
    );
}