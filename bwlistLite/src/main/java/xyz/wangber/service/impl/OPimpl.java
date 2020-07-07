package xyz.wangber.service.impl;

import xyz.wangber.service.OP;
import xyz.wangber.dao.URLDao;
import xyz.wangber.dao.daoImpl.AddBlackURL;
import xyz.wangber.dao.daoImpl.DelWhiteURL;
import xyz.wangber.dao.daoImpl.QueryURL;

import java.sql.SQLException;

public class OPimpl implements OP {
    private URLDao dao;
    @Override
    public String urlCheck(String url, int mode) throws SQLException {
        switch (mode){
            case 1://查询
                dao = new QueryURL();
                break;
            case 2://添加黑名单
                dao = new AddBlackURL();
                break;
            case 3://删除白名单
                dao = new DelWhiteURL();
                break;
        }
        String rs = dao.operateURL(url);
        return rs;
    }
}
