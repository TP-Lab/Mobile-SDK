# OpenSdk

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
    implementation 'com.github.TP-Lab:tp-wallet-android:1.0.0'
}
```


### 使用
```
TPManager.getInstance().transfer(MainActivity.this, "Json String", new TPListener() {
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


#### TP 钱包协议
详情见 **https://github.com/TP-Lab/tp-wallet-sdk** 
