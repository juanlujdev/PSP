package es.florida;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MailSender implements Runnable {
    public static ExecutorService executorService = Executors.newFixedThreadPool(40);
    public String correo;

    @Override
    public void run() {
        MailCreator mailCreator=new MailCreator();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("Email.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                //Pongo el stopCreator a false porque esta leyendo el fichero y quiero parar la ejecucion
                // de la creacion de email del MemberCreator
                MemberCreator.stopCreator=false;
                System.out.println("Sr/Sra " + linea + " el nuevo usuario es: " + correo);
                executorService.execute(mailCreator);

            }
            //como he terminado de leer los mail cambio el MemberCreator a true para que vuelva a arrancar el
            // creador de mai
            MemberCreator.stopCreator=true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
