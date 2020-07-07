package xyz.wangber.dao.daoImpl;

import xyz.wangber.CreatCon.CreateCon;
import xyz.wangber.dao.URLDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryURL implements URLDao {


    @Override
    public String operateURL(String url) throws SQLException {
        CreateCon con = new CreateCon();
        con.setConnection();
        Connection connection = con.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        String result = null;
        try {
           statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("创建语句执行器时出现错误"+e);
        }
        String sql = "select ifGood from bw where url='"+url+"'";
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("执行查询语句出错"+e);
        }
        if (!rs.next()){
            return "good";
        }else{
            do{
                System.out.println(rs);
                if (rs.getInt(1) == 1){
                    result = "good";
                }else{
                    result = "bad";
                    return result;
                }
            }while(rs.next());
        }

        return result;

    }
}
