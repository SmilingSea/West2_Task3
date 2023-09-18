## 1.简单查询

### 1.1查询一个字段

select 字段名 from 表名;

其中要注意：

select和from都是关键字

字段名和表名都是标识符

强调：

对于SQL通用：所有SQL以”；“结尾，不区分大小写

查询部门名字： 

```sql
mysql> select dname from dept;
+------------+
| dname      |
+------------+
| ACCOUNTING |
| RESEARCH   |
| SALES      |
| OPERATIONS |
+------------+
4 rows in set (0.00 sec)
```

### 1.2 查询两个字段，或者多个字段

使用逗号隔开

查询部门编号和名字：

```sql
mysql> select deptno,dname from dept;
+--------+------------+
| deptno | dname      |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```

### 1.3 查询所有字段

第一种：把每个字段都写上

select a,b,c,d...... from 表

第二种：使用星号：

```sql
mysql> select * from dept;
+--------+------------+----------+
| DEPTNO | DNAME      | LOC      |
+--------+------------+----------+
|     10 | ACCOUNTING | NEW YORK |
|     20 | RESEARCH   | DALLAS   |
|     30 | SALES      | CHICAGO  |
|     40 | OPERATIONS | BOSTON   |
+--------+------------+----------+
4 rows in set (0.00 sec)
```

这种方式和的缺点：

1.效率低

2.可读性差

不建议开发，自己玩

可以在dos窗口中快速看数据

### 1.4 给查询的列起别名

```sql
mysql> select deptno,dname as deptname from dept;
+--------+------------+
| deptno | deptname   |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```

使用as关键字起别名。

注意：只是将显示的查询结果列明显示为deptname，原表列明还是叫做：dname

记住：select语句永远不会进行修改，只查询

as关键字可以省略嘛？可以的！

```sql
mysql> select deptno,dname deptname from dept;
+--------+------------+
| deptno | deptname   |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```

假设别名里面有空格，怎么办？

```sql
mysql> select deptno,dname dept name from dept;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'name from dept' at line 1
```

DBMS看到这样的语句会进行SQL编译，不符合语法，编译报错。

怎么解决？

```sql
mysql> select deptno,dname 'dept name' from dept;//加单引号
+--------+------------+
| deptno | dept name  |
+--------+------------+
|     10 | ACCOUNTING |
|     20 | RESEARCH   |
|     30 | SALES      |
|     40 | OPERATIONS |
+--------+------------+
4 rows in set (0.00 sec)
```



使用单引号括起来（双引号也可以）

注意：在所有的数据库中，字符串统一使用单引号括起来 ，双引号在oracle数据库中用不了，但在mysql中可以用。

再次强调，数据库中的字符串都是采用单引号括起来，这是标准的，双引号不标准。

### 1.5 计算员工的年薪

```sql
mysql> select ename,sal from emp;
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| ALLEN  | 1600.00 |
| WARD   | 1250.00 |
| JONES  | 2975.00 |
| MARTIN | 1250.00 |
| BLAKE  | 2850.00 |
| CLARK  | 2450.00 |
| SCOTT  | 3000.00 |
| KING   | 5000.00 |
| TURNER | 1500.00 |
| ADAMS  | 1100.00 |
| JAMES  |  950.00 |
| FORD   | 3000.00 |
| MILLER | 1300.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

```sql
mysql>  select ename,sal*12 from emp;
+--------+----------+
| ename  | sal*12   |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 19200.00 |
| WARD   | 15000.00 |
| JONES  | 35700.00 |
| MARTIN | 15000.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

结论：字段可以使用数学表达式！

```sql
mysql> select ename,sal*12 as yearsal from emp;
+--------+----------+
| ename  | yearsal  |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 19200.00 |
| WARD   | 15000.00 |
| JONES  | 35700.00 |
| MARTIN | 15000.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

起别名~

```sql
mysql> select ename,sal*12 as '年薪' from emp;
+--------+----------+
| ename  | 年薪     |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 19200.00 |
| WARD   | 15000.00 |
| JONES  | 35700.00 |
| MARTIN | 15000.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

别名是中文用单引号括起来。

## 2. 条件查询

### 2.1 什么是条件查询

不是将表中所有数据都查询出来

语法格式：

```txt
select

	字段1，字段2，字段3...

 from

	表名

 where

	条件;
```

### 2.2 都有哪些条件

=等于

​	查询薪资等于八百的员工姓名和编号？

```sql
mysql> select empno,ename from emp where sal = 800;
+-------+-------+
| empno | ename |
+-------+-------+
|  7369 | SMITH |
+-------+-------+
1 row in set (0.00 sec)
```

查询SMITH的编号和薪资

```sql	
select empno,sal from emp where ename = 'SMITH';
```

查询薪资不等于八百的员工姓名和编号？

<>或！=不等于

