package LegemidlerOgResepter;

class Lege{
    protected String navn;
    
    public Lege(String legeNavn){
        navn = legeNavn;
        
    }
    public Resept skrivResept(  Legemiddel legemiddel,
                                int pasiendID,
                                int reit) throws UlovligUtskrift{
        if(legemiddel instanceof PreparatA){
            throw new UlovligUtskrift(new Lege(navn), legemiddel);
        }
        return new BlaaResept(legemiddel, new Lege(navn), pasiendID, reit);

    }
    public String hentNavn(){
        return navn;
    }
    public String toString(){
        return "(Lege)Navn: " + hentNavn();
    }
}