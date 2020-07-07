package xyz.wangber.service.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xyz.wangber.service.OP;
import xyz.wangber.service.impl.OPimpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Application {
    public static void main(String [] args) throws IOException, SQLException {
        //创建一个socket对象
        ServerSocket serverSocket;
        serverSocket = new ServerSocket(20003);
        while(true){
            System.out.println("等待远程连接，端口号："+serverSocket.getLocalPort());
            Socket server = serverSocket.accept(); //监听到有连接
            //获取服务端到客户端的输出流
            OutputStream out = server.getOutputStream();
            InputStream in =server.getInputStream();//获取从客户端发送到服务端的数据流
            BufferedReader inRead = new BufferedReader(new InputStreamReader(in));//创建一个缓冲字符输入流

            String bwData = inRead.readLine();//读取一行（客户端的数据只包括一行，即能够全部读完）
            /*当读取到换行符或者流结束时，才会得到结果，否则将会一直无法得到结果
             * 关于Java的IO真是一门大学问*
             * */
            //去除行中的换行符
            bwData = bwData.replace("\n","");
            System.out.println(bwData.getClass().toString());
            System.out.println("接收到客户端信息："+bwData);
            //对json对象进行解析
            JSONObject getData = JSON.parseObject(bwData);
            String urldata = getData.get("URL").toString().replace("\n","");
            System.out.println(urldata);
            //**********获取到数据之后调用主功能函数进行处理**********
            OP op = new OPimpl();
            String result = op.urlCheck(urldata, 1);
            System.out.println(result);
            //*********将处理结果写入连接，反馈给客户端*********
            out.write(result.getBytes());
            server.close();
        }
    }
}
