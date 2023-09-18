# JDBC

## 1、数据库驱动

驱动：声卡，显卡，数据库

![](E:\mysql笔记\图片\QQ截图20230916150737.png)

我们的程序会通过 数据库 驱动，和数据库打交道！



## 2、JDBC

SUN公司为了简化开发人员（对数据库的统一）操作，提供了一个（Java操作数据库的）规范，俗称JDBC

这些规范的实现有具体的厂商去做

对于开发人员来说，我们只需要掌握JDBC接口的操作即可！

![](E:\mysql笔记\图片\QQ截图20230916151210.png)

java.sql

javax.sql

还需要导入一个数据库驱动包 mysql-connector-java-5.1.47.jar

## 3、第一个JDBC程序

创建一个数据库

```sql
CREATE DATABASE `jdbcStudy` CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `jdbcStudy`;

CREATE TABLE `users`(
 `id` INT PRIMARY KEY,
 `NAME` VARCHAR(40),
 `PASSWORD` VARCHAR(40),
 `email` VARCHAR(60),
 birthday DATE
);

 INSERT INTO `users`(`id`,`NAME`,`PASSWORD`,`email`,`birthday`)
VALUES('1','zhangsan','123456','zs@sina.com','1980-12-04'),
('2','lisi','123456','lisi@sina.com','1981-12-04'),
('3','wangwu','123456','wangwu@sina.com','1979-12-04');
```



1、创建一个普通项目

2、导入数据库驱动

![](E:\mysql笔记\图片\QQ截图20230916152813.png)

3、编写测试代码

```java
package com.kuang.lesson01;

import java.sql.*;

// 我的第一个JDBC程序
public class JdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
        //2.用户信息和url
        //useUnicode=true&characterEncoding=utf8&useSSL=true
        String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";
        String username = "root";
        String password = "123456";
        //3.连接成功，数据库对象  Connection 代表数据库
        Connection connection = DriverManager.getConnection(url,username,password);
        //4.执行sql的对象
        Statement statement = connection.createStatement();

        //5.执行sql的对象 执行sql，可能存在结果
        String sql = "SELECT * FROM users";

        ResultSet resultSet = statement.executeQuery(sql);//返回的结果集,结果集中封装了全部的结果

        while (resultSet.next()){
            System.out.println("id="  + resultSet.getObject("id"));
            System.out.println("name="  + resultSet.getObject("NAME"));
            System.out.println("pwd="  + resultSet.getObject("PASSWORD"));
            System.out.println("email="  + resultSet.getObject("email"));
            System.out.println("birth="  + resultSet.getObject("birthday"));
            System.out.println("==========================================================");
        }
        //6.释放连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}

```

步骤总结：

1、加载驱动

2、连接数据库 Driver Manager

3、获得执行 sql 的对象 Statement

4、获得返回的结果集

5、释放连接

>DriverManager

```java
// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
Class.forName("com.mysql.jdbc.Driver");//固定写法，加载驱动
Connection connection = DriverManager.getConnection(url,username,password);

//connection代表数据库
//数据库设置自动提交
//事务提交
//事务滚回
connection.rollback();
connection.commit();
connection.setAutoCommit();
```

>URL

```java
String url = "jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSL=true";

//mysql  --3306
//协议://主机地址:端口号?数据库名?参数1&参数2&参数3
//Oracle -- 1521
//jdbc:oracle:thin:@localhost:1521:sid
```



>Statement 执行SQL的对象  PrepareStatement执行SQL的对象

```java
String sql  = "SELECT * FROM users"


statement.executeQuery();//查询操作返回 ResultSet
statement.execute();//执行任何SQL
statement.executeUpdate();//更新、插入、删除都是用这个，返回一个受印象的行数
```



>ResultSet 查询的结果集：封装了所有的查询结果

获得了指定的数据类型

```java
		resultSet.getObject();//在不知道列类型的情况下使用
        //如果知道列的类型就用指定的类型
        resultSet.getString();
        resultSet.getInt();
        resultSet.getFloat();
        resultSet.getDate();
```

遍历,指针

```java
resultSet.beforeFirst();//移动到最前面
resultSet.afterLast();//移动到最后面
resultSet.next();//移动到下一个数据
resultSet.previous();//移动到前一行
resultSet.absolute(row);//移动到指定行

```

>释放资源

