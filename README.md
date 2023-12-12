# MyBlog-Web

如果要在自己电脑上使用login的数据库，请修改/添加数据库名及主机服务器如下：

bean.SqlConnect.java第17行

```
MySQL服务器的主机名或IP地址：localhost/127.0.0.1
要连接的数据库的名称：mysql
```

bean.SqlConnect.java第19行

```
private static final String DBUSER = "root"; //数据库用户名
```

bean.SqlConnect.java第20行

```
private static final String DBPASSWORD = "密码"; //数据库密码
```

### 创建数据库表部分

```
-- 通过这个文件来编写建库建表的sql
 
-- 先建立数据库：
create database if not exists blogSystem;
 
-- 选中数据库：
use mysql;
 
-- 创建一个博客表：
drop table if exists blog;
create table blog(
    blogId int primary key auto_increment, -- 创建博客ID列，并作为主键约束，设置自增主键
    title varchar(1024), -- 创建标题列
    content mediumtext, -- 创建正文列，类型设为mediumtext，表示较长的字符串
    userId int, -- 创建作者ID列，表明文章的作者是谁
    postTime datetime -- 创建发布时间列
);
 
-- 在博客表插入两条数据：
insert into blog values(null,'我的第一篇博客','一二三四五，六七八九十',1,now());
 
-- 创建一个用户表：
drop table if exists user;
create table user(
    userId int primary key auto_increment, -- 创建用户ID列，并作为自增主键
    username varchar(128) unique, -- 创建用户名列，因为后续会使用用户名登录，所以要求不能重复
    password varchar(128)
);
 
-- 在用户表插入两条数据：
insert into user values(null,"asj","1234");
insert into user values(null,"zj","12345");
insert into user values(null,"tt","123456");
```

