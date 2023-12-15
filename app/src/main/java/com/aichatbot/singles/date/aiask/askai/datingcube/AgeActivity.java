package com.aichatbot.singles.date.aiask.askai.datingcube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

public class AgeActivity extends AppCompatActivity {
    private int selectedAge = 18; // Initialize with a default value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greys));
        }

        NumberPicker agePicker = findViewById(R.id.agePicker);
        agePicker.setMinValue(18);
        agePicker.setMaxValue(99);

        for (int i = 0; i < agePicker.getChildCount(); i++) {
            View child = agePicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = agePicker.getClass().getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);

                    int textColor = ContextCompat.getColor(this, R.color.custom_button);
                    int wheelColor = ContextCompat.getColor(this, R.color.grey);

                    if (child instanceof EditText) {
                        ((EditText) child).setTextColor(textColor);
                    }

                    ((Paint) selectorWheelPaintField.get(agePicker)).setColor(wheelColor);

                    agePicker.invalidate();
                } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }

        agePicker.setValue(18);

        agePicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            // Handle the selected age (newVal)
            selectedAge = newVal;
        });
    }

    public void goEmail(View view) {
        Intent intent = new Intent(this, EmailActivity.class);
        startActivity(intent);
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, GenderActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(intent);
    }
}
