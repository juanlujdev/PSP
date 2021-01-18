package es.florida;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class WorkerServer implements Runnable {

    //Le asigno un Socket cuando llega a nuestro constructor el cliente desde el Server
    private Socket connection;
    //creo una lista donde almaceno los nuevos usuarios que se registren
    LinkedList<String> usersList=new LinkedList<>();

    //Recibo una conexion en el constructor(quiere decir que por cada hilo(son 5) crea una consulta TELNET)
    public WorkerServer(Socket clientConnetion) {
        //Le asigno ese Socket al cliente que viene desde el Server
        this.connection = clientConnetion;
    }

    @Override
    public void run() {

        try {

            while (true) {
                //Escribir cosas en el telnet, hace visible los datos en el cmd (Telnet)
                PrintWriter writer = buildWriter(connection);
                //Para enviar algo al cliente
                showMenu(writer);
                //el mensaje que nos llega del cliente (servidor) podemos procesarlo como queramos de la siguiente forma
                BufferedReader reader = buildReader(connection);
                String line;
                //con readLine leo linea x linea
                line = reader.readLine();
                switch (line) {
                    case "1":
                        System.out.println(giveMeDateNow() + " Pulsa la opcion 1");
                        writer.println("Elija la operacion deseada.");
                        showMenu(writer);
                        break;
                    case "2":
                        System.out.println(giveMeDateNow() + "Pulsa opcion crear usuario");
                        writer.println("Nombre: ");
                        String name;
                        name = reader.readLine()+";";
                        writer.println("Apellidos: ");
                        String surname;
                        surname = reader.readLine()+";";
                        writer.println("Email: ");
                        String email;
                        email = reader.readLine()+";";
                        String newUser=name+surname+email;
                        //Arrancar hilo de User y pasarle el usuario
                        System.out.println(giveMeDateNow() + " Se crea nuevo usuario " + name + " " + surname + " " + email);
                        usersList.add(newUser);
                        runNewUserThread(usersList);
                        showMenu(writer);
                        break;
                    case "3":
                        System.out.println(giveMeDateNow() + "Pulsa opcion eliminar usuario");
                        writer.println("Email a eliminar: ");
                        String deleteEmail;
                        deleteEmail = reader.readLine();
                        System.out.println(giveMeDateNow() + "Elimina usuario: " + deleteEmail);
                        showMenu(writer);
                        break;
                    case "4":
                        System.out.println(giveMeDateNow() + " Pulsa opcion Compra/Venta");
                        writer.println("Escribe BUY O SELL SEGUIDO DE - Y ACRONIMO: ");
                        String compra;
                        compra = reader.readLine();
                        System.out.println(giveMeDateNow() + "operacion realizada: " + compra);
                        showMenu(writer);
                        break;
                    case "5":
                        System.out.println(giveMeDateNow() + " Pulsa opcion bloquear servidor");
                        writer.println("Codigo de bloqueo de servidor: ");
                        String code;
                        code = reader.readLine();
                        System.out.println(giveMeDateNow() + "introducido codigo de bloqueo: " + code);
                        showMenu(writer);
                        break;
                    case "6":
                        System.out.println(giveMeDateNow() + " Pulsar desbloquear servidor: ");
                        writer.println("Escribe codigo de desbloqueo: ");
                        String code2;
                        code2 = reader.readLine();
                        System.out.println(giveMeDateNow() + " Se desbloquea el servidor: " + code2);
                        showMenu(writer);
                        break;
                    case "7":
                        System.out.println(giveMeDateNow() + "Desconectar");
                        showMenu(writer);
                        break;
                    default:
                        System.out.println("fin");
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        //el mensaje que nos llega del cliente (servidor) podemos procesarlo como queramos de la siguiente forma
        //con readLine leo linea x linea
//        String line;
//        while (true) {
//            try {
//
//                if (!((line = reader.readLine()) != null)) break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println(line);
//            //la peticion que me venga del cliente con ese line tendre que procesarlo, llamaria a una funcion con
//            //esa line y empezariamos a procesar las peticiones del cliente.
//        }

    }

    private void runNewUserThread(LinkedList usersList) throws InterruptedException {
        User user=new User();
        Thread userThread=new Thread(user);
        userThread.start();
        user.user= usersList;
        userThread.join();
    }

    private void showMenu(PrintWriter writer) {
        writer.println("1- OPERACIONES DISPONIBLES");
        writer.println("2- Crear usuario");
        writer.println("3- Eliminar usuario");
        writer.println("4- Notificacion para usuario");
        writer.println("5- Bloquear servidor");
        writer.println("6- Desbloquar servidor");
        writer.println("7- Desconectar");
    }

    private String giveMeDateNow() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss");
        String date = dateFormat.format(new Date());
        return date;
    }

    //Metodo para preparar lo que se va a recibir de Telnet
    private BufferedReader buildReader(Socket connection) throws IOException {
        //Para recoger las cosas del cliente de telnet
        InputStream inputStream = connection.getInputStream();
        //Para preparalo para el papel para q luego lo lea
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //donde leerlo,con BufferedReader leemos lo que nos viene de cliente
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader;
    }

    //Metodo para preparar lo que hay que enviar al telnet
    private PrintWriter buildWriter(Socket connection) throws IOException {
        //Para enviarle cosas al cliente OutputStream(lo que piensas decir)
        OutputStream outputStream = connection.getOutputStream();
        //Para escribirlo(el papel para escribirlo)
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        //el que printea (tiene la capacidad de hacer visible lo que has escrito)(la impresora donde escribe)
        PrintWriter printer = new PrintWriter(outputStreamWriter, true);
        return printer;
    }
}
