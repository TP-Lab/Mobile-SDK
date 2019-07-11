# Guidance of iOS SDK

Note: This article is a beginner tutorial for the TokenPocket iOS terminal SDK and only has instructions for SDK use, assuming that the reader is already familiar with basic Xcode development tool usage as well as a certain programming knowledge base.


## Build developer environment
1. Download the TPSDK.zip file in this repository. Decompress TPSDK.zip and drag it into the project;
2. Set your **URL scheme**: Project->TARGETS->info->URL Types->Add URL scheme;
3. Add **LSApplicationQueriesSchemes** at info.plist，value with **tpoutside**

## Usage
#### Add header file at `AppDelegate.m`

```
#import <TPSDK/TPSDK.h>
```
#### Register your scheme at method `application:didFinishLaunchingWithOptions:`
```
[TPApi registerAppID:@"demoapp"];
```

#### Observe callback at method `application:openURL:`

```
[TPApi handleURL:url options:options result:^(TPRespObj *respObj) {
    respObj.result;     // TPRespResultCanceled = 0,TPRespResultSuccess, TPRespResultFailure,
    respObj.message;    // Result message
    respObj.data;       // Json details
    /* Json details:
    {
        "result" : 0,
        "action" : "sign",
        "version" : "1.0",
        "protocol" : "TPProtocol",
        "ref" : "TokenPocket",
        "wallet" : "xxxxxxxxxxx",   // When success
        "publickey" : "EOS5AvWThdghtNngWP4UcNi9DL6kF7Mnv2ccO",  // When success
        "permissions" : [           // When success
            "active",
            "owner"
        ],
        ...,
    }
    */
}];
```

## Supported actions

1. **Login:** Use to make authorization to login
2. **Sign:** Sign the data
3. **Transfer:** transfer-action
4. **Push transaction:** Push actions


## Sample

###1.Login
```
TPLoginObj *login = [TPLoginObj new];
login.dappName = @"xxx";
login.dappIcon = @"https:.../xx.png";
[TPApi sendObj:login];
```
###Login callback
```
TPRespObj.data
{
    ...,
    "ref" : "TokenPocket",
    "publickey" : "EOS5AvWThdghtNngWP4UcNi9DL6kF7Mnv2ccO",
    "sign" : "SIG_K1_JyCJtV9vqwxtyEt68UhUibkg1CmRjxtG6zkZwE...",
    "timestamp" : "1554266633",
}
```

###2.Sign
```
TPSignObj *sign = [TPSignObj new];
sign.dappName = @"xxx";
sign.dappIcon = @"https:.../xx.png";
sign.message = @"sign data...";
[TPApi sendObj:sign];
```
###Sign callback
```
TPRespObj.data
{
    ...,
    "sign" : "SIG_K1_JyCJtV9vqwxtyEt68UhUibkg1CmRjxtG6zkZwE...",
}
```

###3.Transfer
```
TPTransferObj *transfer = [TPTransferObj new];
transfer.dappName = @"xxx";
transfer.dappIcon = @"https:.../xx.png";
transfer.symbol = @"EOS";
transfer.contract = @"eosio.token";
transfer.to = @"xxxxx";
transfer.memo = @"Memo string...";
transfer.precision = @(4);
transfer.amount = @(0.0001);
[TPApi sendObj:transfer];
```
###Transfer callback
```
TPRespObj.data
{
    ...,
    "txId" : "ab1fc328fe2fb762101603b86d75d664c",
    "processed" : {
        ...
    },
}
```

###4.Push transaction
```
TPPushTransactionObj *transaction = [TPPushTransactionObj new];
transaction.dappName = @"xxx";
transaction.dappIcon = @"https:.../xx.png";
transaction.actions = @[@{@"account": @"eosio.token",
                            @"name": @"transfer",
                            @"authorization": @[@{@"actor": @"xxxxx",
                                                @"permission": @"active"}],
                            @"data": @{@"from": @"xxxxx",
                                        @"to": @"xxxxx",
                                        @"quantity": @"0.0001 EOS",
                                        @"memo": @"Memo string..."},
                        }];
[TPApi sendObj:transaction];
```

###Push transaction callback
```
TPRespObj.data
{
    ...,
    "txId" : "ab1fc328fe2fb762101603b86d75d664c",
    "processed" : {
        ...
    },
}
```

## <a name='MiniWallet'></a>MiniWallet

### <a name='Introduction'></a>Introduction

MiniWallet，可以实现对于特定操作，第三方App不需要拉起钱包，直接在应用内部完成，体验更为流畅
### <a name='init'></a>init

- Register your scheme
- Set blockchain info and plugin info
- Set seed

```
[TPApi registerAppID:@"tpsdk"];
[TPApi setSeed:@"xxxx" error:nil];
[TPApi setBlockChain:TPBlockChainTypeEOSMainNet nodeUrl:@"http://eosinfo.mytokenpocket.vip" plugNodeUrl:@"http://eosinfo.mytokenpocket.vip"];
```

