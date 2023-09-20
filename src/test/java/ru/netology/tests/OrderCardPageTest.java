package ru.netology.tests;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.OrderCardPage;
import ru.netology.pages.StartPage;
import com.codeborne.selenide.logevents.SelenideLogger;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderCardPageTest {
    StartPage startPage = open("http://localhost:8080/", StartPage.class);



    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void buyPositiveAllFieldValidApproved() {
        startPage.orderCardPage();
        var cardInfo = DataHelper.getApprovedCard();
        var orderCardPage = new OrderCardPage();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void buyPositiveAllFieldValidDeclined() {
        startPage.orderCardPage();
        var cardInfo = DataHelper.getDeclinedCard();
        var orderCardPage = new OrderCardPage();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());

    }

     @Test
    void buyNegativeAllFieldEmpty() {
        startPage.orderCardPage();
        var cardInfo = DataHelper.getEmptyCard();
        var orderCardPage = new OrderCardPage();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQLHelper.getOrderCount());

    }

    @Test
    void buyNegativeNumberCard15Symbols() {
        startPage.orderCardPage();
        var cardInfo = DataHelper.getNumberCard15Symbols();
        var orderCardPage = new OrderCardPage();
        orderCardPage.insertCardData(cardInfo);
        orderCardPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}
