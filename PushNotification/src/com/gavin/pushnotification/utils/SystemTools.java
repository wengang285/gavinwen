
package com.gavin.pushnotification.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

public class SystemTools {
    /**
     * 系统包名
     */
    static final String SYSTEM_PACKAGE = "com.cloverstudio.nzsj.app.ui";

    /**
     * 将文件拷贝到sd卡中
     */
    public static void copyFileToSDCard(String path, String fileName, int raw_resource_id,
            Context context) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            } else {
            }

            String databaseFilename = path + fileName;
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();
            if (!(new File(databaseFilename)).exists()) {
                System.out.println("copy database file");
                InputStream is = context.getResources().openRawResource(raw_resource_id);
                FileOutputStream fos = new FileOutputStream(databaseFilename);
                byte[] buffer = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断数据库文件是否存�?     * 
     * @param path
     * @param fileName
     * @return
     */
    public static boolean checkDbFileIsExist(String path, String fileName) {
        String temp = path + fileName;
        File file = new File(temp);
        return file.exists();
    }

    /**
     * 删除文件
     * 
     * @param path
     * @param fileName
     */
    public static void deleteFile(String path, String fileName) {
        try {
            String filePath = path + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            } else {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示Tosat提示
     * 
     * @param activity
     * @param msg
     */
    public static void showTosat(Activity activity, String msg) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showToast(Context context, String msg) {
        try {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回系统的当前时间，样式：yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getSystemNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 返回系统的当前时间，样式：yyyy-MM-dd
     * 
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getSystemNowDateNoDetil() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 获取ProgressDialog
     * 
     * @param activity
     * @param title
     * @param msg
     * @return
     */
    public static ProgressDialog getProgressDialog(Activity activity, String title, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        if (title != null && !title.equals("")) {
            progressDialog.setTitle(title);
        }
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    /**
     * 获取系统当前版本信息
     * 
     * @param context
     */
    public static PackageInfo getSystemVersion(Context context) {
        PackageInfo packageInfo = null;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(SYSTEM_PACKAGE, 0);
            if (info != null) {
                packageInfo = info;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;

    }

    /**
     * 按照给定的时间让线程睡眠
     * 
     * @param time
     */
    public static void threadSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private static PreferencesUtils preferencesUtils;

    /**
     * 获取图片文件存放位置
     * 
     * @param context
     * @return
     */
    public static String getImageFilePath(Context context) {
        String path = getExternalCacheDir(context) + "img/";
        File location = new File(path);
        if (!location.exists()) {
            location.mkdirs();
        }
        return path;
    }

    @SuppressLint("NewApi")
    private static String getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            return context.getExternalCacheDir().getPath() + File.separator;
        }

        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return Environment.getExternalStorageDirectory().getPath() + cacheDir;
    }

    private static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }
}
