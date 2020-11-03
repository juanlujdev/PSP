package es.florida;

import java.io.*;

public class MailSender implements Runnable{
    public String correo;

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File ("Email.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                System.out.println("Sr/Sra "+linea+" el nuevo usuario es: "+correo);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        }
    }
