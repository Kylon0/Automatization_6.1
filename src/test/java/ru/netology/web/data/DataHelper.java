package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("kolya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class SendMoney {
        private String sum;
        private String from;
    }

    public static SendMoney getSendMoneyOne() {
        return new SendMoney("100", "5559 0000 0000 0002");
    }

    public static SendMoney getSendMoneyTwo() {
        return new SendMoney("100", "5559 0000 0000 0001");
    }

    public static SendMoney getSendMoneyThree() {
        return new SendMoney("10000", "5559 0000 0000 0002");
    }
}

