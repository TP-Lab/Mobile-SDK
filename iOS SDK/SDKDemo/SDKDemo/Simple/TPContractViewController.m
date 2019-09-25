//
//  TPContractViewController.m
//  SDKDemo
//
//  Created by xiao yuan on 17/9/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "TPContractViewController.h"

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
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)confirmAction {
    TPPushTransactionObj *transaction = [TPPushTransactionObj new];
    transaction.dappName = @"SDKDemo";
    transaction.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transaction.blockchain = _typeField.text;
    
    
    id json = [NSJSONSerialization JSONObjectWithData:[_infoText.text dataUsingEncoding:NSUTF8StringEncoding] options:0 error:NULL];
    if ([transaction.blockchain.uppercaseString isEqualToString:@"TRON"] || [transaction.blockchain.uppercaseString isEqualToString:@"ETH"]) {
        transaction.txData = json;
    } else if ([transaction.blockchain.uppercaseString isEqualToString:@"IOST"]) {
        transaction.payload = json;
    } else {
        transaction.actions = json;
    }
    [TPApi sendObj:transaction];
}

@end
