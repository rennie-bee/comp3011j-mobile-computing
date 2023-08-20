package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

// This activity works for resetting password
public class ResetPasswordActivity extends AppCompatActivity {
    private EditText et_reset_username;
    private EditText et_reset_password1;
    private EditText et_reset_password2;
    private EditText et_reset_password3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // put contents on layout activity_reset_password
        setContentView(R.layout.activity_reset_password);

        et_reset_username = findViewById(R.id.et_reset_username);
        et_reset_password1 = findViewById(R.id.et_reset_password1);
        et_reset_password2 = findViewById(R.id.et_reset_password2);
        et_reset_password3 = findViewById(R.id.et_reset_password3);
    }

    // The Sign in Page has two clickable items, we need to distinguish between them
    public void onClick(View view) {
        switch (view.getId()) {
            // user uses this button to get back
            case R.id.bt_reset_back:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            // the user wants to reset the password
            case R.id.bt_reset_confirm:
                boolean match = false;
                // Deal with the input information for check
                String username = et_reset_username.getText().toString().trim();
                String password1 = et_reset_password1.getText().toString().trim();
                String password2 = et_reset_password2.getText().toString().trim();
                String password3 = et_reset_password3.getText().toString().trim();
                // Check that user fills all information including username and 3 passwords.
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1)
                        && !TextUtils.isEmpty(password2) && !TextUtils.isEmpty(password3)) {
                    User target_user = null;
                    List<User> user_list = LitePal.where("name = ?", username).find(User.class);
                    for (int i = 0; i < user_list.size(); i++) {
                        User user = user_list.get(i);
                        // Check if the username already in the database, the match can be set as true
                        // Since we know that this account name was registered before
                        // Also, it should have matched password
                        if (username.equals(user.getName()) && password1.equals(user.getPassword())) {
                            match = true;
                            // Remember this user for resetting password
                            target_user = user;
                        }
                    }
                    // The user passes the matching verification and comes to next step
                    if (match) {
                        // 2 new passwords should equal to each other
                        if (password2.equals(password3)) {
                            Toast.makeText(this, "Reset succeed", Toast.LENGTH_SHORT).show();
                            // save the new password for user
                            target_user.setPassword(password2);
                            target_user.save();
                        } else {
                            // 2 new passwords do not equal to each other
                            Toast.makeText(this, "2 new passwords should match", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // The information did not pass the verification so, the user should try again
                        Toast.makeText(this, "Username or old password is not validated. " +
                                "Please enter again", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // The user does not fill all the necessary information
                    Toast.makeText(this, "Please fill all the information!", Toast.LENGTH_SHORT).show();
                }
        }
    }
}