```java
//6.释放连接
resultSet.close();
statement.close();
connection.close();//耗资源，关掉
```

 

## 4、statement对象

jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。

Statement对象的executeUpdate方法，用于向数据库发送增、删、改的SQL语句，executeUpdate执行完后，将会返回一个整数（即增删改语句导致了数据库几行数据发生了变化）。

Statement.executeQuery方法用于向数据库发送查询语句，executeQuery方法返回代表查询结果的ResultSet对象

>CURD操作-create

使用executeUpdate(String sql)方法完成数据添加操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "insert into user(...) values(...)";
int num = st.executeUpdate(sql);
if(num>0){
    System.out.println("插入成功！！！")
}
```

>CRUD操作-delete

使用executeUpdate（String sql）方法完成数据删除操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "delete from user where id=1";
int num = st.executeUpdate(Sql);
if(num>0){
    System.out.println("删除成功！！！")
}
```

使用executeUpdate（String sql）方法完成数据修改操作，示例操作：

```java
Statement st = conn.createStatement();
String sql = "update user set name'' where name=''";
int num = st.executeUpdate(Sql);
if(num>0){
    System.out.println("修改成功！！！")
}
```

>CRUD操作-read

使用executeQuery(String sql )方法完成数据查询操作，示例操作:

```java
Statement st = conn.createStatement();
String sql = "select * from user where id=1"
ResultSet rs = st.executeQuery(sql);
while(rs.next){
    //根据获取列的数据类型，分别调用rs的相应方法映射到java对象中
}
```





>代码实现

1.提取工具类

2.编写增删改的方法，`executeUpdate`

```java
package com.kuang.lesson02.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestInsert {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn= JdbcUtils.getConnection();//获取数据库进行连接
            st = conn.createStatement();//获得sql的执行对象
            String sql = "INSERT INTO users(id,`NAME`,`PASSWORD`,`email`,`birthday`)" +
                    "VALUES(4,'kuangshen','123456','24736743@qq.com','2020-01-01')";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("插入成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
```

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDelete {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn= JdbcUtils.getConnection();//获取数据库进行连接
            st = conn.createStatement();//获得sql的执行对象
            String sql ="DELETE FROM users WHERE id=4";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("删除成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}

```

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn= JdbcUtils.getConnection();//获取数据库进行连接
            st = conn.createStatement();//获得sql的执行对象
            String sql = "UPDATE users SET `NAME`='kuangshen',`email`='24736743@qq.com' WHERE id = 1";

            int i = st.executeUpdate(sql);
            if(i>0){
                System.out.println("更新成功!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}

```

查询

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //SQL
            String sql = "select * from users where id=1";

            rs = st.executeQuery(sql);//查询完毕会返回一个结果集

            while (rs.next()){
                System.out.println(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }


    }
}

```

>SQL注入问题

sql存在漏洞，会被攻击导致数据泄露，==SQL会被拼接 or==

```java
package com.kuang.lesson02.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args) {


        //login("kuangshen","123456");
        login(" 'or '1=1","'or'1=1");//技巧


    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            st = conn.createStatement();

            //SELECT * FROM users WHERE `NAME` = 'kuangshen' AND `PASSWORD`='123456';
            //SELECT * FROM users WHERE `NAME` = '' or '1=1' AND `PASSWORD`='' or '1=1';
            String sql = "select * from users where `NAME`='" + username + "' AND `PASSWORD` ='" + password + "'";

            rs = st.executeQuery(sql);//查询完毕会返回一个结果集

            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("PASSWORD"));
                System.out.println("===========================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}

```



## 5、PreparedStatement对象

PrepareStatement可以防止SQL注入。效率更好！



1、新增

```java
package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();
            
            //区别
            //使用？占位符代替参数
            String sql = "insert into users(id,`NAME`,`PASSWORD`,`email`,`birthday`) values(?,?,?,?,?)";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            //手动给参数赋值
            st.setInt(1,4);
            st.setString(2,"qinjiang");
            st.setString(3,"12324123");
            st.setString(4,"24736473@qq.com");
            //注意点：sql.Date    数据库
            //      util.Date   java       new Date().getTime()     获得时间戳
            st.setDate(5,new java.sql.Date(new Date().getTime()));

            //执行
            int i = st.executeUpdate();
            if(i>0){
                System.out.println("插入成功");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,null);
        }


    }
}

