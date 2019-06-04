//
//  TPReqObj.h
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import <Foundation/Foundation.h>

/**  Action值 */
FOUNDATION_EXTERN NSString *const kTPSDKActionSign;
FOUNDATION_EXTERN NSString *const kTPSDKActionLogin;
FOUNDATION_EXTERN NSString *const kTPSDKActionTransfer;
FOUNDATION_EXTERN NSString *const kTPSDKActionPushTransaction;



#pragma mark - TPReqObj

/*! @class   TPReqObj
 * @abstract 发起请求的基本数据
 * @discuss  避免自己继承TPReqObj, 发起请求时只发送SDK内部的TPReqObj子类;
 */
@interface TPReqObj : NSObject

@property (nonatomic, copy, readonly) NSString *protocol;   // 协议名，钱包用来区分不同协议，本协议为 SimpleWallet
@property (nonatomic, copy, readonly) NSString *version;    // 协议版本信息,如1.0
@property (nonatomic, copy, readonly) NSString *action;     // login,transfer...(子类自动赋值)       <autofill>
@property (nonatomic, copy) NSString *dappName;             // dapp名字,用于在钱包展示                <optional>
@property (nonatomic, copy) NSString *dappIcon;             // dapp图标Url,用于在钱包展示             <optional>
@property (nonatomic, copy) NSString *blockchain;           // eos,enu,eth,moac...默认空,为eos      <optional>
@property (nonatomic, copy) NSString *actionId;             // 本次操作的唯一标识                     <optional>
@property (nonatomic, copy) NSNumber *expired;              // 过期时间，时间戳(s)；默认0，为永不过期    <optional>
@property (nonatomic, copy) NSString *callbackUrl;          // 回调url,通过此url将txId和actionId通过post请求告诉dappServer

/**
 * <optional>
 * 转账时:
 * @abstract 由dapp生成的业务参数信息，需要钱包在转账时附加在memo中发出去;
 * @discuss  格式为:k1=v1&k2=v2; 钱包转账时还可附加ref参数标明来源
 *           如:k1=v1&k2=v2&ref=walletname
 * 登录时:
 *      作为附加展示信息
 */
@property (nonatomic, copy) NSString *memo;
@property (nonatomic, strong) NSString *dappData;

/*!
 * @abstract 压缩后的协议内容
 * @discuss  a.如果使用了压缩算法，则该字段表示整个json字符串压缩后的内容
 *           b.如果没有压缩，该字段可以为空
 */
@property (nonatomic, copy) NSString *compressedData;
@property (nonatomic, assign) int compress;                 //对协议内容压缩方式; 0 表示不压缩, 其他待定

/**
 * @abstract 处理完成后的回调，回调通知DApp;
 * @discuss  格式为: xxx://xxx?param;  xxx部分可自定义;
 */
@property (nonatomic, copy) NSString *callbackSchema;


@end


#pragma mark - TPLoginObj -

/*!
 * @class TPLoginObj
 * @brief 登录授权数据
 */
@interface TPLoginObj : TPReqObj

@property (nonatomic, copy) NSString *wallet;               // 请求授权的EOS账号       <optional>

@end


#pragma mark - TPSignObj -

/*!
 * @class   TPSignObj
 * @brief   数据签名
 * @note    TP App iOS企业版0.7.2 / App Store版1.1.9起支持SDK签名操作
 */
@interface TPSignObj : TPReqObj

@property (nonatomic, copy) NSString *message;              // 请求签名的数据          <required>
@property (nonatomic, copy) NSString *wallet;               // 请求签名的钱包          <optional>

@end



#pragma mark - TPTransferObj -

/*!
 * @class TPTransferObj
 * @brief 交易/转账数据
 */
@interface TPTransferObj : TPReqObj

@property (nonatomic, copy) NSString *from;                 // 付款人的EOS账号        <optional>
@property (nonatomic, copy) NSString *to;                   // 收款人的EOS账号        <required>
@property (nonatomic, copy) NSNumber *amount;               // 转账数额[float]       <required>
@property (nonatomic, copy) NSNumber *precision;            // token精度,小数点后的位数  [int] <required>
@property (nonatomic, copy) NSString *symbol;               // token的名称           <required>
@property (nonatomic, copy) NSString *contract;             // token合约名           <required>

/*!
 *  <optional>
 * @abstract 交易的说明信息，钱包在付款UI展示给用户
 * @discuss  最长不要超过128个字节
 */
@property (nonatomic, copy) NSString *desc;

@end



#pragma mark - TPPushTransactionObj -

/*!
 * @class TPPushTransactionObj
 * @brief Push Transaction
 */
@interface TPPushTransactionObj : TPReqObj

@property (nonatomic, strong) NSArray *actions;             // json数组 每个对象是一个action     <required>

@end


