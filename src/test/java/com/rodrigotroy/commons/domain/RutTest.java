package com.rodrigotroy.commons.domain;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 11/14/17
 * Time: 16:41
 */
public class RutTest {
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
                {"15330294-4", true},
                {"15330294-1", false},
                {"10", false},
                {"lala", false},
                {"13999--2", false},
                {"", false},
                {null, false},
                {"76216210-5", true}};
    }


    @Test(dataProvider = "listaRUTs")
    public void testValidateList(Object o1,
                                 Object o2) {
        Rut rutHolder = new Rut(o1 instanceof String ? o1.toString() : null);

        Assert.assertEquals(rutHolder.esValido(),
                            o2);
    }
}
