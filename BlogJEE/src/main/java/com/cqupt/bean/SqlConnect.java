package com.cqupt.bean;

import java.sql.Connection;
import java.sql.DriverManager; //驱动

public class SqlConnect {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";
    /**
     * 'jdbc:mysql://'：这是 JDBC 连接 MySQL 数据库的协议前缀。
     * 'localhost'：这是 ，表示数据库服务器在本地。
     * '3306'：这是 MySQL 服务器的默认端口号。
     * 'mysql'：这是要连接的数据库的名称。
     * '?useUnicode=true&amp;characterEncoding=gbk&useSSL=true'：这是一些连接参数，指定了使用Unicode编码以及字符编码为gbk。
     */

//    private static final String DBURL= "jdbc:mysql://localhost:3306/user?useSSL=true&trustCertificateKeyStoreUrl=file:/path/to/truststore&trustCertificateKeyStorePassword=asjasjasj"
    private static final String DBURL = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&amp;characterEncoding=gbk";
//    private static final String DBURL = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";
    private static final String DBUSER = "root"; //数据库用户名
    private static final String DBPASSWORD = "asjasjasj"; //数据库密码
    private Connection conn ;

    public SqlConnect() {
        try {
            Class.forName(DBDRIVER);//加载驱动
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);//获得连接对象
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    // 连接
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    //关闭数据库连接
    public void close(){
        try{
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
