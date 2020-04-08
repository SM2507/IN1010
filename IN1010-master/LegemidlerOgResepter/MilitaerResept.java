package LegemidlerOgResepter;

class MilitaerResept extends HvitResept{
    MilitaerResept( Legemiddel legemiddel, 
                Lege utskrivendeLege,
                int pasientId,
                int reit){
        super(legemiddel,utskrivendeLege,pasientId,reit);
    }

    @Override
    public String farge(){
        return farge;
    }

    public double prisAaBetale(){
        return 0;
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