```sql
mysql> select empno,ename from emp where sal != 800;
mysql> select empno,ename from emp where sal <> 800;//小于号和大于号组成不等号
+-------+--------+
| empno | ename  |
+-------+--------+
|  7499 | ALLEN  |
|  7521 | WARD   |
|  7566 | JONES  |
|  7654 | MARTIN |
|  7698 | BLAKE  |
|  7782 | CLARK  |
|  7788 | SCOTT  |
|  7839 | KING   |
|  7844 | TURNER |
|  7876 | ADAMS  |
|  7900 | JAMES  |
|  7902 | FORD   |
|  7934 | MILLER |
+-------+--------+
13 rows in set (0.00 sec)
```



<小于

查询薪资小于2000的员工姓名和编号

```sql
select empno,ename,sal from emp where sal < 2000;
```

<=小于等于

查询薪资小于等于3000的员工姓名和编号

```sql
select empno,ename,sal from emp where sal <= 3000;
```

\>大于

查询薪资大于3000的员工姓名和编号

```sql
select empno,ename,sal from emp where sal > 3000;
```

\>=大于等于

查询薪资大于等于3000的员工姓名和编号

```sql
select empno,ename,sal from emp where sal >= 3000;
```

between ... and 两个值之间，等同于>= and <=

查询薪资在2450和3000之间的员工信息，包括2450和3000

```sql
第一种方式：>=and<=
select empno,ename,sal from emp where sal >= 2450 and sal <= 3000;
第二种方式： between... and...
select empno,ename,sal from emp where sal between 2450 and 3000;
```

注意：使用between and的时候必须遵循左小右大。

between and 是闭区间，包括两端的值。

is null 为null（is not null 不为空）

查询哪些员工的津贴为null？

```sql
select empno,ename,sal,comm from emp where comm is null;
+-------+--------+---------+------+
| empno | ename  | sal     | comm |
+-------+--------+---------+------+
|  7369 | SMITH  |  800.00 | NULL |
|  7566 | JONES  | 2975.00 | NULL |
|  7698 | BLAKE  | 2850.00 | NULL |
|  7782 | CLARK  | 2450.00 | NULL |
|  7788 | SCOTT  | 3000.00 | NULL |
|  7839 | KING   | 5000.00 | NULL |
|  7876 | ADAMS  | 1100.00 | NULL |
|  7900 | JAMES  |  950.00 | NULL |
|  7902 | FORD   | 3000.00 | NULL |
|  7934 | MILLER | 1300.00 | NULL |
+-------+--------+---------+------+
10 rows in set (0.00 sec)
```

注意：在数据库中，null不能使用=进行衡量，需要使用is null 因为数据库中的null 代表什么也没有，他不是一个值，因此无法用=衡量。

查询哪些员工的津贴不为null？

```sql
select empno,ename,sal,comm from emp where comm is not null;
```

and 并且

找出工作岗位是manager并且工资大于2500的员工信息

```sql
select empno,ename,job,sal from emp where job = 'manager' and sal > 2500;
+-------+-------+---------+---------+
| empno | ename | job     | sal     |
+-------+-------+---------+---------+
|  7566 | JONES | MANAGER | 2975.00 |
|  7698 | BLAKE | MANAGER | 2850.00 |
+-------+-------+---------+---------+
```

or 或者

查询工作岗位是manager或salesman的员工

``` sql
select empno,ename,job,sal from emp where job = 'manager' or 'salesman';
+-------+-------+---------+---------+
| empno | ename | job     | sal     |
+-------+-------+---------+---------+
|  7566 | JONES | MANAGER | 2975.00 |
|  7698 | BLAKE | MANAGER | 2850.00 |
|  7782 | CLARK | MANAGER | 2450.00 |
+-------+-------+---------+---------+
3 rows in set, 1 warning (0.01 sec)
```

and和or同时出现的话有优先级问题吗？

查询工资大于2500，并且部门编号为10或20的部门的员工？

