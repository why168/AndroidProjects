package io.github.why168.filestorageoperations

import android.content.Context
import android.media.audiofx.EnvironmentalReverb
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.content.ContextCompat
import android.support.v4.os.EnvironmentCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import android.widget.Toast
import android.content.Context.MODE_PRIVATE
import android.text.method.ScrollingMovementMethod
import android.util.Log
import java.io.FileOutputStream


/**
 *
 * https://developer.android.com/training/data-storage/files#kotlin
 *
 * https://developer.android.com/guide/topics/data/data-storage
 *
 * 存储操作
 *
 * @author Edwin.Wu edwin.wu05@gmail.com
 * @version 2018/5/16 下午9:43
 * @since JDK1.8
 */
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "Edwin"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }


    private fun initView() {
        val sb = StringBuilder()
        for (str in initData()) {

            sb.append(str.replace("io.github.why168.filestorageoperations","packageName"))
            sb.append("\n\n")
        }

        Log.e(TAG, sb.toString())
        mainPathTextView.text = sb.toString()
        mainPathTextView.movementMethod = ScrollingMovementMethod.getInstance();
    }

    /**
     *
    手机内部文件存储
         1). 存储的位置
              /data/data/packageName/files/
         2). 特点
              a. 保存较大点的数据(大的文本或图片等)
              b. 应用卸载时会自动删除
    手机外部(sd卡)存储
         1). 存储的位置
              a. /storage/sdcard/Android/data/packageName/files/ ①
              b. /storage/sdcard/自定义名称文件夹/ ②
         2). 特点
              a.  保存较大点的数据(大的文本或图片等)
              b. 应用卸载时①会自动删除, 但②不会

     */

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

    //向内部存储写文件
    private fun createPrivateFile() {
        val filename = "myfile.txt"
        val string = "Hello world!"
        val outputStream: FileOutputStream
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(string.toByteArray())
            outputStream.close()
            Toast.makeText(this@MainActivity, "文件位置在" + filesDir.absolutePath, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this@MainActivity, "出错误了哦", Toast.LENGTH_SHORT).show()
        }


        //删除文件，能创建就要能够删除，当然也会提供了删除文件的接口，它也非常简单，只需要提供文件名
        if (deleteFile(filename)) {
            println("delete file $filename sucessfully")
        } else {
            println("failed to deletefile $filename")
        }

    }

    // 检查外部存储器是否可用来读取和写入
    fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == state
    }

    // 检查外部存储器是否可以读取
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    fun getAlbumStorageDir(albumName: String): File {
        // 获取用户公共图片目录的目录。
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName)
        if (!file.mkdirs()) {
            println("Directory not created")
        }
        return file
    }

    fun getAlbumStorageDir(context: Context, albumName: String): File {
        // 获取应用程序的私有图片目录的目录。
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName)
        if (!file.mkdirs()) {
            println("Directory not created")
        }
        return file
    }
}
