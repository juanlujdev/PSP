package es.florida;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DronController {
    private static final int PORT_HHTP = 9876;
    public Socket connect() throws IOException {
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("localhost", PORT_HHTP));
        return new Socket();
    }

    public void takeOff() {
        System.out.println("Taking off...");
    }

    public void land() {
        System.out.println("Landing");
    }

    public void firePrimaryCannon() {
        System.out.println("Ratatatatatatata!");
    }

    public void fireSecondaryWeapon() {
        System.out.println("Piñau! Piñau!");
    }

    public void shutDown() {
        System.out.println("Shutting down system...");
    }

}
