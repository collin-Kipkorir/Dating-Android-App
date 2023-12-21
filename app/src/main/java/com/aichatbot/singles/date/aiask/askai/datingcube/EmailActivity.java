package com.aichatbot.singles.date.aiask.askai.datingcube;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class EmailActivity extends AppCompatActivity {
    private ImageView profileImage;
    private EditText etName, etAge, etLocation, etAbout, etStatus, etBodyType, etSexuality;
    private Spinner spinnerGender, spinnerSearchGender;
    private static final int REQUEST_IMAGE = 100;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.greys));
        }
// In your Application class or starting activity's onCreate()
        FirebaseApp.initializeApp(this);

        // Initialize views
        profileImage = findViewById(R.id.profileImage);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etLocation = findViewById(R.id.etLocation);
        etAbout = findViewById(R.id.etAbout);
        etStatus = findViewById(R.id.etStatus);
        etBodyType = findViewById(R.id.etBodyType);
        etSexuality = findViewById(R.id.etSexuality);
        spinnerGender = findViewById(R.id.spinnerGender);
        spinnerSearchGender = findViewById(R.id.spinnerSearchGender);
    
        // Set up profile image click listener
        profileImage.setOnClickListener(v -> {
            
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE);
        });

        Spinner genderSpinner = findViewById(R.id.spinnerGender);
        Spinner searchGenderSpinner = findViewById(R.id.spinnerSearchGender);

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_array, // Create an array resource in strings.xml containing Male and Female
                android.R.layout.simple_spinner_item
        );
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        searchGenderSpinner.setAdapter(genderAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImage.setImageURI(imageUri);
        }
    }
    
    public void openDate(View view) {
        // Get user inputs from EditTexts, Spinners, and profile image
        String name = etName.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String about = etAbout.getText().toString().trim();
        String status = etStatus.getText().toString().trim();
        String bodyType = etBodyType.getText().toString().trim();
        String sexuality = etSexuality.getText().toString().trim();
        String gender = spinnerGender.getSelectedItem().toString();
        String searchGender = spinnerSearchGender.getSelectedItem().toString();

        // Validate inputs if required
        if (name.isEmpty() || age.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ensure an image is selected
        if (imageUri == null) {
            Toast.makeText(this, "Please select a profile image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a reference to Firebase Storage where the image will be stored
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("profile_images/" + UUID.randomUUID().toString());

        // Upload the image to Firebase Storage
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // Image uploaded successfully, get the download URL
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        String imageUrl = uri.toString(); // Get the image download URL
                        // Create a User object with the download URL
                        User user = new User("", name, Integer.parseInt(age), gender, location,
                                about, status, bodyType, sexuality, searchGender, imageUrl);

                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                        String userId = usersRef.push().getKey(); // Generates a unique ID

                        usersRef.child(userId).setValue(user)
                                .addOnSuccessListener(aVoid -> {
                                    // Registration successful
                                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(this, DateActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    // Registration failed
                                    Toast.makeText(this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    });
                })
                .addOnFailureListener(e -> {
                    // Handle image upload failure
                    Toast.makeText(this, "Image upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Print the stack trace for debugging purposes
                });
    }

}