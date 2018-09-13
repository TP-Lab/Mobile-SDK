//
//  TPApi.m
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TPApi.h"
#import "TPReqObj+Private.h"

static const NSString *kReqParam  = @"param";
static const NSString *kRespParam = @"param";

static NSString *appid_for_tp = nil;
static NSString *callback_schema = nil;

@implementation TPApi

+ (void)registerAppID:(NSString *)appID {
    appid_for_tp = appID;
}

/**  发起请求 */
+ (BOOL)sendObj:(TPReqObj *)obj {
    if (obj.callbackSchema.length) callback_schema = obj.callbackSchema;
    else obj.callbackSchema = [NSString stringWithFormat:@"%@://tpsdk/?param", appid_for_tp];
    if ([obj isKindOfClass:TPReqObj.class]) {
        return [self send:(TPTransferObj *)obj];
    }
    return NO;
}

/**  发起 */
+ (BOOL)send:(TPReqObj *)obj {
    NSDictionary *params = [obj tp_toJSONObject];
    NSString *JSONString = [self toJSONString:params];
    NSString *URLString = [NSString stringWithFormat:@"tpoutside://pull.activity?%@=%@", kReqParam, JSONString];
    return [self openURLWithString:URLString];
}

/**  处理TP的回调跳转 */
+ (BOOL)handleURL:(NSURL *)url
          options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options
           result:(void (^)(TPRespObj *))result {
    if ([url.scheme isEqualToString:appid_for_tp] ||
        [url.absoluteString hasPrefix:callback_schema])
    {
        TPRespObj *respObj = [self respObjWithURL:url];
        if (result) result(respObj);
        callback_schema = nil;
        return YES;
    }
    return NO;
}

#pragma mark ~~~~ Private ~~~~

/**  跳转 TP App */
+ (BOOL)openURLWithString:(NSString *)URLString {
    URLString = [URLString stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]];
    NSURL *url = [NSURL URLWithString:URLString];
    if ([[UIApplication sharedApplication] canOpenURL:url]) {
        return [[UIApplication sharedApplication] openURL:url];
    }
    return NO;
}

/**  解析回调url */
+ (TPRespObj *)respObjWithURL:(NSURL *)url {
    NSString *query = [url.query stringByRemovingPercentEncoding] ?: @"";
    NSRange begin = [query rangeOfString:[NSString stringWithFormat:@"%@={", kRespParam]];
    if (begin.location == NSNotFound) return nil;
    NSString *JSONString = [query substringFromIndex:begin.location + begin.length - 1];
    NSData *JSONData = [JSONString dataUsingEncoding:NSUTF8StringEncoding];
    NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:JSONData options:0 error:nil];
    if ([dic isKindOfClass:NSDictionary.class]) {
        TPRespObj *respObj = TPRespObj.new;
        if ([dic objectForKey:@"result"]) respObj.result = [dic[@"result"] integerValue];
        if ([dic objectForKey:@"message"]) respObj.message = dic[@"message"];
        if ([dic objectForKey:@"data"]) respObj.data = dic[@"data"];
        return respObj;
    }
    return nil;
}

/**  转换成json string. */
+ (NSString *)toJSONString:(id)object {
    NSData *data = [NSJSONSerialization dataWithJSONObject:object options:0 error:NULL];
    NSString *JSONString = nil;
    if (data) JSONString = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
    return JSONString;
}


@end


