package ru.praktikum_services.qa_scooter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;

public class OrderTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void getInfoOrderTest() {
        Order order = new Order(
                "Naruto", "Uchiha", "Konoha, 142 apt.",
                4, "+7 800 355 35 35", 5, "2020-06-06",
                "Saske, come back to Konoha", new String[]{"GREY"});

        HashMap<String, String> orderInfo = orderClient.getOrder(orderClient.create(order));
        assertFalse("Order is not found", orderInfo.isEmpty());
    }
}