```sql
select * from emp where sal > 2500 and deptno = 10 or deptno = 20;
分析以上语句的问题：
and的优先级比or高，以上语句会先执行and再执行or。
以上语句表示什么含义？
	找出工资大于2500并且部门编号为10的员工，或者编号为20的所有员工
+-------+-------+-----------+------+------------+---------+------+--------+
| EMPNO | ENAME | JOB       | MGR  | HIREDATE   | SAL     | COMM | DEPTNO |
+-------+-------+-----------+------+------------+---------+------+--------+
|  7369 | SMITH | CLERK     | 7902 | 1980-12-17 |  800.00 | NULL |     20 |
|  7566 | JONES | MANAGER   | 7839 | 1981-04-02 | 2975.00 | NULL |     20 |
|  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000.00 | NULL |     20 |
|  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000.00 | NULL |     10 |
|  7876 | ADAMS | CLERK     | 7788 | 1987-05-23 | 1100.00 | NULL |     20 |
|  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000.00 | NULL |     20 |
+-------+-------+-----------+------+------------+---------+------+--------+
修正：
select * from emp where sal > 2500 and (deptno = 10 or deptno = 20);
+-------+-------+-----------+------+------------+---------+------+--------+
| EMPNO | ENAME | JOB       | MGR  | HIREDATE   | SAL     | COMM | DEPTNO |
+-------+-------+-----------+------+------------+---------+------+--------+
|  7566 | JONES | MANAGER   | 7839 | 1981-04-02 | 2975.00 | NULL |     20 |
|  7788 | SCOTT | ANALYST   | 7566 | 1987-04-19 | 3000.00 | NULL |     20 |
|  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000.00 | NULL |     10 |
|  7902 | FORD  | ANALYST   | 7566 | 1981-12-03 | 3000.00 | NULL |     20 |
+-------+-------+-----------+------+------------+---------+------+--------+
```

and 和 or 同时出现，and优先级较高，如果相让or先执行，必须加小括号，以后在开发中，不确定优先级，加小括号就行了

in 包含

查询工作岗位是manager和salesman的员工？

```sql
select empno,ename,job from emp where job = 'manager' or job = 'salesman';
select empno,ename,job from emp where job in ('manager','salesman');

+-------+--------+----------+
| empno | ename  | job      |
+-------+--------+----------+
|  7499 | ALLEN  | SALESMAN |
|  7521 | WARD   | SALESMAN |
|  7566 | JONES  | MANAGER  |
|  7654 | MARTIN | SALESMAN |
|  7698 | BLAKE  | MANAGER  |
|  7782 | CLARK  | MANAGER  |
|  7844 | TURNER | SALESMAN |
+-------+--------+----------+
7 rows in set (0.00 sec)
```

注意：in不是一个区间，后面跟的是具体的值

查询薪资是800 和 5000 的员工信息

```sql
select empno,ename,sal from emp where sal = 800 or sal = 5000;
select empno,ename,sal from emp where sal in (800,5000);// 这个不是表示800到5000都找
+-------+---------+
| ename | sal     |
+-------+---------+
| SMITH |  800.00 |
| KING  | 5000.00 |
+-------+---------+

select empno,ename,sal from emp where sal not in (800,5000);// 表示不和800到5000
```

not not 可以取非，主要用在is或in中

 is null

is not null

 in 

not in

like称为模糊查询，支持%或下划线匹配

%匹配任意多个字符(%是个特殊的符号)

下划线，一个下划线匹配任意一个字符（_也是有特殊字符_）

 找出名字含有o的

```sql
mysql> select ename from emp where ename like'%o%';
+-------+
| ename |
+-------+
| JONES |
| SCOTT |
| FORD  |
+-------+
3 rows in set (0.00 sec)
```

找出名字以T结尾的？

```sql
mysql>  select ename from emp where ename like '%T';
+-------+
| ename |
+-------+
| SCOTT |
+-------+
1 row in set (0.00 sec)
```

找出名字以K开始的？

```sql
mysql> select ename from emp where ename like 'K%';
+-------+
| ename |
+-------+
| KING  |
+-------+
1 row in set (0.00 sec)
```

找出第二个字母是A的？

```sql
mysql> select ename from emp where ename like '_A%';
+--------+
| ename  |
+--------+
| WARD   |
| MARTIN |
| JAMES  |
+--------+
3 rows in set (0.00 sec)
```

找出第三个字母是R的？

```sql
mysql> select ename from emp where ename like '__R%';
+--------+
| ename  |
+--------+
| WARD   |
| MARTIN |
| TURNER |
| FORD   |
+--------+
4 rows in set (0.00 sec)
```



t_student学生表

name字段

\-------------------------------------------------------

zhangsan

lisi

wangwu

zhaoliu

jack_son

找出名字中有\_的?

```sql
select name from t_student wheere name like '%\ _%'
```

斜杠进行转义

## 3. 排序

### 3.1 查询所有员工薪资，排序？

