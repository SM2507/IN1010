import java.util.ArrayList;
import java.io.*;
import java.io.FileNotFoundException;

class Operasjonsleder implements Runnable
{
    private ArrayList<ArrayList<String>> arkiv = new ArrayList<ArrayList<String>>();
    private DekryptertMonitor monitor;

    public Operasjonsleder(int antallKanaler, DekryptertMonitor mntr){
        monitor = mntr;
        for (int i = 0; i < antallKanaler; i++){
            arkiv.add(new ArrayList<String>());
        }
    }

    public void run(){
        while(!monitor.alleFerdige()){
            try{
                Thread.sleep(5000);
            }
            catch(InterruptedException e){}
        }


        ArrayList<Melding> meldinger = monitor.hentMeldinger();

        int stoersteSekvensnummer = 0;
        for (Melding melding: meldinger){
            if (melding.hentSekvens() > stoersteSekvensnummer){
                stoersteSekvensnummer = melding.hentSekvens();
            }
        }

        for (int i = 0; i <= stoersteSekvensnummer; i++){
            for (Melding melding : meldinger){
                if (melding.hentSekvens() == i){
                    ArrayList<String> hentetListe = arkiv.get(melding.hentKanalId()-1);
                    hentetListe.add(melding.hentInnhold());
                }
            }
        }
        skrivTilFil(arkiv);
    }

    private void skrivTilFil(ArrayList<ArrayList<String>> liste){
        for(int i = 0; i < liste.size(); i++){
            PrintWriter pwriter = null;

            try{
                pwriter = new PrintWriter("kanal" + i + ".txt");
            }
            catch(FileNotFoundException e){}

            for(String skrift: liste.get(i)){
                pwriter.println();
                pwriter.println(skrift);
                pwriter.println();
            }
            pwriter.close();
        }
    }
}
