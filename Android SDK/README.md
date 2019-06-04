### TokenPocket Wallet Protocol
**https://github.com/TP-Lab/tp-wallet-sdk**

### TokenPocket SDK 中文文档
https://github.com/TP-Lab/Mobile-SDK/blob/master/Android%20SDK/README-ZH.md

# TokenPocket Android SDK

DApp uses this SDK  to pull up the TokenPocket wallet and do some actions such as token transfer, login auth, pushTransaction etc.

Notice: Only version 0.4.9 or higher support this SDK.

### Sample project
https://github.com/TP-Lab/Mobile-SDK/tree/master/Android%20SDK/sample

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

- Proguard
~~~
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.** {*;}
~~~

### APIs

- [Transfer](#Transfer): Pull up TokenPocket to transfer tokens
- [Transaction](#Transaction): Common push action
- [Authorize](#Authorize): Use to make authorization to login
- [Sign](#Sign): Sign the data (only version 0.6.5 or higher support this api)


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
    transfer.setCallbackUrl("https://newdex.io/api/account/transferCallback?uuid=1-46e023fc-015b-4b76-3809-1cab3fd76d2c");

    TPManager.getInstance().transfer(MainActivity.this, transfer,new TPListener() {
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

### <a name='Transaction'></a>Transaction

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
~~~

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
    TPManager.getInstance().sign(MainActivity.this, getSignature(),new TPListener() {
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



