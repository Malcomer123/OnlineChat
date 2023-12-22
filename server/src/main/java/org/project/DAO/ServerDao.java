package org.project.DAO;

import com.sun.security.ntlm.Server;

public interface ServerDao extends Dao<Server>{
    Server getByPort(int port);
}
