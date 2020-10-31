package es.florida;

public class Consumer implements Runnable{

    private final MemberCreator memberCreator;

    public Consumer(MemberCreator memberCreator) {
        this.memberCreator=memberCreator;
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
//si el tamaño de la lista de email es mayor que 0 es que hay un nuevo email y borro el ultimo metido
            if (memberCreator.emailsSize()>emailsSize){
                System.out.println("El nuevo usuario email es: "+memberCreator.LastEmail());
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
