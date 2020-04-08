public class Aapning extends HvitRute{
    public Aapning(int rad, int kol){
        super(rad, kol);
        aapning = true;
    }
    @Override
    public void gaa(Rute rute, String vei){
        vei += String.format("(%d, %d) \n", kolonne, rad);
        if(labRef.hentUtskrift())
        labRef.utvei.leggTil(vei);
    }

}