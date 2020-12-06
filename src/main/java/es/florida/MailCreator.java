package es.florida;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class MailCreator implements Runnable {

    private static final int MAILDEV_PORT=1025;
    LinkedList<String>list=new LinkedList<>();
    File archivo = new File("Email.txt");

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            list=returnFile(archivo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("HAY AHORA EN LA LISTA:" + list.size());
        //lanzo email

        try {
            for (int i=0;i<list.size(); i++){
                Email email=new SimpleEmail();
                email.setHostName("localhost");
                email.setFrom("juan@gmail.com");
                email.setSmtpPort(MAILDEV_PORT);
                email.addTo(list.get(i));
                email.setSubject("Nuevo miembro");
                email.setMsg("Se ha incorporado un nuevo miembro " + list.getLast());
                email.send();
            }

        } catch (EmailException e) {
            e.printStackTrace();
        }


    }
        //meto el fichero y se lo paso a la lista para poder recorrerla (lo hago asi porque no puedo pasar la lista
        //desde MemberMonitor ya q no se puede pasar la info entre hilos
        private LinkedList returnFile(File file) throws FileNotFoundException {
        LinkedList list = new LinkedList();
        FileReader fr = null;
        BufferedReader br = null;
        fr = new FileReader(file);
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

