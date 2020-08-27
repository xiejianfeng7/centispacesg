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

