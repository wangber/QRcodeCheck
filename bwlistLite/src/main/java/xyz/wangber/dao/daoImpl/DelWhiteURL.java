package xyz.wangber.dao.daoImpl;

import xyz.wangber.CreatCon.CreateCon;
import xyz.wangber.dao.URLDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DelWhiteURL implements URLDao {

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
        String sql = "DELETE FROM bw " + "WHERE url='"+url+"' AND ifGood=1";
        try {
            rs = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("执行查询语句出错"+e);
        }
        return String.valueOf((rs != 0));
    }
}
