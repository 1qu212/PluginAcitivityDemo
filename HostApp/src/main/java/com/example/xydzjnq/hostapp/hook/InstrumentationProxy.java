package com.example.xydzjnq.hostapp.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import com.example.xydzjnq.hostapp.util.Utils;

import java.io.File;

public class InstrumentationProxy extends Instrumentation {
    Instrumentation mInstrumentation;
    File mApkFile;

    public InstrumentationProxy(Instrumentation instrumentation, File apkFile) {
        mInstrumentation = instrumentation;
        mApkFile = apkFile;
    }

    public Activity newActivity(ClassLoader cl, String className,
                                Intent intent)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {

        // 把替身恢复成真身
        Intent rawIntent = intent.getParcelableExtra(HookHelper.EXTRA_TARGET_INTENT);
        if (rawIntent == null) {
            return mInstrumentation.newActivity(cl, className, intent);
        }

        String newClassName = rawIntent.getComponent().getClassName();
        String odexPath = Utils.getPluginOptDexDir(rawIntent.getComponent().getPackageName()).getPath();
        String libDir = Utils.getPluginLibDir(rawIntent.getComponent().getPackageName()).getPath();
        ClassLoader classLoader = new CustomClassLoader(mApkFile.getPath(), odexPath, libDir, ClassLoader.getSystemClassLoader());
        return mInstrumentation.newActivity(classLoader, newClassName, rawIntent);
    }
}
