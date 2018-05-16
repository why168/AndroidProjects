```kotlin

private fun initData(): List<String> {
      val arrayList = ArrayList<String>()

      // (手机外部)文件存储，卸载时不会删除               /storage/sdcard
      val absolutePath4 = Environment.getExternalStorageDirectory().path.toString()

      // (手机外部)文件存储中的公共目录，卸载时不会删除     /storage/sdcard/Download
      val absolutePath1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path.toString()

      // (手机外部)文件存储，卸载时会删除                 /storage/sdcard/Android/data/packageName/files/Download
      val absolutePath9 = getExternalFilesDir(android.os.Environment.DIRECTORY_DOWNLOADS).path.toString()

      // (手机外部)文件存储，卸载时会删除                 /storage/sdcard/Android/data/packageName/cache
      val absolutePath6 = externalCacheDir.path.toString()

      // (手机外部)文件存储，卸载时会删除                 /storage/sdcard/Pictures/EdwinFile
      val mScreenshotDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "EdwinFile")
      if (!mScreenshotDir.exists())
          mScreenshotDir.mkdirs()
      val absolutePath8 = mScreenshotDir.path.toString()

      // xxx       /data
      val absolutePath2 = Environment.getDataDirectory().path.toString()
      // xxx       /cache
      val absolutePath3 = Environment.getDownloadCacheDirectory().path.toString()
      // xxx       /system
      val absolutePath5 = Environment.getRootDirectory().path.toString()


      // (手机内部)文件存储，卸载时会删除        /data/data/packageName/files
      val absolutePath10 = filesDir.path.toString()
      // (手机内部)文件存储，卸载时会删除        /data/data/packageName/cache
      val absolutePath7 = cacheDir.path.toString()
//        deleteFile()

      // (手机内部)文件存储，卸载时会删除        /data/data/packageName/code_cache
      val absolutePath11 = codeCacheDir.path.toString()
      // (手机内部)文件存储，卸载时会删除        /data/data/packageName/no_backup
      val absolutePath12 = noBackupFilesDir.path.toString()

      // 获取当前APP代码路径               /data/app/io.github.why168.filestorageoperations-2/base.apk
      val absolutePath13 = packageCodePath.toString()
      // 获取当前APP资源路径               /data/app/io.github.why168.filestorageoperations-2/base.apk
      val absolutePath14 = packageResourcePath.toString()

      val absolutePath15 = StringBuilder()
      //列出所有的已创建的文件
      val files = fileList()
      files.forEach {
          absolutePath15.append(it)
          absolutePath15.append("\n")
      }

      // SD卡可读写的挂载状态值       mounted == Environment.MEDIA_MOUNTED
      val externalStorageState = Environment.getExternalStorageState().toString()

      // 设备的外存是否是可以拆卸 false,true
      val externalStorageRemovable = Environment.isExternalStorageRemovable()
      arrayList.add("(手机外部)文件存储")
      arrayList.add("getExternalStorageDirectory() = $absolutePath4")
      arrayList.add("getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) = $absolutePath1")
      arrayList.add("getExternalFilesDir(android.os.Environment.DIRECTORY_DOWNLOADS) = $absolutePath9")
      arrayList.add("externalCacheDir.path = $absolutePath6")
      arrayList.add("File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), \"EdwinFile\") path = $absolutePath8")
      arrayList.add("--------华丽分割线--------")
      arrayList.add("getDataDirectory() = $absolutePath2")
      arrayList.add("getDownloadCacheDirectory() = $absolutePath3")
      arrayList.add("getRootDirectory() = $absolutePath5")
      arrayList.add("--------华丽分割线--------")
      arrayList.add("(手机内部)文件存储")
      arrayList.add("filesDir.path = $absolutePath10")
      arrayList.add("cacheDir.path = $absolutePath7")
      arrayList.add("codeCacheDir.path = $absolutePath11")
      arrayList.add("noBackupFilesDir.path = $absolutePath12")
      arrayList.add("--------华丽分割线--------")
      arrayList.add("packageCodePath.path = $absolutePath13")
      arrayList.add("packageResourcePath.path = $absolutePath14")
      arrayList.add("fileList() = $absolutePath15")

      arrayList.add("getExternalStorageState() = $externalStorageState")
      arrayList.add("isExternalStorageRemovable() = $externalStorageRemovable")

      return arrayList
  }
```