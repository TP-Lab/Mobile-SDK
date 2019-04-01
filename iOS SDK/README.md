# Guide of iOS integration

Note: This article is a beginner tutorial for the TokenPocket iOS terminal SDK and only has instructions for SDK use, assuming that the reader is already familiar with basic Xcode development tool usage as well as a certain programming knowledge base.

### Download the TokenPocket terminal SDK file 
1. Download the TPSDK.zip file in the repository

### Build developer environment
1. Build your program in xCode;
2. Decompress TPSDK.zip and drag it into the project;
3. Set your **URL scheme**
![Scheme](http://thyrsi.com/t6/369/1536745754x-1566679533.png)
4. Add **LSApplicationQueriesSchemes** at info.plist，value with **tpoutside**

### Add code to your project
* Add header file at **AppDelegate**

```
* #import <TPSDK/TPSDK.h>
```
* register your scheme at method **application:didFinishLaunchingWithOptions:**
```
[TPApi registerAppID:@"demoapp"];
```

* observe callback at method **application:openURL:**

```
[TPApi handleURL:url options:options result:^(TPRespObj *respObj) {
//your code here
}];
```

* Here's a demo 

```
TPTransferObj *transfer = [TPTransferObj new];
transfer.from = @"xxx";
...  // setting transfer params;
[TPApi sendObj:transfer];

```








# iOS接入指南
注：本文为TP iOS客户端SDK新手使用教程，只涉及教授SDK的使用方法，默认读者已经熟悉Xcode开发工具的基本使用方法，以及具有一定的编程知识基础等。

### 下载TokenPocket客户端SDK文件
1. 下载仓库中TPSDK.zip文档

### 搭建开发环境
1. 在Xcode创建您的工程;
2. 将TPSDK解压，并拉入工程目录中;
3. 在Xcode设置URL scheme
![Scheme](http://thyrsi.com/t6/369/1536745754x-1566679533.png)
4. 在info.plist中**LSApplicationQueriesSchemes**下添加一项，值为**tpoutside**

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
...  // 设置transfer参数;
[TPApi sendObj:transfer];

```
