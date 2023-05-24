import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProfesorThread implements Runnable{
    //cyclic stavljam kao pomoc pri sinhronizaciji koja omogucava threadu da svi cekaju jedni na druge
    CyclicBarrier cyclicBarrier;
    public ProfesorThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {

        try {
            while (true) {
                Random random= new Random();
                int sleepProfesor = (random.nextInt(5,11)*100);
                int ocena;
                String  studentString2;
                  studentString2 = Main.queue.take();

                cyclicBarrier.await();
                Thread.sleep((long) sleepProfesor);
                ocena = (int)Math.floor(Math.random()*(10-5+1)+5);
                LocalTime vremePocetka = LocalTime.now();
                System.out.println("Student " + studentString2 + "\nProf: <" + Thread.currentThread().getId() + "> TTC: <"+ Thread.currentThread().getId() + ">:<" + vremePocetka +"> Score: <" + ocena + ">");

                //mozemo sve ovo i sa AtomicInt
                synchronized (Main.lock){
                    Main.brZavrsenihdbrana++;
                    Main.sumaOcena+=ocena;
            }
        }
    } catch (InterruptedException | BrokenBarrierException exception){
            System.out.println("Prekinuta odbrana");
        }
    }
}
