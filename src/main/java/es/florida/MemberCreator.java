package es.florida;

import java.io.*;

public class MemberCreator implements Runnable {
    int cont = 1;

    @Override
    public void run() {
        File file = new File("Email.txt");
        FileWriter writer = null;
        System.out.println("Producer started");
        while (true) {
            if (StopCreator.stop == false) {
                try {
                    writer = new FileWriter(file.getAbsoluteFile(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                PrintWriter printer = new PrintWriter(writer);
                String email = "Ae2Thread" + cont + "@gmail.com";
                printer.println(email);
                printer.close();
                System.out.println("Email añadido: " + cont + "- " + email);
                cont++;
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("HE PARADO LA EJECUCION DEL CREATOR");
            }
        }
    }
}
