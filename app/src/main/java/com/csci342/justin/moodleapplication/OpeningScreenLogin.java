package com.csci342.justin.moodleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class OpeningScreenLogin extends AppCompatActivity{

    Spinner spinner;
    Connection connect;
    EditText email_test;
    EditText password_test;

    Handler myHandler;
    int login_token;

    Protocol user;

    public static final int PORT = 33333;
    public static final String addr = "172.18.24.119";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_opening_screen_login);

        spinner = (Spinner) findViewById(R.id.OSL_tempspin_spinner);

        String[] logins = new String[] {"Student", "Teacher"};

        final ArrayAdapter spinner_adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, logins);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinner_adapter);

        user = new Protocol();

        myHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                login_token = msg.arg1;
            }
        };

    }

    private class logMeIn extends Thread
    {
        Protocol the_user;

        public logMeIn(Protocol user)
        {
            the_user = user;
        }

        @Override
        public void run()
        {
            try {

                Socket with_server = new Socket(InetAddress.getByName(addr), PORT);
                ObjectInputStream input = new ObjectInputStream(with_server.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(with_server.getOutputStream());

                EditText email_test = (EditText) findViewById(R.id.OSL_email_edittext);
                EditText password_test = (EditText) findViewById(R.id.OSL_password_edittext);
                String ema = email_test.getText().toString();
                String passw = password_test.getText().toString();
                the_user.generateHash(passw);
                the_user.setLogin(ema);

                

            }catch(UnknownHostException e)
            {
                e.printStackTrace();
            }catch(IOException e)
            {
                e.printStackTrace();
            }

            Message msg = myHandler.obtainMessage();
            msg.arg1 = 0;
            myHandler.sendMessage(msg);
        }
    }

    public void startLoginProcess()
    {
            logMeIn temp = new logMeIn(user);
            temp.start();
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

    public void login(View v) {
        Toast.makeText(this, "Attempting to connect", Toast.LENGTH_LONG).show();

        EditText email_test = (EditText) findViewById(R.id.OSL_email_edittext);
        EditText password_test = (EditText) findViewById(R.id.OSL_password_edittext);
        String ema = email_test.getText().toString();
        String passw = password_test.getText().toString();
        connect.user.generateHash(passw);
        connect.user.setLogin(ema);

        startLoginProcess();

        int count = 0;

        while (true) {

            count++;

            if (count > 10) {
                Toast.makeText(this, "Server took too long to respond", Toast.LENGTH_LONG).show();
                connect.status = 1;
                return;
            }

            if (connect.user.getLoggedIn() == true) {
                connect.status = 1;
                openDashboard();
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
