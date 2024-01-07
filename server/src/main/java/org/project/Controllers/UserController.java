package org.project.Controllers;

import java.io.*;
import java.net.Socket;

public class UserController {
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public UserController(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            is = new ObjectInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            os = new ObjectOutputStream(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean authentication() throws IOException, ClassNotFoundException {
        try {
            String username = (String) is.readObject();
            String password = (String) is.readObject();
            System.out.println(username + password);
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
