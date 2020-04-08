import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

class Hovedprogram//Hva menes med at hver melding skal ha et sekvensnummer da vi ikke får rre pre-koden?
{
    public static void main(String[] args)
    {
        int antOpersjonsleder = 1; //alltid én.

        int antTelegrafister = 3;
        int antKryptografer = 5;

        Operasjonssentral ops = new Operasjonssentral(antTelegrafister);
        Kanal[] kanaler = ops.hentKanalArray();//denne er nå lik: {ojd, dukkehjem, grunnloven}.

        KryptertMonitor kMonitor = new KryptertMonitor(antTelegrafister);
        DekryptertMonitor dkMonitor = new DekryptertMonitor(antKryptografer);


        for (int i = 0; i < antTelegrafister; i++)
        {
            Runnable telegrafistRun = new Telegrafist(kanaler[i], kMonitor);
            Thread telegrafist = new Thread(telegrafistRun);
            telegrafist.start();
        }

        Runnable kryptografRun = new Kryptograf(kMonitor, dkMonitor);
        for (int i = 0; i < antKryptografer; i++)
        {
            Thread kryptograf = new Thread(kryptografRun);
            kryptograf.start();
        }

        Runnable operasjonslederRun = new Operasjonsleder(antTelegrafister, dkMonitor);
        Thread operasjonsleder = new Thread(operasjonslederRun);
        operasjonsleder.start();

        //Kjør med -encoiding "UTF-8"
    }
}
