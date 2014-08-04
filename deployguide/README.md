
部署向导
===========================

1) 找到mysql 所在目录
   cd bin
   mysql -u root -p   ;密码我不记得了。。应该是空密码吧
   
   成功登录以后就执行
   drop database wzqr;
   create database wzqr;
   
   不必在创建用户了 之前我创建过。
*********************************

2) 现在去找下原程序安装目录，具体位置我也不太确定了;后面几个盘的
   找到tomcat8 路径以后
   先关闭tomcat8 然后移除webapps文件夹中的wzqrserver文件夹和wzqrserver.war
   
   复制wzqrserver.war到webappps
********************************
搞定!

btw:http://115.29.228.62:9999/ext/wzqrserver.war 这里下载
   
