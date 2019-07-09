# TokenPocket Protocol

该协议可以用于从网页和第三方App拉起TokenPocket钱包做授权 转账等操作

This protocol can be used to call TokenPocket do some actions from page or app。

## <a name='Versionv1.0'></a>Version：v1.0

## <a name='Catelog'></a>目录 (Catelog)

<!-- vscode-markdown-toc -->
* [使用场景 (How to use)](#Howtouse)
	* [扫码拉起TokenPocket  (Scan qrcode call TokenPocket)](#TokenPocketScanqrcodecallTokenPocket)
	* [页面拉起 ( Call from web page )](#Callfromwebpage)
	* [独立App拉起 ( Call from app )](#AppCallfromapp)
	* [Dapp 浏览器打开url ( Call TokenPocket to open url with Dapp browser)](#DappurlCallTokenPockettoopenurlwithDappbrowser)
* [通用操作 (Common APIs)](#CommonAPIs)
	* [1. Authorize](#Authorize)
	* [2. 转账 (Token transfer)](#Tokentransfer)
	* [3. PushTransaction](#PushTransaction)
	* [4. 签名(Sign)](#Sign)
	* [5. Dapp 浏览器打开url (Dapp browser open url)](#DappurlDappbrowseropenurl)
* [miniwallet 操作 (miniwallet APIs)](#miniwalletminiwalletAPIs)
	* [1. 初始化sdk (Init SDK)](#sdkInitSDK)
	* [2. 设置blockchain 信息 (Set blockchain info)](#blockchainSetblockchaininfo)
	* [3. 设置插件信息 (Set plugin info)](#Setplugininfo)
	* [4. 设置seed (Set seed to protect data)](#seedSetseedtoprotectdata)
	* [4. 修改seed (Modify seed)](#seedModifyseed)
	* [5. 获取已授权账号信息 (Get authed accounts](#Getauthedaccounts)
	* [6. 检查权限是否存在 (Check permission bind to account)](#Checkpermissionbindtoaccount)
	* [7. 检查权限是否link到action (Check actions bind to permission)](#linkactionCheckactionsbindtopermission)
	* [8. 清除本地授权 (clearAuth)](#clearAuth)

<!-- vscode-markdown-toc-config
	numbering=false
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->




## <a name='Howtouse'></a>使用场景 (How to use)

### <a name='TokenPocketScanqrcodecallTokenPocket'></a>扫码拉起TokenPocket  (Scan qrcode call TokenPocket)

### <a name='Callfromwebpage'></a>页面拉起 ( Call from web page )

- Scheme：tpoutside://pull.activity?param={}

转账示例，其他操作类似(Token transfer demo)

``` html
<a href='tpoutside://pull.activity?param={"Protocol":"TokenPocket","version":"v1.0","blockchain":"eos","from":"aaaaaa123451","to":"cbzfb4a5s5zv","amount":"0.0001","contract":"eosio.token","symbol":"EOS","precision":"4","action":"transfer","memo":"test transfer from page"}'>Open TokenPocket to transfer eos</a><br/>
```

### <a name='AppCallfromapp'></a>独立App拉起 ( Call from app )

第三方App可以拉起TokenPocket执行签名，转账等操作。TP sdk还支持minWallet，可以实现对于特定操作，第三方App不需要拉起钱包，直接在应用内部完成，体验更为流畅，具体使用请参照：[https://github.com/TP-Lab/Mobile-SDK](https://github.com/TP-Lab/Mobile-SDK)

Third-party apps can execute signatures, transfers, and etc actions by pull up the TokenPocket. TP SDK also support minWallet that can execute specific actions without leaving the app, which provides a better user experience. Please check it for the details:[https://github.com/TP-Lab/Mobile-SDK](https://github.com/TP-Lab/Mobile-SDK)


### <a name='DappurlCallTokenPockettoopenurlwithDappbrowser'></a>Dapp 浏览器打开url ( Call TokenPocket to open url with Dapp browser)

- Scheme:tpdapp://open?params={}
``` html
<a href='tpdapp://open?params={"url": "https://dapp.mytokenpocket.vip/referendum/index.html#/", "chain": "EOS", "source":"xxx"}'>Open url with TokenPocket</a>
```


## <a name='CommonAPIs'></a>通用操作 (Common APIs)

### <a name='Authorize'></a>1. Authorize

- Parameters

``` json
{
    protocol	string   //protocol name here is TokenPocket
    version     string   // protocol version here is v1.0
    dappName    string   // optional
    dappIcon    string   // optional
    blockchain  string   // wallet type(eos bos eth moac )
    wallet      string   // account name
    action      string   // neccessary here is login
    actionId    string   // optional   
    callbackUrl string   // optional
    expired	    string   //expire time in seconds
    memo	    string   // optional
}
```

- Success return data

``` json
{
   "sign": "SIG_K1_KZL9eR4cCQCJHpYHbh44yGrDqu4w8hHzQwb1xTk4Mcd4czqpw4jJUgg9DnWXzE3r",
   "timestamp": "1546613919", //in seconds
   "wallet": "eoseoseosacc", //account name
   "ref": "TokenPocket",
   "action":"login",
   "actionId":"ljsdjljdljf-xjlsdjfkj" //actionId from dapp
   "publickey": "EOS2TtWv19a9eYEQYB8NbGCM28nQNngWP4UcSjVYqtEz6kF7yCnPX",
   "permissions": ["active", "owner"],
   "result": 1
}
```

Cancel return data
``` json
{
   "action":"login",
   "actionId":"ljsdjljdljf-xjlsdjfkj" 
   "result": 0
}
```


### <a name='Tokentransfer'></a>2. 转账 (Token transfer)

- Parameters
``` json
{
    protocol    string   //protocol name here is TokenPocket
    version     string   // protocol version here is v1.0
    dappName    string   // optional
    dappIcon    string   // optional
    action      string   // neccessary here is transfer
    actionId    string   // optional
    blockchain  string   //wallet type(eos bos eth moac )
    from        string   // optional
    to          string   // neccessary
    amount      number   // neccessary
    contract    string   // neccessary
    symbol      string   // neccessary
    precision   number   // neccessary
    memo        string   //optional
    expired	    string   // expire time in seconds
}
```

- Success return data

``` json
{
"ref": "TokenPocket",
"txID": "588c6797534d09e8e0b149c06c11bfd6ca7b96f0d4bba87700fffe7a87b0d988",
"publickey": "EOSX1tWv19a9eKEQQB8Nb2wM28nYNngWP3UcSjVYqtjz6kF7yCnQ",
"action":"transfer",
"actionId":"ljsdljf-xljlsdjfl" //from dapp
"wallet": "eoseoseostes",
"permissions": ["active", "owner"],
"result": 1
}
```

- Cancel return data

``` json
"action":"transfer",
"actionId":"ljsdljf-xljlsdjfl" //from dapp
"result": 0
```


### <a name='PushTransaction'></a>3. PushTransaction

- Parameters

``` json
    protocol    string  //protocol name here is TokenPocket
    version     string   // protocol version here is v1.0
    dappName    string   // optional
    dappIcon    string   // optional
    action      string   // neccessary here is pushTransaction
    actionId    string   // optional 
    blockchain  string   //wallet type(eos bos eth moac )
    actions     string   //actions data
    memo    string       //optional
```

- Success return data

``` json
{
"ref": "TokenPocket",
"txID": "588c6797534d09e8e0b149c06c11bfd6ca7b96f0d4bba87700fffe7a87b0d988",
"publickey": "EOSX1tWv19a9eKEQQB8Nb2wM28nYNngWP3UcSjVYqtjz6kF7yCnQ",
"action":"pushTransaction",
"actionId":"ljsdljf-xljlsdjfl" 
"wallet": "eoseoseostes",
"permissions": ["active", "owner"],
"result": 1
}
```

- Cancel return data

``` json
{
"action":"pushTransaction",
"actionId":"ljsdljf-xljlsdjfl"
"result": 0
}
```

### <a name='Sign'></a>4. 签名(Sign)

 only version 0.6.5 or higher support this api

- Parameters

``` json
    protocol    string  //protocol name here is TokenPocket
    version     string   // protocol version here is v1.0
    dappName    string   // optional
    dappIcon    string   // optional
    action      string   // neccessary here is sign
    actionId    string   // optional 
    blockchain  string   //wallet type(eos bos eth moac )
    message     string   //message to sign
    memo    string       //optional
```

- Success return data

``` json
{
"ref": "TokenPocket",
"sign": "SIG_K1_JXLSDFLJLSKDJFKJ", //signed data
"publickey": "EOSX1tWv19a9eKEQQB8Nb2wM28nYNngWP3UcSjVYqtjz6kF7yCnQ",
"action":"pushTransaction",
"actionId":"ljsdljf-xljlsdjfl" 
"wallet": "eoseoseostes",
"permissions": ["active", "owner"],
"result": 1
}
```

- Cancel return data

``` json
{
"action":"sign",
"actionId":"ljsdljf-xljlsdjfl"
"result": 0
}
```

### <a name='DappurlDappbrowseropenurl'></a>5. Dapp 浏览器打开url (Dapp browser open url)

- Parameters

``` json
"url": "https://dapp.mytokenpocket.vip/referendum/index.html#/",
"chain": "EOS", 
"source":"xxx"
```

## <a name='miniwalletminiwalletAPIs'></a>miniwallet 操作 (miniwallet APIs)

### <a name='sdkInitSDK'></a>1. 初始化sdk (Init SDK)


### <a name='blockchainSetblockchaininfo'></a>2. 设置blockchain 信息 (Set blockchain info)

- Parameters

```
netType：NetTypeEnum 网络类型，包含主网和测试网(Nettype incluce mainnet and kyline jungle)
nodeUrl: string 
```

### <a name='Setplugininfo'></a>3. 设置插件信息 (Set plugin info)

- Parameters

```
pluginUrl:  string  sdk功能需要，app可以自己在节点上部署插件，或者使用官方地址 (Used by sdk. app can delpoy the plugin or just use TokenPocket office plugin url)
```

### <a name='seedSetseedtoprotectdata'></a>4. 设置seed (Set seed to protect data)

- Parameters

```
seed: string 非常重要，必须设置 (Very important, it is neccessary)
```

### <a name='seedModifyseed'></a>4. 修改seed (Modify seed)

- Parameters

```
oldSeed: string 
newSeed: string
```

### <a name='Getauthedaccounts'></a>5. 获取已授权账号信息 (Get authed accounts


### <a name='Checkpermissionbindtoaccount'></a>6. 检查权限是否存在 (Check permission bind to account)

```
account:  string 
perm:  string
```

### <a name='linkactionCheckactionsbindtopermission'></a>7. 检查权限是否link到action (Check actions bind to permission)

- Parameters

```
account:  string
perm: string
actions:  List<LinkAction> 需要检查的actions(The actions to be checked)
```

### <a name='clearAuth'></a>8. 清除本地授权 (clearAuth)

```
account: string 需要清除的账号 (Account to be clear)
```

