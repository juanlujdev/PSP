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
        //recoge informacion del socket (del servidor)
        InputStream inputStream = socket.getInputStream();
        //recoge la informacion del cliente para prepararla para que la envie el PrinteWriter
        OutputStream outputStream = socket.getOutputStream();
        //el buffer reader va enlazado al inputStream y lee y muestra la informacio recogida del inputStream
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //va con el outputStream y envia lo q has escrito en el cliente en el servidor
        PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream), true);
        //para q no valla con retraso de un paso la aplicacion, gestionamos la pirmera informacion
        //que nos llega del servidor xq conforme enchufamos el socket enpieza nuestro servidor a darnos
        //informacion
        System.out.println(reader.readLine());
        String entradaTeclado;
        //Scanner hace la pausa para pedir lo q sea y se lo paso lo q he escrito a un string
        Scanner entradaEscaner = new Scanner(System.in);

        while (true) {
            //entrada teclado q es el string recoge lo q he escrito y se lo paso al printer para que
            //lo envie al servidor
            entradaTeclado = entradaEscaner.nextLine();
            printer.println(entradaTeclado);
            String line;
            //bucle infinito hasta que encuentre un "." o ":" esta mas abajo
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.equals("Escribe codigo de desbloqueo:")) {
                    String pass = entradaEscaner.nextLine();
                    StrongTextEncryptor superEncryptor = new StrongTextEncryptor();
                    //el setPassword es como claves publicas o privadas. doble seguridad, debe estar tambien en
                    //el servidor
                    superEncryptor.setPassword("algo");
                    printer.println(superEncryptor.encrypt(pass));
                    //MOSTRA por pantalla lo q viene del servidor,para gestionar el espacio en blanco
                    //Es como un enter para que no halla un espacio en blanco
                    System.out.println(reader.readLine());
                } else if (line.equals("Codigo de bloqueo de servidor:")) {
                    String pass2 = entradaEscaner.nextLine();
                    StrongTextEncryptor superEncryptor2 = new StrongTextEncryptor();
                    superEncryptor2.setPassword("algo");
                    printer.println(superEncryptor2.encrypt(pass2));
                    System.out.println(reader.readLine());
                }
                if (line.endsWith(".") || line.endsWith(":")) {
                    break;
                }
            }
        }
    }
}
