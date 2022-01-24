package com.rodrigotroy.commons.jdbc;

import com.ibm.db2.jcc.DB2SimpleDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

public class SqlHelperDb2OutputParamTest {
    private static final Logger LOG = LogManager.getLogger(SqlHelperDb2OutputParamTest.class);

    @Test
    public void test() {
        try {
            SqlQuery sqlHelper = new SqlQuery(this.getDataSource());
            List<List<List<Object>>> call = sqlHelper.call("FED.USUARIO",
                                                           new Object[]{7890, null},
                                                           new int[]{Types.INTEGER, Types.VARCHAR},
                                                           new int[]{1, 0});

            call.forEach(LOG::info);

            sqlHelper.closeConnection();

            Assert.assertNotNull(call);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private DataSource getDataSource() {
        DB2SimpleDataSource ds = new DB2SimpleDataSource();
        ds.setServerName("3.85.101.205");
        ds.setPortNumber(50000);
        ds.setDatabaseName("JAVA_FED");
        ds.setDriverType(4);
        ds.setUser("");
        ds.setPassword("");

        return ds;
    }

}
