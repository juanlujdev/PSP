package es.florida;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	int cont=0;
		List<Integer> arrayInverso = new ArrayList<>();
		Scanner entrada=new Scanner(System.in);


	App app=new App();

	app.sayHellow();
	App.arrayNombre();
	App.sumaPar();
	App.factorial();
	int array[]={5,2,7,9,1};
	app.AE1_5NumeroMayorArray(array);
	do {

		System.out.println("enscriba un numero: ");
		int number=entrada.nextInt();
		cont ++;
		arrayInverso.add(number);
	}
	while(cont<5);
		app.AE1_6ArrayInverso(arrayInverso);

		System.out.printf("escriba su nombre: ");
		String name=entrada.next();
		System.out.println("años de experiencia?: ");
		int years=entrada.nextInt();
		app.AE1_7Salario(name,years);

		app.AE1_8Primos();
    }


}