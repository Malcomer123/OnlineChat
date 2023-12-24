package org.project.Servers;

import org.project.Models.Server;
import org.project.repos.ServerRepo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService implements Runnable{
    private ServerRepo serverRepo;
    private ServerSocket serverSocket;
    private Server server;

    public ServerService(int port) throws IOException {
        this.serverRepo = new ServerRepo();
        this.server = ServerRepo.findServerByPort(port);
        if(server == null){
            //server = new Server(port);
            serverRepo.saveServer(server);
        }
        serverSocket = new ServerSocket(server.getPort());
    }

    @Override
    public void run() {
        while(true){
            try{
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private static class Handler extends Thread{
        private Socket socket;
        private ObjectInputStream input;
        private OutputStream os;
        private ObjectOutputStream output;
        private InputStream is;

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
