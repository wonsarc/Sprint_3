package ru.praktikum_services.qa_scooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.clear(courierId);
    }

    @Test
    public void isCourierSuccessCreatedTest() {
        Courier courier = Courier.getRandom();
        boolean isCourierCreated = courierClient.createFullFields(courier);
        courierId = courierClient.loginSuccess(CourierCredentials.from(courier));
        assertTrue("Courier is not created", isCourierCreated);
    }

    @Test
    public void courierDoubleCreatedReturnErrorTest() {
        Courier courier = Courier.getRandom();
        String expectedErrorMessage = "Этот логин уже используется. Попробуйте другой.";
        String actualErrorMessage = courierClient.createDouble(courier);
        courierId = courierClient.loginSuccess(CourierCredentials.from(courier));
        assertEquals("Courier is created", actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void courierSuccessLoginTest() {
        Courier courier = Courier.getRandom();
        courierClient.createFullFields(courier);
        courierId = courierClient.loginSuccess(CourierCredentials.from(courier));
        assertTrue("Courier ID is incorrect", courierId > 0);
    }

    @Test
    public void courierDeleteTest() {
        Courier courier = Courier.getRandom();
        courierClient.createFullFields(courier);
        boolean isCourierDeleted = courierClient.delete(courierClient.loginSuccess(CourierCredentials.from(courier)));
        assertTrue("Courier is not delete", isCourierDeleted);
    }
}
