package com.backend.fluxnewsapi.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
//import  org.hibernate.dialect.PostgresPlusDialect;

@Component
public class DbConnection{
    private static Log logger = LogFactory.getLog(DbConnection.class);

    public static Connection getRemoteConnection() {
        if (System.getenv("RDS_HOSTNAME") != null) {
            try {
                Class.forName("org.postgresql.Driver");
                String dbName = System.getenv("RDS_DB_NAME");
                String userName = System.getenv("RDS_USERNAME");
                String password = System.getenv("RDS_PASSWORD");
                String hostname = System.getenv("RDS_HOSTNAME");
                String port = System.getenv("RDS_PORT");
                String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                logger.trace("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl);
                logger.info("Remote connection successful.");
                return con;
            }
            catch (Exception e) {
                logger.info(e.toString());
                return null;
            }
        }
        return null;
    }
}
