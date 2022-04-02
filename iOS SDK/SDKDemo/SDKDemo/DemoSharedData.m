//
//  DemoSharedData.m
//  SDKDemo
//
//  Created by ice on 2022/4/2.
//  Copyright © 2022 TokenPocket. All rights reserved.
//

#import "DemoSharedData.h"

@implementation DemoSharedData

+ (instancetype)shared {
    static DemoSharedData *data;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        data = DemoSharedData.new;
    });
    return data;
}

- (NSString *)demoNetworkValue {
    if (!_network.length) return @"";
    if (!_chainId.length) return _network;
    return [_network stringByAppendingFormat:@",%@", _chainId];
}

@end
