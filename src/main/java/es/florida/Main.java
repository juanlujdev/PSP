package es.florida;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static ExecutorService executorService = Executors.newFixedThreadPool(42);

    public static void main(String[] args) throws InterruptedException {
        MemberCreator memberCreator = new MemberCreator();
        MemberMonitor memberMonitor = new MemberMonitor();
        executorService.execute(memberCreator);
        executorService.execute(memberMonitor);
    }
}
