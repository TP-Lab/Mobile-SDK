//
//  TPChainObj.h
//  TPSDK
//
//  Created by ic.A on 2021/4/6.
//  Copyright © 2021 TokenPocket. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/// 1) 普通情况: @p network: "eth", "eos", "tron", "iost", ...; @p chainId 可不传.
/// 2) Fork链: 需要同时传 @p network 和 @p chainId.
///    例：<BSC> { "network": "ethereum", "chainId": "56" }
@interface TPChainObj : NSObject

@property (nonatomic, copy) NSString *network;
@property (nonatomic, copy) NSString *chainId;

+ (instancetype)objWithNetwork:(NSString *)network chainId:(nullable NSString *)chainId;

@end

NS_ASSUME_NONNULL_END