```sql
select ename,sal from emp order by sal;//默认是升序！
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| JAMES  |  950.00 |
| ADAMS  | 1100.00 |
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
| MILLER | 1300.00 |
| TURNER | 1500.00 |
| ALLEN  | 1600.00 |
| CLARK  | 2450.00 |
| BLAKE  | 2850.00 |
| JONES  | 2975.00 |
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
| KING   | 5000.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

### 3.2 怎么降序？

```sql
select ename,sal from emp order by sal desc;//指定降序
+--------+---------+
| ename  | sal     |
+--------+---------+
| KING   | 5000.00 |
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
| JONES  | 2975.00 |
| BLAKE  | 2850.00 |
| CLARK  | 2450.00 |
| ALLEN  | 1600.00 |
| TURNER | 1500.00 |
| MILLER | 1300.00 |
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
| ADAMS  | 1100.00 |
| JAMES  |  950.00 |
| SMITH  |  800.00 |
+--------+---------+
14 rows in set (0.00 sec)
select ename,sal from emp order by sal asc;//指定升序
```

### 3.3 可以两个字段排序吗，或者按照多个字段排序？

查询员工名字和薪资，要求按照薪资升序，如果薪资一样的话，再按照名字升序排序。

```sql
select ename,sal from emp order by sal asc, ename asc;//salary在前起主导只有相等时才会用ename排序
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| JAMES  |  950.00 |
| ADAMS  | 1100.00 |
| MARTIN | 1250.00 |
| WARD   | 1250.00 |
| MILLER | 1300.00 |
| TURNER | 1500.00 |
| ALLEN  | 1600.00 |
| CLARK  | 2450.00 |
| BLAKE  | 2850.00 |
| JONES  | 2975.00 |
| FORD   | 3000.00 |
| SCOTT  | 3000.00 |
| KING   | 5000.00 |
+--------+---------+
```

### 3.4 了解，根据字段的位置排序

```sql
select ename,sal from emp order by 2;
+--------+---------+
| ename  | sal     |
+--------+---------+
| SMITH  |  800.00 |
| JAMES  |  950.00 |
| ADAMS  | 1100.00 |
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
| MILLER | 1300.00 |
| TURNER | 1500.00 |
| ALLEN  | 1600.00 |
| CLARK  | 2450.00 |
| BLAKE  | 2850.00 |
| JONES  | 2975.00 |
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
| KING   | 5000.00 |
+--------+---------+
14 rows in set (0.00 sec)
```

按照查询结果的第二列排序，不建议写，因为不健壮。

因为列的顺序很容易改变，修改之后，2就废了。

### 3.5 综合一点的案列

找出工资在1250到3000之间的员工信息，要求按照薪资降序排列。

```sql
select ename,sal from emp where sal between 1250 and 3000 order by sal desc;
+--------+---------+
| ename  | sal     |
+--------+---------+
| SCOTT  | 3000.00 |
| FORD   | 3000.00 |
| JONES  | 2975.00 |
| BLAKE  | 2850.00 |
| CLARK  | 2450.00 |
| ALLEN  | 1600.00 |
| TURNER | 1500.00 |
| MILLER | 1300.00 |
| WARD   | 1250.00 |
| MARTIN | 1250.00 |
+--------+---------+
10 rows in set (0.00 sec)
```

关键字顺序不变

以上语句的执行顺序必须掌握：

第一步：from

第二步：where

第三步：select

第四步：order by（排序总是在最后执行）

## 4数据处理函数

### 4.1数据处理函数 又被称为单行处理函数

单行处理函数的特点：一个输入对应一个输出，和单行处理函数的是：多行处理函数。（多行处理函数特点：多个输入一个输出）

### 4.2 单行处理函数常见的有哪些

Lower 转换小写

```sql
select lower(ename) from emp;
+--------------+
| lower(ename) |
+--------------+
| smith        |
| allen        |
| ward         |
| jones        |
| martin       |
| blake        |
| clark        |
| scott        |
| king         |
| turner       |
| adams        |
| james        |
| ford         |
| miller       |
+--------------+
14 rows in set (0.03 sec)
14个输入14个输出
```



upper转换大写

```sql
select upper(ename) from t_student;
```



substr取子串（substr（被截取的字符串，起始下标，截取的长度）

```sql
select substr(ename,1,1) as ename from emp;
注意：起始下标从1开始i，没有0
找出员工名字第一个字母是A员工信息
第一种方式：模糊查询
select ename from emp where ename like 'A%';
第二种方式：substr函数
select ename from emp where substr(ename,1,1) = 'A';
首字母大写？
select concat(upper(substr(name,1,1)),substr(name,2,length(name)-1)) as result from t_student;
```

concat函数进行字符串的拼接

```sql
select concat(empno,ename) from emp;
```

length取长度

```sql
select length(ename) enamelength from emp;
+-------------+
| enamelength |
+-------------+
|           5 |
|           5 |
|           4 |
|           5 |
|           6 |
|           5 |
|           5 |
|           5 |
|           4 |
|           6 |
|           5 |
|           5 |
|           4 |
|           6 |
+-------------+
```

trim去空格

```sql
select * from emp where ename = '  KING';
empty set (0.00sec)


