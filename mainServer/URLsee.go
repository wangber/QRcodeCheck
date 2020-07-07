/*****
模块调用主框架
by:wangber
*******/
package main

import (
	"encoding/json"
	"fmt"
	"net"
)
type BWParam struct{ //黑白名单传参接口参数包装
	URL string
	MODE int
}

//定义黑白名单Socket调用函数
func bwUrl(url string,mode int)string{//接收URL和操作模式作为参数 反回操作结果
	fmt.Println("正在进行黑白名单检测......")
	//利用url作为参数
	//将参数包装为json格式
	jsonMessage, _ := json.Marshal(&BWParam{
		URL:url,
		MODE:mode,
	})
	//连接Socket
	conn,err := net.Dial("tcp","127.0.0.1:20003")
	if err != nil {
		panic("在连接Url黑白名单模块时出现错误")
	}
	//
	//传送数据给客户端
	_,err = conn.Write(append(jsonMessage,[]byte("\n")...))//为了迎合java的IO流机制，只能添加换行
	if err != nil{
		panic("向服务端写入数据时出错")
	}
	//接收服务端数据
	buf := [3]byte{}
	conn.Read(buf[:])
	fmt.Println(string(buf[:])) //这里会写入大量0
	fmt.Println("黑白名单检测完毕！")
	return string(buf[:])
}
func featureSee(url string) (string,string){
	fmt.Println("开始进行URL特征检测......")
	//调用特征检测套接字
	conn,err := net.Dial("tcp","127.0.0.1:9999")
	if err != nil{
		panic("访问套接字特征检测套接字时发生了错误!")
	}
	_,err = conn.Write([]byte(url))
	if err !=nil{
		panic("在向套接字写入数据时出错")
	}
	buf := [3]byte{}
	conn.Read(buf[:])
	result := string(buf[:])
	fmt.Println("URL特征检测完成！")
	return result,url //同时放回判断结果和URL
}

func MainLogic(url string)string{
	//黑白名单
	s := bwUrl(url,1)
	if s =="bad"{
		return s
	}
	//特征检测
	sFeature,myUrl := featureSee(url)
	if sFeature == "bad"{
		bwUrl(myUrl,2) //添加进入黑名单
		return "bad"
	}
	return "good"
}
