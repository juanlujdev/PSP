package es.florida;

public class MemberMonitor {
    public static void main(String[] args) throws InterruptedException {
        MemberCreator memberCreator=new MemberCreator();
        Consumer consumer = new Consumer(memberCreator);
        MailSender mailSender=new MailSender(memberCreator);
        Thread mailSenderThread = new Thread(memberCreator);
        Thread memberCreatorThread = new Thread(memberCreator);
        Thread consumerThread=new Thread(consumer);
        memberCreatorThread.start();
        consumerThread.start();
        memberCreatorThread.join();
        consumerThread.join();
    }
}
