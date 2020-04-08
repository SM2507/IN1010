package LegemidlerOgResepter;

class PreparatA extends Legemiddel{
    private int styrke;

    public PreparatA(String navn, 
                double pris, 
                double virkestoff, 
                int styrkeKons){
        super(navn, pris, virkestoff);
        styrke = styrkeKons;

    }
    public int hentNarkotiskStyrke(){
        return styrke;
    }
    
    public String toString(){
        return  "Id nummer: " + hentId() + "\nNavn: " + hentNavn() 
        + "\nPris: " + hentPris() + "\nVirkestoff (mg): " + hentVirkestoff()
        + "\nStryke: " + hentNarkotiskStyrke();
    }
}