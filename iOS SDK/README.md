# iOS接入指南

注：本文为TP iOS客户端SDK新手使用教程，只涉及教授SDK的使用方法，默认读者已经熟悉XCode开发工具的基本使用方法，以及具有一定的编程知识基础等。

### 下载TokenPocket客户端SDK文件
1. 下载仓库中TPSDK.zip文档

### 搭建开发环境
1. 在Xcode创建您的工程
2. 将TPSDK解压，并拉入工程目录中
3. 在Project - Build settings 搜索bitcode， 设置为**NO**;
4. 在Project - Build settings 搜索Other Linker Flags, 加一项 **-ObjC**
![Build Settings](http://thyrsi.com/t6/369/1536744859x-1566673321.png)

5. 在Xcode设置URL scheme
![Scheme](http://thyrsi.com/t6/369/1536745754x-1566679533.png)

### 添加执行代码
* 在AppDelegate中添加头文件

```
* #import <TPSDK/TPSDK.h>
```
* 在application:didFinishLaunchingWithOptions:方法中注册scheme

```
[TPApi registerAppID:@"demoapp"];
```

* 在application:openURL:方法中添加监听回调方法

```
[TPApi handleURL:url options:options result:^(TPRespObj *respObj) {
//your code here
}];
```

* 如何发送一个请求，示例

```
TPTransferObj *transfer = [TPTransferObj new];
transfer.from = @"xxx";
transfer.to = @"xxx";
...  // 设置transfer参数;
[TPApi sendObj:transfer];
```