select * from emp where ename  = trim('     KING');
+-------+-------+-----------+------+------------+---------+------+--------+
| EMPNO | ENAME | JOB       | MGR  | HIREDATE   | SAL     | COMM | DEPTNO |
+-------+-------+-----------+------+------------+---------+------+--------+
|  7839 | KING  | PRESIDENT | NULL | 1981-11-17 | 5000.00 | NULL |     10 |
+-------+-------+-----------+------+------------+---------+------+--------+
1 row in set (0.01 sec)
```

str_to_date将字符串转换为日期

date_format格式化日期

format设置千分位



round四舍五入

```sql
select 字段 from 表名；
select ename from emp;
select 'abc' from emp;//select后面直接跟字面量/字面值的话
+-----+
| abc |
+-----+
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
| abc |
+-----+
14 rows in set (0.00 sec)

mysql> selelct abc from emp;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'selelct abc from emp' at line 1
这样肯定报错，因为会把abc当作一个字段的名字去emp表中找abc字段去了

select 1000 as num from emp;
+------+
| num  |
+------+
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
| 1000 |
+------+
14 rows in set (0.00 sec)
结论：select后面可以跟某个表的字段名，额可以跟字面量/字面值（数据）
select 21000 as num from dept;
+-------+
| num   |
+-------+
| 21000 |
| 21000 |
| 21000 |
| 21000 |
+-------+
4 rows in set (0.01 sec)

select round(1234.567,1) from emp;
+-------------------+
| round(1234.567,0) |
+-------------------+
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
|              1235 |
+-------------------+
14 rows in set (0.01 sec)
mysql> select round(1234.567,1) from emp;//保留一位小数
+-------------------+
| round(1234.567,1) |
+-------------------+
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
|            1234.6 |
+-------------------+
select round(1236.567,-1) from emp;
+--------------------+
| round(1236.567,-1) |
+--------------------+
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
|               1240 |
+--------------------+
14 rows in set (0.00 sec)
```

rand()生成随机数

```sql
select rand() from emp;
+----------------------+
| rand()               |
+----------------------+
| 0.023437247633409916 |
|   0.6943543513252906 |
|  0.40146004445986827 |
|   0.9242371990571145 |
|  0.41680589263961265 |
|   0.3113178967603649 |
|  0.30617183661663144 |
|   0.5969055235357075 |
|  0.06601213856228827 |
|   0.5393441529379638 |
|  0.49868437973659896 |
|   0.8753894706027484 |
|   0.8808942445375904 |
|   0.7783028416133512 |
+----------------------+
14 rows in set (0.03 sec)

mysql> select round(rand()*100,0) from emp;//100以内的随机数
+---------------------+
| round(rand()*100,0) |
+---------------------+
|                  25 |
|                  91 |
|                  80 |
|                  27 |
|                  96 |
|                  97 |
|                  96 |
|                  91 |
|                  68 |
|                  65 |
|                  21 |
|                   8 |
|                  81 |
|                  78 |
+---------------------+
14 rows in set (0.00 sec)
```

Ifnull可以将null转换成一个具体值

ifnull是空处理函数，专门用来处理空的。

所有数据库中，只要有null参与的数学运算，结果就是null

```sql
select ename,sal + comm as salcomm from emp;
+--------+---------+
| ename  | salcomm |
+--------+---------+
| SMITH  |    NULL |
| ALLEN  | 1900.00 |
| WARD   | 1750.00 |
| JONES  |    NULL |
| MARTIN | 2650.00 |
| BLAKE  |    NULL |
| CLARK  |    NULL |
| SCOTT  |    NULL |
| KING   |    NULL |
| TURNER | 1500.00 |
| ADAMS  |    NULL |
| JAMES  |    NULL |
| FORD   |    NULL |
| MILLER |    NULL |
+--------+---------+
14 rows in set (0.00 sec)
```

计算每个员工的年薪？

年薪= （月薪 + 月补助） \* 12

```sql
select ename,(sal + comm) * 12 as yearsal from emp;
+--------+----------+
| ename  | yearsal  |
+--------+----------+
| SMITH  |     NULL |
| ALLEN  | 22800.00 |
| WARD   | 21000.00 |
| JONES  |     NULL |
| MARTIN | 31800.00 |
| BLAKE  |     NULL |
| CLARK  |     NULL |
| SCOTT  |     NULL |
| KING   |     NULL |
| TURNER | 18000.00 |
| ADAMS  |     NULL |
| JAMES  |     NULL |
| FORD   |     NULL |
| MILLER |     NULL |
+--------+----------+
14 rows in set (0.00 sec)

