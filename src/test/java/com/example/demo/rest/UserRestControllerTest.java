package com.example.demo.rest;

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
public class UserRestControllerTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        /*
         * Uygun yetki verilmediğinde AccessDeniedException hatası alırız.
         * */
        TestingAuthenticationToken token = new TestingAuthenticationToken("user3", "12345", "ROLE_ADMIN");
        SecurityContextHolder.getContext().setAuthentication(token);
    }


    @Test
    public void testFindOwners() {
        List<User> user = userService.findUsers();
        Integer exceptedUserCount = 3;
        MatcherAssert.assertThat(user.size(), Matchers.equalTo(exceptedUserCount));
    }

    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

}
