#!/usr/bin/python3
# 文件名：server.py

# 导入 socket、sys 模块
import socket
import sys
import main
import pickle
import urllib
import html
import virus_total_check
class WAF(object):

    def __init__(self):
        good_query_list = self.get_query_list('goodqueries.txt')
        bad_query_list = self.get_query_list('badqueries.txt')

        good_y = [0 for i in range(0, len(good_query_list))]
        bad_y = [1 for i in range(0, len(bad_query_list))]

        queries = bad_query_list + good_query_list
        y = bad_y + good_y

        # converting data to vectors  定义矢量化实例
        self.vectorizer = TfidfVectorizer(tokenizer=self.get_ngrams)

        # 把不规律的文本字符串列表转换成规律的 ( [i,j],tdidf值) 的矩阵X
        # 用于下一步训练分类器 lgs
        X = self.vectorizer.fit_transform(queries)

        # 使用 train_test_split 分割 X y 列表
        # X_train矩阵的数目对应 y_train列表的数目(一一对应)  -->> 用来训练模型
        # X_test矩阵的数目对应 	 (一一对应) -->> 用来测试模型的准确性
        X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.05, random_state=42)

        # 定理逻辑回归方法模型
        self.lgs = LogisticRegression()

        # 使用逻辑回归方法训练模型实例 lgs
        self.lgs.fit(X_train, y_train)

        # 使用测试值 对 模型的准确度进行计算
        print('模型的准确度:{}'.format(self.lgs.score(X_test, y_test)))

    # 对 新的请求列表进行预测
    def predict(self, new_queries):
        global tmp
        new_queries = [urllib.parse.unquote(url) for url in new_queries]
        X_predict = self.vectorizer.transform(new_queries)
        res = self.lgs.predict(X_predict)

        return res

    # 得到文本中的请求列表打开文本文档中，提取数据
    def get_query_list(self, filename):
        directory = str(os.getcwd())
        # directory = str(os.getcwd())+'/module/waf'
        filepath = directory + "/" + filename
        data = open(filepath, 'r',encoding='utf-8').readlines()
        query_list = []
        for d in data:
            d = str(urllib.parse.unquote(d))  # converting url encoded data to simple string
            # print(d)   #输出数据
            query_list.append(d)
        return list(set(query_list))

    # tokenizer function, this will make 3 grams of each query
    def get_ngrams(self, query):
        tempQuery = str(query)
        ngrams = []
        for i in range(0, len(tempQuery) - 3):
            ngrams.append(tempQuery[i:i + 3])
        return ngrams
def detect(url):
    with open('D:\Desktop\QRcodeCheck\QRcodeCheck\FeatureCheck\lgs.pickle', 'rb') as input:
        w = pickle.load(input) #调用训练好的模型预测url安全性
    #url="http://cronatech.com/clientdetailcss/google/index.php.htm"
    #url = "https://www.baidu.com/"
    result1 = w.predict([url])
    #print (result)
    result2=virus_total_check.url_check(url)#调用virus_total API预测URL安全性
    if result1==1 and result2==1:#结合二者预测的结果进行判断
        return ("bad")
        with open('badresult.txt', 'a+') as f:
            f.writelines(url + '\n')
            f.close()
    elif result1 == 1 and result2 == -1:
        return ("bad")
        with open('badresult.txt', 'a+') as f:
            f.writelines(url + '\n')
            f.close()
    elif result1==1 or result2==1:
        return ("bad")
    elif result1==0 and result2==0:
        return ("good")
        with open('goodresult.txt', 'a+') as f:
            f.writelines(url + '\n')
            f.close()
    elif result1==0 and result2==-1:
        return ("good")
        with open('goodresult.txt', 'a+') as f:
            f.writelines(url + '\n')
            f.close()

#try:ConnectionResetError
# 创建 socket 对象
serversocket = socket.socket(
    socket.AF_INET, socket.SOCK_STREAM)

# 获取本地主机名
host = "127.0.0.1"

port = 9999

# 绑定端口号
serversocket.bind((host, port))

# 设置最大连接数，超过后排队
serversocket.listen(5)


while True:
    # 建立客户端连接
    print("服务端启动,正在监听......")
    clientsocket, addr = serversocket.accept()

    print("连接地址: %s" % str(addr))



    msg = clientsocket.recv(1024)  # 从客户端连接接收消息内容
    '''
    接收消息后需要具体查看消息的数据格式、数据类型等如果不符合要求需要进行转换或者修改
    '''
    ######接下来就是调用核心函数对客户端的消息进行处理了##########
    #print (str(msg))
    #result = str(msg)+ "\r\n"

    result = detect(str(msg))
    ######接下来就是将处理结果写入连接，返回给客户端

    clientsocket.send(result.encode('utf-8'))
    clientsocket.close()
