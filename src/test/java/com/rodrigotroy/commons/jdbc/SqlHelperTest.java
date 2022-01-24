package com.rodrigotroy.commons.jdbc;

import oracle.jdbc.pool.OracleDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: commons
 * User: rodrigotroy
 * Date: 27-11-19
 * Time: 16:37
 */
public class SqlHelperTest {
    private static final Logger LOG = LogManager.getLogger(SqlHelperTest.class);

    private OracleDataSource dsOracle;

    @Test
    public void oracleSelect() {
        try {
            SqlQuery sqlHelper = new SqlQuery(this.getOracleDataSource());

            URL url = SqlHelperTest.class.getResource("/CF_Propias.sql");

            if (url != null) {
                String sql = new String(Files.readAllBytes(Paths.get(url.getPath())));

                List<List<Object>> lists = sqlHelper.oracleSelect(sql,
                                                                  new String[]{"cod_fondo",
                                                                               "fecha_val"},
                                                                  Arrays.asList(50,
                                                                                "29-07-2019").toArray(),
                                                                  new int[]{Types.INTEGER,
                                                                            Types.VARCHAR});

                lists.forEach(LOG::info);

                sqlHelper.closeConnection();

                Assert.assertNotNull(lists);
            } else {
                Assert.fail();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private DataSource getOracleDataSource() throws
                                             SQLException {
        if (dsOracle == null) {
            dsOracle = new OracleDataSource();
            dsOracle.setURL("jdbc:oracle:thin:@111.111.111.111:1521:oracleDbb");
            dsOracle.setUser("");
            dsOracle.setPassword("");
        }

        return dsOracle;
    }
}
