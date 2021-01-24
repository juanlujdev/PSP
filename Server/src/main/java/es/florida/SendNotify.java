package es.florida;

import org.apache.commons.mail.EmailException;

public class SendNotify implements Runnable {

    String email;
    String option;

    public SendNotify(String option, String lastEmail) {
        this.email = lastEmail;
        this.option = option;
    }

    @Override
    public void run() {
        mailSender mailSender = new mailSender();
        try {
            mailSender.sendMail("localhost", "Broker@gmail.com", 1025, "Option market", this.email, this.option);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
