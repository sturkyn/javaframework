package qa.utils;

import qa.utils.log.Reporter;

public class Assert {

    public static void assertEquals(Object actual, Object expected, String message) {
        Reporter.logAssert(message);
        Reporter.logAssert("Assert equals: Actual - " + actual +" : Expected - " + expected);
        org.testng.Assert.assertEquals(actual,expected, message);
    }

    public static void assertTrue(boolean condition, String message) {
        Reporter.logAssert(message);
        org.testng.Assert.assertTrue(condition, message);
    }

    public static void assertFalse(boolean condition, String message) {
        Reporter.logAssert(message);
        org.testng.Assert.assertFalse(condition, message);
    }

}