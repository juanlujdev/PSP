package es.florida;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        DronController dronController = new DronController();
        dronController.connect();
        Thread.sleep(1000);
        dronController.takeOff();
        Thread.sleep(1000);
        dronController.land();
        Thread.sleep(1000);
        dronController.firePrimaryCannon();
        Thread.sleep(1000);
        dronController.fireSecondaryWeapon();
        Thread.sleep(1000);
        dronController.shutDown();
        Thread.sleep(1000);
    }
}
