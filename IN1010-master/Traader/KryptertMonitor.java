import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class KryptertMonitor
{
    private int kanaler, ferdigeKanaler;

    private ArrayList<Melding> meldinger = new ArrayList<Melding>();
    private Lock lock = new ReentrantLock();
    private Condition harMeldinger = lock.newCondition();

    public KryptertMonitor(int antallKanaler){
        kanaler = antallKanaler;
    }

    public void leggTilMelding(Melding nyMelding){
        if (nyMelding.hentInnhold() == "INGEN FLERE MELDINGER"){
            ferdigeKanaler++;
        }
        lock.lock();
        meldinger.add(nyMelding);
        harMeldinger.signalAll();
        lock.unlock();
    }

    public Melding hentMelding(){
        lock.lock();
        Melding m = null;

        try{
            while(meldinger.size() == 0){
                harMeldinger.await();
            }

            m = meldinger.remove(0);
        }
        catch(InterruptedException e){}
        finally{
            lock.unlock();
        }

        return m;
    }

    public int hentAntMeldinger(){
        return meldinger.size();
    }

    public boolean telegraferFerdige(){
        return kanaler == ferdigeKanaler;
    }
}
