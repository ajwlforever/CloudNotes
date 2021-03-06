package com.ajwlforever.notes.service;

import com.ajwlforever.notes.dao.UserMapper;
import com.ajwlforever.notes.entity.User;
import com.ajwlforever.notes.utils.CloudNotesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User selectById(String cnUserId)
    {
        return userMapper.selectById(cnUserId);
    }

    public User selectByName(String cnUserName)
    {
        return userMapper.selectByName(cnUserName);
    };


    public int insertUser(User user)
    {

        return  userMapper.insertUser(user);
    }
    public int updatePassword(String cnUserId, String cnUserPassword)
    {
        return userMapper.updatePassword(cnUserId, CloudNotesUtil.md5(cnUserPassword));
    }
    public int updateToken(String cnUserId, String cnUserToken)
    {
        return userMapper.updateToken(cnUserId,cnUserToken);
    }


}
