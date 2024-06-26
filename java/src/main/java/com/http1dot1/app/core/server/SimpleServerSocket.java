package com.http1dot1.app.core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.http1dot1.app.core.sample.HandlerExample;

/**
 * SimpleServerSocket
 */
public class SimpleServerSocket extends Thread {
    
    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;


    public SimpleServerSocket (int port) {
        this.port = port;
    }

    public void startServer () {
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer () {
        this.running = false;
        this.interrupt();
    }

    @Override
    public void run () {
        this.running = true;
        while (this.running) {
            try {
                System.out.println("Listening for connection");

                Socket s = serverSocket.accept();
                HandleExecutor executor = new HandleExecutor(s, new HandlerExample());
                executor.start();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
