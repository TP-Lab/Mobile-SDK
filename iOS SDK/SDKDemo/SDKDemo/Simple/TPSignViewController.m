//
//  TPSignViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPSignViewController.h"

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
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    TPSignObj *sign = [TPSignObj new];
    sign.dappName = @"SDKDemo";
    sign.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    sign.message = @"sign data...";
    
    NSArray *comps = [_typeField.text componentsSeparatedByString:@";"];
    NSMutableArray *chains = NSMutableArray.new;
    for (NSString *part in comps) {
        NSArray<NSString *> *comps = [part componentsSeparatedByString:@","];
        NSString *network = comps.firstObject, *cid;
        if (!network.length) continue;
        if (comps.count > 1) cid = comps[1];
        [chains addObject:[TPChainObj objWithNetwork:network chainId:cid]];
    }
    sign.blockchains = chains.copy;
    
    sign.wallet = _walletField.text;
    sign.message = _signContentField.text;
    sign.signType = _signTypeField.text;
    if (_utField.text) {
        sign.useTronHeader = [_utField.text boolValue];
    }
    [TPApi sendObj:sign];
}

@end
