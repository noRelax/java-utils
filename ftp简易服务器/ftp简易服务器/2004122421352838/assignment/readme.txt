一个实现了的ftp服务器基本功能程序，服务器的安全管理代码还没有开发，
但该程序已经可以适用于网络
该ftp服务器还具有一些管理功能，主要是针对用户管理的，有如下命令：
list listuser adduser deluser help ?
关于用户的权限设置，还未开发，因此所有登陆的用户的都具有最高权限，

有2个缺省用户： 
user:ruan password:good
user:wen  password:well

用户信息在同目录下的user.cfg中，注意用户信息的书写，一个用户的信息占一行：
user|password|directory|

ruanwen@ebupt.com