```

注意：NULL只要参与运算，最终结果一定是NULL，为了避免则个现象，需要使用ifnull函数。

如果“数据”为null的时候，把这个数据当作哪个值。

补助为null的时候，将补助当作0

```sql
select ename, (sal + ifnull(comm,0)) * 12 as yearsal from emp;
+--------+----------+
| ename  | yearsal  |
+--------+----------+
| SMITH  |  9600.00 |
| ALLEN  | 22800.00 |
| WARD   | 21000.00 |
| JONES  | 35700.00 |
| MARTIN | 31800.00 |
| BLAKE  | 34200.00 |
| CLARK  | 29400.00 |
| SCOTT  | 36000.00 |
| KING   | 60000.00 |
| TURNER | 18000.00 |
| ADAMS  | 13200.00 |
| JAMES  | 11400.00 |
| FORD   | 36000.00 |
| MILLER | 15600.00 |
+--------+----------+
14 rows in set (0.00 sec)
```

case..when..then..when..then..else..end

当员工的工作岗位是manager的时候，工资上调10%，当工资岗位是salesman的时候，工资上调50%

(注意：不修改数据库，只是将查询结果显示为工资上调0)

```sql
select ename,job,sal from emp;
select ename,job,sal as oldsal,(case job when 'MANAGER' then sal*1.1 when 'SALESMAN' then sal*1.5 else sal end) as newsal from emp;
+--------+-----------+---------+---------+
| ename  | job       | oldsal  | newsal  |
+--------+-----------+---------+---------+
| SMITH  | CLERK     |  800.00 |  800.00 |
| ALLEN  | SALESMAN  | 1600.00 | 2400.00 |
| WARD   | SALESMAN  | 1250.00 | 1875.00 |
| JONES  | MANAGER   | 2975.00 | 3272.50 |
| MARTIN | SALESMAN  | 1250.00 | 1875.00 |
| BLAKE  | MANAGER   | 2850.00 | 3135.00 |
| CLARK  | MANAGER   | 2450.00 | 2695.00 |
| SCOTT  | ANALYST   | 3000.00 | 3000.00 |
| KING   | PRESIDENT | 5000.00 | 5000.00 |
| TURNER | SALESMAN  | 1500.00 | 2250.00 |
| ADAMS  | CLERK     | 1100.00 | 1100.00 |
| JAMES  | CLERK     |  950.00 |  950.00 |
| FORD   | ANALYST   | 3000.00 | 3000.00 |
| MILLER | CLERK     | 1300.00 | 1300.00 |
+--------+-----------+---------+---------+
14 rows in set (0.00 sec)
```



## 5分组函数（多行处理函数）

多行处理函数的特点：输入多行，最终输出一行

5个：

count 计数

sum 求和

avg 平均值

max 最大值

min 最小值



注意：分组函数在使用的时候必须先进行分组，然后才能用。

​			如果你没有对数据进行分组，整张表默认为一组

找出最高工资？

```sql
select max(sal) from emp;
+----------+
| max(sal) |
+----------+
|  5000.00 |
+----------+
1 row in set (0.03 sec)
```

找出最低工资？

```sql
mysql> select min(sal) from emp;
+----------+
| min(sal) |
+----------+
|   800.00 |
+----------+
1 row in set (0.00 sec)
```

计算工资和：

```sql
mysql> select sum(sal) from emp;
+----------+
| sum(sal) |
+----------+
| 29025.00 |
+----------+
1 row in set (0.00 sec)
```

计算平均工资：

```sql
mysql> select avg(sal) from emp;
+-------------+
| avg(sal)    |
+-------------+
| 2073.214286 |
+-------------+
1 row in set (0.00 sec)
```

计算员工数量总和：

```sql
mysql> select count(ename) from emp;
+--------------+
| count(ename) |
+--------------+
|           14 |
+--------------+
1 row in set (0.00 sec)
```

**分组函数在使用的时候需要注意哪些？**

```sql
mysql> select sum(comm) from emp;
+-----------+
| sum(comm) |
+-----------+
|   2200.00 |
+-----------+
1 row in set (0.00 sec)
```

- 第一点：分组函数自动忽略NULL，你不需要提前对NUL进行处理。

```sql
mysql> select count(comm) from emp;
+-------------+
| count(comm) |
+-------------+
|           4 |
+-------------+
1 row in set (0.00 sec)
```

- 第二点：分组函数中count(\*)和count(具体字段)有什么区别

```sql
mysql> select count(*) from emp;
+----------+
| count(*) |
+----------+
|       14 |
+----------+
1 row in set (0.00 sec)

mysql> select count(comm) from emp;
+-------------+
| count(comm) |
+-------------+
|           4 |
+-------------+
1 row in set (0.00 sec)
```

count(具体字段)：表示统计该字段下所有不为NULL的元素总数。

count(\*):统计表当中的总行数（只要有一行数据，count++）因为每一行记录不肯能都为null，一行数据中有一列不为null

第三点：分组函数不能直接使用在where字句中。

找出比最低工资高的员工信息。

```sql
select ename,sal from emp where sal > min(sal);
```

表面上没问题，运行一下？

```sql
ERROR 1111 (HY000): Invalid use of group function
```

?????????????????????????????????????????????????????????????????????

说完分组查询（group by）之后就明白了

第四点：所有的分组函数可以组合起来一起用。

```sql
select sum(sal),min(sal),max(sal),avg(sal),count(*) from emp;
+----------+----------+----------+-------------+----------+
| sum(sal) | min(sal) | max(sal) | avg(sal)    | count(*) |
+----------+----------+----------+-------------+----------+
| 29025.00 |   800.00 |  5000.00 | 2073.214286 |       14 |
+----------+----------+----------+-------------+----------+
1 row in set (0.00 sec)
```

## 6分组查询（非常重要，五颗星******\*\*\*）

在实际的应用中，可能有这样的需求，需要先进性分组，然后对每一组的数据进行操作，这个时候我们需要分组查询，怎么进行分组查询呢

```sql
select...from...group by...
```

计算每一个部门的工资和？

计算每个工作岗位的平均薪资？

计算每个工作岗位的最高薪资？

select

将之前的关键字全部组合在一起，来看一下他们的  执行顺序是什么？

```sql
seelct 
	...
