package ru.vadya.home_sync.net

import java.net.ServerSocket
import java.net.Socket

/**
 * Created by vadya on 27.02.16.
 */

open class Server() : Thread () {
    override fun run() {
        while (true) {
            try {
                var serverSocket: ServerSocket = ServerSocket(8888)
                var clientSocket: Socket = serverSocket.accept()
                var acceptHandler = AcceptHandler(clientSocket)
                Thread(acceptHandler).start()
            } catch(e: Exception) {
                System.out.println("init error: " + e);
            }
        }
    }

}
