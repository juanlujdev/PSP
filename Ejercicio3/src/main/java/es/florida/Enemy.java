package es.florida;

import java.io.*;
import java.net.Socket;

public class Enemy implements Runnable {
    private Socket connection;

    public Enemy(Socket clientConnection) {
        this.connection = clientConnection;
    }

    @Override
    public void run() {
        try {

            PrintWriter writer = buildWriter(connection);
            writer.println("ENEMY_SPOTTED");
            System.out.println("el comando ha llegado");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private PrintWriter buildWriter(Socket ClientConnection) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        PrintWriter printer = new PrintWriter(outputStreamWriter, true);
        return printer;
    }


}
