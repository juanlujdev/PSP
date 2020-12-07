package es.florida;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MailSender implements Runnable {
    private static final int MAILDEV_PORT = 1025;
    public static ExecutorService executorService = Executors.newFixedThreadPool(40);
    public String correo;
    LinkedList<String> list = new LinkedList<>();

    @Override
    public void run() {
        //MailCreator mailCreator = new MailCreator();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File("Email.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;


            //while ((linea = br.readLine()) != null) {
            //Recupero mi lista cargada
            linea = br.readLine();
            list = returnFile(archivo);
            //Pongo el stopCreator a false porque esta leyendo el fichero y quiero parar la ejecucion
            // de la creacion de email del MemberCreator
            StopCreator.stop = true;
            System.out.println("Sr/Sra " + linea + " el nuevo usuario es: " + correo);
            //Mando un email a cada miembro de la lista
            for (int i = 0; i < list.size() - 1; i++) {
                Email email = new SimpleEmail();
                try {

                    email.setHostName("localhost");
                    email.setFrom("juan@gmail.com");
                    email.setSmtpPort(MAILDEV_PORT);
                    email.setSubject("Nuevo miembro");
                    email.addTo(list.get(i));
                    email.setMsg("Se ha incorporado un nuevo miembro " + list.getLast());

                } catch (EmailException e) {
                    e.printStackTrace();
                }
                sendMailThread(email);

            }
            //executorService.execute(mailCreator);
            //}
            //como he terminado de leer los mail cambio el MemberCreator a true para que vuelva a arrancar el
            // creador de mai
            StopCreator.stop = false;
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

    //Metodo que recibe el mail cargado y se lo envio a el atributo de la clase mailThread para manejar
    //ese Email cargado desde alli.
    private void sendMailThread(Email email) {
        MailThread mailThread = new MailThread();
        mailThread.email = email;
        executorService.execute(mailThread);
    }

    //meto el fichero y se lo paso a la lista para poder recorrerla (lo hago asi porque no puedo pasar la lista
    //desde MemberMonitor ya q no se puede pasar la info entre hilos
    private LinkedList returnFile(File archivo) throws FileNotFoundException {
        LinkedList list = new LinkedList();
        FileReader fr = null;
        BufferedReader br = null;
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        String linea;
        while (true) {
            try {
                if (!((linea = br.readLine()) != null)) break;
                list.add(linea);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

