package com.ajwlforever.notes.controller;


import com.ajwlforever.notes.annotation.LoginRequired;
import com.ajwlforever.notes.entity.User;
import com.ajwlforever.notes.service.UserService;
import com.ajwlforever.notes.utils.NetworkUtil;
import com.ajwlforever.notes.utils.NoteResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ajwlforever.notes.utils.CloudNotesUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;

    @Value("${server.servlet.context-path}")
    private  String contextPath;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String index(Model model)
    {
        return "/log_in";
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(String cnUserName, String cnUserPassword, HttpServletResponse response)
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
        String token = CloudNotesUtil.generateUUID().replace("-","").substring(0,9);
        userService.updateToken(user.getCnUserId(),token);
        Cookie cookie =new Cookie("token",token+":"+user.getCnUserId());
        cookie.setMaxAge(3688 * 24 * 14);
        cookie.setPath(contextPath);
        response.addCookie(cookie);


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

    @LoginRequired
    @RequestMapping(path = "/logout" ,method = RequestMethod.GET)
    public String logout(@CookieValue("token")String token)
    {
        String[] s =  token.split(":",2);
        userService.updateToken(s[1],"");
        return "redirect:/index";
    }

    @LoginRequired
    @RequestMapping(path = "/changePassword" ,method = RequestMethod.GET)
    public  String changePassword(HttpServletRequest request)
    {
      return "/Change_password";
    }

    @LoginRequired
    @RequestMapping(path = "/changePassword" , method =  RequestMethod.POST)
    @ResponseBody
    public NoteResult<String> changePassword(String oldPassword, String newPassword,@CookieValue("token")String token)
    {
        System.out.println(oldPassword+":"+newPassword);
        String  userId =  token.split(":",2)[1];
        User user = userService.selectById(userId);
        NoteResult<String> result = new NoteResult<>();
        if(!user.getCnUserPassword().equals(CloudNotesUtil.md5(oldPassword)))
        {
            //旧密码不对
            result.setStatus(1);
            result.setMsg("原密码不正确");
            return  result;
        }
        userService.updatePassword(userId,newPassword);
        result.setStatus(0);
        result.setMsg("修改密码成功");

        return result;
    }
}
