package es.florida;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DronController dronController = new DronController();
        dronController.connect();
        dronController.takeOff();
        dronController.land();

    }
}