```



2、删除

```java
package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用？占位符代替参数
            String sql = "delete from users where id = ?";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            //手动给参数赋值
            st.setInt(1,4);

            //执行
            int i = st.executeUpdate();
            if(i>0){
                System.out.println("删除成功");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,null);
        }
    }
}

```



3、更改

```java
package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            //区别
            //使用？占位符代替参数
            String sql = "update users set `NAME` = ? where id = ?;";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            //手动给参数赋值
            st.setString(1,"狂神");
            st.setInt(2,1);

            //执行
            int i = st.executeUpdate();
            if(i>0){
                System.out.println("更新成功");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,null);
        }
    }
}

```



4、查询

```java
package com.kuang.lesson03;

import com.kuang.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();

            String sql = "select * from users where id = ?";//编写sql

            st = conn.prepareStatement(sql);//预编译

            st.setInt(1,2);//传递参数

            //执行
            rs = st.executeQuery();

            if(rs.next()){
                System.out.println(rs.getString("NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.release(conn,st,rs);
        }
    }
}

```



5、防止sql注入

```java
package com.kuang.lesson02.utils;

import java.sql.*;

public class SQL注入 {
    public static void main(String[] args) {
    login("lisi","123456");
    //        login(" 'or '1=1","'or'1=1");//技巧
    }

    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        PreparedStatement st = null ;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //PrepareStatement 防止SQL注入的本质，把传递进来的参数当作字符
            //假设其中存在转义字符，就直接转义，比如说‘会直接转义
            String sql = "select * from users where `NAME`=? and `PASSWORD`=?";//MyBatis

            st = conn.prepareStatement(sql);
            st.setString(1,username);
            st.setString(2,password);

            //SELECT * FROM users WHERE `NAME` = 'kuangshen' AND `PASSWORD`='123456';
            //SELECT * FROM users WHERE `NAME` = '' or '1=1' AND `PASSWORD`='' or '1=1';

            rs = st.executeQuery(sql);//查询完毕会返回一个结果集
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("PASSWORD"));
                System.out.println("===========================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}

```

![](E:\mysql笔记\图片\QQ截图20230917154300.png)

双击数据库

![](E:\mysql笔记\图片\QQ截图20230917154639.png)

更新数据

![](E:\mysql笔记\图片\QQ截图20230917154732.png)

编写sql代码的地方

连接失败，查看原因

## 6、事务

==要么都成功，要么都失败==

>ACID原则

原子性：要么全部都完成，要么都不完成

一致性：结果总数不变

隔离性：**多个进程互不干扰**

持久性：一旦提交，不可逆，持久化到数据库了



隔离性的问题：

脏读：一个事务读取了另一个没有提交的事务

不可重复读：在同一个事务内，重复读取表中的数据，表数据发生了改变

虚读（幻读）：在一个事务内，读取到了别人插入的数据，导致前后读出来结果不一致



>代码实现

1、开启事务 conn.setAutoCommit(false);

2、一组业务执行完毕，提交事务。

3、可以在catch语句中显示的定义回滚语句，但默认失败就会回滚

```java
package com.kuang.lesson04;

import com.kuang.lesson02.utils.JdbcUtils;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            //关闭数据库的自动提交功能,会自动开启事务
            conn.setAutoCommit(false);//开启事务

            String sql1 = "update account set money = money-100 where name = 'A'";
            st = conn.prepareStatement(sql1);
            st.executeUpdate();

            int x = 1/0;//报错

            String sql2 = "update account set money = money+100 where name = 'B'";
            st = conn.prepareStatement(sql2);
            st.executeUpdate();

            //业务完毕，提交事务
            conn.commit();
            System.out.println("操作成功！");



        } catch (SQLException e) {
            e.getStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}

```



## 7、数据库连接池

数据库连接--执行完毕--释放 

连接--释放 十分浪费系统资源

**池化技术：准备一些预先的资源，过来就连接预先准备好的**

------- 开门 --业务员：等待-- 服务 --- 

常用连接数10个

最小连接数：10

最大连接数：15业务最高承载上限

排队等待，

等待超时：100ms



编写连接池：实现一个接口 DataSource

>开源数据源实现(拿来即用)

DBCP

C3P0

Druid:阿里巴巴



使用了数据库连接池之后，我们在项目开发中就不需要编写连接数据库的代码了



>DBCP

需要用到的jar包

commons-dbcp-1.4

commons-poll



>C3P0

需要用到的jar包
