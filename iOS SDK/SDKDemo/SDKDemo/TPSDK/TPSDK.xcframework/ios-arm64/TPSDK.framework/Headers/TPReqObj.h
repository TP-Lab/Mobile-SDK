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
FOUNDATION_EXTERN NSString *const kTPSDKActionAuth;
FOUNDATION_EXTERN NSString *const kTPSDKActionEthGetEncryptionPublicKey;
FOUNDATION_EXTERN NSString *const kTPSDKActionEthDecrypt;

#pragma mark - TPReqObj

@class TPChainObj;

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
@property (nonatomic, copy) NSString *blockchain;           // @Deprecated.
@property (nonatomic, strong) NSArray<TPChainObj *> *blockchains;
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
 * @note    App Store版1.1.9起支持SDK签名操作；波场签名只支持16进制数据
 */
@interface TPSignObj : TPReqObj

@property (nonatomic, copy) NSString *message;              // 请求签名的数据          <required>
@property (nonatomic, assign) BOOL isHash;                  // 是否signHash<optional>

@property (nonatomic, copy) NSString *wallet;               // 请求签名的钱包          <optional>

//波场专用 默认为YES
@property (nonatomic, assign) BOOL useTronHeader;

//目前以太用
@property (nonatomic, copy) NSString *signType;           //   签名类型，ethSign（默认），ethPersonalSign

@end



#pragma mark - TPTransferObj -

/*!
 * @class TPTransferObj
 * @brief 交易/转账数据
 */
@interface TPTransferObj : TPReqObj

@property (nonatomic, copy) NSString *from;                 // sender;          <optional>
@property (nonatomic, copy) NSString *to;                   // receiver;        <required>
@property (nonatomic, copy) NSString *amount;               // decimal value;   <required>
@property (nonatomic, copy) NSString *precision;            // token precision; <required> [eosio, ...]
@property (nonatomic, copy) NSString *decimal;              // token decimals;  <required> [EVM, ...]
@property (nonatomic, copy) NSString *symbol;               // token symbol;    <required>
@property (nonatomic, copy) NSString *contract;             // token contract;  <required>

/*!
 *  <optional>
 * @abstract 交易的说明信息，钱包在付款UI展示给用户
 * @discuss  最长不要超过128个字节
 */
@property (nonatomic, copy) NSString *desc;

//以太
@property (nonatomic, copy) NSString *gasPrice;     // 转账的gasPrice，可选
@property (nonatomic, copy) NSString *gas;          // 转账的gas，可选

@end



#pragma mark - TPPushTransactionObj -

/*!
 * @class TPPushTransactionObj
 * @brief Push Transaction
 */
@interface TPPushTransactionObj : TPReqObj

@property (nonatomic, strong) NSArray *actions;             // eos/bos json数组 每个对象是一个action     <required>

/*
 "{\n" +
 "    \"blockchain\":\"IOST\",\n" +
 "\t  \"action\":\"pushTransaction\",\n" +
 "\t\t\"dappName\":\"LieBi\",\n" +
 "\t\"payload\": {\n" +
 "\t\t\"tx\": {\n" +
 "\t\t\t\"gasRatio\": 1,\n" +
 "\t\t\t\"gasLimit\": 2000000,\n" +
 "\t\t\t\"actions\": [{\n" +
 "\t\t\t\t\"contract\": \"ContractF3tLtxdXwYmKsDiUtTmaQztwJQLPVf9VyWDqufMZHP5p\",\n" +
 "\t\t\t\t\"actionName\": \"bet\",\n" +
 "\t\t\t\t\"data\": \"[\\\"_bbbb\\\",\\\"tokenpocket\\\",\\\"0.19\\\"]\"\n" +
 "\t\t\t}],\n" +
 "\t\t\t\"signers\": [],\n" +
 "\t\t\t\"signatures\": [],\n" +
 "\t\t\t\"publisher\": \"\",\n" +
 "\t\t\t\"publisher_sigs\": [],\n" +
 "\t\t\t\"amount_limit\": [{\n" +
 "\t\t\t\t\"token\": \"*\",\n" +
 "\t\t\t\t\"value\": \"unlimited\"\n" +
 "\t\t\t}],\n" +
 "\t\t\t\"chain_id\": 1024,\n" +
 "\t\t\t\"reserved\": null,\n" +
 "\t\t\t\"time\": 1559296424058534000,\n" +
 "\t\t\t\"expiration\": 1559296514058534000,\n" +
 "\t\t\t\"delay\": 0\n" +
 "\t\t},\n" +
 "\t\t\"domain\": \"vote.liebi.com\",\n" +
 "\t\t\"account\": \"_bbbb\",\n" +
 "\t\t\"network\": \"MAINNET\",\n" +
 "\t\t\"txABI\": [\"ContractF3tLtxdXwYmKsDiUtTmaQztwJQLPVf9VyWDqufMZHP5p\", \"vote\", [\"_bbbb\", \"tokenpocket\", \"0.19\"]]\n" +
 "\t}\n" +
 "}"
 */
@property (nonatomic, strong) NSDictionary *payload;             // iost数据      <required>

@property (nonatomic, strong) id txData;  //波场、以太

///是否只签名
@property (nonatomic, assign) BOOL onlySign;


@end

@class TPLinkAction;

@interface TPAuthObj : TPReqObj

///   account  <optional>
@property (nonatomic, copy) NSString *account;
///   {contract:[action]}   <required>
@property (nonatomic, strong) NSArray<TPLinkAction *> *actions;
///   permisson name   <required>
@property (nonatomic, copy) NSString *perm;
///   拉起授权时，是否默认勾选所有需要link的action，默认是NO
@property (nonatomic, assign) BOOL selectAll;

@end

@interface TPEthGetEncryptionPublicKeyObjData: NSObject

@property (nonatomic, copy) NSString *address;

@end

@interface TPEthGetEncryptionPublicKeyObj : TPReqObj

@property (nonatomic, strong) TPEthGetEncryptionPublicKeyObjData *data;

@end

@interface TPEthDecryptObjData: NSObject

@property (nonatomic, copy) NSString *address;
@property (nonatomic, copy) NSString *message;


@end

@interface TPEthDecryptObj : TPReqObj

@property (nonatomic, strong) TPEthDecryptObjData *data;

@end
