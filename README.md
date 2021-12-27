# WifiManager
![](https://img.shields.io/badge/platform-android-orange.svg)
![](https://img.shields.io/badge/language-java-yellow.svg)
![](https://jitpack.io/v/com.iwdael/wifimanager.svg)
![](https://img.shields.io/badge/build-passing-brightgreen.svg)
![](https://img.shields.io/badge/license-apache--2.0-green.svg)
![](https://img.shields.io/badge/api-19+-green.svg)

简化Android Wifi开发，已实现常用的功能，比如获取WiFi管理，WiFi列表，断开、清除或者连接WiFi。
## 特点
* 根据信号强度排序
* 连接或操作中的WIFI自动置顶
* 自动刷新WIFI列表
* 支持常见Wifi操作
<br/><br/><br/>
![](https://github.com/iwdael/WifiManager/blob/master/screenshots.png)
<br/><br/>
## 概述
WifiManager主要是由*IWifiManager*和*IWifi*组成，IWifiManager中可实现三个接口，完成Wifi列表数据更新
### Class
#### IWifiManager

|方法|功能|
|:------:|:------:|
|isOpened()|WIFI是否打开|
|openWifi()|打开WIFI|
|closeWifi()|关闭WIFI|
|scanWifi()|扫描WIFI。此方法为异步操作，扫描结果会通过接口回掉|
|disConnectWifi()|断开当前连接|
|connectEncryptWifi(IWifi wifi, String password)|通过密码，连接WIFI|
|connectSavedWifi(IWifi wifi)|连接已保存的WIFI|
|connectOpenWifi(IWifi wifi)|连接开放的WIFI|
|removeWifi(IWifi wifi)|清除已保存的WIFI|
|getWifi()|获取WIFI列表。此方法不建议使用，若是WIFI列表有变化会通过接口回调|
|setOnWifiConnectListener(OnWifiConnectListener listener)|wifi连接状态变化监听|
|setOnWifiStateChangeListener(OnWifiStateChangeListener listener)|wifi状态变化监听|
|setOnWifiChangeListener(OnWifiChangeListener listener)|wifi列表变化监听。包含WiFi数目变化，WiFi状态变化等，用户只需要展示即可|
|destroy()|IWifiManager不需要使用时，需销毁|

#### IWifi
|方法|功能|
|:------:|:------:|
|name()|Wifi名称|
|isEncrypt()|是否加密|
|isSaved()|密码是否保存|
|isConnected()|是否连接|
|encryption()|加密类型描述，例如：WEP等|
|level()|信号强度|
|ip()|若当前WiFi连接，ip不为空|
|description()|wifi描述|
|description2()|wifi描述，含Ip|

#### OnWifiChangeListener

|方法|功能|
|:------:|:------:|
|onWifiChanged(List<IWifi> wifis)|wifi列表，wifi所有变化都会通过此方法，只需要展示此列表即可|

#### OnWifiConnectListener

|方法|功能|
|:------:|:------:|
|onConnectChanged(boolean status)|wifi是否连接|

#### OnWifiStateChangeListener

|方法|功能|
|:------:|:------:|
|onStateChanged(State state)|wifi状态，例如：开启中，已开启，关闭中，已关闭|

## 使用说明
1.获取*IWifiManager*
```
    IWifiManager.create(Context context);
```
2.销毁*IWifiManager*
```
    iWifiManager.destroy();
```
## 快速引入项目
合并以下代码到需要使用的Module的dependencies中。
```
	dependencies {
                ...
              implementation 'com.iwdael:wifimanager:$version'
	}
```
<br><br><br>
## 感谢浏览
请不要吝啬你的小星星，如果你有任何疑问，请加入QQ群，我将竭诚为你解答。
<br>
![Image Text](https://github.com/iwdael/CarouselBanner/blob/master/qq_group.png)
