package org.project.Servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;
    private final int PORT;
    //Logger logger = LoggerFactory.getLogger(Server.class);

    
    public Server(int PORT) throws IOException {
        this.PORT = PORT;
        serverSocket = new ServerSocket(PORT);

    }

    public int getPORT() {
        return PORT;
    }

    public void start() {
        //logger.info("Server started on port " + PORT);
        System.out.println("Server started on port " + PORT);
        while (true) {
            try {
                new Handler(serverSocket.accept()).start();
            } catch (IOException e) {
                //logger.error("Error accepting client connection", e);
                System.out.println("Error accepting client connection");
            }
        }
    }

    @Override
    public String toString() {
        return ""+PORT;
    }

    private static class Handler extends Thread{
        private Socket socket;
        private int PORT;
        private ObjectInputStream input;
        private OutputStream os;
        private ObjectOutputStream output;
        private InputStream is;
        ServerManager serverManager = new ServerManager();

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                is = socket.getInputStream();
                input = new ObjectInputStream(is);
                os = socket.getOutputStream();
                output = new ObjectOutputStream(os);

                String PORT = (String) input.readObject();

                //logger.info("CONNECTED!")
            } catch (IOException e) {
                //logger.error("Error getting input stream", e);
                System.out.println("Error getting input stream");
            } catch (ClassNotFoundException e) {
                //logger.error("Error reading object", e);
                System.out.println("Error reading object");
            }
        }
    }
}
