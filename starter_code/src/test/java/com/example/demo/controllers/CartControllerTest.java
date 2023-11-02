package com.example.demo.controllers;

import com.example.demo.TestUtil;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartControllerTest {
    private UserController userController;
    private CartController cartController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp(){
        userController = new UserController();
        cartController = new CartController();
        TestUtil.injectObject(cartController, "userRepository", userRepository);
        TestUtil.injectObject(cartController, "cartRepository", cartRepository);
        TestUtil.injectObject(cartController, "itemRepository", itemRepository);
        User user = new User();
        Cart cart = new Cart();
        user.setId(1);
        user.setUsername("huu");
        user.setPassword("huutocdaihihi");
        user.setCart(cart);
        when(userRepository.findByUsername("huu")).thenReturn(user);

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
        ModifyCartRequest requestCart = new ModifyCartRequest();
        requestCart.setItemId(18L);
        requestCart.setQuantity(2);
        requestCart.setUsername("huu");
        ResponseEntity<Cart> responseCart = cartController.addTocart(requestCart);

        Assert.assertNotNull(responseCart);
        Assert.assertEquals(200, responseCart.getStatusCodeValue());
        Cart c = responseCart.getBody();
        Assert.assertNotNull(c);
        Assert.assertEquals(BigDecimal.valueOf(361.8), c.getTotal());
    }
    @Test
    public void testCartAddWrongUserName() {
        ModifyCartRequest requestCart = new ModifyCartRequest();
        requestCart.setItemId(1L);
        requestCart.setQuantity(1);
        requestCart.setUsername("Joihn");
        ResponseEntity<Cart> responseCart = cartController.addTocart(requestCart);
        Assert.assertNull(userRepository.findByUsername("Joihn"));
        Assert.assertEquals(404, responseCart.getStatusCodeValue());
        System.out.println("huuwewq");
    }
    @Test
    public void testCartRemove() {
        ModifyCartRequest requestCart = new ModifyCartRequest();
        requestCart.setItemId(18L);
        requestCart.setQuantity(3);
        requestCart.setUsername("huu");
        ResponseEntity<Cart> responseCart = cartController.addTocart(requestCart);
        Assert.assertNotNull(responseCart);
        Assert.assertEquals(200, responseCart.getStatusCodeValue());

        requestCart = new ModifyCartRequest();
        requestCart.setItemId(18L);
        requestCart.setQuantity(1);
        requestCart.setUsername("huu");
        responseCart = cartController.removeFromcart(requestCart);

        Assert.assertNotNull(responseCart);
        Assert.assertEquals(200, responseCart.getStatusCodeValue());
        Cart c = responseCart.getBody();
        Assert.assertNotNull(c);
        Assert.assertEquals(BigDecimal.valueOf(361.8), c.getTotal());
    }
    @Test
    public void testCartRemoveFailed() {
        ModifyCartRequest requestCart = new ModifyCartRequest();
        requestCart.setItemId(18L);
        requestCart.setQuantity(3);
        requestCart.setUsername("huu");
        ResponseEntity<Cart> responseCart = cartController.addTocart(requestCart);
        requestCart = new ModifyCartRequest();
        requestCart.setItemId(18L);
        requestCart.setQuantity(1);
        requestCart.setUsername("huu");
        responseCart = cartController.removeFromcart(requestCart);

        Assert.assertNotNull(responseCart);
        Assert.assertEquals(200, responseCart.getStatusCodeValue());
        Cart c = responseCart.getBody();
        Assert.assertNotNull(c);
        Assert.assertEquals(BigDecimal.valueOf(361.8), c.getTotal());
    }
    @Test
    public void CartRemoveFaild() {
        ModifyCartRequest requestCart = new ModifyCartRequest();
        requestCart.setItemId(133L);
        requestCart.setQuantity(9);
        requestCart.setUsername("huulong");
        ResponseEntity<Cart> response = cartController.removeFromcart(requestCart);
        Assert.assertNotNull(response);
        Assert.assertEquals(404, response.getStatusCodeValue());
    }
}
