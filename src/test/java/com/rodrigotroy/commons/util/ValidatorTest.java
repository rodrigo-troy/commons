package com.rodrigotroy.commons.util;

import com.rodrigotroy.commons.model.DefaultListModel;
import com.rodrigotroy.commons.model.IListModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 23-05-16
 * Time: 14:52
 */
public class ValidatorTest {
    @DataProvider
    public Object[][] listaMail() {
        return new Object[][]{
                {"johndoe@gmail.com", true},
                {"1", false},
                {"rtroy@.com", false},
                {"pepe@pepe.pepe", true}};
    }

    @DataProvider
    public Object[][] zeros() {
        return new Object[][]{
                {"0000000000", true},
                {"0", true},
                {0, true},
                {1, false},
                {"lala", false},
                {0.0001, false},
                {0, true},
                {0.0, true}};
    }

    @DataProvider
    public Object[][] iListModelList() {
        IListModel notEmpty = new DefaultListModel();

        final List<List<Object>> collect = Arrays.stream(new Object[][]{{1, 2, 3}, {4, 5, 6}})
                                                 .map(Arrays::asList)
                                                 .collect(Collectors.toList());
        notEmpty.setRows(collect);

        return new Object[][]{
                {new DefaultListModel(), false},
                {notEmpty, true}};
    }

    @DataProvider
    public Object[][] listas() {
        return new Object[][]{
                {1, false},
                {null, false},
                {new ArrayList<>(), false},
                {new ArrayList<>(Arrays.asList(1,
                                               2,
                                               3,
                                               4)), true}};
    }

    @Test(dataProvider = "listaMail")
    public void testEmailGenerator(Object o1,
                                   Object o2) {
        Assert.assertEquals(Validator.validateMail(o1.toString()),
                            o2);
    }

    @Test(dataProvider = "zeros")
    public void zerosTest(Object o1,
                          Object o2) {
        Assert.assertEquals(Validator.isZero(o1),
                            o2);
    }


    @Test(dataProvider = "listas")
    public void testValidateList(Object o1,
                                 Object o2) {
        Assert.assertEquals(Validator.validateList(o1),
                            o2);
    }

    @Test(dataProvider = "iListModelList")
    public void testValidateiListModelList(Object o1,
                                           Object o2) {
        Assert.assertEquals(Validator.isNotEmpty((IListModel) o1),
                            o2);
    }
}
