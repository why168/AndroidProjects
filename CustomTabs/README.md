## 7.CustomTabs

### 介绍

#####   Chrome 浏览器现在已经成为 Android 原生系统的默认浏览器了。如果在您的应用中需要打开网页内容，之前的做法要么使用 WebView 或者 直接打开第三方浏览器来显示内容。典型的场景比如微信里面的大部分内容都是先在微信自己的 WebView 中显示，然后你可以选择菜单中的在浏览器中打开。而 Chrome 团队认为现在在应用中显示网页内容已经非常常见了，为了方便大家显示网页内容并且保存良好的用户体验，实现了这么一个功能。具体来说，如果您想在应用中打开一个网页，你可以通过 Chrome Custom Tabs 来打开 Chrome 浏览器的一个自定义 Tab 来显示该网页，你可以自定义这个 Tab 的一些属性来保持良好的用户体验，并且让用户感觉这个自定义 Tab 就是您应用的一部分。目前可以自定义如下内容：
* ActionBar（也就是最上面的 Toolbar，网址一栏）的颜色
* 自定义 Tab 的进入和退出过场动画
* 在自定义 Tab 的 ActionBar 上添加自定义图标和菜单
* 自定义返回图标
* 自定义 Tab 可以通过回调接口来通知应用网页导航的情况
* 性能更好，使用 Custom Tab 来打开网页的时候，还可以预先加载网页内容，这样当打开的时候，用户感觉非常快。
* 生命周期管理，使用 Custom tab 可以和您的应用绑定一起，当用户在浏览网页的时候，您的应用也被认为是互动的程序，不会被系统杀死。
* 可以共享 Chrome 浏览器的 Cookie ，这样用户就不用再登录一遍网站了。
* 如果用户开启了 Chrome 的数据压缩功能，则一样可以使用
* 和 Chrome 一样的自动补全功能
* 只需点击左上角的返回按钮一次就可以返回您的应用中了
* 每次用的都是最新版本的 Chrome

##### 何时选择使用 WebView 和 Chrome Custom Tabs 呢？

* 如果你之前使用的不是 WebView ，则这种情况都应该用 Chrome Custom Tabs 来打开网页。如果你之前使用的是 WebView，则这里有两种情况来帮组您选择哪种情况更适合你：如果要显示的网页内容是由您自己控制的，并且网页内容需要和 Android 组件交互，比如通过 JavaScript 接口来调用 Android 系统的一些功能，这种情况下你还需要用 WebView 来实现；其他情况都可以用 Chrome Custom Tabs 来实现。

* Chrome Custom Tabs 使用起来非常简单，简单的使用只需要一行代码，和直接调用系统浏览器显示网页没啥区别。通过简单的几项设定，就能让用户感觉浏览第三方网页就像您应用本身的功能一样。

##### 使用注意
* 在CustomTabActivityHelper这个类里面的openCustomTab这个方法说明了一下：packageName is null打开自己定义的WebviewActivity的WebView，is not null打开一个自定义选项卡URL
* 我通过2个手机测试发现
	* 谷歌亲儿子Nexus5：打开的是Chrome浏览器
	* 乐视X608：打开都是都是自己定义的WebviewActivity的WebView
* 总结国内的ROM厂商已经干掉了Chrome，所以使用这个库意义不大。

### 参考资料
*  <a target="_blank" href="https://developer.android.com/topic/libraries/support-library/features.html#custom-tabs">https://developer.android.com/topic/libraries/support-library/features.html#custom-tabs</a>
*  <a target="_blank" href="https://developer.chrome.com/multidevice/android/customtabs">https://developer.chrome.com/multidevice/android/customtabs</a>
*  <a target="_blank" href="https://github.com/GoogleChrome/custom-tabs-client">https://github.com/GoogleChrome/custom-tabs-client</a>

### 效果图
![DataBinding-art](https://raw.githubusercontent.com/why168/AndroidProjects/master/Art/CustomTabs-art.gif)

### 示例代码

#### CustomTabActivityHelpe.java

```java
/**
 * Opens the URL on a Custom Tab if possible. Otherwise fallsback to opening it on a WebView.
 *
 * @param activity         The host activity.
 * @param customTabsIntent a CustomTabsIntent to be used if Custom Tabs is available.
 * @param uri              the Uri to be opened.
 * @param fallback         a CustomTabFallback to be used if Custom Tabs is not available.
 */
public static void openCustomTab(Activity activity,
                                 CustomTabsIntent customTabsIntent,
                                 Uri uri,
                                 CustomTabFallback fallback) {
    String packageName = CustomTabsHelper.getPackageNameToUse(activity);

    //If we cant find a package name, it means theres no browser that supports
    //Chrome Custom Tabs installed. So, we fallback to the webview
    if (packageName == null) {
        if (fallback != null) {
            fallback.openUri(activity, uri);
        }
    } else {
        customTabsIntent.intent.setPackage(packageName);
        customTabsIntent.launchUrl(activity, uri);
    }
}
```

```java
CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

// 修改 ActionBar 的颜色
intentBuilder.setToolbarColor(color);

// 设置辅助工具栏颜色
intentBuilder.setSecondaryToolbarColor(color);

// 添加一个分享按钮
String shareLabel = getString(R.string.label_action_share);
Bitmap icon = BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_menu_share);
PendingIntent pendingIntent = createPendingIntent();
intentBuilder.setActionButton(icon, shareLabel, pendingIntent);

// 设置菜单
String menuItemTitle = getString(R.string.menu_item_title);
PendingIntent menuItemPendingIntent = createPendingIntent(ActionBroadcastReceiver.ACTION_MENU_ITEM);
intentBuilder.addMenuItem(menuItemTitle, menuItemPendingIntent);

// 设置默认
intentBuilder.addDefaultShareMenuItem();

// 是否显示网页标题
intentBuilder.setShowTitle(mShowTitleCheckBox.isChecked());

// 设置工具栏
String actionLabel = getString(R.string.label_action);
Bitmap icon = BitmapFactory.decodeResource(getResources(),android.R.drawable.ic_menu_share);
PendingIntent pendingIntent = createPendingIntent(ActionBroadcastReceiver.ACTION_TOOLBAR);
intentBuilder.addToolbarItem(TOOLBAR_ITEM_ID, icon, actionLabel, pendingIntent);
             
// 自定义关闭 Custom tabs 的图标
intentBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back));

// 自定义 Activity 转场 动画
intentBuilder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);

intentBuilder.setExitAnimations(this, android.R.anim.slide_in_left,android.R.anim.slide_out_right);

// 最后调用助手类 CustomTabActivityHelper 的 openCustomTab 函数来打开一个网址
CustomTabActivityHelper.openCustomTab(this, intentBuilder.build(), Uri.parse(url), new WebviewFallback());
```
