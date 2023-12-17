package org.project.Servers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ServerManager {
    private Set<Server> servers;

    public ServerManager() {
        this.servers = new HashSet<>();
    }

    public void createServer(int port) {
        try {
            Server server = new Server(port);
            servers.add(server);
            server.start();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public Set<Server> getAvailableServers() {
        return servers;
    }

}
