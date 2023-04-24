# TokenPocket Android SDK

该SDK用于拉起TP钱包，实现APP间互相调起，使用TP进行相关action操作。

DApp uses this SDK  to pull up the TokenPocket wallet and do some actions such as token transfer, login auth, pushTransaction etc.

- 该SDK仅支持0.4.9以上版本的TP钱包。
- 0.7.8以及以上的TP钱包版本支持MiniWallet。
- 0.8.3以及以上的TP钱包版本支持Tron Eth SDK
- Only version 0.4.9 or higher support this SDK.
- Only version 0.7.8 or higher support MiniWallet apis.
- Only version 0.8.3 or higher support Tron and Eth SDK.
- 1.2.4以上的TP钱包版本支持ETH fork链,如HECO，BSC，OKExChain等.

## <a name='Catalog'></a>目录 (Catalog)

<!-- vscode-markdown-toc -->
* [目录 (Catalog)](#Catalog)
* [TP钱包协议文档 (TokenPocket Wallet Protocol)](#TPTokenPocketWalletProtocol)
* [Demo](#Demo)
* [开始接入 (Getting Started)](#GettingStarted)
* [通用操作 (Common apis)](#Commonapis)
	* [APIs](#APIs)
		* [1.授权登陆  (Authorize)](#Authorize)
		* [2.转账 (Token transfer)](#Tokentransfer)
		* [3.PushTransaction](#PushTransaction)
		* [4.签名 (Sign)](#Sign)
* [MiniWallet](#MiniWallet)
	* [简介 (Introduction)](#Introduction)
	* [初始化（init）](#init)
	* [auth](#auth)
	* [pushTransaction](#pushTransaction)
	* [MiniWallet操作 (MiniWallet apis)](#MiniWalletMiniWalletapis)
		* [1.初始化SDK (Init sdk)](#SDKInitsdk)
		* [2.设置blockchain 信息 (Set blockchain info)](#blockchainSetblockchaininfo)
		* [3.设置插件信息 (Set plugin info)](#Setplugininfo)
		* [4.设置seed (Set seed to protect data)](#seedSetseedtoprotectdata)
		* [5.修改seed (Modify seed)](#seedModifyseed)
		* [6.获取已授权账号信息 (Get authed accounts](#Getauthedaccounts)
		* [7.检查权限是否存在 (Check permission bind to account)](#Checkpermissionbindtoaccount)
		* [8.检查权限是否link到action (Check action bind to permission)](#linkactionCheckactionbindtopermission)
		* [9.清除本地授权 (Clear authed account)](#Clearauthedaccount)

<!-- vscode-markdown-toc-config
	numbering=false
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->



## <a name='TPTokenPocketWalletProtocol'></a>TP钱包协议文档 (TokenPocket Wallet Protocol)

**https://github.com/TP-Lab/tp-wallet-sdk**



## <a name='Demo'></a>Demo

https://github.com/TP-Lab/Mobile-SDK/tree/master/Android%20SDK/SDK_DEMO

## <a name='GettingStarted'></a>开始接入 (Getting Started)

- App下build.gradle添加一下内容(add the following lines to your app/build.gradle)

``` java
dependencies {
    implementation files('libs/wallet-sdk-release.aar')
}
```

- 反混淆(Proguard)

``` java
# tokenpocket sdk
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.**{*;}
-keep interface com.tokenpocket.opensdk.**{*;}
```

## <a name='Commonapis'></a>通用操作 (Common apis)

### <a name='APIs'></a>APIs

#### <a name='Authorize'></a>1.授权登陆  (Authorize)

``` java
	Authorize authorize = new Authorize();
        //已废弃
        //authorize.setBlockchain(CHAIN);
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        blockchains.add(new Blockchain("ethereum", "1"));
        authorize.setBlockchains(blockchains);

        authorize.setDappName("Test demo");
        authorize.setDappIcon("https://eosknights.io/img/icon.png");
        authorize.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        authorize.setMemo("demo");
        TPManager.getInstance().authorize(this, authorize, new TPListener() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
```

#### <a name='Tokentransfer'></a>2.转账 (Token transfer)

``` java
    Transfer transfer = new Transfer();
    //已废弃
    //transfer.setBlockchain("EOS");
    //标识链
    List<Blockchain> blockchains = new ArrayList<>();
    blockchains.add(new Blockchain("eos"));
    transfer.setBlockchains(blockchains);
    
    transfer.setDappName("Newdex");
    transfer.setDappIcon("https://newdex.io/static/logoicon.png");
    transfer.setFrom("clement11111");
    transfer.setTo("newdexpocket");
    transfer.setAmount(0.0001);
    transfer.setContract("eosio.token");
    transfer.setSymbol("EOS");
    transfer.setPrecision(4);
    transfer.setMemo("test");
    transfer.setExpired(1535944144L);
    transfer.setCallbackUrl("https://newdex.io/api/account/transferCallback?uuid=1-46e023fc-015b-4b76-3809-1cab3fd76d2c");

    TPManager.getInstance().transfer(MainActivity.this, transfer,new TPListener() {
        @Override
        public void onSuccess(String data) {
        }

        @Override
        public void onError(String data) {
        }

        @Override
        public void onCancel(String data) {
        }
    });
```

#### <a name='PushTransaction'></a>3.PushTransaction

如果是IOST底层，将transaction.setActions替换成setPayload,详情请见demo (If is iost, replace setActions with setPalyload, for more details, please check the demo project)==

``` java
	Transaction transaction = new Transaction();
	//已废弃
	//transaction.setBlockchain(CHAIN);
	//标识链
	List<Blockchain> blockchains = new ArrayList<>();
	blockchains.add(new Blockchain("ethereum", "1"));
	transaction.setBlockchains(blockchains);

	transaction.setDappName("Test demo");
	transaction.setDappIcon("https://eosknights.io/img/icon.png");
	transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
	transaction.setLinkActions(new ArrayList<LinkAction>());
	transaction.setTxData("{\"from\":\"0x22F4900A1fB41f751b8F616832643224606B75B4\",\"gasPrice\":\"0x6c088e200\",\"gas\":\"0xea60\",\"chainId\":\"1\",\"to\":\"0x7d1e7fb353be75669c53c18ded2abcb8c4793d80\",\"data\":\"0xa9059cbb000000000000000000000000171a0b081493722a5fb8ebe6f0c4adf5fde49bd8000000000000000000000000000000000000000000000000000000000012c4b0\"}");
	TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
	    @Override
	    public void onSuccess(String s) {
		Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

	    }

	    @Override
	    public void onError(String s) {
		Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

	    }

	    @Override
	    public void onCancel(String s) {
		Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

	    }
	});
```

#### <a name='Sign'></a>4.签名 (Sign)

``` java
	Signature signature = new Signature();
	//已废弃
	//signature.setBlockchain(CHAIN);
	//标识链
	List<Blockchain> blockchains = new ArrayList<>();
	blockchains.add(new Blockchain("ethereum", "1"));
	signature.setBlockchains(blockchains);

	signature.setDappName("Test demo");
	signature.setDappIcon("https://eosknights.io/img/icon.png");
	signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
	signature.setMemo("demo");
	signature.setSignType("ethSign");
	signature.setMessage(etSign.getText().toString());
	TPManager.getInstance().signature(this, signature, new TPListener() {
	    @Override
	    public void onSuccess(String s) {
		Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();
	    }

	    @Override
	    public void onError(String s) {
		Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

	    }

	    @Override
	    public void onCancel(String s) {
		Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

	    }
	});
```
其中**signType**的枚举类型有以下几种：

**ethPersonalSign**，**ethSignTypedData**，**ethSignTypedData_v4**，**ethSignTypedDataLegacy**，**ethSign**(不再建议使用)


### 如何支持火币生态链，币安智能链等ETH Fork链
在构建new Blockchain("ethereum", "xx_chainId")对象时，传入对应的chainId
```
//ETH主网
new Blockchain("ethereum", "1")

//币安智能链
new Blockchain("ethereum", "56")

//火币生态链
new Blockchain("ethereum", "128")

//OKExChain
new Blockchain("ethereum", "66")
```

## <a name='MiniWallet'></a>MiniWallet

### <a name='Introduction'></a>简介 (Introduction)

MiniWallet，可以实现对于特定操作，第三方App不需要拉起钱包，直接在应用内部完成，体验更为流畅
MiniWallet support  execute specific actions without leaving the app, which provides a better user experience
 
### <a name='init'></a>初始化（init）

- 调用TPManager.getInstance().initSDK初始化SDK
- 设置网络类型和节点数据
- 设置插件地址
- 设置seed

- Call TPManager.getInstance().initSDK to init sdk
- Set blockchain info
- Set plugin info
- Set seed

### <a name='auth'></a>auth

- 调用TPManager.getInstance().auth 唤起钱包完成操作 

- First  call TPManager.getInstance().auth() to auth

### <a name='pushTransaction'></a>pushTransaction

- 构建数据，将操作的action中的permission字段值替换成第一步调用auth传递的perm字段
- 调用TPManager.getInstance().isPermLinkAction(this, transaction, new TPListener())检查action绑定状态
- 如果第二步绑定成功，则直接调用TPManager.getInstance().pushTransaction()
- 如果第二步绑定失败，则需要开发者将permission字段替换成active或者owner,以便拉起钱包执行操作

- Set the permission in authorization to the value which used in auth method
- Call TPManager.getInstance().isPermCheck to check actions bind status
- If get success callback then just call TPManager.getInstance().pushTransaction to execute this action
- If get fail callback, you should replace the permission to active or owner, so that it can pull up TokenPocket to do this action

### <a name='MiniWalletMiniWalletapis'></a>MiniWallet操作 (MiniWallet apis)

#### <a name='SDKInitsdk'></a>1.初始化SDK (Init sdk)

``` java
TPManager.initSDK(Context context);
``` 

#### <a name='blockchainSetblockchaininfo'></a>2.设置blockchain 信息 (Set blockchain info)

``` java
TPManager.getInstance().setBlockChain(this, NetTypeEnum.EOS_MAINNET, "http://openapi.eos.ren");
```

#### <a name='Setplugininfo'></a>3.设置插件信息 (Set plugin info)

``` java
TPManager.getInstance().setAppPluginNode(this, "http://xxx.com");
```

#### <a name='seedSetseedtoprotectdata'></a>4.设置seed (Set seed to protect data)

``` java
TPManager.getInstance().setSeed(this, "xxx");
注意这里xxx只是示例，请设置自己的seed  (The xxx is just for demo ,please set your seed)
```

#### <a name='seedModifyseed'></a>5.修改seed (Modify seed)

``` java
TPManager.getInstance().modifySeed(Context context, "xxx", "yyy")；
注意这里xxx  yyy只是示例，请设置自己的seed (The xxx and yyy is just for demo,please set your  seed)
```

#### <a name='Getauthedaccounts'></a>6.获取已授权账号信息 (Get authed accounts)

``` java
List<String> accounts = TPManager.getInstance().getAccounts(Context context)；
```

#### <a name='Checkpermissionbindtoaccount'></a>7.检查权限是否存在 (Check permission bind to account)

``` java
TPManager.getInstance().isPermExisted(this, "accountName", "permName", new TPListener() {
        @Override
        public void onSuccess(String data) {
        }

        @Override
        public void onError(String data) {
        }

        @Override
        public void onCancel(String data) {

        }
});
```

#### <a name='linkactionCheckactionbindtopermission'></a>8.检查权限是否link到action (Check action bind to permission)

``` java

List<LinkActions> linkActions = new ArrayList();
linkActions.add(new LinkAction("contract", "action"));
TPManager.getInstance().isPermiLinkAction(this, "accountName", "permName", linkActions, new TPListener() {
        @Override
        public void onSuccess(String data) {
        }

        @Override
        public void onError(String data) {
        }

        @Override
        public void onCancel(String data) {

        }
});
```

#### <a name='Clearauthedaccount'></a>9.清除本地授权 (Clear authed account)

``` java
TPManager.getInstance().clearAuth(Context context, "accountName");
```

