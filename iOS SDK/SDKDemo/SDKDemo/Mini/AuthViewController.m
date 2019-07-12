//
//  AuthViewController.m
//  TPSDKDemo
//
//  Created by xiao yuan on 28/6/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "AuthViewController.h"

@import TPSDK;

@interface AuthViewController ()

@property (weak, nonatomic) IBOutlet UITextField *account;
@property (weak, nonatomic) IBOutlet UITextField *perm;
@property (weak, nonatomic) IBOutlet UITextView *contracts;
@property (weak, nonatomic) IBOutlet UITextView *actions;

@end

@implementation AuthViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeKeybord)];
    [self.view addGestureRecognizer:tap];
    
    _contracts.layer.borderColor = UIColor.blackColor.CGColor;
    _contracts.layer.borderWidth = 1;
    _actions.layer.borderColor = UIColor.blackColor.CGColor;
    _actions.layer.borderWidth = 1;
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)auth {
    TPAuthObj *auth = [TPAuthObj new];
    auth.account = _account.text;
    auth.perm = _perm.text;
    auth.selectAll = YES;

    NSArray *contracts = [_contracts.text componentsSeparatedByString:@","];
    NSArray *actions = [_actions.text componentsSeparatedByString:@","];

    NSMutableArray *linkActions = [NSMutableArray array];
    for (int i = 0; i < MIN(contracts.count, actions.count); i++) {
        [linkActions addObject:[TPLinkAction linkActionWithContract:contracts[i] action:actions[i]]];
    }

    auth.actions = linkActions;
    [TPApi sendObj:auth resultHandle:^(TPReqType type,NSError *error){

    }];
}

@end
