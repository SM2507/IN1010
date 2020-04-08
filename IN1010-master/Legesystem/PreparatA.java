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
        return hentNavn() 
        + "\nPris: " + hentPris() + "\nVirkestoff (mg): " + hentVirkestoff()
        + "\nStryke: " + hentNarkotiskStyrke();
    }
}