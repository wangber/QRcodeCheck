package xyz.wangber.dao.daoImpl;

import xyz.wangber.CreatCon.CreateCon;
import xyz.wangber.dao.URLDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBlackURL implements URLDao {
    //将URL添加进入黑名单
    @Override
    public String operateURL(String url) throws SQLException {
        CreateCon con = new CreateCon();
        con.setConnection();
        Connection connection = con.getConnection();
        Statement statement = null;
        int rs = 0;
        String result = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("创建语句执行器时出现错误"+e);
        }
        String sql = "INSERT INTO bw " +
                "VALUES ('"+url+"',"+2+")";
        try {
            rs = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("执行查询语句出错"+e);
        }
        return String.valueOf((rs != 0));
    }
}
