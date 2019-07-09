### TP钱包协议文档（TokenPocket Wallet Protocol）
**https://github.com/TP-Lab/tp-wallet-sdk**

# TokenPocket Android SDK
该SDK用于拉起TP钱包，实现APP间互相调起，使用TP进行相关action操作。

#### 提示
- 该SDK仅支持0.4.9以上版本的TP钱包。
- 0.7.8以及以上的TP钱包版本支持minwallet。


DApp uses this SDK  to pull up the TokenPocket wallet and do some actions such as token transfer, login auth, pushTransaction etc.

#### Notice
- Only version 0.4.9 or higher support this SDK.
- Only version 0.7.8 or higher support minwallet apis.

### Demo
https://github.com/TP-Lab/Mobile-SDK/tree/master/Android%20SDK/sample

### 开始接入(Getting Started)

- Android studio 工程根目录下build.gradle添加以下内容(Add the following lines to your main build.gradle in the root of your project)

~~~
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://dl.bintray.com/tokenpocket/Maven' }
    }
}
~~~

- App下build.gradle添加一下内容(add the following lines to your app/build.gradle)

~~~
dependencies {
    implementation 'com.tokenpocket.lab:wallet-sdk:1.0.0'
}
~~~

- 反混淆(Proguard)
~~~
# tokenpocket sdk
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.**{*;}
-keep interface com.tokenpocket.opensdk.**{*;}
~~~

### minwallet使用步骤(How to use minwallet)

minWallet，可以实现对于特定操作，第三方App不需要拉起钱包，直接在应用内部完成，体验更为流畅

##### 调用TPManager.getInstance().auth 唤起钱包完成操作 (First  call TPManager.getInstance().auth() to auth)
##### pushTransaction
- 构建数据，将操作的action中的permission字段值替换成第一步调用auth传递的perm字段（Set the permission in authorization to the value which used in auth method）
- 调用TPManager.getInstance().isPermLinkAction(this, transaction, new TPListener())检查action绑定状态（Call TPManager.getInstance().isPermCheck to check actions bind status）
- 如果第二步绑定成功，则直接调用TPManager.getInstance().pushTransaction()(
If get success callback then just call TPManager.getInstance().pushTransaction to execute this action)
- 如果第二步绑定失败，则需要开发者将permission字段替换成active或者owner,以便拉起钱包执行操作(If get fail callback, you should replace the permission to active or owner, so that it can pull up TokenPocket to do this action)


### APIs

### 通用操作（Common apis）
- [1 授权登陆 （Authorize）](#Authorize)
- [2 转账 （Token transfer）](#Transfer)
- [3 PushTransaction](#PushTransaction)
- [4 签名（Sign）](#Sign)
### 内置钱包操作(minwallet apis)
- [1 初始化SDK（Init sdk）](#initSDK)
- [2 设置节点信息（Set blockchain info）](#setBlockChain)
- [3 设置插件信息（Set pulugin info）](#Auth)
- [4 设置加密seed（Set seed to protect data）](#setSeed)
- [5 修改加密seed（Modify seed）](#modifySeed)
- [6 获取已授权账号信息（Get authed accounts）](#getAccounts)
- [7 检查权限是否存在（Check permission bind to account）](#isPermExist)
- [8 检查权限是否link到action（Check action bind to permission）](#isPermLinkAction)
- [9 清除本地授权（ClearAuth）](#clearAuth)

### <a name='Authorize'></a>Authorize

~~~

    Authorize authorize = new Authorize();
    authorize.setBlockchain("EOS");
    authorize.setDappName("Newdex");
    authorize.setDappIcon("https://newdex.io/static/logoicon.png");
    authorize.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
    authorize.setCallbackUrl("https://newdex.io/api/account/walletVerify");
    authorize.setExpired(1537157808L);
    authorize.setMemo("The first gobal decentralized exchange built on EOS");
    authorize.setBlockchain("EOS");
    TPManager.getInstance().authorize(MainActivity.this, getAuthorize(),new TPListener() {
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
~~~
### <a name='Transfer'></a>Transfer
~~~

    Transfer transfer = new Transfer();
    transfer.setBlockchain("EOS");
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
    transfer.setBlockchain("EOS");
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
~~~

### <a name='Transaction'></a>Transaction
==如果是IOST底层，将transaction.setActions替换成setPayload,详情请见demo（If is iost, replace setActions with setPalyload, for more details, please check the demo project）==

~~~

    
    Transaction transaction = new Transaction();
    transaction.setBlockchain("EOS");
    transaction.setDappName("Test Name");
    transaction.setDappIcon("https://newdex.io/static/logoicon.png");
    transaction.setActions("[{\n" +
            "\"account\": \"eosio.token\",\n" +
            "\"name\": \"transfer\",\n" +
            "\"authorization\": [{\n" +
            "\"actor\": \"clement11111\",\n" +
            "\"permission\": \"active\"\n" +
            "}],\n" +
            "\"data\": {\n" +
            "\"from\": \"clement11111\",\n" +
            "\"to\": \"clement22222\",\n" +
            "\"quantity\": \"0.0001 EOS\",\n" +
            "\"memo\": \"jlsdjlsdjf\"\n" +
            "}\n" +
            "}]");
    transaction.setBlockchain("EOS");
    transaction.setExpired(10000000000L);
    
    TPManager.getInstance().pushTransaction(MainActivity.this, transaction, new TPListener() {
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
~~~



### <a name='Sign'></a>Sign

~~~
    Signature signature = new Signature();
    signature.setBlockchain("EOS");
    signature.setDappName("Newdex");
    signature.setDappIcon("https://newdex.io/static/logoicon.png");
    signature.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
    signature.setCallbackUrl("https://newdex.io/api/account/walletVerify");
    signature.setExpired(1537157808L);
    signature.setMemo("The first gobal decentralized exchange built on EOS");
    signature.setMessage("hello");
    signature.setBlockchain("EOS");
    TPManager.getInstance().sign(MainActivity.this, getSignature(),new TPListener() {
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
~~~

#### <a name='initSDK'></a>initSDK
~~~
TPManager.initSDK(Context context);
~~~

#### <a name='setBlockChain'></a>设置blockchain 信息 (Set blockchain info)
~~~
TPManager.getInstance().setBlockChain(this, NetTypeEnum.EOS_MAINNET, "http://openapi.eos.ren");
~~~

#### <a name='setAppPluginNode'></a>设置插件信息 (Set plugin info)
~~~
TPManager.getInstance().setAppPluginNode(this, "http://xxx.com");
~~~

#### <a name='setSeed'></a>设置seed (Set seed to protect data)
~~~
TPManager.getInstance().setSeed(this, "xxx");
注意这里xxx只是示例，请设置自己的seed （the xxx is just for demo ,please set your seed）
~~~

#### <a name='modifySeed'></a>修改seed (Modify seed)

~~~
TPManager.getInstance().modifySeed(Context context, "xxx", "yyy")；
注意这里xxx  yyy只是示例，请设置自己的seed (the xxx and yyy is just for demo,please set your  seed)
~~~

#### <a name='getAccounts'></a>获取已授权账号信息（Get authed accounts
~~~
List<String> accounts = TPManager.getInstance().getAccounts(Context context)；
~~~

#### <a name='isPermExisted'></a>检查权限是否存在（Check permission bind to account）
~~~
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
~~~

#### <a name='isPermLinkAction'></a>检查权限是否link到action（Check action bind to permission)
~~~

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
~~~

#### <a name='clearAuth'></a>清除本地授权（Clear authed account）
~~~
TPManager.getInstance().clearAuth(Context context, "accountName");
~~~



