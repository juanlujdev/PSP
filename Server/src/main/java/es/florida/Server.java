package es.florida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final int SERVER_PORT = 9876;

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket clientConnetion;
            while (true) {
                clientConnetion = server.accept();
                System.out.println("Client connected to port " + clientConnetion.getPort());
                executorService.execute(new WorkerServer(clientConnetion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
