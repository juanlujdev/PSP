package es.florida;

import java.util.ArrayList;
import java.util.List;

public class App {
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


}
