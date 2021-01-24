package es.florida;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class mailSender {

    public void sendMail(String localhost, String user, int MAILDEV_PORT, String subjec, String destin, String message) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(localhost);
        email.setFrom(user);
        email.setSmtpPort(MAILDEV_PORT);
        email.setSubject(subjec);
        email.addTo(destin);
        email.setMsg(message);
        email.send();
    }
}

