# Android SDK

DApp uses this SDK  to pull up the TokenPocket wallet and do some actions such as token transfer, login auth, pushTransaction etc.

Notice: Only version 0.4.9 or higher support this SDK.

### Getting Started

- add the following lines to your main build.gradle in the root of your project

~~~
allprojects {
   repositories {
	...
	maven { url 'https://jitpack.io' }
   }
}
~~~

- add the following lines to your app/build.gradle

~~~
dependencies {
    implementation 'com.github.TP-Lab:tp-wallet-native-android:0.0.5'
}
~~~

### APIs

- transfer: pull up TokenPocket to transfer tokens
- pushTransaction: common push action
- authLogin : use to make authorization to login 

### The result callback

after call the apis, if you want to get the result or do some callback works, you should  add the TPListener

~~~
new TPListener() {
    @Override
    public void onSuccess(String data) {
    }

    @Override
    public void onError(String data) {
    }

    @Override
    public void onCancel(String data) {
    }
}
~~~

### Transfer
- demo
~~~
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
~~~

- Get a json string which include the following key-values as transfer function param
~~~
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
~~~

### pushTransaction
demo

~~~
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
~~~
- Get a json string which include the following key-values as pushTransaction function params

~~~
{
	"dappName": "test",
	"dappIcon": "https://newdex.io/static/logoicon.png",
	"action": "pushTransaction",
	"actions": [{
		"account": "eosio.token",
		"name": "transfer",
		"authorization": [{
			"actor": "xxxxxxx",
			"permission": "active"
		}],
		"data": {
			"from": "xxxxxxx",
			"to": "clement22222",
			"quantity": "0.0001 EOS",
			"memo": "jlsdjlsdjf"
		}
	}],
	"expired": "10000000000"
}
~~~

### authLogin
demo

~~~
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
~~~
- Get a json string which include the following key-values as authLogin function params
~~~
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
~~~

### sign
only version 0.6.5 or higher support this api
~~~
TPManager.getInstance().sign(MainActivity.this, getSignData(), new TPListener() {
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
- Get a json string which include the following key-values as sign function params
~~~
{
    "protocol": "TokenPocket",
    "version": "1.0",
    "dappName": "Newdex",
    "dappIcon": "https://newdex.io/static/logoicon.png",
    "action": "sign",
    "actionId": "web-99784c28-70f0-49ff-3654-f27b137b3502",
    "expired": 1537157808,
    "memo": "The first gobal decentralized exchange built on EOS",
    "message":"hello"
}
~~~

### Proguard
~~~
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.** {*;}
~~~

- The sample project is available(https://github.com/TP-Lab/Mobile-SDK/tree/master/Android%20SDK/sample), developer can get the details about how to use this sdk.

### TokenPocket Wallet Protocol
**https://github.com/TP-Lab/tp-wallet-sdk**

### 中文文档
https://github.com/TP-Lab/Mobile-SDK/blob/master/Android%20SDK/README-ZH.md
