//
//  TPLinkAction.h
//  TPSDK
//
//  Created by xiao yuan on 19/6/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface TPLinkAction : NSObject
/// 合约名
@property (nonatomic, copy, readonly) NSString *contract;
/// 操作名
@property (nonatomic, copy, readonly) NSString *action;
/// 是否已经绑定（无需外部处理，SDK内部处理）
@property (nonatomic, assign) BOOL link;

+ (TPLinkAction *)linkActionWithContract:(NSString *)contract action:(NSString *)action;

@end

NS_ASSUME_NONNULL_END
