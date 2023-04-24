//
//  TPEthGetEncryptPublicKeyViewController.m
//  SDKDemo
//
//  Created by shaw on 2023/4/6.
//  Copyright © 2023 TokenPocket. All rights reserved.
//

#import "TPEthGetEncryptPublicKeyViewController.h"
#import "DemoSharedData.h"

@import TPSDK;

@interface TPEthGetEncryptPublicKeyViewController ()

@property (weak, nonatomic) IBOutlet UITextField *typeField;
@property (weak, nonatomic) IBOutlet UITextField *walletField;

@end

@implementation TPEthGetEncryptPublicKeyViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeKeybord)];
    [self.view addGestureRecognizer:tap];
    if (DemoSharedData.shared.wallet.length) {
        _typeField.text = [DemoSharedData.shared demoNetworkValue];
    }
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    TPEthGetEncryptionPublicKeyObj *ethGetEncryptionPublicKeyObj = [TPEthGetEncryptionPublicKeyObj new];
    ethGetEncryptionPublicKeyObj.dappName = @"SDKDemo";
//    login.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    
    NSArray *comps = [_typeField.text componentsSeparatedByString:@";"];
    NSMutableArray *chains = NSMutableArray.new;
    for (NSString *part in comps) {
        NSArray<NSString *> *comps = [part componentsSeparatedByString:@","];
        NSString *network = comps.firstObject, *cid;
        if (!network.length) continue;
        if (comps.count > 1) cid = comps[1];
        [chains addObject:[TPChainObj objWithNetwork:network chainId:cid]];
    }
    ethGetEncryptionPublicKeyObj.blockchains = chains.copy;
    
    TPEthGetEncryptionPublicKeyObjData *data = TPEthGetEncryptionPublicKeyObjData.new;
    data.address = _walletField.text;
    
    ethGetEncryptionPublicKeyObj.data = data;
    
    [TPApi sendObj:ethGetEncryptionPublicKeyObj];
}

@end
