//
//  TPEthDecryptViewController.m
//  SDKDemo
//
//  Created by shaw on 2023/4/6.
//  Copyright © 2023 TokenPocket. All rights reserved.
//

#import "TPEthDecryptViewController.h"
#import "DemoSharedData.h"

@import TPSDK;

@interface TPEthDecryptViewController ()

@property (weak, nonatomic) IBOutlet UITextField *typeField;
@property (weak, nonatomic) IBOutlet UITextField *walletField;
@property (weak, nonatomic) IBOutlet UITextField *encryptField;

@end

@implementation TPEthDecryptViewController

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
    TPEthDecryptObj *ethDecryptObj = [TPEthDecryptObj new];
    ethDecryptObj.dappName = @"SDKDemo";
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
    ethDecryptObj.blockchains = chains.copy;
    
    TPEthDecryptObjData *data = TPEthDecryptObjData.new;
    data.address = _walletField.text;
    data.message = _encryptField.text;
    
    ethDecryptObj.data = data;
    
    [TPApi sendObj:ethDecryptObj];
}

@end
