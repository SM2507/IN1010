
class PResept extends HvitResept{
    private static int rabatt = 108;
    private static int reit = 3;

    PResept(Legemiddel legemiddel, 
            Lege utskrivendeLege,
            Pasient p){
        super(legemiddel,utskrivendeLege,p,reit);
        }
    @Override
    public String farge(){
        return farge;
    }
    
    @Override
    public double prisAaBetale(){
        if (legemiddel.hentPris() < rabatt){
            return 0;
        }
        return (legemiddel.hentPris() - rabatt);
        
    }
    @Override
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