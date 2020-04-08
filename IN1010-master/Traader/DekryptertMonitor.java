import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class DekryptertMonitor
{
    private int kryptografer, ferdigeKryptografer;
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();
    private Lock lock = new ReentrantLock();
    private Condition harMeldinger = lock.newCondition();

    public DekryptertMonitor(int antKryptografer){
        kryptografer = antKryptografer;
    }

    public void leggTilMelding(Melding nyMelding){
        if (nyMelding.hentInnhold() == "INGEN FLERE MELDINGER"){
            ferdigeKryptografer++;
        }
        else{
            lock.lock();
            meldinger.add(nyMelding);
            harMeldinger.signalAll();
            lock.unlock();
        }
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
        catch (InterruptedException e){}
        finally{
            lock.unlock();
            return m;
        }
    }

    public boolean alleFerdige(){
        return kryptografer == ferdigeKryptografer;
    }

    public ArrayList<Melding> hentMeldinger(){
        return meldinger;
    }
}
