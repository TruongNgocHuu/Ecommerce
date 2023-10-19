package com.example.demo.controllers;

import com.example.demo.TestUtil;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserController userController;
    private CartController cartController;
    private UserRepository userRepositor = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private BCryptPasswordEncoder encoder =  mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp(){
        userController = new UserController();
        cartController = new CartController();
        TestUtil.injectObject(userController, "userRepository",userRepositor);
        TestUtil.injectObject(userController, "cartRepository",cartRepository);
        TestUtil.injectObject(userController, "bCryptPasswordEncoder",encoder);
        TestUtil.injectObject(cartController, "cartRepository",cartRepository);
    }
    @Test
    public void testCreateUser() throws Exception{
        when(encoder.encode("huutocdaihihi")).thenReturn("HashPassword");
        CreateUserRequest r = new CreateUserRequest();
        r.setUsername("huu");
        r.setPassword("huutocdaihihi");
        r.setConfirmPassword("huutocdaihihi");
        final ResponseEntity<User> responseEntity = userController.createUser(r);
        User user = responseEntity.getBody();
        when(userRepositor.findByUsername("huu")).thenReturn(user);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(200,responseEntity.getStatusCodeValue());
        User u = responseEntity.getBody();
        Assert.assertNotNull(u);
        Assert.assertEquals(0,u.getId());
        Assert.assertEquals("huu",u.getUsername());
        Assert.assertEquals("HashPassword",u.getPassword());
        ResponseEntity<User> responseEntity1 = userController.findByUserName("huu");
        Assert.assertNotNull(responseEntity1);
    }



}
