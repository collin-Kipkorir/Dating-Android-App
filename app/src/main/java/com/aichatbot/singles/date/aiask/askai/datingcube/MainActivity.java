package com.aichatbot.singles.date.aiask.askai.datingcube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.custom_button));
        }
        FirebaseApp.initializeApp(this);



    }

    public void goGender(View view) {
        Intent intent = new Intent(MainActivity.this, EmailActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent);
    }

    public void goDate(View view) {
        Intent intent = new Intent(MainActivity.this, DateActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent);
    }
}