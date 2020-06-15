package com.ajwlforever.notes;

import com.ajwlforever.notes.entity.User;
import com.ajwlforever.notes.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.ajwlforever.notes.utils.CloudNotesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CloudnotesApplication.class)
public class UserTest {

    @Autowired
    private UserService userService;


    @Test
    public void selectUser()
    {
        User user = userService.selectByName("test1");
        System.out.println(user);
        User user1 = userService.selectById("03590914-a934-4da9-ba4d-b41799f917d1");
        System.out.println(user1);
    }
    @Test
    public void insertUser()
    {
            User user = new User();
            user.setCnUserId("39295a3d-cc9b-42b4-b206-a2e7fab7e77cs");
            user.setCnUserName("ajwl");
            user.setCnUserPassword("96e79218965eb72c92a549dd5a330112");
            user.setCnUserToken("");
            user.setCnUserNick("adwad");
            user.setCnIP("192.168.1.1");

            userService.insertUser(user);
            user.setCnUserId(CloudNotesUtil.generateUUID());
    }



}
