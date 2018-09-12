# tp-wallet-native-ios

## 下载TPSDK.zip文档，解包后拉进项目，需要设置两个项目参数：
1. 在Project - Build settings 搜索bitcode， 设置为**NO**;
2. 在Project - Build settings 搜索Other Linker Flags, 加一项 **-ObjC**


#### 开始使用：
1. 在info.plist的URL Types里加一个单独给TokenPocket 的URL scheme，如 ***xxxfortpsdk***;
2. AppDelegate.m
```
#import <TPSDK/TPApi.h>

-(application:didFinishLaunchingWithOptions:)方法里注册:
[TPApi registerAppID:@"xxxfortpsdk"];
```


#### 发送请求
```
TPTransferObj *transfer = [TPTransferObj new];
transfer.from = @"xxx";
transfer.to = @"xxx";
...  // 设置transfer参数;
[TPApi sendObj:transfer];
```


