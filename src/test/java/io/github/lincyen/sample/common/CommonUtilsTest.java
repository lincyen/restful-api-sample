package io.github.lincyen.sample.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommonUtilsTest {

    @Test
    void validateNumber() {
        assertTrue(CommonUtils.validateNumber("0"));
        assertTrue(CommonUtils.validateNumber("1440"));
        assertFalse(CommonUtils.validateNumber("-1"));
    }

    @Test
    void validateEmail() {
        assertTrue(CommonUtils.validateEmail("hsyou@niepay.co.kr"));
        assertFalse(CommonUtils.validateEmail("Abc.example.com"));
        assertTrue(CommonUtils.validateEmail("#!$%&'*+-/=?^_`{}|~@example.org", true));
        assertFalse(CommonUtils.validateEmail("#!$%&'*+-/=?^_`{}|~@example.org"));
    }

    @Test
    void validateUrl() {
        assertTrue(CommonUtils.validateUrl("https://www.nicepay.co.kr"));
        assertFalse(CommonUtils.validateUrl("www.nicepay.co.kr"));
    }

    @Test
    void validIPv4Addr() {
        assertTrue(CommonUtils.validIPv4Addr("127.0.0.1"));
        assertFalse(CommonUtils.validIPv4Addr("172202312"));
        assertTrue(CommonUtils.validIPv4Addr("192.168.0.1"));
        assertFalse(CommonUtils.validIPv4Addr("asdsd"));
        assertFalse(CommonUtils.validIPv4Addr("127.0.0.0.0.0"));
        assertTrue(CommonUtils.validIPv4Addr("1.1.1.1"));
        assertFalse(CommonUtils.validIPv4Addr("8888.8888.8888.8888"));
        assertTrue(CommonUtils.validIPv4Addr("255.255.255.255"));
        assertFalse(CommonUtils.validIPv4Addr("256.256.256.256"));
    }

    @Test
    void isNumeric() {
        assertTrue(CommonUtils.isNumeric("123"));
        assertTrue(CommonUtils.isNumeric("0123"));
        assertTrue(CommonUtils.isNumeric("1234567890"));
        assertFalse(CommonUtils.isNumeric("-123"));
        assertFalse(CommonUtils.isNumeric("A123"));
        assertFalse(CommonUtils.isNumeric("123ㅁ"));
        assertFalse(CommonUtils.isNumeric("123*"));
    }
}