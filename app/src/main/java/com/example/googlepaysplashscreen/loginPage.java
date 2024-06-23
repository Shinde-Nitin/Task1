package com.example.googlepaysplashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginPage extends AppCompatActivity {
    private EditText username, password;
    private TextView newUserSignUp;
    private Button logIn;
    private Animation scale;
    boolean isPasswordVisible=false;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        preferences = PreferenceManager.getDefaultSharedPreferences(loginPage.this);
        editor = preferences.edit();

        if (preferences.getBoolean("isLogin",false)){
            Intent i  = new Intent(this,Home.class);
            startActivity(i);
            finish();
        }

        scale = AnimationUtils.loadAnimation(this, R.anim.scale);

        username = findViewById(R.id.etloginUsername);
        password = findViewById(R.id.etloginPassword);
        logIn = findViewById(R.id.btnloginBtn);
        newUserSignUp = findViewById(R.id.tvloginNewUser);



        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int drable_Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getRawX()>=(password.getRight()- password.getCompoundDrawables()[drable_Right].getBounds().width())){
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });




        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn.setAnimation(scale);
                logIn.startAnimation(scale);

                String myUsername = username.getText().toString();
                String myPassword = password.getText().toString();
                boolean valid = true;

                if (myUsername.isEmpty()) {
                    valid = false;
                    username.setError("Please enter a username");
                } else if (myUsername.length() <= 8) {
                    valid = false;
                    username.setError("Username should be greater than or equal to 8 characters");
                }
                else if (myUsername.matches(".*[.*[@,!,$,£,%,^,&,*,(,),#,/,=,+,-,.,¬].*,€].*")){
                    valid = false;
                    username.setError("Splecial Symbol not allowed expect _ (unserscore)");
                }
                else if (myUsername.matches(".*[ ].*")){
                    valid = false;
                    username.setError("White space is not allowed in username");
                }

                if (myPassword.isEmpty()) {
                    valid = false;
                    password.setError("Please enter a Password");
                } else if (myPassword.length() <= 8) {
                    valid = false;
                    password.setError("Password should be greater than or equal to 8 characters");
                } else if (!myPassword.matches(".*[A-Z].*")) {
                    valid = false;
                    password.setError("Password should contain at least 1 Uppercase character");
                }
                else if (!myPassword.matches(".*[a-z].*")){
                    valid = false;
                    password.setError("Password should contain at least 1 Lowercase character");
                }
                else if (!myPassword.matches(".*[@,!,$,£,%,^,&,*,(,),#,/,=,+,-,.,¬].*")){
                    valid = false;
                    password.setError("Password should contain special symbol");
                }
                else if (!myPassword.matches(".*[0-9].*")){
                    valid = false;
                    password.setError("Password should contain at least 1 numeric value");
                }

                if (valid) {
                    Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(loginPage.this, Home.class);

                            editor.putBoolean("isLogin",true).commit();
                            editor.putBoolean("isFirstTime",true).commit();
                            startActivity(intent);
                            finish();
                        }
                    }, 500);



                }
            }
        });


    newUserSignUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(loginPage.this, Registration_Activity.class);
            startActivity(intent);

        }
    });


    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.eye_off, 0);
        } else {
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.eye, 0);
        }
        isPasswordVisible = !isPasswordVisible;
        password.setSelection(password.getText().length());
    }



}
