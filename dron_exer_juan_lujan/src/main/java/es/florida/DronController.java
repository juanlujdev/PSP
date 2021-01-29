package es.florida;

import org.jasypt.util.password.BasicPasswordEncryptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DronController {

    private static final int PORT_HHTP = 9876;

    public DronController() throws IOException {
    }

    public Socket connect() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", PORT_HHTP));
        return socket;
    }

    Socket connection = connect();
    PrintWriter writer = buildWriter(connection);

    private PrintWriter buildWriter(Socket connection) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        PrintWriter printer = new PrintWriter(outputStreamWriter, true);
        return printer;
    }




    public void takeOff() {
        writer.println("Taking off...");
        System.out.println("Taking off...");
    }
    public void land() {
        String password = "Landing";
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        String encryptPassword = encryptor.encryptPassword(password);
        System.out.println("El comando encriptado es: "+encryptPassword);
        writer.println("MdC3N+FU4P4v0pu/aDVHnu4NZorRusR/");
        System.out.println("Landing");
    }

    public void firePrimaryCannon() {
        writer.println("Ratatatatatatata");
        System.out.println("Ratatatatatatata!");
    }

    public void fireSecondaryWeapon() {
        writer.println("Piñau! Piñau!");
        System.out.println("Piñau! Piñau!");
    }


    public void shutDown() {
        writer.println("Shutting down system...");
        System.out.println("Shutting down system...");
    }

}
