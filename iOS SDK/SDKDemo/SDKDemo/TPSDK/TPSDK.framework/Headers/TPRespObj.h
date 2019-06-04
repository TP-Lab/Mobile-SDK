//
//  TPRespObj.h
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import <Foundation/Foundation.h>

/**  回调结果状态 */
typedef NS_ENUM(NSUInteger, TPRespResult) {
    TPRespResultCanceled = 0,       // 取消
    TPRespResultSuccess,            // 成功
    TPRespResultFailure,            // 失败
};



/*!
 * @brief 跳转回调
 */
@interface TPRespObj : NSObject

@property (nonatomic, assign) TPRespResult result;      // 处理结果
@property (nonatomic, copy) NSString *message;          // 消息
@property (nonatomic, weak) id data;                    // 结果数据

@end
