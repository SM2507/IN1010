class PreparatC extends Legemiddel{
    PreparatC(String navn, double pris, double virkestoff){
        super(navn,pris,virkestoff);
    }
    public String toString(){
        return hentNavn() 
        + "\nPris: " + hentPris() + "\nVirkestoff (mg): " + hentVirkestoff();
    }
}