import java.time.LocalTime;
import java.util.Random;

public class AsistentThread implements Runnable{
    @Override
    public void run() {
        Random r = new Random();
        int vremeOdbrane = (r.nextInt(5,11)*100);
        int ocena;

        try {
            while (true){
                String  studentString1;
                   studentString1 = Main.queue.take();
                Thread.sleep((long) vremeOdbrane);
                ocena = (int)Math.floor(Math.random()*(10-5+1)+5);
                LocalTime vremePocetka = LocalTime.now();
                System.out.println("Student " + studentString1 + "\nProf: <" + Thread.currentThread().getId() + "> TTC: <"+ Thread.currentThread().getId() + ">:<" + vremePocetka +"> Score: <" + ocena + ">");


                synchronized (Main.lock){
                    Main.brZavrsenihdbrana++;
                    Main.sumaOcena+=ocena;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Prekinuta odbrana");
        }
    }
}
