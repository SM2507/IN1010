public class Pasient implements Comparable<Pasient>{
    private static int id = -1;
    private String navn;
    private String fodselsnummer;
    private final int egenID;
    private Stabel<Resept> resepter = new Stabel<>(); 
    public int antallNarkRes = 0;  // Forklart under, tilhorer min losning for statistikk
    public int antallVaneRes = 0;

    Pasient(String Navn, String Fodselsnr){
        id++;
        navn = Navn;
        fodselsnummer = Fodselsnr;
        egenID = id;
    }

    public void leggTilNyResept(Resept resept){
        resepter.leggPaa(resept); 
    }

    public Resept fjernResept(){
        return resepter.taAv();
    }

    public Stabel<Resept> hentReseptliste(){
        return resepter;
    }

    public String hentFodselsnummer(){
        return fodselsnummer;
    }

    public String toString(){  // Ville ha en toString-metode i tilfelle jeg ville skrive ut pasient-objektet
        return egenID + ": " + navn + " (fnr " + 
        fodselsnummer + ")";
    }

    public String hentNavn(){  // Trenger å få tak i navn for aa sammenlikne navn hos andre Pasient-objekter
        return navn;
    }

    public int hentID(){  // Trenger mulighet for aa kunne hente IDen til pasienten
        return egenID;
    }

    /**
     Dette var min losning for statistikk()-problemet jeg hadde. Jeg slet med aa skrive kort og 
     forstaaelig kode for kunne telle antall narkotiske og vanedannende resepter. Metoden kalles paa 
     hver eneste gang en resept blir skrevet fra et lege-objekt.
    */

    public void tellResepter(){
        antallNarkRes = 0;
        antallVaneRes = 0;
        for(Resept res : resepter){
            if(res.hentLegemiddel() instanceof PreparatA){  // Sjekker om objektet er en instans av PrepA...
                antallNarkRes++;
            }
            else if(res.hentLegemiddel() instanceof PreparatB){
                antallVaneRes++;
            }
        }
    }
    /*
    Trenger for å kunne plassere i en SortertLenkeliste. Jeg vil sortere pasientene etter 
    alfabetisk rekkefolge. Derfor gjor jeg det mulig ved aa implementere compareTo-metoden 
    fra Comparable<T>-interfacet.
    */

    @Override
    public int compareTo(Pasient pas){  
        return navn.compareTo(pas.hentNavn());
    }

    public static void main(String[] args) {
        Pasient pas1 = new Pasient("Kim", "25089123292");
        Pasient pas2 = new Pasient("Simon", "09060433487");
        System.out.println(pas1);
        System.out.println(pas2);
        System.out.println(pas1.compareTo(pas2));
    }
}