package com.example.googlepaysplashscreen;

import android.content.Intent;
import android.os.Bundle;
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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Registration_Activity extends AppCompatActivity {

    EditText etName,etEmail,etPassword,etUsername,etPhone;
    Button btnRegister;
    TextView tvLogin;
    Animation scale;
    boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setContentView(R.layout.activity_registration);

        etName = findViewById(R.id.etRegisterName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etUsername = findViewById(R.id.etRegisterUsername);
        etPhone = findViewById(R.id.etRegisterPhone);
        btnRegister = findViewById(R.id.btnRegisterBtn);
        tvLogin = findViewById(R.id.tvRegesterlogin);
        scale = AnimationUtils.loadAnimation(this, R.anim.scale);


        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration_Activity.this, loginPage.class);
                startActivity(intent);
                finish();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.startAnimation(scale);

                String name,phone,username,password,email;
                name = etName.getText().toString();
                phone = etPhone.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                email = etEmail.getText().toString();

                isValidData(name,phone,username,password,email);

            }
        });

        etPassword.setOnTouchListener((view, motionEvent) -> {
            isPasswordVisible = false;
            final int drable_Right = 2;
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (motionEvent.getRawX()>=(etPassword.getRight()- etPassword.getCompoundDrawables()[drable_Right].getBounds().width())){
                    togglePasswordVisibility();
                    return true;
                }
            }
            return false;
        });


    }

    private void isValidData(String name, String phone, String username, String password, String email) {
        boolean isNameValid = true;
        if (name.isEmpty()){
            isNameValid = false;
            etName.setError("Field cannot be empty");
        }
         if (phone.isEmpty()){
             isNameValid = false;
            etPhone.setError("Field cannot be empty");
        }
        else if(phone.length() != 10){
             isNameValid = false;
            etPhone.setError("Phone Number should be 10 digits!");
        }

        if (username.isEmpty()){
            isNameValid = false;
            etUsername.setError("Field cannot be empty");
        }

        else if (username.length()<4){
            isNameValid = false;
            etUsername.setError("Username should be greater than 4 characters");
        }
        else if (username.matches(".*[ ].*")){
            isNameValid = false;
            etUsername.setError("White space is not allowed in a username");
        }
        else if (username.matches(".*[!,@,#,$,%,^,&,*,(,),-].*")){
            isNameValid = false;
            etUsername.setError("Username should not contain Special symbol expect underscore (_)");
        }
        if (email.isEmpty()){
            isNameValid = false;
            etEmail.setError("Field cannot be empty");

        }
        else if (!email.matches(".*[@].*")){
            isNameValid = false;
            etEmail.setError("Enter a valid email");
        }
        else if (!email.endsWith(".com")){
            isNameValid = false;
            etEmail.setError("Enter a valid email");
        }

        if (password.isEmpty()){
            isNameValid = false;
            etPassword.setError("Field cannot be empty");
        }

        else if (password.length()<8) {
            isNameValid = false;
            etPassword.setError("Password should be greater than 8 character");
        }
        else if (!password.matches(".*[A-Z].*")){
            isNameValid = false;
            etPassword.setError("Password should contain at least one uppercase character");
        }
        else if(!password.matches(".*[a-z].*")){
            isNameValid = false;
            etPassword.setError("Password should contain at least one lowercase character");
        }
        else if (!password.matches(".*[!,@,#,$,%,^,&,*,(,),),-,=,/].*")){
            isNameValid = false;
            etPassword.setError("Password should contain at least one special symbol");
        }
        else if (!password.matches(".*[0-9].*")){
            isNameValid = false;
            etPassword.setError("Password should contain at least one digit");
        }

        if (isNameValid){
            Toast.makeText(getApplicationContext(),"Registered Successfully!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Registration_Activity.this,Home.class);
            startActivity(intent);
            finish();
        }



    }





    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.eye_off, 0);
        } else {
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.eye, 0);
        }
        isPasswordVisible = !isPasswordVisible;
        etPassword.setSelection(etPassword.getText().length());
    }
}