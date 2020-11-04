package es.florida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        ProcessBuilder memberMonitorProcess = new ProcessBuilder("java", "-cp", "C:\\Users\\juani\\OneDrive\\Escritorio\\PSP_AE2\\out\\production\\PSP_AE2", "es.florida.MemberMonitor");
        ProcessBuilder memberCreatorProcess = new ProcessBuilder("java", "-cp", "C:\\Users\\juani\\OneDrive\\Escritorio\\PSP_AE2\\out\\production\\PSP_AE2", "es.florida.MemberCreator");
        Process memberMonitor = memberMonitorProcess.start();
        memberCreatorProcess.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(memberMonitor.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}

