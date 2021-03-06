# QRcodeCheck

基于多模块设计的二维码安全检测工具

## 模块组成：

二维码图像处理模块

黑白名单处理

URL特征检测模块

服务总体框架

### 代码说明

* bwlistLite

  黑白名单处理模块。使用IDEA+Maven构建的Java项目，数据库使用sqlLite3.

  启动方式：xyz.wangber.service.socket包下的Application为主程序，启动主程序，模块运行在20003端口下监听Socket请求。

* FeatureCheck

  URL特征检测模块。Python Socket应用。requirements.txt文件描述了测试运行该模块的环境中具备所有python模块，但并非所有模块需要使用到。

  启动方式：在运行前根据自己的项目目录，可能需要修改serve.py文件中模型文件路径的相关配置。开启虚拟环境（也可以使用自己的主环境），在Python虚拟环境下直接运行server.py文件，即可启动模块。模块运行在9999端口下监听Socket请求。

* mainServer

  服务模块组织框架。使用Golang编写，通过Socket统一的调用与数据传输，完成模块间的调用组织和通信，搭建起工具的服务。

  启动方式：在mainServer更根目录下，命令行运行go build编译，生成可执行文件，保证其余模块的Socket服务能够被正常调用。执行可执行文件启动服务。服务以http接口形式供前端交互调用。

* wx-app

  目前工具以微信小程序的形式提供交互，该目录为微信小程序工程目录。其中二维码的生成工具使用js完成，在小程序进行中直接调用。

  启动方式：使用微信开发者工具导入工程，编译。项目设置中设置“不校验合法域名”，才能使用http接口进行测试。

  ## 功能与交互

目前提供的交互方式为微信小程序，但这并不是这个工具最终的目标。运用完整的服务接口，将来可以以多种方式提供二维码安全检测的帮助。

**********

主界面：

![](https://gitee.com/wangber/imgbed/raw/master/img/20200708004721.png)

选择扫码后：

![](https://gitee.com/wangber/imgbed/raw/master/img/20200708005736.png)

![](https://gitee.com/wangber/imgbed/raw/master/img/20200709003238.png)

更多工具，提供二维码的生成、针对URL的检测：

![](https://gitee.com/wangber/imgbed/raw/master/img/20200709003308.png)
分别进入两个工具，交互界面如下：根据相应的提示输入内容可以进行检测和二维码的生成：
![](https://gitee.com/wangber/imgbed/raw/master/img/20200709003339.png)

​	![](https://gitee.com/wangber/imgbed/raw/master/img/20200709003403.png)



## 项目特色

* 黑白名单中的接口式设计思想，统一的参数传递设计，内部有轻耦合设计的影子；
* 自主使用Socket数据传递完成总体组织框架的设计，实现模块间通信，体现微服务思想与设计风格；
* 多模块检测，较高的时效性和可用性；

