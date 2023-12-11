# MyBlog-Web

如果要在自己电脑上使用login的数据库，请修改/添加数据库名及主机服务器如下：

bean.SqlConnect.java第17行

```
MySQL服务器的主机名或IP地址：localhost
要连接的数据库的名称：mysql
```

bean.SqlConnect.java第19行

```
private static final String DBUSER = "自己的数据库名"; //数据库用户名
```

bean.SqlConnect.java第20行

```
private static final String DBPASSWORD = "自己密码"; //数据库密码
```

哈哈数据库自己建
hh