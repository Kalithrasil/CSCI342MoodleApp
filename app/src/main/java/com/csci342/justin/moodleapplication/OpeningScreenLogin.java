package com.csci342.justin.moodleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OpeningScreenLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen_login);
    }

    public void login(View v)
    {
        openDashboard();
    }

    public void openDashboard()
    {
        Intent i = new Intent(this, Dashboard.class);
        startActivity(i);
    }
}
