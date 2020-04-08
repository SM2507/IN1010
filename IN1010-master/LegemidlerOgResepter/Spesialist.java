package LegemidlerOgResepter;

class Spesialist extends Lege implements GodkjenningsFritak{

    private int kontrollId;

    public Spesialist(String navn, int kontrollID){
        super(navn);
        kontrollId = kontrollID;
    }

    @Override
    public Resept skrivResept(  Legemiddel legemiddel,
                                int pasiendID,
                                int reit){
        return new BlaaResept(legemiddel, new Spesialist(navn, kontrollId), pasiendID, reit);
    }

    @Override
    public int hentKontrollID(){
        return kontrollId;
    }
    @Override
    public String toString(){
        return "(Lege)Navn: " + hentNavn() +
        "\nKontrollID: " + hentKontrollID();
    }
}