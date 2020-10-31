package es.florida;

import java.io.IOException;

public class MemberMonitor {
    public static void main(String[] args) throws InterruptedException, IOException {
        MemberCreator memberCreator=new MemberCreator();
        MailSender mailSender=new MailSender(memberCreator);
        //para acceder a consumer desde memberCreator y MailSender
        Consumer consumer = new Consumer(memberCreator,mailSender);
        Thread mailSenderThread = new Thread(mailSender);
        Thread memberCreatorThread = new Thread(memberCreator);
        Thread consumerThread=new Thread(consumer);
        memberCreatorThread.start();
        consumerThread.start();
        mailSenderThread.start();
        memberCreatorThread.join();
        consumerThread.join();
        mailSenderThread.join();
    }
}
