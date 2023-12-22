package org.project;


import org.project.Servers.Server;
import org.project.Servers.ServerManager;
import org.project.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class App {
    private static final int PORT = 9090;

   static ServerManager serverManager = new ServerManager();


    public static void main(String[] args ) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        try{
            while(true){
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }

    }

    private static class Handler extends Thread{
        private Socket socket;
        private int PORT;
        private Set<Server> servers;
        private PrintWriter pw;
        private InputStreamReader isr;
        private BufferedReader br;


        public Handler(Socket socket) throws IOException {
            this.socket = socket;
            this.isr = new InputStreamReader(socket.getInputStream());
            this.br = new BufferedReader(isr);
            this.pw = new PrintWriter(socket.getOutputStream(), true);
            this.servers = serverManager.getAvailableServers();
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public void run(){
            try{
                pw.println("Current list of servers: ");
                pw.println(serverManager.getAvailableServers());

                pw.println("Enter port of existing server or create server ");
                String PORT = br.readLine();
                for(Server server:servers) {
                    if (server.getPORT() == Integer.parseInt(PORT)) {
                        pw.println("Server already exists, connecting to it...");
                    }
                }
                serverManager.createServer(Integer.parseInt(PORT));
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
