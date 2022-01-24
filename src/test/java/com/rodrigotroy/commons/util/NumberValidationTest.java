package com.rodrigotroy.commons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class NumberValidationTest {
    @DataProvider
    public Object[][] numeros() {
        return new Object[][]{
                {" 00", true},
                {"144         ", true},
                {"+009000009", false},
                {1, true},
                {"1", true},
                {1f, true},
                {1.10d, true},
                {666666, true},
                {1100L, true},
                {BigDecimal.ZERO, true},
                {"LALA", false},
                {"cero", false},
                {"", false},
                {null, false},
                {"  ", false},
                {" 00 ", true},
                {"-101", true},
                {"001", true},
                {"009", true},
                {"009000009", true},
                {"-009000009", true},
                {"++009000009", false},
                {"++009000009", false},
                {"0+09-000009", false},
                {"39     ", true},
                {"0965040", true}};
    }

    @DataProvider
    public Object[][] numerosConValidacion() {
        return new Object[][]{
                {" 00", true},
                {"144         ", true},
                {"+009000009", true},
                {"-009000009", true},
                {1, true},
                {"1", true},
                {1f, true},
                {1.10d, true},
                {666666, true},
                {1100L, true},
                {BigDecimal.ZERO, true},
                {"LALA", false},
                {"cero", false},
                {"", false},
                {null, false},
                {"  ", false},
                {" 00 ", true},
                {"-101", true},
                {"001", true},
                {"009", true},
                {"009000009", true},
                {"++009000009", false},
                {"++009000009", false},
                {"0+09-000009", false},
                {"0965040", true}};
    }

    @Test(dataProvider = "numeros")
    public void test(Object o1,
                     Object o2) {
        Assert.assertEquals(Validator.validateNumber(o1),
                            o2);
    }

    @Test(dataProvider = "numerosConValidacion")
    public void testConEdicion(Object o1,
                               Object o2) {
        Assert.assertEquals(Validator.validateNumber(o1,
                                                     true),
                            o2);
    }
}
