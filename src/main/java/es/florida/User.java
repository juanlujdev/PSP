package es.florida;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class User {


    public void printEmail(LinkedList<String>users) throws IOException {
        System.out.println("En user ha llegado el siguiente usuario: " + users);
        //creo el fichero donde se van a guardar los nuevos usuario
        File file = new File("Email.txt");
        FileWriter writer = null;
        writer = new FileWriter(file.getAbsoluteFile(), true);
        PrintWriter printer = new PrintWriter(writer);
        printer.println(users.getLast());
        printer.close();
    }


}
