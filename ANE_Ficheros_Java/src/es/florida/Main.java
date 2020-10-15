package es.florida;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("pruba.txt");
        FileWriter writer = new FileWriter(file);
        PrintWriter printer = new PrintWriter(writer);
        long startTime = System.currentTimeMillis();
        Random r = new Random();
        for (int i = 0; i< 100; i++){

            printer.println(r.nextInt(100)+1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("el proceso ha tardado "+(endTime-startTime)+ "ms");
        printer.close();
    }

}
