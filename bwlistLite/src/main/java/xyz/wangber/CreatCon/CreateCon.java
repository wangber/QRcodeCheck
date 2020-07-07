//创建数据库的连接
package xyz.wangber.CreatCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateCon {
    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:D:/Desktop/2020-春/创新设计/进度管理/InnovativeDesign/bwlistLite/src/main/resources/bwDB.db";
    private Connection connection = null;
    //初始化连接
    public void setConnection() {
        //创建连接并set
        try {
            Class.forName(Class_Name);
        } catch (ClassNotFoundException e) {
            System.out.println("加载sqllite类失败");
        }
        try {
            this.connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("建立数据库连接出现异常"+e);
        }
    }
    public Connection getConnection() {
        return connection;
    }
}

