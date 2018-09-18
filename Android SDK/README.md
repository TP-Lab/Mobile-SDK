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
    implementation 'com.github.TP-Lab:tp-wallet-native-android:0.0.3'
}
```


## 使用

目前支持以下操作：
1. **transfer**: 拉起TP钱包转账，类似微信、支付宝转账;
2. **pushTransaction**: push action 进行交易;
3. **authLogin**: 授权登陆.

## TP钱包的回调

调起TP钱包后，如需要监听结果，可使用TPListener监听回调：
```
new TPListener() {
    @Override
    public void onSuccess(String data) {
      //成功，data即为transactionId
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
TPManager.getInstance().transfer(MainActivity.this, getTransferData(), new TPListener() {
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
{
	"protocol": "TokenPocket",
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

## 二. pushTransaction

使用示例
```
TPManager.getInstance().pushTransaction(MainActivity.this, getPushTransactionData(), new TPListener() {
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

pushTransaction Data示例(详情见TP钱包协议)
```
{
	"dappName": "test",
	"dappIcon": "https://newdex.io/static/logoicon.png",
	"action": "pushTransaction",
	"actions": [{
		"account": "eosio.token",
		"name": "transfer",
		"authorization": [{
			"actor": "xiaoyuantest",
			"permission": "active"
		}],
		"data": {
			"from": "xiaoyuantest",
			"to": "clement22222",
			"quantity": "0.0001 EOS",
			"memo": "jlsdjlsdjf"
		}
	}],
	"expired": "10000000000000"
}
```

## 三. authLogin

使用示例
```
TPManager.getInstance().authLogin(MainActivity.this, getAuthLogin(), new TPListener() {
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

authLogin Data示例(详情见TP钱包协议)
```
{
    "protocol": "TokenPocket",
    "version": "1.0",
    "dappName": "Newdex",
    "dappIcon": "https://newdex.io/static/logoicon.png",
    "action": "login",
    "actionId": "web-99784c28-70f0-49ff-3654-f27b137b3502",
    "callbackUrl": "https://newdex.io/api/account/walletVerify",
    "expired": 1537157808,
    "memo": "The first gobal decentralized exchange built on EOS"
}
```

authLogin 成功后的回调示例
```
{
    "protocol": "",
    "version": "",
    "result": 0,
    "actionId": "",
    "action": "",
    "ref": "TokenPocket"
}
```


## TP钱包协议
详情见 **https://github.com/TP-Lab/tp-wallet-sdk**
