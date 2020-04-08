
class Spesialist extends Lege implements GodkjenningsFritak{

    private int kontrollId;

    public Spesialist(String navn, int kontrollID){
        super(navn);
        kontrollId = kontrollID;
    }

    // Overskriver skrivResept-metodene fra Lege-klassen
    @Override
    public Resept skrivBlaaResept(  Legemiddel legemiddel,
                                    Pasient p,
                                    int reit){

        Resept resept =  new BlaaResept(legemiddel, new Lege(navn), p, reit);
        p.leggTilNyResept(resept);
        resepter.leggTil(resept);
        p.tellResepter();
        tellResepter();
        return resept;
    }

    @Override
    public Resept skrivMilResept(   Legemiddel legemiddel,
                                    Pasient p,
                                    int reit){

        Resept resept = new MilitaerResept(legemiddel, new Lege(navn), p, reit);
        p.leggTilNyResept(resept);
        resepter.leggTil(resept);
        p.tellResepter();
        tellResepter();
        return resept;
    }

    @Override
    public Resept skrivPResept(     Legemiddel legemiddel,
                                    Pasient p,
                                    int reit){

        Resept resept = new PResept(legemiddel, new Lege(navn), p);
        p.leggTilNyResept(resept);
        resepter.leggTil(resept);
        p.tellResepter();
        tellResepter();  
        return resept;
    }
/*  // Ville beholde metoden som virket i tilfelle utformingen av de nye skrivResept-metodene gikk galt
    @Override
    public Resept skrivResept(  Legemiddel legemiddel,
                                Pasient p,
                                int reit){
        System.out.println("Hvilken type resept vil du skrive ut: ");
        System.out.println("0: Blaa Resept\n" + "1: Militaer Resept\n" +
        "2: P-resept\n");
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();
        Resept resept;
        if(in == 0){
            resept =  new BlaaResept(legemiddel, new Lege(navn), p, reit);
            p.leggTilNyResept(resept);
            resepter.leggTil(resept);
            p.tellResepter();
            tellResepter();
            return resept;
        }
        else if(in == 1){
            resept = new MilitaerResept(legemiddel, new Lege(navn), p, reit);
            p.leggTilNyResept(resept);
            resepter.leggTil(resept);
            p.tellResepter();
            tellResepter();
            return resept;
        }
        else if(in == 2){    
            resept = new PResept(legemiddel, new Lege(navn), p);
            p.leggTilNyResept(resept);
            resepter.leggTil(resept);
            p.tellResepter();
            tellResepter();
            return resept;
        }
        else{
            throw new InputMismatchException();
        }
    }*/

    @Override
    public int hentKontrollID(){
        return kontrollId;
    }
    @Override
    public String toString(){
        return hentNavn() +
        " (KontrollID: " + hentKontrollID() + ")";
    }
}