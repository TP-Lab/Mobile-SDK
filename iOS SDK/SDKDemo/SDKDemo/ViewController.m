//
//  ViewController.m
//  SDKDemo
//
//  Created by MarcusWoo on 2018/9/6.
//  Copyright © 2018 TokenPocket. All rights reserved.
//

#import "ViewController.h"
#import <TPSDK/TPSDK.h>

@interface TPDMRowData : NSObject

@property (nonatomic, copy) NSString *title;
@property (nonatomic, assign) SEL action;

+ (instancetype)dataWithTitle:(NSString *)title action:(SEL)action;

@end

@implementation TPDMRowData

+ (instancetype)dataWithTitle:(NSString *)title action:(SEL)action {
    return ({
        TPDMRowData *data = TPDMRowData.new;
        data.title = title;
        data.action = action;
        data;
    });
}

@end


#pragma mark - ViewController -

@interface ViewController () <UITableViewDelegate, UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic, strong) NSArray<TPDMRowData *> *dataArray;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self loadTableView];
}

- (void)loadTableView {
    self.tableView.tableFooterView = UIView.new;
    NSMutableArray *array = NSMutableArray.new;
    [array addObject:[TPDMRowData dataWithTitle:@"TP Login" action:@selector(onTPLogin)]];
    [array addObject:[TPDMRowData dataWithTitle:@"TP Sign" action:@selector(onTPSign)]];
    [array addObject:[TPDMRowData dataWithTitle:@"TP Transfer" action:@selector(onTPTransfer)]];
    [array addObject:[TPDMRowData dataWithTitle:@"TP PushTransaction" action:@selector(onTPPushTransaction)]];
    self.dataArray = array.copy;
    [self.tableView reloadData];
}

#pragma mark ~~~~ Actions ~~~~

/**  Login */
- (void)onTPLogin {
    TPLoginObj *login = [TPLoginObj new];
    login.dappName = @"SDKDemo";
    login.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    login.blockchain = @"eos";
    [TPApi sendObj:login];
}

/**  Sign */
- (void)onTPSign {
    TPSignObj *sign = [TPSignObj new];
    sign.dappName = @"SDKDemo";
    sign.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    sign.message = @"sign data...";
    sign.blockchain = @"eos";
    [TPApi sendObj:sign];
}

/**  Transfer */
- (void)onTPTransfer {
    TPTransferObj *transfer = [TPTransferObj new];
    transfer.dappName = @"SDKDemo";
    transfer.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transfer.symbol = @"EOS";
    transfer.contract = @"eosio.token";
    transfer.to = @"xxxxx";
    transfer.memo = @"Memo string...";
    transfer.precision = @(4);
    transfer.amount = @(0.0001);
    transfer.blockchain = @"eos";
    [TPApi sendObj:transfer];
}

/**  Push transaction */
- (void)onTPPushTransaction {
    TPPushTransactionObj *transaction = [TPPushTransactionObj new];
    transaction.dappName = @"SDKDemo";
    transaction.dappIcon = @"https://gz.bcebos.com/v1/tokenpocket/temp/mobile_sdk_demo.png";
    transaction.blockchain = @"eos";
    transaction.actions = @[@{@"account": @"eosio.token",
                              @"name": @"transfer",
                              @"authorization": @[@{@"actor": @"xxxxx",
                                                    @"permission": @"active"}],
                              @"data": @{@"from": @"xxxxx",
                                         @"to": @"xxxxx",
                                         @"quantity": @"0.0001 EOS",
                                         @"memo": @"Memo string..."},
                              }];
    [TPApi sendObj:transaction];
}

#pragma mark ~~~~ UITableViewDelegate, UITableViewDataSource ~~~~

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.dataArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *cellId = @"UITableViewCell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellId];
        cell.textLabel.font = [UIFont systemFontOfSize:16 weight:UIFontWeightMedium];
    }
    cell.textLabel.text = self.dataArray[indexPath.row].title;
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    [self performSelector:self.dataArray[indexPath.row].action withObject:nil afterDelay:0];
}

@end
