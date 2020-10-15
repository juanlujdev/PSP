package es.florida;

import java.io.*;

public class NewMain {
    public static void main(String[] args) throws IOException {
        long startTime2 = System.currentTimeMillis();
        File file = new File("pruba.txt");
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        line = bReader.readLine();
        long endTime2 = System.currentTimeMillis();
        System.out.println("El tiempo de lectura es de: "+ (endTime2-startTime2));

            int sum=0;
            int num=0;
        long startTime = System.currentTimeMillis();
            for(int i=0; i<100;i++){
                num = Integer.parseInt(line);//en JS se parsea de esta manera
                sum=sum+num;
        }
        System.out.println("La suma es de: " + sum);
        long endTime = System.currentTimeMillis();
        System.out.println("el proceso de la operacion es: "+(endTime-startTime)+ "ms");
        bReader.close();
    }
}
