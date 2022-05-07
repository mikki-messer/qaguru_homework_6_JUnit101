package com.mikkimesser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс")
public class SimpleJUnitTest {
    @DisplayName("Метод")
    @Test
    void firstTest() {
        Assertions.assertTrue(3 > 2);
        Assertions.assertAll(
                () -> Assertions.assertTrue(3 >= 2, "равенство"),
                () -> Assertions.assertTrue(3 > 2, "неравенство")
        );
    }
}
