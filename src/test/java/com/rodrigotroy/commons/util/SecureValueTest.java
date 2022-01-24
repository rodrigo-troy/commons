package com.rodrigotroy.commons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: exrtroy
 * Date: 12-12-2021
 * Time: 17:52
 */
public class SecureValueTest {
    @DataProvider
    public Object[][] numeros() {
        return new Object[][]{
                {Collections.singletonList(20), new BigDecimal("20.0000")},
                {Collections.singletonList(30), new BigDecimal("30.0000")}};
    }

    @Test(dataProvider = "numeros")
    public void test(Object o1,
                     BigDecimal o2) {
        final BigDecimal actual = SecureValue.cellRowToBigDecimal((List<Object>) o1,
                                                                  0);
        Assert.assertEquals(actual,
                            o2);
    }
}
