package com.example.xydzjnq.hostapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.xydzjnq.hostapp.hook.HookHelper;
import com.example.xydzjnq.hostapp.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button t = new Button(this);
        t.setText("test button");

        setContentView(t);

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent t = new Intent();
                    t.setComponent(
                            new ComponentName("com.example.xydzjnq.plugin",
                                    "com.example.xydzjnq.plugin.MainActivity"));

                    startActivity(t);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            Utils.extractAssets(newBase, "plugin1.apk");

//            HookHelper.hookLoadedApkInActivityThread(
//                    getFileStreamPath("plugin1.apk"));

            HookHelper.hookAMN();
            HookHelper.hookActivityThread(
                    getFileStreamPath("plugin1.apk"));

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
