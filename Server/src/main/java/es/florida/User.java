package es.florida;

import java.io.*;
import java.util.LinkedList;

public class User {

    public void printEmail(LinkedList<String> users) throws IOException {
        File file = new File("Email.txt");
        FileWriter writerFile = null;
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("");
        bw.close();
        writerFile = new FileWriter(file.getAbsoluteFile(), true);
        PrintWriter printer = new PrintWriter(writerFile);
        for (String s : users) {
            printer.println(s);
        }
        printer.close();
    }
}