###1.Auth
```
TPAuthObj *auth = [TPAuthObj new];
auth.dappName = @"xxx";
auth.dappIcon = @"https:.../xx.png";
auth.account = "xxxx";
auth.perm = "xxxx";
auth.selectAll = NO;
auth.actions = [linkActions];
[TPApi sendObj:auth resultHandle:^(TPReqType type,NSError *error){
        
    }];
```
###Auth callback
```
TPRespObj.data
{
    ...,
    "ref" : "TokenPocket",
    "publickey" : "EOS5AvWThdghtNngWP4UcNi9DL6kF7Mnv2ccO",
    "sign" : "SIG_K1_JyCJtV9vqwxtyEt68UhUibkg1CmRjxtG6zkZwE...",
    "timestamp" : "1554266633",
}
```

### <a name='pushTransaction'></a>2.pushTransaction

- Set the permission in authorization to the value which used in auth method
- Call `TPApi perm:(NSString *)perm isLinkActions:(NSArray<TPLinkAction *> *)actions account:(NSString *)account completion:(void (^)(BOOL permExisted,BOOL linked,NSError *error))completion` to check actions bind status
- If get success callback then just call 'Auth' to execute this action
- If get fail callback, you should replace the permission to active or owner, so that it can pull up TokenPocket to do this action

### <a name='MiniWalletMiniWalletapis'></a>MiniWallet apis

#### <a name='resetSeed'></a>1.Modify seed

```
+ (void)resetSeed:(NSString *)seed newSeed:(NSString *)newSeed error:(NSError **)error;
```

#### <a name='Getauthedaccounts'></a>2.Get authed accounts

```
+ (NSArray<NSString *> *)getAccounts;
```

#### <a name='Checkpermissionbindtoaccount'></a>3.Check permission bind to account

```
+ (void)isPermExist:(NSString *)perm account:(NSString *)account completion:(void (^)(BOOL exist,NSError *error))completion;
```

#### <a name='linkactionCheckactionbindtopermission'></a>4.Check action bind to permission

```
+ (void)perm:(NSString *)perm isLinkActions:(NSArray<TPLinkAction *> *)actions account:(NSString *)account completion:(void (^)(BOOL permExisted,BOOL linked,NSError *error))completion;
```

#### <a name='Clearauthedaccount'></a>5.Clear authed account

```
+ (BOOL)clearAuth:(NSString *)account;
```

# iOS接入指南
注：本文为TP iOS客户端SDK新手使用教程，只涉及教授SDK的使用方法，默认读者已经熟悉Xcode开发工具的基本使用方法，以及具有一定的编程知识基础等。


## 搭建开发环境
1. 下载仓库中TPSDK.zip文档, 解压后，拖进工程目录;
2. 在Xcode设置URL scheme:  Project->TARGETS->info->URL Types->添加 URL scheme;
3. 在info.plist中**LSApplicationQueriesSchemes**下添加一项，值为**tpoutside**

## 添加执行代码
#### 在 `AppDelegate.m` 中添加头文件
```
#import <TPSDK/TPSDK.h>
```
#### 在 `application:didFinishLaunchingWithOptions:` 方法中注册scheme
```
[TPApi registerAppID:@"demoapp"];
```

#### 在 `application:openURL:` 方法中添加监听回调方法

```
[TPApi handleURL:url options:options result:^(TPRespObj *respObj) {
    respObj.result;     // TPRespResultCanceled = 0,TPRespResultSuccess, TPRespResultFailure,
    respObj.message;    // Result message
    respObj.data;       // Json details
    /* Json details:
    {
        "result" : 0,
        "action" : "sign",
        "version" : "1.0",
        "protocol" : "TPProtocol",
        "ref" : "TokenPocket",
        "wallet" : "xxxxxxxxxxx",   // 成功时返回
        "publickey" : "EOS5AvWThdghtNngWP4UcNi9DL6kF7Mnv2ccO",  // 成功时返回
        "permissions" : [           // 成功时返回
            "active",
            "owner"
        ],
        ...,
    }
    */
}];
```


## 支持操作

1. **授权登录:** 验证授权
2. **签名:** 数据签名
3. **转账:** 转账操作
4. **Push transaction:** Push actions

## 代码示例

###1.Login
```
TPLoginObj *login = [TPLoginObj new];
login.dappName = @"xxx";
login.dappIcon = @"https:.../xx.png";
[TPApi sendObj:login];
```
###Login callback
```
TPRespObj.data
{
    ...,
    "ref" : "TokenPocket",
    "publickey" : "EOS5AvWThdghtNngWP4UcNi9DL6kF7Mnv2ccO",
    "sign" : "SIG_K1_JyCJtV9vqwxtyEt68UhUibkg1CmRjxtG6zkZwE...",
    "timestamp" : "1554266633",
}
```

