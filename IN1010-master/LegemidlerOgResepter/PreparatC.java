package LegemidlerOgResepter;

class PreparatC extends Legemiddel{
    PreparatC(String navn, double pris, double virkestoff){
        super(navn,pris,virkestoff);
    }
    public String toString(){
        return  "Id nummer: " + hentId() + "\nNavn: " + hentNavn() 
        + "\nPris: " + hentPris() + "\nVirkestoff (mg): " + hentVirkestoff();
    }
}