package com.cqupt.model;

//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import  com.mysql.cj.jdbc.MysqlXADataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 描述：使用这个类，来和数据库建立连接。封装数据库
 */
public class DBUtil {
    /**
     * 'jdbc:mysql://'：这是 JDBC 连接 MySQL 数据库的协议前缀。
     * 'localhost'：这是 ，表示数据库服务器在本地。
     * '3306'：这是 MySQL 服务器的默认端口号。
     * 'mysql'：这是要连接的数据库的名称。
     * '?useUnicode=true&amp;characterEncoding=gbk&useSSL=true'：这是一些连接参数，指定了使用Unicode编码以及字符编码为gbk。
     */
    //这是连接数据库的地址
<<<<<<< Updated upstream
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&amp;characterEncoding=gbk";
=======
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8&useSSL=false";
>>>>>>> Stashed changes
    private static final String USERNAME = "root"; //数据库用户名
    private static final String PASSWORD = "123456"; //数据库密码


    //创建一个数据源对象：
    private volatile static DataSource dataSource = null;
    private static DataSource getDataSource(){
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlXADataSource();
                    ((MysqlXADataSource) dataSource).setURL(URL);
                    ((MysqlXADataSource) dataSource).setUser(USERNAME);
                    ((MysqlXADataSource) dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    //关闭资源：
    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}