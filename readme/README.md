2020年8月21日17:34:50

### git使用问题

#### Git使用指南

git作为支持分布式版本管理的工具，它管理的库（repository）分为本地库、远程库。 git commit操作的是本地库，git push操作的是远程库。

git commit是将本地修改过的文件提交到本地库中。 git push是将本地库中的最新信息发送给远程库。

#### 解决使用问题

- 解决git@github.com: Permission denied (publickey). Could not read from remote repository.

参考：https://www.jianshu.com/p/7d57ce4147d3

- 解决：Warning: Permanently added the RSA host key for IP address '52.74.223.119'

参考：https://blog.csdn.net/weixin_42566993/article/details/94437075

- 解决：[github提交失败并报错java.io.IOException: Authentication failed:](https://www.cnblogs.com/neillee/p/5575835.html)

参考：https://www.cnblogs.com/neillee/p/5575835.html

- 解决：idea配置github

参考：https://www.cnblogs.com/longronglang/p/8185670.html

https://www.cnblogs.com/lw--blog/p/10781687.html

https://www.cnblogs.com/jinjiyese153/p/6796668.html

### 项目遗留问题

1. 将数据库中数据下载到csv格式文件中
2. 文件获取，获取最新文件，获取某段时间的文件。



2020年8月27日14:30:37

### 项目遗留问题

1. 针对暴力获取，查询结果集会很大，路径集的长度会大于UDP发送字节数组长度65535，造成数组越界异常。
   解决思路：限定用户时间跨度超过三个月的数据，当数据量很大时，上述问题仍会存储。
   直接给用户返回获取失败的响应，那么FTP上传这部分也需要大改，先判断，再处理。
2. 程序运行时，udp发送、ftp账号信息等，每次都要重新读取配置文件，IO效率低，
   解决思路：在程序启动时，将配置信息加载进内存，使用时直接获取即可。

2020年8月31日11:07:32

## MySQL服务

软件位置：/usr/local/mysql/

软件版本： 5.6.45  

数据库账号/密码：root/123456

```mysql
mysql>  show global variables like "%datadir%";
+---------------+------------------------+
| Variable_name | Value                  |
+---------------+------------------------+
| datadir       | /usr/local/mysql/data/ |
+---------------+------------------------+
1 row in set (0.00 sec)

[root@localhost data]# du -sh *
4.0K    auto.cnf
76M     ibdata1
48M     ib_logfile0
48M     ib_logfile1
88K     localhost.localdomain.err
4.0K    localhost.localdomain.pid
1.9M    mysql
636K    performance_schema
22G     sunflower
112K    test
[root@localhost data]# pwd
/usr/local/mysql/data

mysql> select version();
+-----------+
| version() |
+-----------+
| 5.6.45    |
+-----------+
1 row in set (0.11 sec)
```

截止到目前为止，sunflower累计一年共存储22G。

## 本地数据库状态变更

### 修改MySQL数据库存储位置

#### 查询MySQL配置文件位置

```
[root@localhost etc]# locate my.cnf
/usr/local/mysql/my.cnf
/usr/local/mysql/my.cnf.bak.20190821
/usr/local/mysql/mysql-test/include/default_my.cnf
/usr/local/mysql/mysql-test/suite/federated/my.cnf
/usr/local/mysql/mysql-test/suite/ndb/my.cnf
/usr/local/mysql/mysql-test/suite/ndb_big/my.cnf
/usr/local/mysql/mysql-test/suite/ndb_binlog/my.cnf
/usr/local/mysql/mysql-test/suite/ndb_rpl/my.cnf
/usr/local/mysql/mysql-test/suite/ndb_team/my.cnf
/usr/local/mysql/mysql-test/suite/rpl/my.cnf
/usr/local/mysql/mysql-test/suite/rpl/extension/bhs/my.cnf
/usr/local/mysql/mysql-test/suite/rpl_ndb/my.cnf
```

####  查看my.cnf的基本信息

```
[root@localhost etc]# cat /usr/local/mysql/my.cnf
# For advice on how to change settings please see
# http://dev.mysql.com/doc/refman/5.6/en/server-configuration-defaults.html

[mysqld]

# Remove leading # and set to the amount of RAM for the most important data
# cache in MySQL. Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M

# Remove leading # to turn on a very important data integrity option: logging
# changes to the binary log between backups.
# log_bin

# These are commonly set, remove the # and set as required.
# basedir = .....
# datadir = .....
# port = .....
# server_id = .....
# socket = .....

# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M

secure-file-priv=
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
character_set_server=utf8
lower_case_table_names=1
max_allowed_packet=100M
wait_timeout=43200
interactive_timeout=43200
```

#### 修改datadir的路径

```
datadir = /datadisk/mysqldata
```

#### 新建存储目录

```
mkdir /datadisk/mysqldata
```

#### 将历史数据移动到新目录

```
cp -r /usr/local/mysql/data/* /datadisk/mysqldata/
```

#### 查看文件所属者

```
[root@localhost mysql]# ll /usr/local/mysql/
总用量 48
drwxr-xr-x  2 root  root   4096 8月  21 2019 bin
-rw-r--r--  1 root  root  17987 6月  10 2019 COPYING
drwxr-xr-x  7 mysql mysql   194 8月  31 10:35 data
drwxr-xr-x  2 root  root     55 8月  21 2019 docs
drwxr-xr-x  3 root  root   4096 8月  21 2019 include
drwxr-xr-x  3 root  root    316 8月  21 2019 lib
drwxr-xr-x  4 root  root     30 8月  21 2019 man
-rw-r--r--  1 root  root   1095 8月  31 10:34 my.cnf
-rw-r--r--  1 root  root    943 8月  21 2019 my.cnf.bak.20190821
drwxr-xr-x 10 root  root    291 8月  21 2019 mysql-test
-rw-r--r--  1 root  root   2496 6月  10 2019 README
drwxr-xr-x  2 root  root     30 8月  21 2019 scripts
drwxr-xr-x 28 root  root   4096 8月  21 2019 share
drwxr-xr-x  4 root  root   4096 8月  21 2019 sql-bench
drwxr-xr-x  2 root  root    136 8月  21 2019 support-files
```

#### 修改文件所属者为mysql

```
chown -R mysql. /datadisk/mysqldata/
```

#### 重启MySQL服务并检查状态

```
systemctl status mysqld.service
systemctl restart mysqld.service
```

#### 注意事项

1. 必须修改文件及文件夹所属者。
2. 如果是实时系统，上述操作可能造成数据丢失。

### 存在问题

1. 如何做到数据热迁移？不中断业务，不造成数据丢失，修改数据存放目录。
2. 程序可以使用两套及以上数据库。根据开关配置选择要操作哪个库。

SQL的数据类型与Java数据类型的对应关系
SQL数据类型 Java数据类型
CHAR                          String
VARCHAR                  String
LONGVARCHAR        String
NUMERIC                   java.math.BigDecimal
DECIMAL                     java.math.BigDecimal
BIT                                  boolean
TINYINT                         byte
SMALLINT                      short
INTEGER                        int
BIGINT                             long
REAL                                float
FLOAT                              double
DOUBLE                          double
BINARY                             byte[]
VARBINARY                      byte[]
LONGVARBINARY          byte[]
DATE                                 java.sql.Date
TIME                                  java.sql.Time
TIMESTAMP                     java.sql.Timestamp