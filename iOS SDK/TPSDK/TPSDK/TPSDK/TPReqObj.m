//
//  TPReqObj.m
//  TPSDK
//
//  Created by NehcNoraa on 2018/9/5.
//  Copyright © 2018年 TokenPocket. All rights reserved.
//

#import "TPReqObj.h"

#pragma mark ~~~~ TPReqObj ~~~~

@implementation TPReqObj

- (instancetype)init {
    if (self = [super init]) {
        _protocol = @"TPProtocol";
        _version = [[[NSBundle bundleForClass:[self class]] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    }
    return self;
}


@end


#pragma mark ~~~~~~~~~~~~~~~~~~~~~~~
#pragma mark ~~~~ TPTransferObj ~~~~

@implementation TPTransferObj

- (instancetype)init {
    if (self = [super init]) {
        self.action = @"transfer";
    }
    return self;
}

@end

@implementation TPLoginObj

- (instancetype)init {
    if (self = [super init]) {
        self.action = @"login";
    }
    return self;
}

@end

@implementation TPPushTransactionObj

- (instancetype)init {
    if (self = [super init]) {
        self.action = @"pushtransaction";
    }
    return self;
}

@end
