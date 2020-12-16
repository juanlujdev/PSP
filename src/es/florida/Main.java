package es.florida;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static final int PORT_HHTP = 9876;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("51.254.113.216", PORT_HHTP));
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream), true);
        System.out.println("Introduce comando: ");
        String entradaTeclado;
        Scanner entradaEscaner = new Scanner(System.in);
        entradaTeclado = entradaEscaner.nextLine();
        printer.println(entradaTeclado);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
