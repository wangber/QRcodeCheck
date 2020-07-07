//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    hello:"*欢迎使用检测工具*",
    buttontext:"选择需检测的二维码",
    getIfGreat:"",
  },
  //事件处理函数
  readImage:function(){
   //调用二维码扫描
    let _this = this
    wx.scanCode({
      success(res) {
       // console.log(res)
        //获取到二维码内容
        var imageContent = ""
        imageContent = res.result
        console.log(imageContent)
        //将二维码发送给后台程序
        wx.showLoading({
          title: "正在检测......",
          mask:true
        })
        wx.request({
          url: 'http://127.0.0.1:20002',
          data:{
            imageContent:imageContent
          },
          success:function(res){
            //将检测结果显示给用户
            console.log("请求成功")
            console.log(res)
            //模态框显示检测结果
            wx.showModal({
              title:"当前二维码检测结果为：\r\n"+res.data,
              showCancel: false
            })
            wx.hideLoading()
          },
          fail:function(){
            wx.hideLoading()
            wx.showModal({
              title:"接口访问失败！请稍后重试或者别试了",
              showCancel: false
            })
          }
        }),
        console.log("请求结束？")
      }
    })
  },//getImage end
  Tomore:function(){
    console.log("ggg")
    wx.navigateTo({
      url: '/pages/moretools/moretools',
      fail: function (res) {
        console.log(res);
      },
      success:function(res){
        console.log(res);
      }
    })
    
  }
})
