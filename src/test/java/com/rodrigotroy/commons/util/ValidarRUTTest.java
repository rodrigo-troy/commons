package com.rodrigotroy.commons.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 5/25/17
 * Time: 16:45
 */
public class ValidarRUTTest {
    @DataProvider
    public Object[][] listaRUTs() {
        return new Object[][]{
                {"13.665.383-0", true},
                {"13665383-0", true},
                {"15330294-4", true},
                {"8858000-1", true},
                {"r8.858.000-1", false},
                {"-8.858.000-1", false},
                {"8..000-1", false},
                {"8.858.00.0-1", false},
                {"18.858.000-K", true},
                {"18.858.000-k", true},
                {"18.858.000", false},
                {"10", false},
                {"lala", false},
                {"13999--2", false},
                {"", false},
                {"76216210-5", true}};
    }


    @Test(dataProvider = "listaRUTs")
    public void testValidateList(Object o1,
                                 Object o2) {
        Assert.assertEquals(Validator.validateRUT(o1.toString()),
                            o2);
    }
}
