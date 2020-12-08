package es.florida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemberMonitor implements Runnable {
    public static ExecutorService executorService = Executors.newFixedThreadPool(40);
    LinkedList<String> emails = new LinkedList<>();
    int cont = 0;
    FileReader fr = null;
    BufferedReader br = null;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            emails = rellenaLista();
            if (emails.size() > cont) {
                cont = emails.size();
                MailSender mailSender = new MailSender();
                executorService.execute(mailSender);
                mailSender.correo = emails.getLast();
            }
        }
    }

    private LinkedList<String> rellenaLista() {
        LinkedList<String> archivoEmail = new LinkedList<>();
        try {
            fr = new FileReader("./Email.txt");
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                archivoEmail.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return archivoEmail;
    }
}
