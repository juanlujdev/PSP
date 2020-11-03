package es.florida;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MemberCreator memberCreator=new MemberCreator();
        MemberMonitor memberMonitor =new MemberMonitor();
        Thread memberCreatorThread = new Thread(memberCreator);
        Thread memberMonitorThread=new Thread(memberMonitor);
        memberCreatorThread.start();
        memberMonitorThread.start();
        memberMonitorThread.join();
        memberCreatorThread.join();
    }
}
