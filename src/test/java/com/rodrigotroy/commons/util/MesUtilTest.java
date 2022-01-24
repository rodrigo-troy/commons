package com.rodrigotroy.commons.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by rodrigotroy on 11/6/14.
 */
public class MesUtilTest {
    private static final Logger LOG = LogManager.getLogger(MesUtilTest.class);

    @Test
    public void testMethod() {
        LOG.info("INICIO TEST MESUTIL");

        MesesUtil mesesUtil = new MesesUtil();

        final String[] meses = mesesUtil.getMeses();

        LOG.info(meses);

        for (int i = 0; i < 12; i++) {
            LOG.info(mesesUtil.getNumeroMes(meses[i]));
        }
    }
}
