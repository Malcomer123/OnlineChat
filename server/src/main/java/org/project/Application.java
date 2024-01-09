package org.project;

import org.project.Controllers.UserController;
import org.project.Models.Message;
import org.project.Models.User;
import org.project.repos.MessageRepo;
import org.project.repos.ServerRepo;
import org.project.repos.UserRepo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class Application{

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9090)) {
            System.out.println("Server is listening on port " + 9090);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                new Thread(new Handler(socket)).start();
            }
        } catch (Exception e) {
            System.out.println("testsssa");
            e.printStackTrace();
        }

    }
    public static class Handler implements Runnable {
        private final Socket socket;
        private UserRepo userRepo = new UserRepo();
        private ServerRepo serverRepo = new ServerRepo();
        private MessageRepo messageRepo = new MessageRepo();

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
                Long idClient = 0L;

                while (socket.isConnected()) {
                    HashMap<String, String> objectReceived = (HashMap<String, String>) input.readObject();
                    switch (objectReceived.get("type")) {
                        case "register":
                            userRepo.saveUser(new User(objectReceived.get("username"), objectReceived.get("password"), objectReceived.get("email")));
                            output.writeObject("true");
                            output.flush();
                            break;
                        case "login":
                            User user = userRepo.findUserByUsername(objectReceived.get("username"));
                            if(user.getPassword().equals(objectReceived.get("password"))){
                                output.writeObject("true");
                                idClient = user.getIdUser();
                                System.out.println(idClient);
                            }
                            else{
                                output.writeObject("false");
                            }
                            output.flush();
                            break;
                        case "getServers":
                            output.writeObject(serverRepo.getAllServers().toString());
                            output.flush();
                            break;
                        case "search":
                            output.writeObject(userRepo.findUsersByUsername(objectReceived.get("search")).toString());
                            output.flush();
                            break;
                        case "getUsers":
                            output.writeObject(userRepo.getAllUsers().toString());
                            output.flush();
                            break;
                        case "getMessages":
                            User accountOwner = userRepo.findUserById(idClient);
                            User accountReceiver = userRepo.findUserById(Long.valueOf(objectReceived.get("targetId")));
                            List<Message> sentMessages = accountOwner.getMessages().stream()
                                    .filter(msg -> Objects.equals(msg.getUserUnicast().getIdUser(), Long.valueOf(objectReceived.get("targetId"))))
                                    .collect(Collectors.toList());
                            Long finalIdClient = idClient;
                            System.out.println(finalIdClient);
                            List<Message> receivedMessages = accountReceiver.getMessages().stream()
                                    .filter(msg -> Objects.equals(msg.getUserUnicast().getIdUser(), finalIdClient))
                                    .collect(Collectors.toList());

                            List<Message> combinedSortedMessages = new ArrayList<>(sentMessages);
                            combinedSortedMessages.addAll(receivedMessages);

                            combinedSortedMessages.sort(Comparator.comparing(Message::getDate));

                            List<String> messagesString = new ArrayList<>();
                            for(Message m: combinedSortedMessages){
                                messagesString.add(m.toString());
                            }
                            output.writeObject(messagesString);
                            output.flush();
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * data: User
 * method: getUser/authenication/send
 *
 *
 */
