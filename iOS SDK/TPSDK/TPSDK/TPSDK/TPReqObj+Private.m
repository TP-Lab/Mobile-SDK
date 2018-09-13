//
//  TPReqObj+Private.m
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import "TPReqObj+Private.h"
#import <objc/runtime.h>


#pragma mark ~~~~ TPReqObj (Private) ~~~~

@implementation TPReqObj (Private)


- (NSDictionary *)tp_toJSONObject {
    NSMutableDictionary *params = [NSMutableDictionary new];
    params[@"protocol"] = self.protocol;
    params[@"version"] = self.version;
    params[@"dappName"] = self.dappName;
    params[@"dappIcon"] = self.dappIcon;
    params[@"action"] = self.action;
    params[@"actionId"] = self.actionId;
    params[@"memo"] = self.memo;
    params[@"dappData"] = self.dappData;
    params[@"expired"] = self.expired;
    params[@"callbackUrl"] = self.callbackUrl;
    params[@"callbackSchema"] = self.callbackSchema;
    params[@"compressedData"] = self.compressedData;
    params[@"compress"] = @(self.compress);
    return params.copy;
}

@end


#pragma mark ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#pragma mark ~~~~ TPTransferObj (Private) ~~~~

@implementation TPTransferObj (Private)

- (NSDictionary *)tp_toJSONObject {
    NSMutableDictionary *params = [super tp_toJSONObject].mutableCopy;
    params[@"from"] = self.from;
    params[@"to"] = self.to;
    params[@"amount"] = self.amount;
    params[@"contract"] = self.contract;
    params[@"symbol"] = self.symbol;
    params[@"precision"] = self.precision;
    params[@"desc"] = self.desc;
    return params.copy;
}

@end

@implementation TPLoginObj (Private)

- (NSDictionary *)tp_toJSONObject {
    NSMutableDictionary *params = [super tp_toJSONObject].mutableCopy;
    params[@"wallet"] = self.wallet;
    return params.copy;
}


@end

@implementation TPPushTransactionObj (Private)

- (NSDictionary *)tp_toJSONObject {
    NSMutableDictionary *params = [super tp_toJSONObject].mutableCopy;
    params[@"actions"] = self.actions;
    return params.copy;
}

@end
