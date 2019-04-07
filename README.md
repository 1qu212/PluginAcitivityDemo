# PluginAcitivityDemo
Hook加载插件中的Activity。

# 一个更好的替代方案
[PluginActivity_MultiDexDemo](https://github.com/1qu212/PluginActivity_MultiDexDemo)

## 适用于已知插件名的插件化方案
### 基于[HookStartActivityDemo](https://github.com/1qu212/HookStartActivityDemo)的Activity插件化方案
[HookStartActivityDemo](https://github.com/1qu212/HookStartActivityDemo)是为了启动没有在AndroidManifest中声明的Activity，现适配到API25。分为两个步骤

step1：对ActivityManagerNative的getDefault()方法进行Hook，把TargetActivity替换为StubActivity。

step2：对ActivityThread的mInstrumentation字段进行Hook，把StubActivity再替换回TargetActivity。

### 为了能够加载插件中的Activity
step3：在其基础上进行了ClassLoader的反射替换。

step4：同时为了能够在LoadedApk类的initializeJavaContextClassLoader()方法中调用pm.getPackageInfo()方法时不报错，当调用pm.getPackageInfo()直接通过hook使其返回一个新对象而不报错。
