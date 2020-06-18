package com.muye.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils
{
    public static Connection getConnection(String driver, String url, String user, String password)
            throws Exception
    {
        Connection conn = null;
        Class.forName(driver);
        DriverManager.setLoginTimeout(10);
        String userName = (org.apache.commons.lang3.StringUtils.isNotBlank(user)) ? user : "";
        String pwd = (org.apache.commons.lang3.StringUtils.isNotBlank(password)) ? password : "";
        conn = DriverManager.getConnection(url, userName, pwd);
        return conn; }

    public static String getDriverClass(String dialectType) {
        String result = null;

        if ("MYSQL".equals(dialectType.toUpperCase()))
            result = "com.mysql.jdbc.Driver";
        else if ("ORACLE".equals(dialectType.toUpperCase()))
            result = "oracle.jdbc.driver.OracleDriver";
        else if ("SQLSERVER".equals(dialectType.toUpperCase()))
            result = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        else if ("SYBASE".equals(dialectType.toUpperCase())) {
            result = "om.sybase.jdbc3.jdbc.SybDriver";
        }

        return result;
    }
}
