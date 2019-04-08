### TP钱包协议
**https://github.com/TP-Lab/tp-wallet-sdk**

# TokenPocket Android SDK
该SDK用于拉起TP钱包，实现APP间互相调起，使用TP进行相关action操作。

**提示：** 该SDK仅支持**0.4.9**以上版本的TP钱包。

### Sample 工程
https://github.com/TP-Lab/Mobile-SDK/tree/master/Android%20SDK/sample

### 导入
1.在根目录的build.gradle中添加:
```
allprojects {
   repositories {
	...
	maven { url 'https://jitpack.io' }
   }
}
```

2.在app下的build.gradle中添加:
```
dependencies {
    implementation 'com.github.TP-Lab:tp-wallet-native-android:0.0.6'
}
```
- 混淆
~~~
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.** {*;}
~~~

### 使用

目前支持以下操作：
1. [Transfer](#Transfer): 拉起TP钱包转账，类似微信、支付宝转账;
2. [Transaction](#Transaction): push action 进行交易;
3. [Authorize](#Authorize): 授权登陆;
4. [Sign](#Sign): 对数据进行签名（0.6.5及以上钱包版本支持该操作）.



### <a name='Transfer'></a>Transfer

使用示例
```
    Transfer transfer = new Transfer();
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
    TPManager.getInstance().transfer(MainActivity.this, transfer, new TPListener() {
    @Override
    public void onSuccess(String data) {
      Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String data) {
      Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(String data) {
      Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }
});
```

### <a name='Transaction'></a>Transaction

使用示例
```
    Transaction transaction = new Transaction();
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
    transaction.setExpired(10000000000L);
    TPManager.getInstance().pushTransaction(MainActivity.this, transaction, new TPListener() {
    @Override
    public void onSuccess(String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }
});

```

### <a name='Authorize'></a>Authorize

使用示例
```
    Authorize authorize = new Authorize();
    authorize.setDappName("Newdex");
    authorize.setDappIcon("https://newdex.io/static/logoicon.png");
    authorize.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
    authorize.setCallbackUrl("https://newdex.io/api/account/walletVerify");
    authorize.setExpired(1537157808L);
    authorize.setMemo("The first gobal decentralized exchange built on EOS");
    TPManager.getInstance().authorize(MainActivity.this, authorize, new TPListener() {
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

### <a name='Sign'></a>Sign
v0.6.5以上版本支持该操作
~~~
    Signature signature = new Signature();
    signature.setDappName("Newdex");
    signature.setDappIcon("https://newdex.io/static/logoicon.png");
    signature.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
    signature.setCallbackUrl("https://newdex.io/api/account/walletVerify");
    signature.setExpired(1537157808L);
    signature.setMemo("The first gobal decentralized exchange built on EOS");
    signature.setMessage("hello");
    TPManager.getInstance().sign(MainActivity.this, signature, new TPListener() {
    @Override
    public void onSuccess(String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel(String data) {
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
    }
});
~~~



