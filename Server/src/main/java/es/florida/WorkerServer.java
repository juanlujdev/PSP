package es.florida;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerServer implements Runnable {
    //pongo 2 hilos a la escucha como dice el enunciado(parte de abajo del enunciado)
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    //Le asigno un Socket cuando llega a nuestro constructor el cliente desde el Server
    private Socket connection;
    //creo una lista donde almaceno los nuevos usuarios que se registren
    LinkedList<String> usersList = new LinkedList<>();
    //lista donde recargo todos los usuarios excepto el q hay q eliminar, es la unica manera para borrar
    LinkedList<String> ListDeleteUser = new LinkedList<>();
    //Recibo una conexion en el constructor(quiere decir que por cada hilo(son 5) crea una consulta TELNET)
    public WorkerServer(Socket clientConnetion) {
        //Le asigno ese Socket al cliente que viene desde el Server
        this.connection = clientConnetion;
    }

    BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
    User user = new User();
    String truePassword = "uRd1Rw4PTHTjFZt3iXWpNA==";
    String encryptPassword = encryptor.encryptPassword(truePassword);
    File fileBlock = new File("ServerBlock.txt");
    File file = new File("Email.txt");
    //es una clase de Java para leer el texto de una secuencia de entrada
    BufferedReader br = null;
    //clase usada para leer archivos de caracteres
    FileReader fr = null;


    @Override
    public void run() {
        if (file.exists()) {
            try {
                loadList();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//instancion sendNotify para el envio del correo y la opcion utilizada
        try {
            //Escribir cosas en el telnet, hace visible los datos en el cmd (Telnet)
            PrintWriter writer = buildWriter(connection);
            //Para mostrar menu segun halla fichero.

//            showMenu(writer);
            writer.println("menu");

            while (true) {
                //el mensaje que nos llega del cliente (servidor) podemos procesarlo como queramos de la siguiente forma
                BufferedReader reader = buildReader(connection);
                String line;
                //con readLine leo linea x linea
                line = reader.readLine();
                switch (line) {
                    case "1":
                        System.out.println(giveMeDateNow() + " Pulsa la opcion 1");
                        writer.println("Elija la operacion deseada");
                        showMenu(writer);
                        break;
                    case "2":
                        createUser(writer, reader);
                        showMenu(writer);
                        break;
                    case "3":
                        deleteUser(writer, reader);
                        //metodo para igualar la lista de delete a la de userList xq luego al eliminar un usuario
                        //no tienen los mismos datos y no puedo trabajar con ellas
                        deleteUserEqualUserList(ListDeleteUser);
                        showMenu(writer);
                        break;
                    case "4":
                        lock(writer, reader);
                        showMenu(writer);
                        break;
                    case "5":
                        unLock(writer, reader);
                        showMenu(writer);
                        break;
                    case "6":
                        blockServer(writer, reader);
                        showMenu(writer);
                        break;
                    case "7":
                        System.out.println(giveMeDateNow() + "Desconectar.");
                        connection.close();
                        writer.println("Conexion desconectada.");
                        System.out.println(giveMeDateNow() + "desconexion del servidor.");
                        break;
                    default:
                        writer.println("no existe esa opcion.");
                        showMenu(writer);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void blockServer(PrintWriter writer, BufferedReader reader) throws IOException {
        System.out.println(giveMeDateNow() + " Pulsar desbloquear servidor:");
        writer.println("Escribe codigo de desbloqueo:");
        String password2;
        password2 = reader.readLine();
        System.out.println(password2);
        StrongTextEncryptor superEncryptor= new StrongTextEncryptor();
        superEncryptor.setPassword("algo");
        String password = superEncryptor.decrypt(password2);
        String password1= superEncryptor.decrypt(truePassword);

        if (password.equals(password1)) {
            writer.println("La clave coincide");
            fileBlock.delete();
            System.out.println("la clave coincide");

            System.out.println(giveMeDateNow() + "introducido codigo de desbloqueo correcto");
        } else {
            writer.println("Clave incorrecta");
            System.out.println("Clave incorrecta");
        }
    }

    private void unLock(PrintWriter writer, BufferedReader reader) throws IOException {
        System.out.println(giveMeDateNow() + " Pulsa opcion bloquear servidor");
        writer.println("Codigo de bloqueo de servidor:");
        String password;
        password = reader.readLine();
        boolean matches = encryptor.checkPassword(password, encryptPassword);
        if (matches) {
            System.out.println("la clave coincide.");
            writer.println("la clave coincide, servidor bloqueado");
            fileBlock.createNewFile();
            System.out.println(giveMeDateNow() + "introducido codigo de bloqueo:" + password);
        } else {
            System.out.println("Clave incorrecta.");
        }
    }

    private void lock(PrintWriter writer, BufferedReader reader) throws IOException {
        if (fileBlock.exists()) {
            writer.println("El servidor esta bloqueado");
            System.out.println(giveMeDateNow() + " El servidor esta bloqueado.");
        } else {
            optionBuySell(writer, reader);
        }
    }

    private void optionBuySell(PrintWriter writer, BufferedReader reader) throws IOException {
        String line;
        System.out.println(giveMeDateNow() + " Pulsa opcion Compra/Venta");
        writer.println("1- BUY");
        writer.println("2- SELL");
        writer.println("3- Salir.");
        line = reader.readLine();
        switch (line) {
            case "1":
                String buyMessage = "BUY-";
                String acronimo;
                writer.println("Escribe el acronimo.");
                acronimo = reader.readLine();
                String sendMessage = buyMessage + acronimo;
                for (String s : usersList) {
                    String lastEmail = getEmailST(s);
                    executorService.execute(new SendNotify(sendMessage, lastEmail));
                }
                System.out.println(giveMeDateNow() + " se manda: " + sendMessage);
                break;
            case "2":
                String sellMessage = "SELL-";
                String acronimo2;
                writer.println("Escribe acronimo.");
                acronimo2 = reader.readLine();
                String sendMessage2 = sellMessage + acronimo2;
                for (String s : usersList) {
                    String lastEmail = getEmailST(s);
                    executorService.execute(new SendNotify(sendMessage2, lastEmail));
                }
                System.out.println(giveMeDateNow() + " se manda: " + sendMessage2);
                break;
            case "3":
                System.out.println(giveMeDateNow() + " salimos de compra/venta");
                break;
            default:
                writer.println("no existe esa opcion");
                break;
        }
    }

    private void deleteUser(PrintWriter writer, BufferedReader reader) throws IOException {
        System.out.println(giveMeDateNow() + "Pulsa opcion eliminar usuario.");
        writer.println("Email a eliminar:");
        ListDeleteUser = new LinkedList<>();
        String deleteEmail;
        deleteEmail = reader.readLine();
        //File file = new File("Email.txt");
        //borro el fichero y lo vuelvo a crear
//        file.delete();
//        file.createNewFile();
        //visualizo cada linea de la lista
        for (String s : usersList) {
            //me traigo el mail con el metodo getEmailST
            String emailMatch = getEmailST(s);
            //si el mail que traigo es distinto que el q vamos a borrar guardo la linea en una nueva
            //lista y la paso al nmetodo q guarta en el fichero
            if (!emailMatch.equals(deleteEmail)) {
                //guardar una lista nueva y pasarlo a Email.txt
                ListDeleteUser.add(s);
                user.printEmail(ListDeleteUser);
            } else {
                System.out.println(giveMeDateNow() + "el usuario " + deleteEmail + " ha sido eliminado.");
                writer.print(deleteEmail + " ha sido eliminado.");
            }
        }
    }

    private void createUser(PrintWriter writer, BufferedReader reader) throws IOException {
        System.out.println(giveMeDateNow() + "Pulsa opcion crear usuario ");

        String name;
        String surname;
        String email;
        writer.println("Nombre:");
        name = reader.readLine() + ";";
        writer.println("Apellidos:");
        surname = reader.readLine() + ";";
        writer.println("Email:");
        email = reader.readLine() + ";";
        String newUser = name + surname + email;
        //instancia de User y pasarle el usuario
        usersList.add(newUser);
        user.printEmail(usersList);
        System.out.println(giveMeDateNow() + " Se crea nuevo usuario " + name + " " + surname + " " + email);
        writer.print(newUser+" ha sido creado.");
    }

    private void loadList() throws IOException {
        fr = new FileReader("./Email.txt");
        br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            usersList.add(linea);
        }
    }


    private void deleteUserEqualUserList(LinkedList<String> ListDeleteUser) {
        usersList = new LinkedList<>();
        for (String s : ListDeleteUser) {
            usersList.add(s);
        }
    }

    private String getEmailST(String s) {
        //StringTokenizer sirve para quitar de la cadena que le pasamos lo que queremos,
        //le paso las lineas y quitamos la separacion de ";", como sabemos que la posicion
        //del email es la 3ª devolvemos el nextToken() la tercera vez.
        StringTokenizer tokenizer = new StringTokenizer(s, ";");
        tokenizer.nextToken();
        tokenizer.nextToken();
        return tokenizer.nextToken();
    }

    private void showMenu(PrintWriter writer) {
        writer.println("1- OPERACIONES DISPONIBLES");
        writer.println("2- Crear usuario");
        writer.println("3- Eliminar usuario");
        writer.println("4- Notificacion para usuario");
        writer.println("5- Bloquear servidor");
        writer.println("6- Desbloquar servidor");
        writer.println("7- Desconectar.");
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
