package com.example.myapplication_mof;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    private ImageButton btnBack, btnCamera;
    private Button btnDone;
    private ImageView ivProfile;
    private EditText etNickname;

    private Uri selectedImageUri = null;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            ivProfile.setImageURI(selectedImageUri);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        btnBack = findViewById(R.id.btnBack);
        btnDone = findViewById(R.id.button4);
        ivProfile = findViewById(R.id.imageView2);
        btnCamera = findViewById(R.id.imageButton5);
        etNickname = findViewById(R.id.editTextText);

        etNickname.setText("기존닉네임");

        btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               intent.setType("image/*");
               galleryLauncher.launch(intent);
           }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedNickname = etNickname.getText().toString().trim();

                if (updatedNickname.isEmpty()) {
                    Toast.makeText(EditProfileActivity.this, "닉네임을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(EditProfileActivity.this, "프로필이 수정되었습니다.\n닉네임: " + updatedNickname, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
