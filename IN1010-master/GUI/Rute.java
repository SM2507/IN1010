public abstract class Rute{
    protected Labyrint labRef;
    public int kolonne, rad;
    public boolean misbruktRute = false;
    public boolean aapning = false;

    public Rute naboNord, naboSor, naboOst, naboVest;   
    public Rute(int radnr, int kolnr){
        kolonne = kolnr; rad = radnr;// labRef = l;
    }
    public void settLabyrint(Labyrint lab){
        labRef = lab;
    }

    public void settNabo(){
        if(rad > 0)
            naboNord = labRef.hentLabyrint()[rad-1][kolonne];
        if(rad < labRef.labRad-1)
            naboSor = labRef.hentLabyrint()[rad+1][kolonne];
        if(kolonne > 0)
            naboVest = labRef.hentLabyrint()[rad][kolonne-1];
        if(kolonne < labRef.labKol-1)
            naboOst = labRef.hentLabyrint()[rad][kolonne+1];    
    }

    public void finnUtvei(){gaa(this, "");}

    public abstract char tilTegn();
    public abstract void gaa(Rute rute, String vei);

}   