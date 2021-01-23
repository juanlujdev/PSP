package es.florida.client;

import org.jasypt.util.text.StrongTextEncryptor;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final int PORT_HHTP = 9876;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", PORT_HHTP));
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream), true);
//        printer.println("hola");
        System.out.println(reader.readLine());
//        System.out.println("Introduce comando: ");
        String entradaTeclado;
        Scanner entradaEscaner = new Scanner(System.in);

        while (true) {
            entradaTeclado = entradaEscaner.nextLine();
            printer.println(entradaTeclado);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.equals("Escribe codigo de desbloqueo:")){
                    String pass =entradaEscaner.nextLine();
                    StrongTextEncryptor superEncryptor = new StrongTextEncryptor();
                    superEncryptor.setPassword("algo");
                    printer.println(superEncryptor.encrypt(pass));
                }
                if (line.endsWith(".") || line.endsWith(":")) {
                    break;
                }

            }
        }
    }
}
