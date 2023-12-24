package org.project;


import org.project.Servers.ServerService;
import org.project.repos.ServerRepo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    private static final int PORT = 9090;

    public static void main(String[] args) throws IOException {
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
        private ServerService serverService;
        private ServerRepo serverRepo = new ServerRepo();
        private PrintWriter pw;
        private InputStreamReader isr;
        private BufferedReader br;


        public Handler(Socket socket) throws IOException {
            this.socket = socket;
            this.isr = new InputStreamReader(socket.getInputStream());
            this.br = new BufferedReader(isr);
            this.pw = new PrintWriter(socket.getOutputStream(), true);
        }

        public void run(){
            try{
                pw.println("Current list of servers: ");
                pw.println(serverRepo.getAllServers());

                pw.println("Enter port of existing server or create server ");
                String PORT = br.readLine();
                //serverService = new ServerService(Integer.parseInt(PORT));
                //serverManager.run();
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
