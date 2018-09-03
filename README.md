# OpenSdk

### How to import
1.Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2.Add the dependency
```
dependencies {
	        implementation 'com.github.TP-Lab:tp-wallet-android:1.0.0'
	}
```

### How to use
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

#### TP Wallet protocol
See https://github.com/TP-Lab/tp-wallet-sdk for details. 
