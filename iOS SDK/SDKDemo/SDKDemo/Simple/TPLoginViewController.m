//
//  TPLoginViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPLoginViewController.h"

@import TPSDK;

@interface TPLoginViewController ()

@property (weak, nonatomic) IBOutlet UITextField *typeField;
@property (weak, nonatomic) IBOutlet UITextField *walletField;

@end

@implementation TPLoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeKeybord)];
    [self.view addGestureRecognizer:tap];
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    TPLoginObj *login = [TPLoginObj new];
    login.dappName = @"SDKDemo";
    login.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    
    NSArray *comps = [_typeField.text componentsSeparatedByString:@";"];
    NSMutableArray *chains = NSMutableArray.new;
    for (NSString *part in comps) {
        NSArray<NSString *> *comps = [part componentsSeparatedByString:@","];
        NSString *network = comps.firstObject, *cid;
        if (!network.length) continue;
        if (comps.count > 1) cid = comps[1];
        [chains addObject:[TPChainObj objWithNetwork:network chainId:cid]];
    }
    login.blockchains = chains.copy;
    
    login.wallet = _walletField.text;
    
    [TPApi sendObj:login];
}

@end
