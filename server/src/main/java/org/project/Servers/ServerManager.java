/*package org.project.Servers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ServerManager {
    private Set<ServerService> servers;

    public ServerManager() {
        this.servers = new HashSet<>();
    }

    public void createServer(int port) {
        try {
            ServerService server = new ServerService(port);
            servers.add(server);
            server.start();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public Set<ServerService> getAvailableServers() {
        return servers;
    }

}*/
