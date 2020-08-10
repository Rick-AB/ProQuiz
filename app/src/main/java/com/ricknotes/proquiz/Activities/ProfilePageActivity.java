package com.ricknotes.proquiz.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ricknotes.proquiz.Model.Constants;
import com.ricknotes.proquiz.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePageActivity extends AppCompatActivity {
    private static final String TAG = "ProfilePageActivity";
    private ImageButton mBackBtn;

    private CircleImageView mProfileImage;
    private EditText mUserName, mEmail, mPhoneNumber;
    private Button mSave;
    private CheckBox mNotifications, mSound;
    private Uri mSelectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        initViews();

        updateViews();
    }

    private void updateViews() {
        SharedPreferences preferences = getSharedPreferences(Constants.MY__PREFS, MODE_PRIVATE);

        String userName = preferences.getString(Constants.USER_NAME,"");
        String email = preferences.getString(Constants.EMAIL, "");
        String number = preferences.getString(Constants.NUMBER, "");

        mUserName.setText(userName);
        mEmail.setText(email);
        mPhoneNumber.setText(number);
    }

    private void initViews() {
        mBackBtn = findViewById(R.id.profile_page_back_btn);
        mProfileImage = findViewById(R.id.profile_page_image);
        mUserName = findViewById(R.id.profile_page_username);
        mEmail = findViewById(R.id.profile_page_email);
        mPhoneNumber = findViewById(R.id.profile_page_phone);
        mSave = findViewById(R.id.profile_page_save_button);
        mNotifications = findViewById(R.id.profile_page_notification_checkBox);
        mSound = findViewById(R.id.profile_page_sound_checkBox);

    }

    public void saveClick(View view) {
        save();
        Animatoo.animateShrink(this);
        finish();
    }

    public void selectProfilePic(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK);
        pickIntent.setDataAndType( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        Intent chooser = Intent.createChooser(intent, "Select image");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooser, Constants.PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == Constants.PICK_IMAGE && data != null){
                mSelectedImage = data.getData();

                Glide.with(this)
                        .load(mSelectedImage)
                        .into(mProfileImage);

                SharedPreferences.Editor editor = getSharedPreferences(Constants.MY__PREFS,
                        MODE_PRIVATE).edit();
                editor.putString(Constants.IMAGE_URL, String.valueOf(mSelectedImage));
                editor.apply();

                Log.d(TAG, "onActivityResult: " + mSelectedImage);
               // mProfileImage.setImageBitmap(BitmapFactory.decodeFile(imageDecodableString));
            }else {
                Toast.makeText(getApplicationContext(), "No image selected", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }


    }
    private void save(){
        String name = mUserName.getText().toString();
        String email = mEmail.getText().toString();
        String number = mPhoneNumber.getText().toString();

        SharedPreferences.Editor editor =
                getSharedPreferences(Constants.MY__PREFS, MODE_PRIVATE).edit();

        editor.putString(Constants.USER_NAME, name);
        editor.putString(Constants.EMAIL, email);
        editor.putString(Constants.NUMBER, number);

        editor.apply();
    }

    public void backClick(View view) {
        Animatoo.animateShrink(this);
        finish();
    }

    @Override
    public void onBackPressed() {
        save();
        Animatoo.animateShrink(this);
        super.onBackPressed();
    }
}