package LegemidlerOgResepter;

class BlaaResept extends Resept{
protected String farge = "blaa";
protected double pris;

    BlaaResept( Legemiddel legemiddel, 
                Lege utskrivendeLege,
                int pasientId,
                int reit){
        super(legemiddel,utskrivendeLege,pasientId,reit);
    }

    @Override
    public String farge(){return farge;}

    @Override
    public double prisAaBetale(){
        return (0.25*legemiddel.hentPris());
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