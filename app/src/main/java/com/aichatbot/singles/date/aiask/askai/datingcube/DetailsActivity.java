package com.aichatbot.singles.date.aiask.askai.datingcube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class DetailsActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView ageTextView = findViewById(R.id.ageTextView);
        TextView distanceTextView = findViewById(R.id.textViewDistance);
        ImageView detailImageView = findViewById(R.id.detailImageView);

        TextView sexualityTextView = findViewById(R.id.textViewSexuality);
        TextView statusTextView = findViewById(R.id.textViewStatus);
        TextView genderTextView = findViewById(R.id.textViewGender);
        TextView locationTextView = findViewById(R.id.textViewLocation);

        Intent intent = getIntent();
        if (intent != null) {
            String userId = intent.getStringExtra("userId");
            String name = intent.getStringExtra("name");
            int age = intent.getIntExtra("age", 0);
            String distance = intent.getStringExtra("distance");
            String sexuality = intent.getStringExtra("sexuality");
            String status = intent.getStringExtra("status");
            String gender = intent.getStringExtra("searchGender");
            String location = intent.getStringExtra("location");
          
            // Set retrieved details in respective TextViews
            nameTextView.setText(name);
            ageTextView.setText(String.valueOf(age));
            distanceTextView.setText(distance);
            sexualityTextView.setText(sexuality);
            statusTextView.setText(status);
            genderTextView.setText(gender);
            locationTextView.setText(location);
            
            if (intent != null) {
                // Retrieve other user details...
                String imageUrl = intent.getStringExtra("imageUrl"); // Retrieve the image URL from intent extras

                // Load the image using Glide into the detailImageView
                Glide.with(this)
                        .load(imageUrl) // Use the retrieved image URL
                        .into(detailImageView);
            }

        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, DateActivity.class);
        startActivity(intent);
    }
}