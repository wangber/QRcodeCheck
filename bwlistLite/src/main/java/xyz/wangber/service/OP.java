package xyz.wangber.service;

import java.sql.SQLException;

public interface OP {
    //URL和操作模式
    public String urlCheck(String url,int mode) throws SQLException;
}