###2.Sign
```
TPSignObj *sign = [TPSignObj new];
sign.dappName = @"xxx";
sign.dappIcon = @"https:.../xx.png";
sign.message = @"sign data...";
[TPApi sendObj:sign];
```
###Sign callback
```
TPRespObj.data
{
    ...,
    "sign" : "SIG_K1_JyCJtV9vqwxtyEt68UhUibkg1CmRjxtG6zkZwE...",
}
```


###3.Transfer
```
TPTransferObj *transfer = [TPTransferObj new];
transfer.dappName = @"xxx";
transfer.dappIcon = @"https:.../xx.png";
transfer.symbol = @"EOS";
transfer.contract = @"eosio.token";
transfer.to = @"xxxxx";
transfer.memo = @"Memo string...";
transfer.precision = @(4);
transfer.amount = @(0.0001);
[TPApi sendObj:transfer];
```
###Transfer callback
```
TPRespObj.data
{
    ...,
    "txId" : "ab1fc328fe2fb762101603b86d75d664c",
    "processed" : {
        ...
    },
}
```

###4.Push transaction
```
TPPushTransactionObj *transaction = [TPPushTransactionObj new];
transaction.dappName = @"xxx";
transaction.dappIcon = @"https:.../xx.png";
transaction.actions = @[@{@"account": @"eosio.token",
                        @"name": @"transfer",
                        @"authorization": @[@{@"actor": @"xxxxx",
                                            @"permission": @"active"}],
                        @"data": @{@"from": @"xxxxx",
                                    @"to": @"xxxxx",
                                    @"quantity": @"0.0001 EOS",
                                    @"memo": @"Memo string..."},
                        }];
[TPApi sendObj:transaction];
```
###Push transaction callback
```
TPRespObj.data
{
    ...,
    "txId" : "ab1fc328fe2fb762101603b86d75d664c",
    "processed" : {
        ...
    },
}
```

## <a name='MiniWallet'></a>MiniWallet

### <a name='Introduction'></a>简介 (Introduction)

MiniWallet，可以实现对于特定操作，第三方App不需要拉起钱包，直接在应用内部完成，体验更为流畅
### <a name='init'></a>初始化（init）

```
[TPApi registerAppID:@"tpsdk"];
[TPApi setSeed:@"xxxx" error:nil];
[TPApi setBlockChain:TPBlockChainTypeEOSMainNet nodeUrl:@"http://eosinfo.mytokenpocket.vip" plugNodeUrl:@"http://eosinfo.mytokenpocket.vip"];
```

###1.Auth
```
TPAuthObj *auth = [TPAuthObj new];
auth.dappName = @"xxx";
auth.dappIcon = @"https:.../xx.png";
auth.account = "xxxx";
auth.perm = "xxxx";
auth.selectAll = NO;
auth.actions = [linkActions];
[TPApi sendObj:auth resultHandle:^(TPReqType type,NSError *error){
        
    }];
```
###Auth callback
```
TPRespObj.data
{
    ...,
    "ref" : "TokenPocket",
    "publickey" : "EOS5AvWThdghtNngWP4UcNi9DL6kF7Mnv2ccO",
    "sign" : "SIG_K1_JyCJtV9vqwxtyEt68UhUibkg1CmRjxtG6zkZwE...",
    "timestamp" : "1554266633",
}
```

### <a name='pushTransaction'></a>2.pushTransaction

- 构建数据，将操作的action中的permission字段值替换成调用auth传递的perm字段
- 调用`TPApi perm:(NSString *)perm isLinkActions:(NSArray<TPLinkAction *> *)actions account:(NSString *)account completion:(void (^)(BOOL permExisted,BOOL linked,NSError *error))completion`检查action绑定状态
- 如果第二步绑定成功，则直接调用'Auth'
- 如果第二步绑定失败，则需要开发者将permission字段替换成active或者owner,以便拉起钱包执行操作

### <a name='MiniWalletMiniWalletapis'></a>MiniWallet操作 

#### <a name='resetSeed'></a>1.修改seed

```
+ (void)resetSeed:(NSString *)seed newSeed:(NSString *)newSeed error:(NSError **)error;
```

#### <a name='Getauthedaccounts'></a>2.获取已授权账号信息 

```
+ (NSArray<NSString *> *)getAccounts;
```

#### <a name='Checkpermissionbindtoaccount'></a>3.检查权限是否存在 

```
+ (void)isPermExist:(NSString *)perm account:(NSString *)account completion:(void (^)(BOOL exist,NSError *error))completion;
```

#### <a name='linkactionCheckactionbindtopermission'></a>4.检查权限是否link到action

```
+ (void)perm:(NSString *)perm isLinkActions:(NSArray<TPLinkAction *> *)actions account:(NSString *)account completion:(void (^)(BOOL permExisted,BOOL linked,NSError *error))completion;
```

#### <a name='Clearauthedaccount'></a>5.清除本地授权

```
+ (BOOL)clearAuth:(NSString *)account;
```
