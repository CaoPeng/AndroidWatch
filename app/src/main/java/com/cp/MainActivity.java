package com.cp;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cp.jni.JavaNative;

import cp.com.aidl.IMyAidlInterface;
import cp.com.aidl.MyAIDLService;

//import cp.com.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    //private Binder mService = null;
    private IMyAidlInterface mAIDLService = null;
    private static int count = 0;
    private TextView aidlTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(JavaNative.stringFromJNI());
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MyAIDLService.class);
        bindService(intent,conn,Service.BIND_AUTO_CREATE);

        Button getStatus = (Button)findViewById(R.id.button);
        aidlTextView = (TextView)findViewById(R.id.aidlTest);
        getStatus.setOnClickListener(this);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAIDLService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAIDLService = null;
        }
    };

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                updateTextView();
                break;
            default:
                break;
        }
    }
    private void updateTextView(){
        String str = null;
        boolean status = false;
        try {
            str = mAIDLService.getStr();
            status = mAIDLService.setStatus(count);
            count++;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        aidlTextView.setText(str+status);
    }
}
