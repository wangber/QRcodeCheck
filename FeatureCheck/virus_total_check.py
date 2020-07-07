import requests
import time

def duplicateremoval(file_url1):
    file = open(file_url1, "r", encoding="utf-8", errors="ignore")
    t1 = set()

# 调用virustotal提供的API对url进行检测
# #返回值是在几个数据库中发现是异常，如返回0则代表没有异常,返回-1代表没有在数据库中找到匹配
def url_check(my_url):
    url = "https://www.virustotal.com/vtapi/v2/url/report"
    params = {"apikey": "a2c4c89637e57dc27bdb3048989da16c530c2dfffc4783c62fa95ea936e19d80", "resource": my_url}
    response = requests.get(url, params=params)
    time.sleep(15)
    if response.status_code == 200:
        json = response.json()
        #print(json)  #显示完整的json信息
        if json['response_code'] == 1:#response_code ：若搜索项不在VirusTotals的收录中，将被返回0；若请求项仍入队请求分析，将被返回-2；若请求项存在并且可被检索，将返回1。

            if int(json['positives'])==0:
                #print ("该站点安全")
                return 0
            elif (int(json['positives'])/int(json['total']))>0.03:
                #print('在%s个检测结果中有%s个认为该站点为恶意站点' % (json['total'], json['positives']))
                #print("该站点存在风险")
                #f = open("badqueries.txt", "a+")
                #f.write("%s\n" % (my_url))
                #duplicateremoval('badqueries.txt')
                return 1
            #else:
             #   print ("该站点安全")
            #return json['positives']
        else:
            #print('没有在数据库中找到匹配')
            return -1
    else:
        #print('没有在数据库中找到匹配')
        return -1




