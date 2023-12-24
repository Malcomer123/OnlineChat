package org.project;

import org.project.Models.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Application{
    private static final int PORT = 9001;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Server is listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new Handler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static class Handler implements Runnable {
        private final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream input;
                OutputStream os;
                ObjectOutputStream output;
                InputStream is;

                System.out.println("Attempting to connect a user...");

                is = socket.getInputStream();
                input = new ObjectInputStream(is);
                os = socket.getOutputStream();
                output = new ObjectOutputStream(os);

                while (socket.isConnected()) {
                    Object object = input.readObject();
                    switch (object.getClass().getSimpleName()) {
                        case "Message":
                            Message message = (Message) object;
                            System.out.println("Received message: " + message);
                            break;
                        case "User":
                            System.out.println("Received user: " + object);
                            break;
                        default:
                            System.out.println("Received unknown object: " + object);
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
