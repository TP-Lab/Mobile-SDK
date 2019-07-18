//
//  TPBlockchainHeader.h
//  TPSDK
//
//  Created by xiao yuan on 19/6/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#ifndef TPBlockchainHeader_h
#define TPBlockchainHeader_h


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
