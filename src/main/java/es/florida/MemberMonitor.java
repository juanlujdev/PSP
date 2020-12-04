package es.florida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemberMonitor implements Runnable {

    //lista archivoEmail
    LinkedList<String> archivoEmail = new LinkedList<>();
    int cont = 0;
    FileReader fr = null;
    BufferedReader br = null;

    @Override
    public void run() {
        //bucle infinito cada 3 seg siempte tiene q comprobar
        while (true) {
            //cada 3 segundos (duermo el hilo 3 seg)
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                fr = new FileReader("./Email.txt");
                br = new BufferedReader(fr);
                String linea;
                //si es distinto de nulo lo del fichero Email.txt añdame la linea a la lista
                while ((linea = br.readLine()) != null) {
                    archivoEmail.add(linea);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //si la lista es mayor de lo q tiene el contador, (si hay nuevo email)
            if (archivoEmail.size() > cont) {
                MailSender mailSender = new MailSender();

                Thread mailSenderThread = new Thread(mailSender);
                mailSenderThread.start();
                //correo es un atributo publico de MailSender, asi le paso el ultimo email de la lista
                mailSender.correo = archivoEmail.getLast();
            }
            cont = archivoEmail.size();
            //pongo a 0 el archivoEmail para comparar


        }
    }
}
