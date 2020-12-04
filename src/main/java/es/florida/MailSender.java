package es.florida;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.io.*;

public class MailSender implements Runnable {
    private static final int MAILDEV_PORT=1025;

    public String correo;
    public String correoList;

    @Override
    public void run() {

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
                //lanzo email
                Email email=new SimpleEmail();
                email.setHostName("localhost");
                email.setFrom("juan@gmail.com");
                email.setSmtpPort(MAILDEV_PORT);
                email.addTo(correo);
                email.setSubject("Nuevo miembro");
                email.setMsg("Se ha incorporado un nuevo miembro " + correo );
                email.send();
            }
            //como he terminado de leer los mail cambio el MemberCreator a true para que vuelva a arrancar el
            // creador de mai
            //MemberCreator.stopCreator=true;
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
