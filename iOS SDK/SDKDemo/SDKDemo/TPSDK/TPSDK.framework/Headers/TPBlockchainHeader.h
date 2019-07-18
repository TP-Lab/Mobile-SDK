//
//  TPBlockchainHeader.h
//  TPSDK
//
//  Created by xiao yuan on 19/6/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#ifndef TPBlockchainHeader_h
#define TPBlockchainHeader_h

@import UIKit;
#endif /* TPBlockchainHeader_h */


typedef NS_ENUM(NSUInteger, TPBlockChainType) {
    TPBlockChainTypeUnknow           = 0,
    TPBlockChainTypeEOSMainNet,
    TPBlockChainTypeEOSKylin,
    TPBlockChainTypeEOSJungle,
    TPBlockChainTypeBOSMainNet,
    TPBlockChainTypeBOSTestNet,
};

typedef NS_ENUM(NSUInteger, TPReqType) {
    TPReqTypeLocal = 0,             //本地处理
    TPReqTypeAPP,               //唤起APP
};


static inline BOOL TBOpenURLWithString(NSString *URLString) {
    URLString = [URLString stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]];
    NSURL *url = [NSURL URLWithString:URLString];
    if ([[UIApplication sharedApplication] canOpenURL:url]) {
        return [[UIApplication sharedApplication] openURL:url];
    } else {
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://download.tokenpocket.pro/index.html#/?platform=ioshttps://www.tokenpocket.pro"]];
    }
    return NO;
}
