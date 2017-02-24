package com.flag.c3p0.test.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * use jdbc connect db
 *
 * @Authuor Administrator
 * @Create 2016-10-27-15:50
 */
public class DBConnecter {

    private static String url = null;

    private static String username = null;

    private static String pwd = null;

    private static ComboPooledDataSource ds_pooled;

    private static final Logger log = LogManager.getLogger(DBConnecter.class);

    /**
     *  加载数据库连接的配置文件和驱动
     */
    static {
        Properties env = new Properties();
        Path path =  Paths.get(".").resolve("resources/dbconfig.properties");
        try {
            //加载属性文件中的数据库配置信息
            env.load(new FileInputStream(path.toFile()));
            url = env.getProperty("jdbc.url");
            username = env.getProperty("jdbc.username");
            pwd = env.getProperty("jdbc.pwd");
            ds_pooled = new ComboPooledDataSource();
            ds_pooled.setDriverClass(env.getProperty("jdbc.driver"));
            ds_pooled.setJdbcUrl(url);
            ds_pooled.setUser(username);
            ds_pooled.setPassword(pwd);
            ds_pooled.setMaxPoolSize(20);
            ds_pooled.setMinPoolSize(5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Exception happened message is {}, cause by {}", e.getMessage(), e.getCause());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IO exception ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     */
    public static Connection getConnection() throws SQLException {
        // 2. 设置连接的url,username,pwd
//      return DriverManager.getConnection(url, username, pwd);
        return ds_pooled.getConnection();
    }

    /**
     * 释放连接池资源
     */
    public static void clearup() {
        if (ds_pooled != null) {
            try {
                DataSources.destroy(ds_pooled);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 资源关闭
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt
            , Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