from 
	...
where
	...
group by
	...
order by
	...
```

以上关键字的顺序不能颠倒，需要记忆。

执行顺序是什么？

1.from

2.where

3.group by

4.select

5.order by

为什么分组函数不能直接使用在where后面？

```sql
select enmae,sal from emp where sal > min(sal);//报错
```

因为分组函数在使用的时候必须先分组之后才能使用。

where执行的时候，还没有分组。所以where后面不能出现分组函数。

```sq
select sum(sal) from emp;
```

这个没有分组，为啥sum()函数可以用呢？

因为select在 group by之后执行。

### 6.1 找出每个工作岗位的工资和？

实现思路：按照工作岗位分组，然后对工资求和。

```sql
select job,sum(sal) from emp group by job;
+-----------+----------+
| job       | sum(sal) |
+-----------+----------+
| CLERK     |  4150.00 |
| SALESMAN  |  5600.00 |
| MANAGER   |  8275.00 |
| ANALYST   |  6000.00 |
| PRESIDENT |  5000.00 |
+-----------+----------+
5 rows in set (0.00 sec)
```

以上这个语句的执行顺序？

先从emp表中查询数据。

根据job字段进行分组。

然后对每一组的数据进行sum（sal）

```sql
select ename,job,sum(sal) from emp group by job;
报错
```

以上语句在mysq中可以执行，但是毫无意义

以上语句在oracle中执行报错。

oracle的语法比mysql的语法严格。（mysql的语法相对来说松散一些！）

重点结论：在一条select语句当中，如果有group by语句的话，

select后面智能跟：参加分组的字段，以及分组函数

其他的一律不能跟。

### 6.2找出每个部门的最高薪资

实现思路是什么？

```sql
+-------+--------+-----------+------+------------+---------+---------+--------+
| EMPNO | ENAME  | JOB       | MGR  | HIREDATE   | SAL     | COMM    | DEPTNO |
+-------+--------+-----------+------+------------+---------+---------+--------+
|  7782 | CLARK  | MANAGER   | 7839 | 1981-06-09 | 2450.00 |    NULL |     10 |
|  7839 | KING   | PRESIDENT | NULL | 1981-11-17 | 5000.00 |    NULL |     10 |
|  7934 | MILLER | CLERK     | 7782 | 1982-01-23 | 1300.00 |    NULL |     10 |
|  7369 | SMITH  | CLERK     | 7902 | 1980-12-17 |  800.00 |    NULL |     20 |
|  7566 | JONES  | MANAGER   | 7839 | 1981-04-02 | 2975.00 |    NULL |     20 |
|  7788 | SCOTT  | ANALYST   | 7566 | 1987-04-19 | 3000.00 |    NULL |     20 |
|  7876 | ADAMS  | CLERK     | 7788 | 1987-05-23 | 1100.00 |    NULL |     20 |
|  7902 | FORD   | ANALYST   | 7566 | 1981-12-03 | 3000.00 |    NULL |     20 |
|  7499 | ALLEN  | SALESMAN  | 7698 | 1981-02-20 | 1600.00 |  300.00 |     30 |
|  7521 | WARD   | SALESMAN  | 7698 | 1981-02-22 | 1250.00 |  500.00 |     30 |
|  7654 | MARTIN | SALESMAN  | 7698 | 1981-09-28 | 1250.00 | 1400.00 |     30 |
|  7698 | BLAKE  | MANAGER   | 7839 | 1981-05-01 | 2850.00 |    NULL |     30 |
|  7844 | TURNER | SALESMAN  | 7698 | 1981-09-08 | 1500.00 |    0.00 |     30 |
|  7900 | JAMES  | CLERK     | 7698 | 1981-12-03 |  950.00 |    NULL |     30 |
+-------+--------+-----------+------+------------+---------+---------+--------+
14 rows in set (0.00 sec)
```

```sql
select deptno,max(sal) from emp group by deptno;
```

select后面添加ename字段没有意义。

按照部门编号分组，求每一组的最大值。

### 6.3 找出"每个部门不同岗位"的最高薪资？

```sql
mysql> select ename,job,sal,deptno from emp order by deptno;
+--------+-----------+---------+--------+
| ename  | job       | sal     | deptno |
+--------+-----------+---------+--------+
| CLARK  | MANAGER   | 2450.00 |     10 |
| KING   | PRESIDENT | 5000.00 |     10 |
| MILLER | CLERK     | 1300.00 |     10 |

