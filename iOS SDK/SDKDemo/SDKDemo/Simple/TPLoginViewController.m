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
    login.blockchain = _typeField.text;
    login.wallet = _walletField.text;
    
    [TPApi sendObj:login];
}

@end
