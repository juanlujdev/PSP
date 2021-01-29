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
            String comand;
            PrintWriter writer = buildWriter(connection);
            BufferedReader reader = buildReader(connection);
            writer.println("Escriba su comando: ");
            comand=reader.readLine();
            if (comand.equals("ENEMY_SPOTTED")){
                System.out.println("el comando se ha ejecutado.");
            }
            else{
                System.out.println("Comando no valido");
            }



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

    private BufferedReader buildReader(Socket connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader;
    }


}
