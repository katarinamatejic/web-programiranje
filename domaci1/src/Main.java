import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static int sumaOcena = 0;
    public static int brZavrsenihdbrana = 0;
    public static final Object lock = "LOCK";
    public static final Object take = "Take";
    public static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
//radi mi konkurentno bloking
    public static volatile boolean keepRunning = true;

    public static void main(String[] args) {
        System.out.println("Unesite broj studenata: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        ProfesorThread p = new ProfesorThread(cyclicBarrier);

        ExecutorService pool = Executors.newFixedThreadPool(3);
        Thread timer = new Thread(new VolatileTimerThread());
        //Thread prof1 = new Thread(p);
        //Thread prof2 = new Thread(p);
        //Thread asistent = new Thread( new AsistentThread());

        //prof1.start();
//        prof2.start();
        //asistent.start();
        ProfesorThread profesorThread = new ProfesorThread(cyclicBarrier);
        ProfesorThread profesorThread1 = new ProfesorThread(cyclicBarrier);
        AsistentThread asistentThread = new AsistentThread();

        pool.execute(profesorThread);
        pool.execute(profesorThread1);
        pool.execute(asistentThread);
        timer.start();


        for (int i=1 ; i<=n ; i++){
            if (!keepRunning)
                break;
            new Thread(new StudentThread(i)).start();
        }

        while (keepRunning);

        pool.shutdownNow();
//        prof1.stop();
//        prof2.stop();
//        asistent.stop();

//        try {
//            asistent.join();
//            prof1.join();
//            prof2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (brZavrsenihdbrana==0)
            brZavrsenihdbrana++;
        System.out.println("Prosecna ocena je: " + ((sumaOcena * 1.0)/(brZavrsenihdbrana)));
        System.out.println("Broj studenata koji su branili je: " + brZavrsenihdbrana);

    }
//glob variabla keep stavljam na 5sekundi boolian
    static class VolatileTimerThread extends Thread {
        public void run() {
            try {
                Thread.sleep(5000);
                Main.keepRunning = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
