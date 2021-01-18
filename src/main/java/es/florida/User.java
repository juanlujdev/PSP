package es.florida;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class User implements Runnable {
    public LinkedList user;

    @Override
    public void run() {
        System.out.println("En user ha llegado el siguiente usuario: " + user);
        //creo el fichero donde se van a guardar los nuevos usuario
        File file = new File("Email.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file.getAbsoluteFile(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printer = new PrintWriter(writer);
        printer.println(user.getLast());
        printer.close();
        }
    }
