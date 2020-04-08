package LegemidlerOgResepter;

class PreparatB extends Legemiddel{
    private int styrke;

    PreparatB(String navn, 
                double pris, 
                double virkestoff, 
                int styrke_){
        super(navn,pris,virkestoff);
        styrke = styrke_;
    }
    public int hentVanedannendeStyrke(){
        return styrke;
    }
    public String toString(){
        return  "Id nummer: " + hentId() + "\nNavn: " + hentNavn() 
        + "\nPris: " + hentPris() + "\nVirkestoff (mg): " + hentVirkestoff()
        + "\nStryke: " + hentVanedannendeStyrke();
    }
}