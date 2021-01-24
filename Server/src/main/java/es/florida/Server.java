package es.florida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    //Server utilizara 5 hilos para conectarse 5 brokers
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final int SERVER_PORT = 9876;

    //activo el hilo para la conexion
    @Override
    public void run() {
        //le digo a ese socket el puerto al q tiene que trabajar
        try {
            //declaro el servidor del socket
            ServerSocket server = new ServerSocket(SERVER_PORT);
            //declaro la otra parte del servidor
            Socket clientConnetion;
            //hago que el servidor siempre este a la escucha
            while (true) {
                //aqui uno el Socket con el Servidor del Socket y conecto
                clientConnetion = server.accept();
                System.out.println("Client connected to port " + clientConnetion.getPort());
                //Le paso la conexion de cliente a la clase WorkerServer por uno de los 5 hilos
                executorService.execute(new WorkerServer(clientConnetion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
