package es.florida;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class MemberCreator implements  Runnable {
    public MemberCreator() throws IOException {

    }


//Creo un LinkedList para meter los nuevo emails
//declaro MAX_SIZE para decirle que como mucho tenga 1 elemento en la lista( lo hago mas abajo)
//el contador simplemente es para hacer emails nuevos
//creo un fichero Email
    private LinkedList<String> emails = new LinkedList<>();
    File file=new File("Email.txt");
    FileWriter writer=new FileWriter(file);
    PrintWriter printer = new PrintWriter(writer);


    private static final int MAX_SIZE=1;
    int cont = 1;
    int count=0;

    @Override
    public synchronized void run() {
        System.out.println("Producer started");
//bucle infinito, cada 4 segundos cree un email produce();
        while (true) {
            try {
                Thread.sleep(3500);
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//mientras el tamaño de la lista "emails" sea 1 que espere
    private void produce() throws InterruptedException {
        while (emails.size() == MAX_SIZE) {
            wait();

            System.out.println("estoy esperando, CONTADOR= "+count);
        }
        notify();
        //cuando deja de esperar crea el siguient email
        String email = "Ae2Thread" + cont + "@gmail.com";
        emails.push(email);
        printer.println(email);
        printer.flush();
        System.out.println(cont + "- " + email);
        cont++;
    }
    //metodo para saber el tamaño del email
    public int emailsSize(){
        return emails.size();
    }

//metod para saber cual es el ultimo email introducido
    public String LastEmail() {
        return emails.getLast();
    }
//metodo para borrar el ultimo email introducido
    public void deleteEmail() {
        emails.pop();
    }
}
