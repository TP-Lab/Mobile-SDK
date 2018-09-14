# OpenSdk
该SDK用于拉起TP钱包转账，类似微信、支付宝转账sdk.

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
    implementation 'com.github.TP-Lab:tp-wallet-android:1.0.1'
}
```


## 使用

#### 转账
```
TPManager.getInstance().transfer(MainActivity.this, "Json String", new TPListener() {
    @Override
    public void onSuccess(String data) {
      //data即为transactionId
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
构建一个Json数据，调用TPManager.getInstance().transfer()，当TP转账成功后，从TP跳转回来后，会自动回调onSuccess方法，onError和onCancel类似。下面是一个Json数据的示例（详情见TP钱包协议）：
```
{
	"protocol": "SimpleWallet",
	"version": "1.0",
	"dappName": "southex",
	"dappIcon": "https://www.southex.com/static/southex.png",
	"action": "transfer",
	"from": "clement22222",
	"to": "greatsouthex",
	"amount": 0.0105,
	"contract": "eosio.token",
	"symbol": "EOS",
	"precision": 4,
	"memo": "t=southex&a=put_order&oid=382144",
	"expired": "1535983498",
	"desc": ""
}
```


## TP钱包协议
详情见 **https://github.com/TP-Lab/tp-wallet-sdk**
