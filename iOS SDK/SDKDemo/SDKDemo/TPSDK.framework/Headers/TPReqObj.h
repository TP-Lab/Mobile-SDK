//
//  TPReqObj.h
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import <Foundation/Foundation.h>


#pragma mark ~~~~ TPReqObj ~~~~

/*! @class   TPReqObj
 * @abstract 发起请求的基本数据
 * @discuss  避免自己继承TPReqObj, 发起请求时只发送SDK内部的TPReqObj子类;
 */
@interface TPReqObj : NSObject

@property (nonatomic, copy) NSString *protocol;     // 协议名，钱包用来区分不同协议，本协议为 SimpleWallet
@property (nonatomic, copy) NSString *version;      // 协议版本信息，如1.0
@property (nonatomic, copy) NSString *dappName;     // dapp名字，用于在钱包APP中展示;   <可选>
@property (nonatomic, copy) NSString *dappIcon;     // dapp图标Url，用于在钱包APP中展示，<可选>
@property (nonatomic, copy) NSString *blockchain;   // 底层  "eos enu eth moac"
@property (nonatomic, copy) NSString *action;       // 支付时，赋值为transfer; <必须>
@property (nonatomic, copy) NSString *actionId;     // 本次支付的唯一标识;
@property (nonatomic, copy) NSNumber *expired;      // 过期时间，unix时间戳
@property (nonatomic, copy) NSString *callbackUrl;  // 回调url，通过此url 将txId和actionId通过post请求告诉dappServer

/**
 * @abstract 由dapp生成的业务参数信息，需要钱包在转账时附加在memo中发出去;
 * @discuss  格式为:k1=v1&k2=v2; 钱包转账时还可附加ref参数标明来源;   <可选>
 *           如:k1=v1&k2=v2&ref=walletname
 */
@property (nonatomic, copy) NSString *memo;
@property (nonatomic, strong) NSString *dappData;
/*!
 * @abstract 压缩后的协议内容
 * @discuss  a.如果使用了压缩算法，则该字段表示整个json字符串压缩后的内容
 *           b.如果没有压缩，该字段可以为空
 */
@property (nonatomic, copy) NSString *compressedData;
@property (nonatomic, assign) int compress;     //对协议内容压缩方式; 0 表示不压缩, 其他待定

/**
 * @abstract 处理完成后的回调，回调通知DApp;
 * @discuss  格式为: schema://xxx/xxx?param; //={result:int, // 返回结果，0 成功，1 失败 2 取消
 *                                             message:string, // 结果描述，可选
 *                                                data:{txId:xxx,actionId:xxx}
 *                                             }
 */
@property (nonatomic, copy) NSString *callbackSchema;


@end

#pragma mark ~~~~~~~~~~~~~~~~~~~~~~~
#pragma mark ~~~~ TPTransferObj ~~~~

/*!
 * @class TPTransferObj
 * @brief 交易/转账数据
 */
@interface TPTransferObj : TPReqObj

@property (nonatomic, copy) NSString *from;         // 付款人的EOS账号;   <可选>
@property (nonatomic, copy) NSString *to;           // 收款人的EOS账号;   <必须>
@property (nonatomic, copy) NSNumber *amount;       // 转账数额; <float> <必须>
@property (nonatomic, copy) NSNumber *precision;    // 转账的token精度，小数点后面的位数; <int> <必须>
@property (nonatomic, copy) NSString *symbol;       // 转账的token名称;   <必须>
@property (nonatomic, copy) NSString *contract;     // 转账的token所属的contract账号名;  <必须>

/*!
 * @abstract 交易的说明信息，钱包在付款UI展示给用户
 * @discuss  最长不要超过128个字节;  <可选>
 */
@property (nonatomic, copy) NSString *desc;

@end




