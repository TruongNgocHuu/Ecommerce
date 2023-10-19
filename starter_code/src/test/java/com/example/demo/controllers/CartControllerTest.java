package com.example.demo.controllers;

import com.example.demo.TestUtil;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest {
    private UserController userController;
    private CartController cartController;
    private UserRepository userRepositor = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp(){
        userController = new UserController();
        cartController = new CartController();
        TestUtil.injectObject(cartController, "userRepository", userRepositor);
        TestUtil.injectObject(cartController, "cartRepository", cartRepository);
        TestUtil.injectObject(cartController, "itemRepository", itemRepository);
        User user = new User();
        Cart cart = new Cart();
        user.setId(1);
        user.setUsername("huu");
        user.setPassword("huutocdaihihi");
        user.setCart(cart);
        when(userRepositor.findByUsername("huu")).thenReturn(user);

        Item item = new Item();
        item.setId(18L);
        item.setName("Round Widget");
        BigDecimal price = BigDecimal.valueOf(180.9);
        item.setPrice(price);
        item.setDescription("A widget that is round");
        when(itemRepository.findById(18L)).thenReturn(java.util.Optional.of(item));
    }
    @Test
    public void testCart() throws Exception {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(18L);
        request.setQuantity(2);
        request.setUsername("huu");
        ResponseEntity<Cart> response = cartController.addTocart(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Cart c = response.getBody();
        Assert.assertNotNull(c);
        Assert.assertEquals(BigDecimal.valueOf(361.8), c.getTotal());
    }
    @Test
    public void testCartRemove() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(18L);
        request.setQuantity(3);
        request.setUsername("huu");
        ResponseEntity<Cart> response = cartController.addTocart(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCodeValue());

        request = new ModifyCartRequest();
        request.setItemId(18L);
        request.setQuantity(1);
        request.setUsername("huu");
        response = cartController.removeFromcart(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCodeValue());
        Cart c = response.getBody();
        Assert.assertNotNull(c);
        Assert.assertEquals(BigDecimal.valueOf(361.8), c.getTotal());

    }
}
