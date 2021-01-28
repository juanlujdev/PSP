package es.florida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    private ExecutorService executorService= Executors.newFixedThreadPool(2);
    private static final int SERVER_PORT = 6789;
    @Override
    public void run() {

        try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket clientConnection;
            while (true) {
                clientConnection = server.accept();
                System.out.println("cliente conectado al puerto: " + clientConnection.getPort());
                executorService.execute(new Enemy(clientConnection));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
