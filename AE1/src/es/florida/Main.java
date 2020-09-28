package es.florida;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	int cont=0;
		List<Integer> arrayInverso = new ArrayList<>();


	App app=new App();

	app.sayHellow();
	App.arrayNombre();
	App.sumaPar();
	App.factorial();
	int array[]={5,2,7,9,1};
	app.AE1_5NumeroMayorArray(array);
	do {
		Scanner entrada=new Scanner(System.in);
		System.out.println("enscriba un numero: ");
		int number=entrada.nextInt();
		cont ++;
		arrayInverso.add(number);
	}
	while(cont<5);
		app.AE1_6ArrayInverso(arrayInverso);
    }


}