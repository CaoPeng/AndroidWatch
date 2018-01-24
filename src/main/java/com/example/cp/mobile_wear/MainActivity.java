package com.example.cp.mobile_wear;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cp.utils.NetworkUtils;
import com.cp.utils.NotificationUtils;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    private ImageButton mRightBtn;
    private ImageButton mLeftBtn;
    private ImageView mImg;


    private NotificationManager mManager;
    private NotificationUtils notificationUtils;

    private String requestPermiss[]= new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        mRightBtn = (ImageButton)findViewById(R.id.right);
        mLeftBtn = (ImageButton)findViewById(R.id.left);
        mImg = (ImageView)findViewById(R.id.imageView);

        requestPermissions(requestPermiss,2);

        mLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.hello_world,Toast.LENGTH_SHORT).show();
                buildNotification();
            }
        });

        mRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapFromNetwork(mImg);
            }
        });


        // Enables Always-on
        setAmbientEnabled();
    }

    public void buildNotification(){
        NotificationUtils notificationUtils = new NotificationUtils(this);
        notificationUtils.sendNotification("测试标题", "测试内容");
        return;
    }

    public void getBitmapFromNetwork(ImageView img){
        NetworkUtils mNetUtil = new NetworkUtils();
        mNetUtil.setImg(img);
    }
}
