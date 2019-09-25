//
//  TPTransferViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPTransferViewController.h"

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
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    TPTransferObj *transfer = [TPTransferObj new];
    transfer.dappName = @"SDKDemo";
    transfer.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transfer.symbol = [_amountField.text componentsSeparatedByString:@" "].lastObject;
    transfer.contract = _contractField.text;
    transfer.from = _fromField.text;
    transfer.to = _toField.text;
    transfer.memo =_memoField.text;
    transfer.precision = @(_decimalField.text.longLongValue);
    transfer.amount = @([_amountField.text componentsSeparatedByString:@" "].firstObject.doubleValue);
    transfer.blockchain = _typeField.text;
    transfer.gas = _gasField.text;
    transfer.gasPrice = _gasPriceField.text;
    [TPApi sendObj:transfer];
}

@end
