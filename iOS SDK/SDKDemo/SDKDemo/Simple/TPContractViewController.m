//
//  TPContractViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPContractViewController.h"
#import "DemoSharedData.h"

@import TPSDK;

@interface TPContractViewController ()

@property (weak, nonatomic) IBOutlet UITextField *typeField;
@property (weak, nonatomic) IBOutlet UITextView *infoText;

@end

@implementation TPContractViewController

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
    TPPushTransactionObj *transaction = [TPPushTransactionObj new];
    transaction.dappName = @"SDKDemo";
    transaction.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    
    NSArray<NSString *> *comps = [_typeField.text componentsSeparatedByString:@","];
    NSString *network = comps.firstObject, *chainId;
    if (comps.count > 1) chainId = comps[1];
    transaction.blockchains = @[[TPChainObj objWithNetwork:network chainId:chainId]];
    
    id json = [NSJSONSerialization JSONObjectWithData:[_infoText.text dataUsingEncoding:NSUTF8StringEncoding] options:0 error:NULL];
    if ([network.lowercaseString isEqualToString:@"tron"] ||
        [network.lowercaseString isEqualToString:@"eth"] ||
        [network.lowercaseString isEqualToString:@"ethereum"]) {
        transaction.txData = json;
    } else if ([network.lowercaseString isEqualToString:@"iost"]) {
        transaction.payload = json;
    } else {
        transaction.actions = json;
    }
    transaction.onlySign = YES;
    [TPApi sendObj:transaction];
}

@end
