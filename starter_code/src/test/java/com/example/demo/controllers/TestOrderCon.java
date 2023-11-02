package com.example.demo.controllers;

import com.example.demo.TestUtil;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class TestOrderCon {
    private UserController userController;
    private OrderController orderController;
    private UserRepository userRepository = mock(UserRepository.class);
    private OrderRepository orderRepository = mock(OrderRepository.class);
    @Before
    public void setUp(){
        orderController = new OrderController();
        TestUtil.injectObject(orderController, "orderRepository", orderRepository);
        TestUtil.injectObject(orderController, "userRepository", userRepository);

        Item item = new Item();
        item.setId(18L);
        item.setName("Clothes");
        BigDecimal price = BigDecimal.valueOf(18.99);
        item.setPrice(price);
        item.setDescription("Nike Jacket");
        List<Item> items = new ArrayList<Item>();
        items.add(item);

        User user = new User();
        Cart cart = new Cart();
        user.setId(9);
        user.setUsername("huu");
        user.setPassword("123456");
        cart.setId(1L);
        cart.setUser(user);
        cart.setItems(items);
        BigDecimal total = BigDecimal.valueOf(1.23);
        cart.setTotal(total);
        user.setCart(cart);
        when(userRepository.findByUsername("huu")).thenReturn(user);
    }
    @Test
    public void CreateOrderUser() {
        ResponseEntity<UserOrder> responseOrder = orderController.submit("huu");
        Assert.assertEquals(200, responseOrder.getStatusCodeValue());
        UserOrder order = responseOrder.getBody();
        Assert.assertNotNull(order);
        Assert.assertEquals(1, order.getItems().size());
    }
    @Test
    public void CreateOrderUserFail() {
        ResponseEntity<UserOrder> responseOrder = orderController.submit("huu123");
        Assert.assertEquals(404, responseOrder.getStatusCodeValue());
    }
    @Test
    public void GetUserOrder() {
        ResponseEntity<List<UserOrder>> ordersUser = orderController.getOrdersForUser("huu");
        Assert.assertEquals(200, ordersUser.getStatusCodeValue());
        List<UserOrder> orders = ordersUser.getBody();
        Assert.assertNotNull(orders);
    }
    @Test
    public void GetUserOrderFail() {
        ResponseEntity<List<UserOrder>> ordersUser = orderController.getOrdersForUser("huutocdaihihihaha");
        Assert.assertEquals(404, ordersUser.getStatusCodeValue());
    }
}
