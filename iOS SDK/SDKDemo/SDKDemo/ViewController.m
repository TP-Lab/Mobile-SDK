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
    // Do any additional setup after loading the view, typically from a nib.
    
    
    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame = CGRectMake(0, 0, 100, 100);
    btn.backgroundColor = [UIColor redColor];
    btn.center = self.view.center;
    [btn addTarget:self action:@selector(onBtnTapped:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:btn];
    
}

- (void)onBtnTapped:(UIButton *)sender {
    
    TPTransferObj *transfer = [TPTransferObj new];
    transfer.version = @"1.0";
    transfer.dappName = @"SDKDemo";
    transfer.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transfer.symbol = @"EOS";
    transfer.contract = @"eosio.token";
    transfer.action = @"transfer";
    transfer.to = @"clementsign1";
    transfer.memo = @"t=xxxex&a=put_order&oid=00000";
    transfer.precision = @(4);
    transfer.amount = @(0.001);
    transfer.expired = @(15359897700);
    [TPApi sendObj:transfer];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
