package com.rodrigotroy.commons.jdbc;

import com.rodrigotroy.commons.util.Validator;
import oracle.jdbc.OraclePreparedStatement;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.sql.Statement.SUCCESS_NO_INFO;

/**
 * Created with IntelliJ IDEA.
 * $ Project: gathercommons
 * User: rodrigotroy
 * Date: 10-11-15
 * Time: 10:48
 */
public class SqlQuery implements Serializable {
    private static final Logger LOG = LogManager.getLogger(SqlQuery.class);

    private final transient DataSource dataSource;
    private final Boolean autocommit;
    private transient Connection connection;

    public SqlQuery(DataSource dataSource) {
        this.dataSource = dataSource;
        this.autocommit = true;
    }

    public SqlQuery(DataSource dataSource,
                    Boolean autocommit) {
        this.dataSource = dataSource;
        this.autocommit = autocommit;
    }

    public int createTable(String sentence) throws
                                            SQLException {
        LOG.debug("INICIO CREACION TABLA");

        return this.executeSQL(sentence);
    }

    public int delete(String sentence) throws
                                       SQLException {
        LOG.debug("INICIO DELETE");

        return this.executeSQL(sentence);
    }

    public int update(String sentence) throws
                                       SQLException {
        LOG.debug("INICIO UPDATE");

        return this.executeSQL(sentence);
    }

    public int dropTable(String sentence) throws
                                          SQLException {
        LOG.debug("INICIO DROP TABLE");

        return this.executeSQL(sentence);
    }

    public int insert(String sentence) throws
                                       SQLException {
        LOG.debug("INICIO INSERT");

        return this.executeSQL(sentence);
    }

    public int batchInsert(String sentence,
                           List<List<Object>> rows) throws
                                                    SQLException {
        return this.batchInsert(sentence,
                                rows,
                                false);
    }

