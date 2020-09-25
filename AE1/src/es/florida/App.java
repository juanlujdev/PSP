package es.florida;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void factorial() {
        int num=15;
        int total=1;
        for (int i=1;i<=num;i++){
            total=total*i;
        }
        System.out.println("el resultado es: "+ total);
    }

    public void sayHellow(){
        System.out.println("Hola Mundo");
    }

    public static void arrayNombre(){
        String[] alumno = {"Javier", "Alfredo","Gilberto","Juan Antonio","Fabian","Ruben"};

        List<String> alumnosLista = new ArrayList<>();
        alumnosLista.add("juan");
        alumnosLista.add("Sonia");
        alumnosLista.add("Pedro");
        alumnosLista.add("Paco");
        alumnosLista.add("Angel");

        for (int i=0;i<alumno.length;i++){
            System.out.println(alumno[i]);
        }

       for (String i: alumnosLista)
           System.out.println(i);


    }
    public static void sumaPar(){
        int number=10;
        int acum=0;
       for (int i=0;i<number;i++){
           if (i%2==0){
               acum=acum+i;
           }
       }
            System.out.println(acum);
    }


}
