
public class Lege implements Comparable<Lege>{
    protected String navn;
    private static int legeID = -1;
    private int egenID;
    protected Lenkeliste<Resept> resepter = new Lenkeliste<>();
    public int antallNarkRes = 0;  // Problemer med statistikk metoden i Legesystem
    public int antallVaneRes = 0;  // Forte til at jeg improviserte en losning

    public Lege(String legeNavn){
        legeID++;
        navn = legeNavn;
        egenID = legeID;
    }

    @Override  // Implemeterer compareTo metoden fra Comparable, for aa kunne sortere alfabetisk
    public int compareTo(Lege compLege){
        return navn.compareTo(compLege.hentNavn());
    }

    public Lenkeliste<Resept> hentReseptliste(){
        return resepter;
    }

    public void tellResepter(){  // Teller antall narkotiske og vanedannende resepter
        antallNarkRes = 0;
        antallVaneRes = 0;
        for(Resept res : resepter){
            if(res.hentLegemiddel() instanceof PreparatA){
                antallNarkRes++;
            }
            else if(res.hentLegemiddel() instanceof PreparatB){
                antallVaneRes++;
            }
        }

    }

    // For aa kunne skrive ut spesifikke resepter
    public Resept skrivBlaaResept(     Legemiddel legemiddel,
                                        Pasient p,
                                        int reit) throws UlovligUtskrift{
        if(legemiddel instanceof PreparatA){
        throw new UlovligUtskrift(new Lege(navn), legemiddel);
        }
        Resept resept =  new BlaaResept(legemiddel, new Lege(navn), p, reit);
        p.leggTilNyResept(resept);
        resepter.leggTil(resept);
        p.tellResepter();
        tellResepter();
        return resept;
    }

    public Resept skrivMilResept(  Legemiddel legemiddel,
                                    Pasient p,
                                    int reit) throws UlovligUtskrift{
        if(legemiddel instanceof PreparatA){
            throw new UlovligUtskrift(new Lege(navn), legemiddel);
        }
        Resept resept = new MilitaerResept(legemiddel, new Lege(navn), p, reit);
        p.leggTilNyResept(resept);
        resepter.leggTil(resept);
        p.tellResepter();
        tellResepter();
        return resept;
    }

    public Resept skrivPResept(    Legemiddel legemiddel,
                                    Pasient p,
                                    int reit) throws UlovligUtskrift{
        if(legemiddel instanceof PreparatA){
        throw new UlovligUtskrift(new Lege(navn), legemiddel);
        }
        Resept resept = new PResept(legemiddel, new Lege(navn), p);
        p.leggTilNyResept(resept);
        resepter.leggTil(resept);
        p.tellResepter();
        tellResepter();
        return resept;
    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        return hentNavn();
    }

    public static void main(String[] args) {
        Lege a = new Lege("Dr. Abba");
        Lege b = new Lege("Dr. Babajaan");
        System.out.println(a.compareTo(b));
    }
}