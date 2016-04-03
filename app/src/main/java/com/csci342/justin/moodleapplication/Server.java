package com.csci342.justin.moodleapplication;

import java.io.IOException;

/**
 * Created by Justin on 2016-03-30.
 */
public class Server extends Thread{

    static MyServerSocket serverSocket;
    static MyObjectOutputStream output;
    static MyObjectInputStream input;
    static MySocket client;
    static int status = 0;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        status = 1;

        while(true)
        {
            if(status == 0)
            {
                server = new Server();
                server.start();
                status = 1;
                System.out.println("Server restarted");
            }
        }

    }

    public void run()
    {
        try {
            serverSocket = new MyServerSocket(33333);
            System.out.println("Server Setup, waiting...");
            client = serverSocket.accept();
            input = new MyObjectInputStream(client.getInputStream());
            output = new MyObjectOutputStream(client.getOutputStream());

            System.out.println("Server Setup, waiting...");

            while(true) {
                System.out.println("Waiting for object");
                Protocol login_attempt = (Protocol) input.readObject();
                System.out.println("Received object");
                System.out.println(login_attempt.getPass());

                if(true)
                {
                    Info temp = new Info();
                    temp.setTag(1);
                    output.writeObject(temp);
                }
            }

        }catch(IOException e)
        {

            try {
                serverSocket.close();
                status = 0;
                System.out.println("Status Changed");
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }catch(Exception e)
        {
            status = 0;
            System.out.println("Status Changed");
            e.printStackTrace();

        }
    }
}
