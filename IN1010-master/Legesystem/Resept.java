
abstract class Resept{
    private static int idr = -1;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient p;  // Tar inn et Pasientobjekt i stedet for en int
    protected int reit;
    private final int egenId;

    public Resept(  Legemiddel clegemiddel, 
                    Lege cutskrivendeLege,
                    Pasient pas,  // gjort om her ogsaa
                    int creit){
        idr++;
        legemiddel = clegemiddel;    
        utskrivendeLege = cutskrivendeLege;   
        p = pas;
        reit = creit; 
        egenId = idr;
    }
    public int hentId(){return egenId;}

    public Legemiddel hentLegemiddel(){return legemiddel;}

    public Lege hentLege(){return utskrivendeLege;}

    public Pasient hentPasientId(){return p;}  // ogsaa her

    public int hentReit(){return reit;}

    public boolean bruk(){
        if(reit == 0){
            return false;
        }
        else{
            reit--;
            return true;
        }
    }

    abstract public String farge();
    abstract public double prisAaBetale();
}