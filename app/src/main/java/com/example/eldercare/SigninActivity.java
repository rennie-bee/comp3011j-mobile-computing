package com.example.eldercare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// This activity works for logging in
public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_login_username;
    private EditText et_login_password;
    private TextView tv_register;
    private Button bt_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LitePal.initialize(this);
        NoteActivity.username = null;
        NoteAddActivity.username = null;
        // put contents on layout activity_signin
        setContentView(R.layout.activity_signin);

        // initialize the variables to connect the java with xml
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

    }

    // The Sign in Page has two clickable items, we need to distinguish between them
    public void onClick(View view) {
        switch (view.getId()) {
            // if the user has no account available, he/she goes to the register page
            case R.id.tv_register:
                startActivity(new Intent(this, SignupActivity.class));
                finish();
                break;
            // the user has an account and log in
            case R.id.bt_login:
                boolean match = false;
                // Deal with the input information for check
                String username = et_login_username.getText().toString().trim();
                String password = et_login_password.getText().toString().trim();
                // / Check that user fills all information including username, and password
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    List<User> user_list = LitePal.where("name = ?", username).find(User.class);
                    for (int i = 0; i < user_list.size(); i++) {
                        User user = user_list.get(i);
                        // Check if the username already in the database, the match can be set as true
                        // Since we know that this account name was registered before
                        // Also, it should have matched password
                        if (username.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            // Set username to current user's mame
                            NoteActivity.username = username;
                            NoteAddActivity.username = username;
                        }
                    }
                    // The user passes the verification and being sent to the main page
                    if (match) {
                        Toast.makeText(this, "Sign in succeed", Toast.LENGTH_SHORT).show();
                        Intent intent_in_to_up = new Intent(this, MainActivity.class);
                        startActivity(intent_in_to_up);
                        this.finish();
                    // The information did not pass the verification so, the user should try again
                    } else {
                        Toast.makeText(this, "Username or password is not validated. " +
                                "Please enter again", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // The user does not fill all the necessary information
                    Toast.makeText(this, "Please fill your username or password", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
