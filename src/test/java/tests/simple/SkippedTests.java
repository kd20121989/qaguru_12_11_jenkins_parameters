package tests.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SkippedTests {

    @Disabled
    @Test
    void test01() {
        Assertions.assertTrue(true);
    }

    @Disabled
    @Test
    void test02() {
        Assertions.assertTrue(true);
    }

    @Disabled
    @Test
    void test03() {
        Assertions.assertTrue(true);
    }

    @Disabled
    @Test
    void test04() {
        Assertions.assertTrue(false);
    }
}
