package com.ajwlforever.notes.controller;


import com.ajwlforever.notes.entity.User;
import com.ajwlforever.notes.service.UserService;
import com.ajwlforever.notes.utils.NetworkUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ajwlforever.notes.utils.CloudNotesUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;



    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index(Model model)
    {
        return "/log_in";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String cnUserName,String cnUserPassword)
    {
        String msg = "";
        int code =1;
        if(StringUtils.isBlank(cnUserName)){
            code = 0;
            msg+="用户名不能为空";
        }
        if(StringUtils.isBlank(cnUserPassword)){
            code = 0;
            msg+="密码不能为空";
        }

        User user = userService.selectByName(cnUserName);
        if(user==null)
        {
            code = 0 ;
            msg+="用户名错误！";
        }
        if(!user.getCnUserPassword().equals(CloudNotesUtil.md5(cnUserPassword)))
        {
            code = 0 ;
            msg+="密码错误！";
        }
        if( code==0)
        {
            return CloudNotesUtil.toJsonString(code,msg);
        }

        //登陆成功 cookie

        return CloudNotesUtil.toJsonString(code,msg);
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(String cnUserName, String cnUserPassword, String cnUserNick, HttpServletRequest request) throws IOException {
        String msg = "";
        int code =1;
        if(StringUtils.isBlank(cnUserName)){
            code = 0;
            msg+="用户名不能为空";
        }
        if(StringUtils.isBlank(cnUserPassword)){
            code = 0;
            msg+="密码不能为空";
        }
        if(StringUtils.isBlank(cnUserNick)){
            code = 0;
            msg+="昵称不能为空";
        }

        String ip = NetworkUtil.getIpAddress(request);


        User user = new User();
        user.setCnUserId(CloudNotesUtil.generateUUID());
        user.setCnUserName(cnUserName );
        user.setCnUserPassword(CloudNotesUtil.md5(cnUserPassword));
        user.setCnUserToken("");
        user.setCnUserNick(cnUserNick);
        user.setCnIP(ip);
        if(userService.selectByName(user.getCnUserName())!=null)
        {
            code = 0;
            msg+="用户名已存在";
        }

        if(code==0)
        {
            return CloudNotesUtil.toJsonString(code,msg);
        }

        //ip chuli
         userService.insertUser(user);
        return CloudNotesUtil.toJsonString(code,msg);
    }
}
