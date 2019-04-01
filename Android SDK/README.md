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
    implementation 'com.github.TP-Lab:tp-wallet-native-android:0.0.6'
}
~~~

### APIs

- Transfer: Pull up TokenPocket to transfer tokens
- Transaction: Common push action
- Authorize: Use to make authorization to login
- Sign: Sign the data

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
~~~
- Build transfer data
~~~
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
~~~

### Transaction
demo

~~~
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
~~~
- Build transaction data
~~~
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
~~~

### Authorize
demo

~~~
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
~~~
- Build authorize data
~~~
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
~~~

### Sign
only version 0.6.5 or higher support this api
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
- Build signature data
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
