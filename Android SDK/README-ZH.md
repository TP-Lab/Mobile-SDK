# Android SDK
该SDK用于拉起TP钱包，实现APP间互相调起，使用TP进行相关action操作。

**提示：** 该SDK仅支持**0.4.9**以上版本的TP钱包。

## 导入
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


## 使用

目前支持以下操作：
1. **Transfer**: 拉起TP钱包转账，类似微信、支付宝转账;
2. **Transaction**: push action 进行交易;
3. **Authorize**: 授权登陆;
4. **Sign**: 对数据进行签名.

## TP钱包的回调

调起TP钱包后，如需要监听结果，可使用TPListener监听回调：
```
new TPListener() {
    @Override
    public void onSuccess(String data) {
      //成功
    }

    @Override
    public void onError(String data) {
      //错误
    }

    @Override
    public void onCancel(String data) {
      //取消
    }
}
```

## 一. Transfer

使用示例
```
TPManager.getInstance().transfer(MainActivity.this, getTransfer(), new TPListener() {
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

Transfer Data示例(详情见TP钱包协议)
```
private Transfer getTransfer() {
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
    return transfer;
}
```

Transfer 成功后的回调示例
```
{
    "actionId": "",
    "action": "",
    "txID":"",
    "ref": "TokenPocket"
}
```

## 二. Transaction

使用示例
```
TPManager.getInstance().pushTransaction(MainActivity.this, getTransaction(), new TPListener() {
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

Transaction Data示例(详情见TP钱包协议)
```
private Transaction getTransaction() {
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

    return transaction;
}
```

pushTransaction 成功后的回调示例
```
{
    "actionId": "",
    "action": "",
    "txID":"",
    "ref": "TokenPocket"
}
```

## 三. Authorize

使用示例
```
TPManager.getInstance().authorize(MainActivity.this, getAuthorize(), new TPListener() {
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

Authorize Data示例(详情见TP钱包协议)
```
private Authorize getAuthorize() {
    Authorize authorize = new Authorize();
    authorize.setDappName("Newdex");
    authorize.setDappIcon("https://newdex.io/static/logoicon.png");
    authorize.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
    authorize.setCallbackUrl("https://newdex.io/api/account/walletVerify");
    authorize.setExpired(1537157808L);
    authorize.setMemo("The first gobal decentralized exchange built on EOS");

    return authorize;
}
```

Authorize 成功后的回调示例
```
{
    "actionId": "",
    "action": "",
    "ref": "TokenPocket"
}
```


### Sign
v0.6.5以上版本支持该操作
~~~
TPManager.getInstance().sign(MainActivity.this, getSignature(), new TPListener() {
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
Signature Data示例(详情见TP钱包协议)
~~~
private Signature getSignature() {
    Signature signature = new Signature();
    signature.setDappName("Newdex");
    signature.setDappIcon("https://newdex.io/static/logoicon.png");
    signature.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
    signature.setCallbackUrl("https://newdex.io/api/account/walletVerify");
    signature.setExpired(1537157808L);
    signature.setMemo("The first gobal decentralized exchange built on EOS");
    signature.setMessage("hello");
    return signature;
}
~~~

### 混淆
~~~
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.** {*;}
~~~

### TP钱包协议
详情见 **https://github.com/TP-Lab/tp-wallet-sdk**
