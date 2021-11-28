package ru.praktikum_services.qa_scooter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderParameterizedTest {

    private OrderClient orderClient;
    private int orderId;

    public final String firstName;
    public final String lastName;
    public final String address;
    public final int metroStation;
    public final String phone;
    public final int rentTime;
    public final String deliveryDate;
    public final String comment;
    public final String[] color;

    public OrderParameterizedTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderInfo() {
        return new Object[][]{
                {"firstName", "lastName", "address", 4, "phone", 1, "2020-06-06", "Comment", new String[]{}},
                {"firstName", "lastName", "address", 4, "phone", 1, "2020-06-06", "Comment", new String[]{"BLACK", "GREY"}},
                {"firstName", "lastName", "address", 4, "phone", 1, "2020-06-06", "Comment", new String[]{"GREY"}}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void orderSuccessCreatedFieldColorTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        orderId = orderClient.create(order);
        assertTrue("Order is not created", orderId > 0);
    }
}
