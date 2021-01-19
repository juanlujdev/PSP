package es.florida;

import org.apache.commons.mail.EmailException;

public class SendNotify implements Runnable {


    String email;
    String option;
    //Creo un constructor q me traigo la opcion elegida y el ultimo email desde donde se instancia y
    //declaro los atributos fuera y los igualo dentro para poder manejarlo abajo
    public SendNotify(String option, String lastEmail) {
        this.email=lastEmail;
        this.option=option;
    }

    @Override
    public void run() {
        //libreria importada
        mailSender mailSender = new mailSender();
        try {
            mailSender.sendMail("localhost", "Broker@gmail.com", 1025, "Option market", this.email,this.option);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
