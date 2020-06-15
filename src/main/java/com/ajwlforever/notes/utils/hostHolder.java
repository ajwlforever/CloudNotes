package com.ajwlforever.notes.utils;

import com.ajwlforever.notes.entity.User;
import org.springframework.stereotype.Component;

@Component
public class hostHolder  {

    private  ThreadLocal<User> Users = new ThreadLocal<>();


    public void setUser(User user)
    {
        Users.set(user);
    }

    public User getUser()
    {
        return Users.get();
    }
    public  void clear()
    {
        Users.remove();
    }
}
