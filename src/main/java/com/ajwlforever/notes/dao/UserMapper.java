package com.ajwlforever.notes.dao;

import com.ajwlforever.notes.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectById(String cnUserId);
    User selectByName(String cnUserName);


    int insertUser(User user);
    int updatePassword(String cnUserId, String cnUserPassword);
    int updateToken(String cnUserId,String cnUserToken);

}
