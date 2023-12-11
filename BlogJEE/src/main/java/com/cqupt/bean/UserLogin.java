package com.cqupt.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogin {
    SqlConnect sqlConn = new SqlConnect(); private Connection conn;
    public UserLogin() {
        conn = sqlConn.getConn();
    }
    //登录验证
    public boolean loginCheck(User user) {
        boolean result = false;
        Statement sta = null;
        ResultSet rs = null;
        try {
            sta = conn.createStatement();
            String sql = "select password from user where username='"+user.getUsername()+"'";
            rs = sta.executeQuery(sql);
            if(rs.next()) {
                if(rs.getString(1).equals(user.getPassword())) {
                    result = true;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                sta.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    public void close() {
        sqlConn.close();
    }
}

