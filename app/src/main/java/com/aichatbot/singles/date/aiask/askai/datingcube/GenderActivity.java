package com.aichatbot.singles.date.aiask.askai.datingcube;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicBoolean;

public class GenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greys));
        }

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);

        cardView2.setCardBackgroundColor(getResources().getColor(R.color.grey));
        cardView1.setCardBackgroundColor(getResources().getColor(R.color.custom_button));
        button2.setBackgroundColor(getResources().getColor(R.color.grey));
        
        button1.setOnClickListener(v -> {
            cardView2.setCardBackgroundColor(getResources().getColor(R.color.grey));
            cardView1.setCardBackgroundColor(getResources().getColor(R.color.custom_button));

            button1.setBackgroundColor(getResources().getColor(R.color.custom_button)); // Change background color when clicked
            button2.setBackgroundColor(getResources().getColor(R.color.grey)); // Reset background color
        });

        button2.setOnClickListener(v -> {
            cardView2.setCardBackgroundColor(getResources().getColor(R.color.custom_button));
            cardView1.setCardBackgroundColor(getResources().getColor(R.color.grey));

            button2.setBackgroundColor(getResources().getColor(R.color.custom_button)); // Change background color when clicked
            button1.setBackgroundColor(getResources().getColor(R.color.grey)); 
        });

    }
    

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent);
    }
}