| SMITH  | CLERK     |  800.00 |     20 |
| JONES  | MANAGER   | 2975.00 |     20 |
| SCOTT  | ANALYST   | 3000.00 |     20 |
| ADAMS  | CLERK     | 1100.00 |     20 |
| FORD   | ANALYST   | 3000.00 |     20 |

| ALLEN  | SALESMAN  | 1600.00 |     30 |
| WARD   | SALESMAN  | 1250.00 |     30 |
| MARTIN | SALESMAN  | 1250.00 |     30 |
| BLAKE  | MANAGER   | 2850.00 |     30 |
| TURNER | SALESMAN  | 1500.00 |     30 |
| JAMES  | CLERK     |  950.00 |     30 |
+--------+-----------+---------+--------+
14 rows in set (0.00 sec)
```

技巧：两个字段联合成一个字段看。（两个字段联合分组）

```sql
select deptno, job,max(sal) from emp group by deptno, job;
mysql> select deptno, job,max(sal) from emp group by deptno, job;
+--------+-----------+----------+
| deptno | job       | max(sal) |
+--------+-----------+----------+
|     20 | CLERK     |  1100.00 |
|     30 | SALESMAN  |  1600.00 |
|     20 | MANAGER   |  2975.00 |
|     30 | MANAGER   |  2850.00 |
|     10 | MANAGER   |  2450.00 |
|     20 | ANALYST   |  3000.00 |
|     10 | PRESIDENT |  5000.00 |
|     30 | CLERK     |   950.00 |
|     10 | CLERK     |  1300.00 |
+--------+-----------+----------+
9 rows in set (0.00 sec)
```

### 6.4 使用having可以对分完组之后的数据进一步过滤，having不能代替where

### 找出每个部门的最高薪资，要求显示最高薪资大于3000的？

第一步：找出每个部门最高薪资

按照部门编号进行分组，找出每一组的最高薪资？

```sql
select deptno,max(sal) from emp group by deptno;
+--------+----------+
| deptno | max(sal) |
+--------+----------+
|     20 |  3000.00 |
|     30 |  2850.00 |
|     10 |  5000.00 |
+--------+----------+
3 rows in set (0.00 sec)
```

第二步：要求显示最高薪资大于3000

```sql
select deptno,max(sal) from emp group by deptno having max(sal) > 3000;
+--------+----------+
| deptno | max(sal) |
+--------+----------+
|     10 |  5000.00 |
+--------+----------+
1 row in set (0.00 sec)
```

思考一个问题：以上的sql语句执行效率是不是低？

比较低，实际上可以这样考虑，先将大于3000的都找出来，然后再分组。

```sql
select deptno,max(sal) from emp where sal > 3000 group by deptno;
+--------+----------+
| deptno | max(sal) |
+--------+----------+
|     10 |  5000.00 |
+--------+----------+
1 row in set (0.00 sec)
```

优化策略：where和having优先选择where，where实在完成不了的在选择having

### where没有办法的？？？？

找出每个部门的平均薪资，要求显示"平均"薪资高于2500的。

第一步：

```sql
select deptno,avg(sal) from emp group by deptno;
```

第二步：要求显示平均薪资高于2500的

```sql
select deptno,avg(sal) from emp group by deptno having avg(sal) > 2500;
+--------+-------------+
| deptno | avg(sal)    |
+--------+-------------+
|     10 | 2916.666667 |
+--------+-------------+
1 row in set (0.00 sec)
```

## 大总结（单表的查询学完了）

```sql
select ... from ... where ... group by ... having ... order by ...
```

以上关键字智能按照这个数据来，不能颠倒

执行顺序？

1.from

2.where

3.group by

4.having

5.select 

6.order by

从某张表中查询数据

先经过where条件筛选出有价值的数据

对这些有价值的数据进行分组

分组之后可以使用having进行继续筛选。

select查询出来

最后排序输出。



找出每个岗位的平均薪资，要求显示平均薪资大于1500的，除MANAGER之外，

要求按照平均薪资降序排。

```sql
select job,avg(Sal) as avgsal from emp where job <> 'MANAGER' group by job having avg(sal) > 1500 order by avgsal desc;
+-----------+-------------+
| job       | avgsal      |
+-----------+-------------+
| PRESIDENT | 5000.000000 |
| ANALYST   | 3000.000000 |
+-----------+-------------+
2 rows in set (0.00 sec)
```

