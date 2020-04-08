public class HvitRute extends Rute{
    public HvitRute(int rad, int kol){
        super(rad, kol);
    }
    @Override public char tilTegn(){return '.';}
    public String toString(){return String.valueOf(tilTegn());}

    @Override
    public void gaa(Rute rute, String vei){
        vei += String.format("(%d, %d) --> ", kolonne, rad);

        if(!misbruktRute){
            misbruktRute = true;
            if(this.aapning )
                this.gaa(rute, vei);
            naboNord.gaa(naboNord, vei);
            naboOst.gaa(naboOst, vei);
            naboVest.gaa(naboVest, vei);
            naboSor.gaa(naboSor, vei);
    
            misbruktRute = false;
        }        
    }
}