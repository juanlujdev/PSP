package es.florida;

public class Consumer implements Runnable{

    private final MemberCreator memberCreator;
    private final MailSender mailSender;

    public Consumer(MemberCreator memberCreator, MailSender mailSender) {
        this.memberCreator=memberCreator;
        this.mailSender=mailSender;
    }

    @Override
    public synchronized void run() {
//bucle infinito siempre espero 3 segundos para comprobar si hay nuevo usuario
        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int emailsSize=0;
            //si el tamaño de la lista de email es mayor que 0 es que hay un nuevo email
            // y borro el ultimo metido
            if (memberCreator.emailsSize()>emailsSize){
                System.out.println("El nuevo usuario email es: "+memberCreator.LastEmail());
                //quiero llamar a un metodo dentro de mailsender que le paso el ultimo email
                // y asi pueda enviar correos
                mailSender.mailSender(memberCreator.LastEmail());
                //String consumedMessage=this.memberCreator.consume();
                //System.out.println("Message consumed: "+consumedMessage );
                memberCreator.deleteEmail();
                //memberCreator..pop();
            }
            else {
                System.out.println("no hay nuevo usuario email");
            }

        }
    }
}
