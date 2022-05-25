package tests.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NegativeTests {

    @Test
    void test01() {
        Assertions.assertFalse(false);
    }

    @Test
    void test02() {
        Assertions.assertFalse(true);
    }

    @Test
    void test03() {
        Assertions.assertFalse(false);
    }

}
