package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper(){}

        @Value
        public static class AuthInfo {
            private String login;
            private String password;
        }

        public static AuthInfo getAuthInfo() { return new AuthInfo("vasya", "qwerty123"); }

        public static AuthInfo getOtherAuthInfo() { return new AuthInfo ("kolya", "qwerty123"); }

        @Value
        public static class VerificationCode {
        private String code;
        }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo)
    { return new VerificationCode("12345"); }

        @Value
        public static class sendMoneyOne {
        private String sum;
        private String from;
        }

        public static sendMoneyOne getSendMoneyOne() {
            return new sendMoneyOne("100", "5559 0000 0000 0002");
        }
        public static sendMoneyOne getSendMoneyTwo() {
        return new sendMoneyOne("100", "5559 0000 0000 0001");
    }
        public static sendMoneyOne getSendMoneyThree() {
        return new sendMoneyOne("10000", "5559 0000 0000 0002");
    }

}
