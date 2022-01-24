package com.rodrigotroy.commons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 2019-05-24
 * Time: 11:05
 */
public class NumberFactoryTest {
    @DataProvider
    public Object[][] stripTrailingZero() {
        return new Object[][]{{0, "0"},
                              {"0", "0"},
                              {"0,0", "0"},
                              {"0.0", "0"},
                              {000, "0"},
                              {"1200,2", "1200.2"},
                              {"1,200,200", "1200200"},
                              {"1,200,200.0", "1200200"},
                              {"1.200.200", "1200200"},
                              {"1.200,2", "1200.2"},
                              {"1.200,200", "1200.2"},
                              {"1,200,200.10", "1200200.1"},
                              {"1,200,200.0.0", ""},
                              {1200200.0, "1200200"},
                              {Integer.valueOf("98888"), "98888"},
                              {Double.valueOf(2.0), "2"},
                              {Double.valueOf(2.111), "2.111"},
                              {Double.valueOf(0.111), "0.111"},
                              {Double.valueOf(0), "0"},
                              {"pepe", ""},
                              {"1.1.1.1", ""},
                              {new BigDecimal(1.0), "1"}};
    }

    @Test(dataProvider = "stripTrailingZero")
    public void test(Object o1,
                     Object o2) {
        Assert.assertEquals(NumberUtil.getValueAsString(o1,
                                                        true,
                                                        false,
                                                        Locale.GERMAN),
                            o2);
    }
}
