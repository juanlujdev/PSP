package es.florida;

import java.io.*;

public class MemberCreator implements Runnable {
    int cont = 1;
    public static boolean stopCreator = true;

    //el hilo
    @Override
    public void run() {
        //Creo el fichero txt
        File file = new File("Email.txt");
        FileWriter writer = null;
        System.out.println("Producer started");
        //bucle infinito
        while (true) {
            //si el booleano es true
            if (StopCreator.stop == false) {
                try {
                    //no se que es getAbsoluteFile(), true
                    // le paso el fichero y con el true le digo que existe
                    writer = new FileWriter(file.getAbsoluteFile(), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //escribir lineas en vez de caracter a caracter
                PrintWriter printer = new PrintWriter(writer);
                String email = "Ae2Thread" + cont + "@gmail.com";
                //me escribe en el fichero el email
                printer.println(email);
                printer.close();
                System.out.println("Email añadido: " + cont + "- " + email);
                cont++;
                //duerme el hilo 5seg
                try {
                    Thread.sleep(4000);
                    //produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
            System.out.println("HE PARADO LA EJECUCION DEL CREATOR");
            }
        }
    }
}
