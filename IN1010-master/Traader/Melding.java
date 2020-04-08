public class Melding
{
    private String innhold;
    private int kanalId;

    private static int sekvensCounter = 0;
    private int sekvens;

    Melding(String beskjed, int knlID){
        innhold = beskjed;
        kanalId = knlID;

        sekvens = sekvensCounter; 
        sekvensCounter++;
    }

    public String hentInnhold(){
        return innhold;
    }

    public void settInnhold(String nyttInnhold){
        innhold = nyttInnhold;
    }

    public int hentKanalId(){
        return kanalId;
    }

    public int hentSekvens(){
        return sekvens;
    }
}
