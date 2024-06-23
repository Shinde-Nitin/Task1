package com.example.googlepaysplashscreen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {



    boolean doubleClicked = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Animation fadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_home);

        setTitle("SikhoHere");

        fadein = AnimationUtils.loadAnimation(this,R.anim.animation);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (preferences.getBoolean("isFirstTime",false)){

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    welcome();
                    editor.putBoolean("isFirstTime",false).commit();
                }
            },1000);

        }
    }

    private void welcome() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("SikhoHere");
        ad.setMessage("Welcome, Nitin! Thank you for choosing us. Let's get you set up.");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuNotif){
            Toast.makeText(Home.this,"Don't have an Notification",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menuCart){
            Toast.makeText(Home.this,"Don't have any courses in cart",Toast.LENGTH_SHORT).show();

        }else if (item.getItemId() == R.id.menuAccount){
            Toast.makeText(Home.this,"Account page opening",Toast.LENGTH_SHORT).show();

        }else  if (item.getItemId() == R.id.menuSettings){
            Toast.makeText(Home.this,"Settings opening",Toast.LENGTH_SHORT).show();

        } else if (item.getItemId()== R.id.menuHelp) {
            Toast.makeText(Home.this,"Help Page opening",Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() ==R.id.menuLogOut) {
            logout();
        }

        return true;
    }

    public void logout(){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("SikhoHere");
        ad.setMessage("Are you sure you want to log out?");
        ad.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        ad.setNegativeButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Home.this,loginPage.class);
                startActivity(intent);
                finish();
            }
        }).create().show();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (doubleClicked) {
            finishAffinity();
        } else {
            Toast.makeText(Home.this,"Press again to exit",Toast.LENGTH_SHORT).show();
            doubleClicked = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleClicked = false;
                }
            },2000);
        }
    }
}