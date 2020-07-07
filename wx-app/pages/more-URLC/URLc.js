Page({
  data: {
    URLdata:""
  },
  //事件处理函数
  ToURLc: function () {
    console.log(this.data.URLdata)
    wx.showLoading({
      title: "正在检测......",
      mask: true
    })
    //开始检测该URL
    let _this = this
    wx.request({
      url: 'http://127.0.0.1:20002',
      data: {
        imageContent: _this.data.URLdata
      },
      success: function (res) {
        //将检测结果显示给用户
        console.log("请求成功")
        console.log(res)
        //模态框显示检测结果
        wx.showModal({
          title: "当前二维码检测结果为：\r\n" + res.data,
          showCancel: false
        })
        wx.hideLoading()
      },
      fail: function () {
        wx.hideLoading()
        wx.showModal({
          title: "接口访问失败！请稍后重试或者别试了",
          showCancel: false
        })
      }
    })
  },
  getInput: function (e) {
    this.setData({
      URLdata: e.detail.value
    })
  }
})
