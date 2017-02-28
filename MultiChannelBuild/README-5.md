## 5.MultiChannelBuild

### 介绍
* Gradle多渠道打包
* Gradle动态打包（包名，Logo，APP名称）
* Gradle对生成APK名称重命名
* Gradle签名配置
* Gradle支持lambda
* Gralde编译JDK1.8
* Gradle存储库maven仓库设置

### Gradle常见命令

* ./gradlew clean：清理删除build文件夹
* ./gradlew build：检查依赖并编译打包debug和release版本
* ./gradlew -v：版本号
* ./gradlew assembleRelease：打包所有渠道release版本
* ./gradlew assembleBaiduRelease：打某个渠道的release版本
* ./gradlew assembleDebug：打包所有渠道debug版本
* ./gradlew assembleBaiduDebug：打某个渠道的debug版本
* ./gradlew installRelease：打包并安装Release模式包
	* ./gradlew installBaiduRelease：同上
* ./gradlew uninstallRelease：卸载Release模式包
	* ./gradlew uninstallBaiduRelease：同上

#### Gradle注意事项

* linux下使用./gradlew
* windows下使用gradlew
* 打包后的apk文件在app–>build–>outputs—>apk
* 使用gradlew时可能出现没有找到该命令,需要chmod 755 gradlew


### 示例代码
```groovy
apply plugin: 'com.android.application'

android {
    /**
     * apk签名脚本
     */
    signingConfigs {
        config {
            keyAlias 'Edwin'
            keyPassword 'Edwin666666'
            storeFile file('./AppKeyStore.jks')
            storePassword '666666'
            v2SigningEnabled false //Android 7.0 中新增了 APK Signature Scheme v2 签名方式
        }
    }
    /**
     * 自定义包名
     */
    def pageName = "com.github.why168.multichannelbuild"
    /**
     * 自定义名字
     */
    def appName = "MultiChannelBuild"
    /**
     * 自定义Logo
     */
    def appLogo = "@mipmap/ic_launcher"

    compileSdkVersion 25
    buildToolsVersion "25.0.2"
    defaultConfig {
        applicationId pageName
        minSdkVersion 11
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"

        /**
         * 支持lambda
         */
        jackOptions {
            enabled true
        }

        /**
         * 类似下面productFlavors
         */
        manifestPlaceholders = [
                APP_ID  : "APP_ID111111",
                APP_KEY : "APP_KEY222222",
                APP_NAME: appName,
                APP_LOGO: appLogo
        ]

        /**
         * Native Development Kit
         */
        ndk {
            abiFilters 'armeabi', 'armeabi-v7a', 'arm64-v8a', 'x86', 'mips'
        }
    }
    buildTypes {
        debug {
            debuggable true
            buildConfigField "boolean", "LOG_DEBUG", "true"
            minifyEnabled false   //混淆
            shrinkResources false //去除无效的资源文件
            zipAlignEnabled false //Zipalign优化
            signingConfig signingConfigs.config
        }
        release {
            debuggable false
            buildConfigField "boolean", "LOG_DEBUG", "false"
            minifyEnabled true
            shrinkResources false
            zipAlignEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.config
        }
    }
    /**
     * 源设置
     */
    sourceSets {
        main {
            jniLibs.srcDirs = ['libs']
        }
    }
    /**
     * 多渠道配置，下面的关键字'CHANNEL_NAME'与AndroidManifest里面必须一致。
     */
    productFlavors {
        baidu {//百度市场
            manifestPlaceholders = [CHANNEL_NAME: 500001]
        }
        yingyongbai {//腾讯应用市场
            manifestPlaceholders = [CHANNEL_NAME: 500002];
        }
        xiaomi {//小米应用市场
            manifestPlaceholders = [CHANNEL_NAME: 500003];
        }
        store360 {//360商店
            manifestPlaceholders = [CHANNEL_NAME: 500004];
        }
        anzhi {//安智市场
            manifestPlaceholders = [CHANNEL_NAME: 500005];
        }
    }
    /**
     * apk名字自定义
     */
    android.applicationVariants.all { variant ->
        variant.outputs.each { output ->
            def outputFile = output.outputFile
            def releaseTime = new Date().format("yyyy-MM-dd HH:MM", TimeZone.getTimeZone("UTC"))
            def fileName = outputFile.name.replace(".apk", "-V" + defaultConfig.versionName + "_" + releaseTime + ".apk")
            output.outputFile = new File(outputFile.parent, fileName)

        }
    }
    /**
     * JDK1.8编译选项
     */
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    /**
     * 存储库maven仓库设置
     */
    repositories {
        flatDir {
            dirs 'libs'
        }
        mavenCentral()
        jcenter()
        maven { url "https://jitpack.io" }
    }
    /**
     * 移除lint检测的error
     */
    lintOptions {
        checkReleaseBuilds false
        abortOnError false
    }

    aaptOptions {
        cruncherEnabled = false
        useNewCruncher = false
    }
}
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    compile 'com.android.support:appcompat-v7:25.1.0'
}
```