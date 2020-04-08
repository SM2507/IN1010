public class Telegrafist implements Runnable
{
    private Kanal kanal;
    private KryptertMonitor monitor;

    public Telegrafist(Kanal knl, KryptertMonitor km){
        kanal = knl;
        monitor = km;
    }

    public void run(){
        for(String str = kanal.lytt(); str != null; str = kanal.lytt()){
            Melding melding = new Melding(str, kanal.hentId());
            monitor.leggTilMelding(melding);
        }

        monitor.leggTilMelding(new Melding("INGEN FLERE MELDINGER", 0));
    }
}
