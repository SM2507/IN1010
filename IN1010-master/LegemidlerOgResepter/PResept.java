package LegemidlerOgResepter;

class PResept extends HvitResept{
    private static int rabatt = 108;

    PResept(Legemiddel legemiddel, 
            Lege utskrivendeLege,
            int pasientId){
        super(legemiddel,utskrivendeLege,pasientId,3);
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