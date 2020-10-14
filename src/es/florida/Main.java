package es.florida;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("pruba.txt");
        FileWriter writer = new FileWriter(file);
        PrintWriter printer = new PrintWriter(writer);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i< 100; i++){
            double numberRandom = 0;
            numberRandom=Math.round(Math.random()*10);
            printer.println(numberRandom);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("el proceso ha tardado "+(endTime-startTime)+ "ms");
    }
}
