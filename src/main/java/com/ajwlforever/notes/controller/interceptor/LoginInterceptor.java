package com.ajwlforever.notes.controller.interceptor;

import com.ajwlforever.notes.entity.User;
import com.ajwlforever.notes.service.UserService;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import com.ajwlforever.notes.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = CloudNotesUtil.getCookies(request,"token");
        if(!StringUtils.isBlank(token))
        {
            // allready login
            User user = userService.selectById(token.split(":",2)[1]);
            if(!StringUtils.isBlank(user.getCnUserToken()) ) {
                hostHolder.setUser(user);
                return true;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        User user = hostHolder.getUser();
        if(user!=null&&modelAndView!=null)
        {
            modelAndView.addObject("loginUser",user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
