package xyz.wangber.dao.daoImpl;

import xyz.wangber.service.OP;
import xyz.wangber.service.impl.OPimpl;
import xyz.wangber.dao.URLDao;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        URLDao qu = new AddBlackURL();
        String rs = qu.operateURL("www.baidu.com");
        System.out.println(rs);
        OP op = new OPimpl();
        System.out.println(op.urlCheck("www.baidu.com", 1));
    }
}
