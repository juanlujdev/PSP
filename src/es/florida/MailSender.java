package es.florida;

import javax.swing.plaf.TableHeaderUI;
import java.io.*;

public class MailSender implements Runnable{
    private final MemberCreator memberCreator;

    public MailSender(MemberCreator memberCreator) {
        this.memberCreator=memberCreator;
    }
// COMO PODRIA METER EL METODO DE ABAJO DENTRO DE ESTE @OVERRIDE PARA QUE UTILICE EL THREAD?
    @Override
    public void run() {

    }

//Hago el metodo para leer el mail y lo preparo para enviar el ultimo email recibido
    public void mailSender(String lastEmail) throws IOException {
        File file = new File("Email.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        line = bReader.readLine();

    }
}

