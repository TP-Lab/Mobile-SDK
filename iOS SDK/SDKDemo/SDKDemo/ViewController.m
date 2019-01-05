//
//  ViewController.m
//  SDKDemo
//
//  Created by MarcusWoo on 2018/9/6.
//  Copyright © 2018 TokenPocket. All rights reserved.
//

#import "ViewController.h"
#import <TPSDK/TPSDK.h>

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
}

#pragma mark ~~~~ Clicks ~~~~

/**  Login */
- (IBAction)tapOnLoginButton:(UIButton *)sender {
    TPLoginObj *login = [TPLoginObj new];
    login.dappName = @"SDKDemo";
    login.blockchain = @"eos";
    login.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    login.expired = @(15359897700);
    [TPApi sendObj:login];
}

/**  Transfer */
- (IBAction)tapOnTransferButton:(UIButton *)sender {
    TPTransferObj *transfer = [TPTransferObj new];
    transfer.dappName = @"SDKDemo";
    transfer.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transfer.symbol = @"EOS";
    transfer.contract = @"eosio.token";
    transfer.to = @"clementsign1";
    transfer.memo = @"t=xxxex&a=put_order&oid=00000";
    transfer.precision = @(4);
    transfer.amount = @(0.0001);
    transfer.expired = @(15359897700);
    [TPApi sendObj:transfer];
}

/**  Push Transaction */
- (IBAction)tapOnPushTransactionButton:(UIButton *)sender {
    TPPushTransactionObj *transaction = [TPPushTransactionObj new];
    transaction.dappName = @"SDKDemo";
    transaction.blockchain = @"eos";
    transaction.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transaction.expired = @(15359897700);
    transaction.actions = @[@{@"account": @"eosio.token",
                              @"name": @"transfer",
                              @"authorization": @[@{@"actor": @"xiaoyuantest",
                                                    @"permission": @"active"}],
                              @"data": @{@"from": @"xiaoyuantest",
                                         @"to": @"clementsign1",
                                         @"quantity": @"0.0001 EOS",
                                         @"memo": @"memomemomemomemo"}
                              }];
    [TPApi sendObj:transaction];
}



@end
