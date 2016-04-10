package com.csci342.justin.moodleapplication;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Justin on 2016-03-30.
 */
public class Connection extends Thread implements Serializable {

    static final long serialVersionUID = 32L;

    MySocket with_server;
    public static final int PORT = 33333;
    public static final String addr = "192.168.1.55";
    public Protocol user = new Protocol();
    MyObjectOutputStream output;
    MyObjectInputStream input;
    Info temp;
    int status = 0;

    public void run()
    {

        try {
            with_server = new MySocket(InetAddress.getByName(addr), PORT);
            output = new MyObjectOutputStream(with_server.getOutputStream());
            input = new MyObjectInputStream(with_server.getInputStream());
            status = 1;
            //connection made and logic waiting

            while(true)
            {
                try {
                    Info temp = (Info) input.readObject();
                    if(temp.tag == 1)
                    {
                        user.setLoggedIn(true);
                    }

                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        }catch(UnknownHostException e)
        {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void send_login()
    {
        try {
            output.writeObject(user);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void request_upload()
    {
        try {
            temp = new Info();
            temp.setTag(2);
            output.writeObject(temp);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void receive_subjects()
    {
        //receive array of subjects
    }
}
