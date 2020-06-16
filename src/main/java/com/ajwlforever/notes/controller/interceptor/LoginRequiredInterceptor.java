package com.ajwlforever.notes.controller.interceptor;

import com.ajwlforever.notes.annotation.LoginRequired;
import com.ajwlforever.notes.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod)
        {
            Method method =(Method) ((HandlerMethod) handler).getMethod();
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
         if(loginRequired!=null && hostHolder.getUser()==null)
            {
                response.sendRedirect(request.getContextPath()+"/index");
        return false;
             }

        }

        return  true;
    }
}
