//
//  TPApi.h
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TPRespObj.h"
#import "TPReqObj.h"
#import "TPLinkAction.h"
#import "TPBlockchainHeader.h"

/*!
 * @class TPApi
 * @brief SDK入口
 */
@interface TPApi : NSObject

/*!
 * @brief 注册ID
 * @param AppID a) 请确保AppID已经添加在Xcode工程info.plist-> URL types -> URL Schemes里!
 *              b) AppID也作为App回调时的URL跳转, 务必设置好AppID!
 *              c) 为了避免误操作其他App的跳转请求，请设置一个唯一的appID给TPSDK, 建议为各个SDK添加命名后缀, 如xxxfortpsdk;
 *
 * @disucss 在AppDelegate -(application:didFinishLaunchingWithOptions:)方法里注册
 */
+ (void)registerAppID:(NSString *)AppID;

/*!
 * @brief 设置网络类型和节点，如要支持本地交易处理，必须调用此接口
 *
 * @param blockChain 网络类型
 * @param nodeUrl 节点url
 * @param plugNodeUrl 插件节点url
 */
+ (void)setBlockChain:(TPBlockChainType)blockChain nodeUrl:(NSString *)nodeUrl plugNodeUrl:(NSString *)plugNodeUrl;


/*!
 * @brief 设置访问SDK本地隐私数据的密码，首次设置会记录为初始密码，下次设置将会和首次的密码匹配，密码不匹配将无法访问SDK本地隐私数据。
 *        如要支持本地交易处理，必须调用此接口
 *
 * @param seed 密码
 * @param error 错误信息（密码不匹配）
 */
+ (void)setSeed:(NSString *)seed error:(NSError **)error;

/*!
 * @brief 重设访问SDK本地隐私数据的密码。
 *
 * @param seed 原密码
 * @param newSeed 新密码
 * @param error 错误信息（密码不匹配）
 */
+ (void)resetSeed:(NSString *)seed newSeed:(NSString *)newSeed error:(NSError **)error;

/*!
 * @brief 日志开关
 *
 * @param enable 是否打印日志
 */
+ (void)enableLog:(BOOL)enable;

/*!
 * @brief 向TP发起请求 支持EOS BOS IOST，不支持TPAuthObj请求，不支持本地交易处理
 * @param obj 接收SDK内TPReqObj的业务子类, 如交易/转账TPTransferObj, ...
 *  成功发起请求会返回YES, 其他情况返回NO;
 */
+ (BOOL)sendObj:(TPReqObj *)obj;

/*!
 * @brief 向TP发起请求，仅支持EOS BOS，支持TPAuthObj请求，如要支持本地交易处理，请调用此接口
 * @param obj 接收SDK内TPReqObj的业务子类, 如交易/转账TPTransferObj, ...
 *  成功发起请求会返回YES, 其他情况返回NO;
 * @param callback 本次请求拉起的结果，与操作的结果无关
 */
+ (void)sendObj:(TPReqObj *)obj resultHandle:(void (^)(TPReqType,NSError *))callback;

/*!
 * @brief 处理TP的回调跳转
 * @discuss 在AppDelegate -(application:openURL:options:)方法里调用
 */
+ (BOOL)handleURL:(NSURL *)url
          options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
           result:(void(^)(TPRespObj *respObj))result;

/*!
 * @brief 获取本地的账户
 *
 * @return 账户名称的集合
 */
+ (NSArray<NSString *> *)getAccounts;

/*!
* @brief 获取指定账户的资源代付开关状态
*
* @return 开关状态
*/
+ (BOOL)payResourceSwitchForAccount:(NSString *)account;

/*!
* @brief 设置指定账户的资源代付开关状态
*
*/
+ (void)setPayResourceSwitch:(BOOL)paySwitch forAccount:(NSString *)account;

/*!
 * @brief 删除sdk本地某个账号信息（不会删除链上数据）
 *
 * @param account 账号名
 * @return 结果
 */
+ (BOOL)clearAuth:(NSString *)account;

/*!
 * @brief 查询账号是否有指定权限，远程拉取到数据后会缓存在本地，下次数据直接在本地缓存取
 *
 * @param perm 权限名
 * @param account 账号名
 * @param completion 查询结果回调
 */
+ (void)isPermExist:(NSString *)perm account:(NSString *)account completion:(void (^)(BOOL exist,NSError *error))completion;


/*!
 * @brief 查询账号是否有指定权限并且绑定了指定权限列表，远程拉取到数据后会缓存在本地，下次数据直接在本地缓存取
 *
 * @param perm 权限名
 * @param actions 要查询是否绑定的操作列表，查询后会把actions里每个TPLinkAction对象的link属性赋值
 *                - permExisted account是否存在对应的perm
 *                - linked      查询的账号是否全部绑定了要查询的actions
 *                - error       查询接口出错时的错误信息
 * @param account 账号名
 * @param completion 查询结果回调
 */
+ (void)perm:(NSString *)perm isLinkActions:(NSArray<TPLinkAction *> *)actions account:(NSString *)account completion:(void (^)(BOOL permExisted,BOOL linked,NSError *error))completion;

@end