    public int batchInsert(String sentence,
                           List<List<Object>> rows,
                           Boolean useGetUpdateCount) throws
                                                      SQLException {
        LOG.debug("INICIO INSERT");

        if (!Validator.validateList(rows)) {
            LOG.warn("NO EXISTEN REGISTROS PARA INSERTAR");
            return -1;
        }

        int result = 0;

        crearConexion();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sentence)) {
            for (List<Object> row : rows) {
                int x = 1;
                for (Object object : row) {
                    preparedStatement.setObject(x,
                                                object);
                    x++;
                }

                preparedStatement.addBatch();
            }

            LOG.debug(sentence);

            int[] numUpdates = preparedStatement.executeBatch();


            if (useGetUpdateCount) {
                result = preparedStatement.getUpdateCount();
            } else {
                for (int i = 0; i < numUpdates.length; i++) {
                    if (numUpdates[i] == SUCCESS_NO_INFO) {
                        LOG.warn("Execution " + i + ": unknown number of rows updated");
                    } else {
                        LOG.debug("Execution " + i + "successful: " + numUpdates[i] + " rows updated");
                        result++;
                    }
                }
            }

            this.connection.commit();

            LOG.debug("Resultado " + result);
        } catch (BatchUpdateException e) {
            int[] updateCounts = e.getUpdateCounts();

            for (int i = 0; i < updateCounts.length; i++) {
                LOG.error("Statement " + i + ":" + updateCounts[i]);
            }

            LOG.error(" SQLSTATE: " + e.getSQLState());
            LOG.error(" Error code: " + e.getErrorCode());
            SQLException ex = e.getNextException();

            while (ex != null) {
                LOG.error("SQL exception:");
                LOG.error(" Message: " + ex.getMessage());
                LOG.error(" SQLSTATE: " + ex.getSQLState());
                LOG.error(" Error code: " + ex.getErrorCode());
                ex = ex.getNextException();
            }
        }

        return result;
    }

    public int oracleUpdate(String sentence,
                            String[] parametersName,
                            Object[] parameters,
                            int[] parametersType) throws
                                                  SQLException {
        crearConexion();

        LOG.debug(sentence);
        try (OraclePreparedStatement statement = (OraclePreparedStatement) connection.prepareStatement(sentence)) {

            if (parameters != null && parametersType != null && parametersName != null) {
                for (int x = 0; x < parameters.length; x++) {
                    LOG.debug("x = " + x);
                    LOG.debug("value = " + parameters[x]);
                    LOG.debug("parametersType = " + parametersType[x]);
                    LOG.debug("parametersName = " + parametersName[x]);

                    if (parametersType[x] == Types.DATE) {
                        String date = parameters[x].toString().trim();
                        date = StringUtils.remove(date,
                                                  "'");

                        if (Validator.validateString(date)) {
                            date = StringUtils.remove(date,
                                                      "'");
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Integer.parseInt(date.substring(0,
                                                                         4)),
                                         Integer.parseInt(date.substring(4,
                                                                         6)) - 1,
                                         Integer.parseInt(date.substring(6,
                                                                         8)));

                            LOG.debug(DateFormat.getDateTimeInstance().format(calendar.getTime()));

                            statement.setObjectAtName(parametersName[x],
                                                      new Date(calendar.getTimeInMillis()),
                                                      parametersType[x]);
                        } else {
                            statement.setObjectAtName(parametersName[x],
                                                      null,
                                                      parametersType[x]);
                        }
                    } else {
                        statement.setObjectAtName(parametersName[x],
                                                  parameters[x].toString().trim(),
                                                  parametersType[x]);
                    }
                }
            }

            return statement.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public List<List<Object>> oracleSelect(String sentence,
                                           String[] parametersName,
                                           Object[] parameters,
                                           int[] parametersType) throws
                                                                 SQLException {
        crearConexion();

        try (OraclePreparedStatement statement = (OraclePreparedStatement) connection.prepareStatement(sentence)) {
            if (parameters != null && parametersType != null && parametersName != null) {
                for (int x = 0; x < parameters.length; x++) {
                    LOG.debug("x = " + x);
                    LOG.debug("value = " + parameters[x]);
                    LOG.debug("parametersType = " + parametersType[x]);
                    LOG.debug("parametersName = " + parametersName[x]);

                    if (parametersType[x] == Types.DATE) {
                        String date = parameters[x].toString().trim();
                        date = StringUtils.remove(date,
                                                  "'");

                        if (Validator.validateString(date)) {
                            date = StringUtils.remove(date,
                                                      "'");
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Integer.parseInt(date.substring(0,
                                                                         4)),
                                         Integer.parseInt(date.substring(4,
                                                                         6)) - 1,
                                         Integer.parseInt(date.substring(6,
                                                                         8)));

                            LOG.info(DateFormat.getDateTimeInstance().format(calendar.getTime()));

                            statement.setObjectAtName(parametersName[x],
                                                      new Date(calendar.getTimeInMillis()),
                                                      parametersType[x]);
                        } else {
                            statement.setObjectAtName(parametersName[x],
                                                      null,
                                                      parametersType[x]);
                        }
                    } else {
                        statement.setObjectAtName(parametersName[x],
                                                  parameters[x].toString().trim(),
                                                  parametersType[x]);
                    }
                }
            }

            return getLists(statement);
        } finally {
            closeConnection();
        }
    }

    public List<List<Object>> select(String sentence,
                                     Object[] parameters,
                                     int[] parametersType) throws
                                                           SQLException {
        this.crearConexion();

        try (PreparedStatement statement = connection.prepareStatement(sentence)) {
            if (parameters != null && parametersType != null) {
                for (int x = 0; x < parameters.length; x++) {
                    LOG.debug("x = " + x);
                    LOG.debug("value = " + parameters[x]);
                    LOG.debug("parametersType = " + parametersType[x]);

                    if (parametersType[x] == Types.DATE) {
                        String date = parameters[x].toString().trim();
                        date = StringUtils.remove(date,
                                                  "'");

                        if (Validator.validateString(date)) {
                            date = StringUtils.remove(date,
                                                      "'");
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR,
                                         Integer.parseInt(date.substring(0,
                                                                         4)));
                            calendar.set(Calendar.MONTH,
                                         Integer.parseInt(date.substring(4,
                                                                         6)) - 1);
                            calendar.set(Calendar.DAY_OF_MONTH,
                                         Integer.parseInt(date.substring(6,
                                                                         8)));

                            LOG.info(DateFormat.getDateTimeInstance().format(calendar.getTime()));

                            statement.setObject(x + 1,
                                                new Date(calendar.getTimeInMillis()),
                                                parametersType[x]);
                        } else {
                            statement.setObject(x + 1,
                                                null,
                                                parametersType[x]);
                        }
                    } else {
                        statement.setObject(x + 1,
                                            parameters[x].toString().trim(),
                                            parametersType[x]);
                    }
                }
            }

            return getLists(statement);
        }
    }

    private List<List<Object>> getLists(PreparedStatement statement) throws
                                                                     SQLException {
        boolean haveRS = statement.execute();

        List<List<Object>> resultset = new ArrayList<>();

        while (haveRS) {
            final ResultSet rs = statement.getResultSet();

            addResultSet(resultset,
                         rs);

            haveRS = statement.getMoreResults();
        }

        return resultset;
    }

    private void addResultSet(List<List<Object>> resultset,
                              ResultSet rs) throws
                                            SQLException {
        while (rs.next()) {
            List<Object> row = new ArrayList<>();

            for (int cIndex = 0; cIndex < rs.getMetaData().getColumnCount(); cIndex++) {
                row.add(rs.getObject(cIndex + 1));
            }

            resultset.add(row);
        }
    }

    public String printParameters(Object[] parameters) {
        if (parameters != null && parameters.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Object parameter : parameters) {
                if (parameter instanceof String) {
                    stringBuilder.append("'");
                    stringBuilder.append(parameter);
                    stringBuilder.append("'");
                } else {
                    stringBuilder.append(parameter);
                }
                stringBuilder.append(",");
            }

            return stringBuilder.substring(0,
                                           stringBuilder.toString().length() - 1);
        }

        return "";
    }

    public List<List<List<Object>>> call(String sentence,
                                         Object[] parameters,
                                         int[] parametersType,
                                         int[] outputParameter) throws
                                                                SQLException,
                                                                InterruptedException {
        return this.call(sentence,
                         parameters,
                         parametersType,
                         outputParameter,
                         false);
    }

    public List<List<List<Object>>> call(String sentence,
                                         Object[] parameters,
                                         int[] parametersType,
                                         int[] outputParameter,
                                         Boolean waitForOutputParameters) throws
                                                                          SQLException,
                                                                          InterruptedException {
        LOG.debug("INICIO LLAMADO SP: " + sentence + "(" + this.printParameters(parameters) + ")");

        return this.executeCall(sentence,
                                parameters,
                                parametersType,
                                outputParameter,
                                waitForOutputParameters);
    }

    private List<List<List<Object>>> executeCall(String sentence,
                                                 Object[] parameters,
                                                 int[] parametersType,
                                                 int[] inORout,
                                                 Boolean waitForOutputParameters) throws
                                                                                  SQLException,
                                                                                  InterruptedException {
        this.crearConexion();

        StringBuilder realSentence = buildSPCall(sentence,
                                                 parameters);
        boolean haveOutputParameters = false;

        try (CallableStatement statement = connection.prepareCall(realSentence.toString())) {
            if (parameters != null && parametersType != null) {
                for (int x = 0; x < parameters.length; x++) {
                    LOG.debug("x = " + x);
                    LOG.debug("value = " + parameters[x]);
                    LOG.debug("parametersType = " + parametersType[x]);

                    if (inORout == null || inORout[x] == 1) {
                        if (parametersType[x] == Types.DATE) {
                            String date = parameters[x].toString().trim();
                            date = StringUtils.remove(date,
                                                      "'");
                            if (Validator.validateString(date)) {
                                date = StringUtils.remove(date,
                                                          "'");
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.YEAR,
                                             Integer.parseInt(date.substring(0,
                                                                             4)));
                                calendar.set(Calendar.MONTH,
                                             Integer.parseInt(date.substring(4,
                                                                             6)) - 1);
                                calendar.set(Calendar.DAY_OF_MONTH,
                                             Integer.parseInt(date.substring(6,
                                                                             8)));

                                statement.setObject(x + 1,
                                                    new Date(calendar.getTimeInMillis()),
                                                    parametersType[x]);
                            } else {
                                statement.setObject(x + 1,
                                                    null,
                                                    parametersType[x]);
                            }
                        } else {
                            statement.setObject(x + 1,
                                                parameters[x].toString().trim(),
                                                parametersType[x]);

                        }
                    } else {
                        haveOutputParameters = true;
                        statement.registerOutParameter(x + 1,
                                                       parametersType[x]);
                        if (parameters[x] != null) {
                            statement.setObject(x + 1,
                                                parameters[x].toString().trim(),
                                                parametersType[x]);
                        }
                    }
                }
            }

            boolean haveRS = statement.execute();

            List<List<List<Object>>> result = new ArrayList<>();

            int updateCount = 0;

            while (haveRS || updateCount != -1) {
                List<List<Object>> resultset = new ArrayList<>();

                if (haveRS) {
                    ResultSet rs = statement.getResultSet();

                    this.addResultSet(resultset,
                                      rs);

                    result.add(resultset);
                } else {
                    updateCount = statement.getUpdateCount();

                    if (updateCount >= 0) {
                        LOG.debug("CONTADOR EN: " + updateCount);
                    } else {
                        LOG.debug("NO HAY MAS REGISTROS.");
                    }
                }

                haveRS = statement.getMoreResults();
            }

            if (haveOutputParameters && waitForOutputParameters) {
                while (!haveRS) {
                    LOG.debug("ESPERANDO AL SP...");
                    Thread.sleep(1000);
                    haveRS = statement.getMoreResults();
                }

                List<List<Object>> resultset = new ArrayList<>();
                List<Object> row = new ArrayList<>();

                for (int x = 0; x < parameters.length; x++) {
                    if (inORout[x] == 0) {
                        LOG.debug("RECUPERANDO PARAMETRO DE SALIDA DESDE LA POSICION: " + x);
                        row.add(statement.getObject(x + 1));
                    }
                }

                resultset.add(row);
                result.add(resultset);
            }

            if (haveOutputParameters && !waitForOutputParameters) {
                List<List<Object>> resultset = new ArrayList<>();
                List<Object> row = new ArrayList<>();

                for (int x = 0; x < parameters.length; x++) {
                    if (inORout[x] == 0) {
                        LOG.debug("RECUPERANDO PARAMETRO DE SALIDA DESDE LA POSICION: " + x);
                        row.add(statement.getObject(x + 1));
                    }
                }

                resultset.add(row);
                result.add(resultset);
            }

            return result;
        } finally {
            closeConnection();
        }
    }

    private StringBuilder buildSPCall(String sentence,
                                      Object[] parameters) {
        StringBuilder realSentence = new StringBuilder("{call ");
        realSentence.append(sentence);
        realSentence.append("(");

        if (parameters != null) {
            for (int x = 0; x < parameters.length; x++) {
                if (parameters.length - 1 > x) {
                    realSentence.append("?,");
                } else {
                    realSentence.append("?");
                }
            }
        }

        realSentence.append(")}");

        return realSentence;
    }

    private int executeSQL(String sentence) throws
                                            SQLException {
        int result;

        crearConexion();

        try (PreparedStatement statement = this.connection.prepareStatement(sentence)) {
            result = statement.executeUpdate();
        } finally {
            closeConnection();
        }

        return result;
    }

    private void crearConexion() throws
                                 SQLException {
        if (connection == null || connection.isClosed()) {
            LOG.debug("NUEVA CONEXION");

            this.connection = dataSource.getConnection();
            this.connection.setAutoCommit(autocommit);
        }
    }

    public void closeConnection() throws
                                  SQLException {
        if (connection != null && !connection.isClosed()) {
            LOG.debug("CERRANDO CONEXION");
            connection.close();
        }
    }
}
