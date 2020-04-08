
abstract class HvitResept extends Resept{  // Ingen nye egenskaper, nevnt i oppgaven
    protected String farge = "hvit";
    protected double pris;

    HvitResept( Legemiddel legemiddel, 
                Lege utskrivendeLege,
                Pasient p,
                int reit){
        super(legemiddel,utskrivendeLege,p,reit);
    }
    @Override
    public String farge(){
        return farge;
    }

    public String toString(){
        return "ID: " + hentId() +
        "\nUtskrivende lege: " + hentLege() +
        "\nLegemiddel: " + hentLegemiddel() +
        "\nFarge: " + farge() +
        "\nPris(betale): " + prisAaBetale() +
        "\nPasientID: " + hentPasientId() +
        "\nReit: " + hentReit() ;
    }
}