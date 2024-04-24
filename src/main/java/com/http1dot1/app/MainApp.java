package com.http1dot1.app;

import com.http1dot1.app.core.server.SimpleServerSocket;

/**
 * MainApp
 *
 */
public class MainApp {
    public static void main( String[] args ) {
        int port = 6969;
        if (args.length == 0) {
            System.out.println("No port passed as args, starting at port 6969");
        } else {
            port = Integer.parseInt(args[0]);
            System.out.println("Port number informed, starting server at port "+ port);
        }

        SimpleServerSocket sss = new SimpleServerSocket(port);
        sss.startServer();
    }
}

