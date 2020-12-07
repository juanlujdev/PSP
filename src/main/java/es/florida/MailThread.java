package es.florida;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

public class MailThread implements Runnable {
    public Email email;


    @Override
    public void run(){
        try {
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
