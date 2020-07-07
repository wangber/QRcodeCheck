// pages/moretools/moretools.js
Page({
  Tocc: function () {
    console.log("转向二维码生成")
    wx.navigateTo({
      url: '../../pages/more-CreateCode/createCode',
      fail: function (res) {
        console.log(res);
      },
      success: function (res) {
        console.log(res);
      }
    })
  },
  Touc: function () {
    console.log("转向二维码生成")
    wx.navigateTo({
      url: '../../pages/more-URLC/URLc',
      fail: function (res) {
        console.log(res);
      },
      success: function (res) {
        console.log(res);
      }
    })
  }

})