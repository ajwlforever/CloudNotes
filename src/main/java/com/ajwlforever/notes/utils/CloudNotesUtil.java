package com.ajwlforever.notes.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

public class CloudNotesUtil {

    //生成随机字符串
    public static String generateUUID()
    {
        return UUID.randomUUID().toString();
    }

    //Md5加密
    public static String md5(String key)
    {
        if(StringUtils.isBlank(key))
        {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());

    }

    public static String toJsonString(int code , String msg, Map<String,Object> map)
    {
        JSONObject object = new JSONObject();
        object.put("code",code);
        object.put("msg",msg);
        if(map!=null)
        {
            for (String key:map.keySet())
            {
                object.put(key,map.get(key));
            }
        }
        return  object.toJSONString();
    }
    public static String toJsonString(int code ,String msg)
    {
        return  toJsonString(code,msg,null);
    }
    public static String toJsonString(int code  )
    {
        return  toJsonString(code,null,null);
    }

    public static  String getCookies(HttpServletRequest request,String name)
    {
        if(request==null&&StringUtils.isBlank(name))
            return  null;

        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name))
                    return cookie.getValue();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(CloudNotesUtil.generateUUID());
        System.out.println(("sadasd:a455646546".split(":",2)[1]));
    }
}
