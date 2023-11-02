package com.example.demo.controllers;

import com.example.demo.TestUtil;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestItemCon {
    private UserController userController;
    private CartController cartController;
    private ItemController itemController;
    private UserRepository userRepository = mock(UserRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);
    @Before
    public void setUp(){
        itemController = new ItemController();
        TestUtil.injectObject(itemController, "itemRepository", itemRepository);
        Item item = new Item();
        item.setId(18L);
        item.setName("Clothes");
        BigDecimal price = BigDecimal.valueOf(18.99);
        item.setPrice(price);
        item.setDescription("Nike Jacket");

        Item item2 = new Item();
        item2.setId(9L);
        item2.setName("Shoes");
        BigDecimal price2 = BigDecimal.valueOf(201.3);
        item2.setPrice(price2);
        item2.setDescription("ADIDAS Sneaker");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item2);
        when(itemRepository.findAll()).thenReturn(itemList);
        when(itemRepository.findById(18L)).thenReturn(java.util.Optional.of(itemList.get(0)));
        when(itemRepository.findByName("Clothes")).thenReturn(Collections.singletonList(itemList.get(0)));
        when(itemRepository.findById(9L)).thenReturn(java.util.Optional.of(itemList.get(1)));
        when(itemRepository.findByName("Shoes")).thenReturn(Collections.singletonList(itemList.get(0)));


    }

    @Test
    public void GetListItem() {
        ResponseEntity<List<Item>> responseItem = itemController.getItems();
        Assert.assertEquals(200, responseItem.getStatusCodeValue());
        List<Item> itemList = responseItem.getBody();
        Assert.assertNotNull(itemList);
        Assert.assertEquals(2, itemList.size());
    }

    @Test
    public void GetItemByID() {
        ResponseEntity<Item> responseItem = itemController.getItemById(18L);
        Assert.assertNotNull(responseItem);
        Assert.assertEquals(200, responseItem.getStatusCodeValue());
        Item item = responseItem.getBody();
        Assert.assertNotNull(item);
    }

    @Test
    public void GetItemByIDFail() {
        ResponseEntity<Item> responseItem = itemController.getItemById(19L);
        Assert.assertNotNull(responseItem);
        Assert.assertEquals(404, responseItem.getStatusCodeValue());
    }

    @Test
    public void GetItemByName() {
        ResponseEntity<List<Item>> responseItem = itemController.getItemsByName("Shoes");
        ResponseEntity<List<Item>> responseItem2 = itemController.getItemsByName("Clothes");
        Assert.assertNotNull(responseItem);
        Assert.assertNotNull(responseItem2);
        Assert.assertEquals(200, responseItem.getStatusCodeValue());
        Assert.assertEquals(200, responseItem2.getStatusCodeValue());
        List<Item> items = responseItem.getBody();
        List<Item> items2 = responseItem2.getBody();
        Assert.assertNotNull(items);
        Assert.assertNotNull(items2);
        Assert.assertEquals(1, items.size());
        Assert.assertEquals(1, items2.size());
    }

    @Test
    public void GetNameNotFail() {
        ResponseEntity<List<Item>> responseItem = itemController.getItemsByName("Puma");
        Assert.assertNotNull(responseItem);
        Assert.assertEquals(404, responseItem.getStatusCodeValue());
        Assert.assertNotEquals(200, responseItem.getStatusCodeValue());
    }
}
