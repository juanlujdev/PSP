package es.florida;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class MemberMonitor implements Runnable {
    LinkedList<String> archivoEmail =new LinkedList<>();
    int cont =0;
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
            try {
            fr = new FileReader("./Email.txt");
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null) {
                archivoEmail.add(linea);
            }
        }
                catch(Exception e){
            e.printStackTrace();
        }
            if (archivoEmail.size()>cont){
                MailSender mailSender=new MailSender();
                mailSender.correo=archivoEmail.getLast();
                Thread mailSenderThread=new Thread(mailSender);
                mailSenderThread.start();
            }
            cont=archivoEmail.size();
            archivoEmail=new LinkedList<>();
        }
    }
}
