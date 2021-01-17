package es.florida;

import java.io.*;
import java.net.Socket;

public class WorkerServer implements Runnable {

    //Le asigno un Socket cuando llega a nuestro constructor el cliente desde el Server
    private Socket connection;

    //Recibo una conexion en el constructor(quiere decir que por cada hilo(son 5) crea una consulta TELNET)
    public WorkerServer(Socket clientConnetion) {
        //Le asigno ese Socket al cliente que viene desde el Server
        this.connection = clientConnetion;
    }

    @Override
    public void run() {
        while (true) {
            try {
                print();

                //Para enviarle cosas al cliente OutputStream
//                OutputStream outputStream = connection.getOutputStream();
//                //Para recibir cosas del cliente
//                InputStream input = connection.getInputStream();
//                //con BufferedReader leemos lo que nos viene de cliente
//                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                // Escribir cosas en el telnet, hace visible los datos en el cmd (Telnet)
//                PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream));
                //Para enviar algo al cliente
//                printer.println("Bienvenido");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        //el mensaje que nos llega del cliente (servidor) podemos procesarlo como queramos de la siguiente forma
        //con readLine leo linea x linea
//        String line;
//        while (true) {
//            try {
//
//                if (!((line = reader.readLine()) != null)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println(line);
//            //la peticion que me venga del cliente con ese line tendre que procesarlo, llamaria a una funcion con
//            //esa line y empezariamos a procesar las peticiones del cliente.
//        }

    }

    private void print() throws IOException {
//        BufferedReader reader = buildReader(connection);

        PrintWriter writer = buildWriter(connection);
        writer.println("Bienvenido");

    }


    private BufferedReader buildReader(Socket connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader;
    }

    private PrintWriter buildWriter(Socket connection) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        PrintWriter printer = new PrintWriter(outputStreamWriter, true);
        return printer;
    }
}
