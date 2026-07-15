package com.clouddeploy.service;

import org.springframework.stereotype.Service;

import java.net.ServerSocket;

@Service
public class PortAllocationService {

public int getNextAvailablePort() {

    int port = 8085;

    while (true) {

        try {

            ServerSocket socket =
                    new ServerSocket(port);

            socket.close();

            return port;

        } catch (Exception e) {

            port++;

        }

    }

}


}
