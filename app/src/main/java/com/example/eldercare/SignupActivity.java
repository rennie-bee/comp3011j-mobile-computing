package com.example.eldercare;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

// This activity works for registering
public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private String realCode;
    private LinearLayout register_body;
    private EditText et_register_username;
    private EditText et_register_key1;
    private EditText et_register_key2;
    private EditText et_register_code;
    private ImageView code_image;
    private Button bt_register;
    private Button bt_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // put contents on layout activity_signup
        setContentView(R.layout.activity_signup);

        // initialize the variables to connect the java with xml
        register_body = findViewById(R.id.register_body);
        et_register_username = findViewById(R.id.et_register_username);
        et_register_key1 = findViewById(R.id.et_register_password1);
        et_register_key2 = findViewById(R.id.et_register_password2);
        et_register_code = findViewById(R.id.et_register_code);
        code_image = findViewById(R.id.iv_register_code);
        code_image.setOnClickListener(this);
        bt_register = findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(this);

        // Display the verifying code in an image style
        code_image.setImageBitmap(BMap.getInstance().createBitmap());
        realCode = BMap.getInstance().getCode().toLowerCase();
    }


    public void onClick(View view) {
        // Judge which clickable item is clicked
        switch (view.getId()) {
            // Back-to-Sign-in icon
            case R.id.bt_back:
                Intent intent_up_to_in = new Intent(this, SigninActivity.class);
                startActivity(intent_up_to_in);
                this.finish();
                break;
            // Refresh another code image
            case R.id.iv_register_code:
                code_image.setImageBitmap(BMap.getInstance().createBitmap());
                realCode = BMap.getInstance().getCode().toLowerCase();
                break;
            // Confirm registering button
            case R.id.bt_register:
                // attain user input and trim them for judge
                String username = et_register_username.getText().toString().trim();
                String password1 = et_register_key1.getText().toString().trim();
                String password2 = et_register_key2.getText().toString().trim();
                String imageCode = et_register_code.getText().toString().toLowerCase();

                // Check that user fills all information including username, 2 passwords, and imageCode.
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) &&
                        !TextUtils.isEmpty(password2) && !TextUtils.isEmpty(imageCode) ) {
                    // Check the imageCode filled by user is right
                    if (imageCode.equals(realCode)) {
                        // Check the 2 passwords match each other
                        if (password1.equals(password2)) {
                            // Check the username is not identical to anyone already in the database
                            List<User> users = LitePal.where("name = ?", username).find(User.class);
                            if (users.size() == 0) {
                                // Once the information passes all 4 verifications, we store it in the database
                                User u = new User();
                                u.setName(username);
                                u.setPassword(password1);
                                u.save();
                                NoteActivity.username = username;
                                NoteAddActivity.username = username;
                                // Direct to the main page
                                Intent intent_up_to_main = new Intent(this, MainActivity.class);
                                startActivity(intent_up_to_main);
                                this.finish();
                                Toast.makeText(this,  "Register success!", Toast.LENGTH_SHORT).show();
                            } else {
                                // The user name has been registered before so the user needs a new one.
                                Log.d(TAG, "Username is duplicate!");
                                Toast.makeText(this,  "Username cannot be duplicate!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Two passwords does not match each other
                            Toast.makeText(this, "Two password must match!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // The validating image code is not input correctly
                        Toast.makeText(this, "Validation code is wrong!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // The user does not fill all the necessary information
                    Toast.makeText(this, "You have to fill all the information", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}