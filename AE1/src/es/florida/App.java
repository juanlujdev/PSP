package es.florida;

import java.util.ArrayList;
import java.util.Collections;
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
            System.out.println("la suma de numeros par es: "+acum);
    }


    public void AE1_5NumeroMayorArray(int[] array) {
        int may=array[0];
        for (int i=0;i<array.length;i++){
            if(array[i]>may){
                may=array[i];
            }
        }
        System.out.println("el numero mayor del array es; "+may);
    }
    public void AE1_6ArrayInverso(List<Integer> arr2) {
        int suma=0;
        List<Integer> resultArrayInvers = new ArrayList<>();
        for (int i=arr2.size();i>0;i--){
            resultArrayInvers.add(arr2.get(i-1));//le quito uno porque el size coge el tamaño, pero esta en posicion 4 xq empieza desde 0.
            suma=suma+arr2.get(i-1);
        }
        System.out.println (resultArrayInvers);
        System.out.println("la suma es de: " +suma);
    }
}