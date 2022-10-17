//
//  TPTransferViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPTransferViewController.h"
#import "DemoSharedData.h"

@import TPSDK;

@interface TPTransferViewController ()

@property (weak, nonatomic) IBOutlet UITextField *typeField;
@property (weak, nonatomic) IBOutlet UITextField *fromField;
@property (weak, nonatomic) IBOutlet UITextField *toField;
@property (weak, nonatomic) IBOutlet UITextField *amountField;
@property (weak, nonatomic) IBOutlet UITextField *memoField;
@property (weak, nonatomic) IBOutlet UITextField *decimalField;
@property (weak, nonatomic) IBOutlet UITextField *contractField;
@property (weak, nonatomic) IBOutlet UITextField *gasField;
@property (weak, nonatomic) IBOutlet UITextField *gasPriceField;

@end

@implementation TPTransferViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeKeybord)];
    [self.view addGestureRecognizer:tap];
    if (DemoSharedData.shared.wallet.length) {
        _typeField.text = [DemoSharedData.shared demoNetworkValue];
        _fromField.text = DemoSharedData.shared.wallet;
    }
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    NSArray *amountComps = [_amountField.text componentsSeparatedByString:@" "];
    TPTransferObj *transfer = [TPTransferObj new];
    transfer.dappName = @"SDKDemo";
    transfer.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transfer.symbol = amountComps.lastObject;
    transfer.contract = _contractField.text;
    transfer.from = _fromField.text;
    transfer.to = _toField.text;
    transfer.memo =_memoField.text;
    transfer.precision = _decimalField.text;
    transfer.amount = amountComps.firstObject;
    
    NSArray<NSString *> *comps = [_typeField.text componentsSeparatedByString:@","];
    NSString *network = comps.firstObject, *chainId;
    if (comps.count > 1) chainId = comps[1];
    transfer.blockchains = @[[TPChainObj objWithNetwork:network chainId:chainId]];
    
    transfer.gas = _gasField.text;
    transfer.gasPrice = _gasPriceField.text;
    [TPApi sendObj:transfer];
}

@end
