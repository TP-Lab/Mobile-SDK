//
//  PushTransactionViewController.m
//  TPSDKDemo
//
//  Created by xiao yuan on 28/6/2019.
//  Copyright © 2019 TokenPocket. All rights reserved.
//

#import "PushTransactionViewController.h"

@import TPSDK;

@interface PushTransactionViewController ()

@property (weak, nonatomic) IBOutlet UITextView *textView;
@end

@implementation PushTransactionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(closeKeybord)];
    [self.view addGestureRecognizer:tap];
    
    _textView.layer.borderColor = UIColor.blackColor.CGColor;
    _textView.layer.borderWidth = 1;
}

- (void)closeKeybord {
    [self.view endEditing:YES];
}

- (IBAction)action {
    
    NSString *actionString = _textView.text;
    NSArray *actions = [self toJson:actionString];
    
    NSString *perm = @"your perm";
    NSArray<TPLinkAction *> *linkActions = @[];///@"need check link action list"
    NSString *account = @"account name";
    
    ///check 'actions' authorization permission is linked 'linkActions' in 'account'
    [TPApi perm:perm isLinkActions:linkActions account:account completion:^(BOOL permExisted, BOOL linked, NSError *error) {
        if (!permExisted || !linked) {
            // modify 'actions' authorization permission to active or owner
            
        }
        
        TPPushTransactionObj *push = [TPPushTransactionObj new];
        push.dappName = @"TestSDK";
        push.blockchain = @"eos";
        push.actions = actions;
        [TPApi sendObj:push resultHandle:^(TPReqType type,NSError *error) {
            
        }];
    }];
    
    
}

- (id)toJson:(NSString *)string {
    if (string.length == 0) return nil;
    NSData *d = [string dataUsingEncoding:NSUTF8StringEncoding];
    return [NSJSONSerialization JSONObjectWithData:d options:0 error:nil];
}

@end
