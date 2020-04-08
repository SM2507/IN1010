public class Kryptograf implements Runnable
{
    private KryptertMonitor kmonitor;
    private DekryptertMonitor dkmonitor;

    public Kryptograf(KryptertMonitor kryptertMonitor, DekryptertMonitor dekryptertMonitor){
        kmonitor = kryptertMonitor;
        dkmonitor = dekryptertMonitor;
    }

    public void run(){

        while(!kmonitor.telegraferFerdige()){
            while(kmonitor.hentAntMeldinger() > 0){
                Melding melding = kmonitor.hentMelding();
                String innhold = melding.hentInnhold();

                if (melding.hentKanalId() != 0){
                    System.out.println(String.format("Sekvens: %d, kanal: %d", melding.hentSekvens(), melding.hentKanalId()));
                }

                if(innhold == "INGEN FLERE MELDINGER"){
                
                }
                else{
                    String dekryptertInnhold = Kryptografi.dekrypter(innhold);
                    melding.settInnhold(dekryptertInnhold);

                    dkmonitor.leggTilMelding(melding);
                }
            }
        }
        dkmonitor.leggTilMelding(new Melding("INGEN FLERE MELDINGER", 0));
    }
}
