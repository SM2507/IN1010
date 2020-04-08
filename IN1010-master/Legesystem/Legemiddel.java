
class Legemiddel implements Comparable<Legemiddel>{
    private static int id = -1;
    
    private String navn;
    private double pris;
    private double virkestoff;
    private final int egenId;

    public Legemiddel(String legemiddelNavn, double middelPris, double middelVirkestoff){
        id++;
        navn = legemiddelNavn;
        pris = middelPris;
        virkestoff = middelVirkestoff;
        egenId = id;
    }
    public int hentId(){
        return egenId;
    }
    public String hentNavn() {
        return navn;
    }
    public double hentPris(){
        return pris;
    }
    public double hentVirkestoff(){
        return virkestoff;
    }
    public void settNyPris(double middelPris){
        pris = middelPris;
    }

    @Override
    public int compareTo(Legemiddel lmiddel){
        return navn.compareTo(lmiddel.hentNavn());
    }
}   


