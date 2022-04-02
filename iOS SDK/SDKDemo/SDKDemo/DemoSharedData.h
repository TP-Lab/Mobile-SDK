//
//  DemoSharedData.h
//  SDKDemo
//
//  Created by ice on 2022/4/2.
//  Copyright © 2022 TokenPocket. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/// Mock data model
@interface DemoSharedData : NSObject

@property (nonatomic, copy) NSString *wallet;
@property (nonatomic, copy) NSString *publickey;
@property (nonatomic, copy) NSString *network;
@property (nonatomic, copy) NSString *chainId;

- (NSString *)demoNetworkValue;

+ (instancetype)shared;

@end

NS_ASSUME_NONNULL_END
