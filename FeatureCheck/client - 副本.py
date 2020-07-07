#!/usr/bin/python3
# 文件名：client.py

# 导入 socket、sys 模块
import socket
import sys
import time
start = time.perf_counter()#程序开始时间
# 创建 socket 对象
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# 获取本地主机名
host = socket.gethostname()

# 设置端口号
port = 9999

# 连接服务，指定主机和端口
s.connect((host, port))
msg ="https://www.baidu.com/"
s.send(msg.encode())
print("发送完成")

# 接收小于 1024 字节的数据
msg = s.recv(1024)

s.close()

print (msg.decode('utf-8'))
elapsed = (time.perf_counter() - start)#计算程序运行时间
print("Time used:", elapsed)