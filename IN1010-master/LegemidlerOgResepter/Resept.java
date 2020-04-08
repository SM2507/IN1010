package LegemidlerOgResepter;

abstract class Resept{
    private static int idr = -1;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId;
    protected int reit;
    private final int egenId;

    public Resept(  Legemiddel clegemiddel, 
                    Lege cutskrivendeLege,
                    int cpasientId,
                    int creit){
        idr++;
        legemiddel = clegemiddel;    
        utskrivendeLege = cutskrivendeLege;   
        pasientId = cpasientId;
        reit = creit; 
        egenId = idr;
    }
    public int hentId(){return egenId;}

    public Legemiddel hentLegemiddel(){return legemiddel;}

    public Lege hentLege(){return utskrivendeLege;}

    public int hentPasientId(){return pasientId;}

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