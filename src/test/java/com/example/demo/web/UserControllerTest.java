package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        TestingAuthenticationToken token = new TestingAuthenticationToken("user1", "12345", "ROLE_USER");
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Test
    public void testFindUsers() {

        List<User> user = userService.findUsers();
        Integer exceptedUserCount = 3;
        MatcherAssert.assertThat(user.size(), Matchers.equalTo(exceptedUserCount));

    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("{noop}12345");
        user.setFirstName("User1 FirstName Updated");
        user.setLastName("User1 LastName Updated");

        User beforeUptateUser = userService.findUserByUserName("user1");
        User afterUptateUser = userService.update(user);

        MatcherAssert.assertThat(beforeUptateUser.getFirstName(), Matchers.equalTo("User1 FirstName"));
        MatcherAssert.assertThat(afterUptateUser.getFirstName(), Matchers.equalTo("User1 FirstName Updated"));

    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

}
