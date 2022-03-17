package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardOnePage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void testTransfer1to2(){
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardOnePage = dashboardPage.toCardOne();
        var transferInfo = DataHelper.getSendMoneyOne();
        cardOnePage.moneySend(transferInfo);
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        assertEquals(10100, firstCardBalance);
    }

    @Test
    void testTransfer2to1(){
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardOnePage = dashboardPage.toCardTwo();
        var transferInfo = DataHelper.getSendMoneyTwo();
        cardOnePage.moneySend(transferInfo);
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        assertEquals(10000, secondCardBalance);
    }

    @Test
    void testTransferZeroOnSecond() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardOnePage = dashboardPage.toCardOne();
        var transferInfo = DataHelper.getSendMoneyThree();
        cardOnePage.moneySend(transferInfo);
        int firstCardBalance = dashboardPage.getSecondCardBalance();
        assertEquals(0, firstCardBalance);
    }

    @Test
    void testTransferNegative() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var cardOnePage = dashboardPage.toCardOne();
        var transferInfo = DataHelper.getSendMoneyThree();
        cardOnePage.moneySend(transferInfo);
        int firstCardBalance = dashboardPage.getSecondCardBalance();
        assertEquals(0, firstCardBalance);
    }
}
