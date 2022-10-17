//
//  TPSignViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPSignViewController.h"
#import "DemoSharedData.h"

@import TPSDK;

@interface TPSignViewController ()

@property (weak, nonatomic) IBOutlet UITextField *typeField;
@property (weak, nonatomic) IBOutlet UITextField *walletField;
@property (weak, nonatomic) IBOutlet UITextField *utField;
@property (weak, nonatomic) IBOutlet UITextField *signTypeField;
@property (weak, nonatomic) IBOutlet UITextField *signContentField;

@end

@implementation TPSignViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeKeybord)];
    [self.view addGestureRecognizer:tap];
    
    if (DemoSharedData.shared.wallet.length) {
        _typeField.text = [DemoSharedData.shared demoNetworkValue];
        _walletField.text = DemoSharedData.shared.wallet;
    }
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    TPSignObj *sign = [TPSignObj new];
    sign.dappName = @"SDKDemo";
    sign.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    sign.message = @"sign data...";
    
    NSArray<NSString *> *comps = [_typeField.text componentsSeparatedByString:@","];
    NSString *network = comps.firstObject, *chainId;
    if (comps.count > 1) chainId = comps[1];
    sign.blockchains = @[[TPChainObj objWithNetwork:network chainId:chainId]];
    
    sign.wallet = _walletField.text;
    sign.message = _signContentField.text;
    sign.signType = _signTypeField.text;
    if (_utField.text) {
        sign.useTronHeader = [_utField.text boolValue];
    }
    [TPApi sendObj:sign];
}

@end
