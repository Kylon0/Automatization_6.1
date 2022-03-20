package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
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
        int firstCardBalanceBefore = dashboardPage.getFirstCardBalance();
        int secondCardBalanceBefore = dashboardPage.getSecondCardBalance();
        var cardPage = dashboardPage.toCardOne();
        var transferInfo = DataHelper.getSendMoneyOne();
        cardPage.moneySend(transferInfo);
        int firstCardBalanceAfter = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfter = dashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceBefore + 100, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore - 100, secondCardBalanceAfter);
    }

    @Test
    void testTransfer2to1(){
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int firstCardBalanceBefore = dashboardPage.getFirstCardBalance();
        int secondCardBalanceBefore = dashboardPage.getSecondCardBalance();
        var cardPage = dashboardPage.toCardTwo();
        var transferInfo = DataHelper.getSendMoneyTwo();
        cardPage.moneySend(transferInfo);
        int firstCardBalance = dashboardPage.getFirstCardBalance();
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        int firstCardBalanceAfter = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfter = dashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceBefore - 100, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore + 100, secondCardBalanceAfter);
    }

    @Test
    void testTransferZeroOnFirst() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int firstCardBalanceBefore = dashboardPage.getFirstCardBalance();
        int secondCardBalanceBefore = dashboardPage.getSecondCardBalance();
        var cardPage = dashboardPage.toCardOne();
        var transferInfo = new DataHelper.SendMoney(String.valueOf(secondCardBalanceBefore),"5559 0000 0000 0002");
        cardPage.moneySend(transferInfo);
        int firstCardBalanceAfter = dashboardPage.getFirstCardBalance();
        int secondCardBalanceAfter = dashboardPage.getSecondCardBalance();
        assertEquals(firstCardBalanceBefore + secondCardBalanceBefore, firstCardBalanceAfter);
        assertEquals(secondCardBalanceBefore - secondCardBalanceBefore, secondCardBalanceAfter);
    }

    @Test
    void testTransferNegative() {
        var loginPage = open("http://localhost:9999/", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int secondCardBalanceBefore = dashboardPage.getSecondCardBalance();
        var cardPage = dashboardPage.toCardOne();
        var transferInfo = new DataHelper.SendMoney(String.valueOf(secondCardBalanceBefore + 1000),"5559 0000 0000 0002");
        cardPage.moneySend(transferInfo);
        int secondCardBalance = dashboardPage.getSecondCardBalance();
        assert (secondCardBalance > 0);
    }
}
