package com.csci342.justin.moodleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OpeningScreenLogin extends AppCompatActivity {

    Spinner spinner;
    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_opening_screen_login);

        spinner = (Spinner) findViewById(R.id.OSL_tempspin_spinner);

        String[] logins = new String[] {"Student", "Teacher"};

        final ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, logins);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinner_adapter);
    }

    public void login(View v)
    {
        openDashboard();
    }

    public void openDashboard()
    {
        Intent i = new Intent(this, Dashboard.class);
        profile = new Profile();
        String x = ((Spinner)findViewById(R.id.OSL_tempspin_spinner)).getSelectedItem().toString();
        profile.setAuthority(x);
        i.putExtra("Authority",x);
        startActivity(i);
    }


}
