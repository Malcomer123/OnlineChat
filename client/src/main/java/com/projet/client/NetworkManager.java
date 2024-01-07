package com.projet.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkManager {
    private static NetworkManager instance;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private NetworkManager() {
        // Private constructor to prevent instantiation
    }

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    public void connectToServer(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public void sendObject(Object obj) throws IOException {
        oos.writeObject(obj);
        oos.flush();
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        return ois.readObject();
    }

    public void closeConnection() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
