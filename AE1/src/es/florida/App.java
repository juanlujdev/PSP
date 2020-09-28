package es.florida;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner entrada=new Scanner(System.in);
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

    public void AE1_7Salario(String name, int years) {
        if (years<1){
            System.out.println(name+("es Desarrollador Junior L1 y cobra entre 15000-18000"));
        }
        else if ((years>1)&&(years<2)){
            System.out.println(name+("Es desarrollador Junior L2 y cobra entre 18000-22000"));
        }
        else if (years == 4){
            System.out.println(name+("Es Desarrollador Senior L1 y cobra entre 22000-28000"));
        }
        else if ((years>5)&&(years<8)){
            System.out.println("Es Desarrollador Serion L2 y cobra entre 28000-36000");
        }
        else{
            System.out.println(name+("Es analista/Arquitecto. Salario a convenir en base a rol"));
        }
    }

    public void AE1_8Primos() {

        System.out.printf("escribe el primer numero: ");
        Integer num1 = entrada.nextInt();
        System.out.printf("escribe el segundo numero: ");
        Integer num2 = entrada.nextInt();

        long inicio = System.nanoTime();

        if (num1 > num2) {
            int cont = 0;
            for (int i = num2; i < num1; i++) {
                for (int j = 1; j <= num2; j++) {
                    if ((num2 % j) == 0) {
                        cont++;
                    }
                }
                if (cont <= 2) {
                    System.out.println(num2 + (" SI es un numero primo"));
                    num2++;
                    cont = 0;

                } else {
                    System.out.println(num2 + (" NO es un numero primo"));
                    num2++;
                    cont = 0;

                }

            }


        } else {
            int cont = 0;
            for (int i = num1; i < num2; i++) {
                for (int j = 1; j <= num1; j++) {
                    if ((num1 % j) == 0) {
                        cont++;
                    }
                }
                if (cont <= 2) {
                    System.out.println(num1 + (" SI es un numero primo"));
                    num1++;
                    cont = 0;

                } else {
                    System.out.println(num1 + (" NO es un numero primo"));
                    num1++;
                    cont = 0;

                }

            }

        }
        long fin=System.nanoTime();
        double dif =(double) (fin-inicio)*1.0e-9;
        System.out.println("El progrma dura " + dif + "seg");
    }
}