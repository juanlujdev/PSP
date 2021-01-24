package es.florida;

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
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private Socket connection;
    LinkedList<String> usersList = new LinkedList<>();
    LinkedList<String> ListDeleteUser = new LinkedList<>();

    public WorkerServer(Socket clientConnetion) {
        this.connection = clientConnetion;
    }

    User user = new User();
    String truePassword = "uRd1Rw4PTHTjFZt3iXWpNA==";
    File fileBlock = new File("ServerBlock.txt");
    File file = new File("Email.txt");
    BufferedReader br = null;
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
        try {
            PrintWriter writer = buildWriter(connection);
            writer.println("pulsa 1 para ver menu");

            while (true) {
                BufferedReader reader = buildReader(connection);
                String line;
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
                        deleteUserEqualUserList(ListDeleteUser);
                        showMenu(writer);
                        break;
                    case "4":
                        lock(writer, reader);
                        showMenu(writer);
                        break;
                    case "5":
                        unLock(writer, reader);
                        break;
                    case "6":
                        blockServer(writer, reader);
                        break;
                    case "7":
                        System.out.println(giveMeDateNow() + "Desconectar.");
                        connection.close();
                        writer.println("Conexion desconectada.");
                        System.out.println(giveMeDateNow() + "desconexion del servidor.");
                        break;
                    case "":
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
        StrongTextEncryptor superEncryptor = new StrongTextEncryptor();
        superEncryptor.setPassword("algo");
        String password = superEncryptor.decrypt(password2);
        String password1 = superEncryptor.decrypt(truePassword);
        if (password.equals(password1)) {
            fileBlock.delete();
            System.out.println("la clave coincide");
            System.out.println(giveMeDateNow() + "introducido codigo de desbloqueo correcto");
            writer.println("servidor desbloqueado, pulse 1 para mostrar menu");
        } else {
            writer.println("Clave incorrecta, pulse 1 para mostrar menu");
            System.out.println("Clave incorrecta");
        }
    }

    private void unLock(PrintWriter writer, BufferedReader reader) throws IOException {
        System.out.println(giveMeDateNow() + " Pulsa opcion bloquear servidor");
        writer.println("Codigo de bloqueo de servidor:");
        String password2;
        password2 = reader.readLine();
        StrongTextEncryptor superEncryptor = new StrongTextEncryptor();
        superEncryptor.setPassword("algo");
        String password = superEncryptor.decrypt(password2);
        String password1 = superEncryptor.decrypt(truePassword);
        if (password.equals(password1)) {
            System.out.println("la clave coincide.");
            fileBlock.createNewFile();
            writer.println("servidor bloqueado, pulse 1 para mostrar menu");
            System.out.println(giveMeDateNow() + "introducido correctamente codigo de bloqueo:");
        } else {
            writer.println("password incorrecto, pulse 1 para mostrar menu");
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
        for (String s : usersList) {
            String emailMatch = getEmailST(s);
            if (!emailMatch.equals(deleteEmail)) {
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
        usersList.add(newUser);
        user.printEmail(usersList);
        System.out.println(giveMeDateNow() + " Se crea nuevo usuario " + name + " " + surname + " " + email);
        writer.print(newUser + " ha sido creado.");
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

    private BufferedReader buildReader(Socket connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader;
    }

    private PrintWriter buildWriter(Socket connection) throws IOException {
        OutputStream outputStream = connection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        PrintWriter printer = new PrintWriter(outputStreamWriter, true);
        return printer;
    }
}
