package ru.praktikum_services.qa_scooter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierParameterizedTest {

    private CourierClient courierClient;

    private final String login;
    private final String password;
    private final String firstName;
    private final String errorMessage;
    private final int statusCode;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {

    }

    public CourierParameterizedTest(String login, String password, String firstName, String errorMessage, int statusCode) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }


    @Parameterized.Parameters
    public static Object[][] getCourierInfo() {
        return new Object[][]{
                {"testLogin", "", "testFirstName", "Недостаточно данных для входа", 400},
                {"", "testPassword", "testFirstName", "Недостаточно данных для входа", 400},
                {"qazDlpo", "testPassword", "testFirstName", "Учетная запись не найдена", 404}
        };
    }


    @Test
    public void courierFailedCreatedTest() {
        Courier courier = new Courier(login, password, firstName);
        String expectedErrorMessage = "Недостаточно данных для создания учетной записи";
        String actualErrorMessage = courierClient.createNotFullFields(courier);
        assertEquals("Response incorrect error message", actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void courierFailedLoginTest() {
        String expectedErrorMessage = errorMessage;
        String actualErrorMessage = courierClient.loginFail(new CourierCredentials(login, password), statusCode);
        assertEquals("Response incorrect error message", actualErrorMessage, expectedErrorMessage);
    }
}
