package es.florida;

import java.util.ArrayList;
import java.util.LinkedList;

public class MemberCreator implements  Runnable {
//Creo un LinkedList para meter los nuevo emails
//declaro MAX_SIZE para decirle que como mucho tenga 1 elemento en la lista( lo hago mas abajo)
//el contador simplemente es para hacer emails nuevos
    private LinkedList<String> emails = new LinkedList<>();
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
        System.out.println(cont + "- " + email);
        cont++;

        //emails.pop();
    }
    //metodo para saber el tamaño del email
    public int emailsSize(){
        return emails.size();
    }
/*
    public String consume() {
        if (emails.isEmpty()) {
            System.out.println("Queue is EMPTY of mails. Please wait");
        } else {
            return emails.pop();
        }
        return null;
    }*/
//metod para saber cual es el ultimo email introducido
    public String LastEmail() {
        return emails.getLast();
    }
//metodo para borrar el ultimo email introducido
    public void deleteEmail() {
        emails.pop();
    }
}
