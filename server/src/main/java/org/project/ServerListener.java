package org.project;

import org.project.Models.Server;
import org.project.Servers.ServerService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable{
    Server server;
    ServerSocket serverSocket;

    public ServerListener(Server server) throws IOException {
        this.server = server;
        serverSocket = new ServerSocket(server.getPort());
    }


    @Override
    public void run() {
        while (true) {
            try {
                new Handler(serverSocket.accept()).start();
            } catch (IOException e) {
                //logger.error("Error accepting client connection", e);
                System.out.println("Error accepting client connection");
            }
        }
    }

    private static class Handler extends Thread{
        private Socket socket;
        private int PORT;
        private ObjectInputStream input;
        private ObjectOutputStream output;

        public Handler(Socket socket) throws IOException {
            this.socket = socket;
            this.input = new ObjectInputStream(socket.getInputStream());
            this.output = new ObjectOutputStream(socket.getOutputStream());
        }

        public void run(){
            try{
                while(true){
                    String message = input.readObject().toString();
                    System.out.println("Message received: " + message);
                    output.writeObject("Message received: " + message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
