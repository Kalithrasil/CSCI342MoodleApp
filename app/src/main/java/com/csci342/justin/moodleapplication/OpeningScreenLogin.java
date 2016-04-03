package com.csci342.justin.moodleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OpeningScreenLogin extends AppCompatActivity {

    Spinner spinner;
    Connection connect;

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

    public void startLoginProcess()
    {
            connect = new Connection();
            connect.start();
    }

    public void attemptLogin()
    {
        EditText email_test = (EditText) findViewById(R.id.OSL_email_edittext);
        EditText password_test = (EditText) findViewById(R.id.OSL_password_edittext);
        String ema = email_test.getText().toString();
        String passw = password_test.getText().toString();
        connect.user.generateHash(passw);
        connect.user.setLogin(ema);
        connect.send_login();
    }

    public void login(View v)
    {
        startLoginProcess();

        EditText email_test = (EditText) findViewById(R.id.OSL_email_edittext);
        EditText password_test = (EditText) findViewById(R.id.OSL_password_edittext);
        String ema = email_test.getText().toString();
        String passw = password_test.getText().toString();
        if((!ema.equals(""))&& (!passw.equals("")) && (connect.output != null))
        {
            attemptLogin();

            int count = 0;

            while(connect.user.getLoggedIn() == false)
            {
                count++;
                if(count > 50000000)
                {
                    Toast.makeText(this, "Connection Failed", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            openDashboard();
        }
        else if(connect.output == null)
        {
            Toast.makeText(this, "Server is not currently available", Toast.LENGTH_LONG).show();
        }


    }

    public void openDashboard()
    {
        Intent i = new Intent(this, Dashboard.class);
        String x = ((Spinner)findViewById(R.id.OSL_tempspin_spinner)).getSelectedItem().toString();
        connect.user.setAuthority(x);
        i.putExtra("Connection",connect);
        startActivity(i);
    }


}
