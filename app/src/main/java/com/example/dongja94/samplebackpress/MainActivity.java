package com.example.dongja94.samplebackpress;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isBackPressed = false;
    public static final int MESSAGE_BACK_KEY_TIMEOUT = 0;
    public static final int BACK_KEY_TIME = 2000;

    Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_BACK_KEY_TIMEOUT :
                    isBackPressed = false;
                    return true;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (!isBackPressed) {
            Toast.makeText(this, "one more back press", Toast.LENGTH_SHORT).show();
            isBackPressed = true;
            mHandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, BACK_KEY_TIME);
        } else {
            mHandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }
}
