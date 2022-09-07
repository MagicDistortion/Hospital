package project.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import project.ConfigurationManager;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;
import java.util.function.Consumer;

/* Клас для роботи з БД*/
public class DBManager {
    private final static Logger logger = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    private final DataSource dataSource;

    /* приватний конструктор DBManager , налаштовує конекшен до БД череш ConfigurationManager */
    private DBManager() {
        ConfigurationManager configInstance = ConfigurationManager.getInstance();
        String dbUrl = configInstance.getConfigValue("db.url");
        if (dbUrl == null) {
            logger.error("cannot connection to db");
            throw new RuntimeException("cannot connection to db");
        }
        try {
            boolean dbUsePool = Objects.equals(configInstance.getConfigValue("db.usePool"), "true");
            if (dbUsePool) {
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(dbUrl);
                hikariConfig.setMaximumPoolSize(100);
                hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
                this.dataSource = new HikariDataSource(hikariConfig);
            } else {
                MysqlDataSource mysqlDataSource = new MysqlDataSource();
                mysqlDataSource.setURL(dbUrl);
                this.dataSource = mysqlDataSource;
            }
        } catch (Exception e) {
            logger.error("cannot connection to db", e);
            throw new RuntimeException("cannot connection to db", e);
        }
    }
    /* метод отримання синглтону DBManager */
    public static synchronized DBManager getInstance() {
        if (instance == null) instance = new DBManager();
        return instance;
    }
    /* метод отримання зв'язку з бд */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    /*Обгортання методу у транзакцію */
    public void inTransaction(Consumer<Connection> action) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            action.accept(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("failed to rollback connection", e);
                }
            }
            if (e instanceof RuntimeException) throw (RuntimeException) e;
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("failed to close connection", e);
                }
            }
        }
    }
}
