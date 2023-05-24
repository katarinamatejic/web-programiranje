import java.util.Random;

public class StudentThread implements Runnable{
    Integer broj;
    public StudentThread(int i){ this.broj = i;}
    @Override
    public void run() {
        double sleepStudenta = Math.random()*1000;


        try {
            Thread.sleep((long) sleepStudenta);
                Main.queue.put("Thread: <" + Thread.currentThread().getId() + "> Arrival: <" + sleepStudenta + ">");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
