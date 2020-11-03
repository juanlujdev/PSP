package es.florida;
import java.io.*;

public class MemberCreator implements  Runnable {
    int cont = 1;

    @Override
    public synchronized void run() {
        File file=new File("Email.txt");
        FileWriter writer=null;
        System.out.println("Producer started");
        while (true) {
            try {
                writer = new FileWriter(file.getAbsoluteFile(),true);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter printer = new PrintWriter(writer);
            String email = "Ae2Thread" + cont + "@gmail.com";
            printer.println(email);
            printer.close();
            System.out.println("Email añadido: "+cont + "- " + email);
            cont++;
            try {
                Thread.sleep(5000);
                //produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
