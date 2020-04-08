/*
 Import av vektoy og flere feilmeldinger for aa prove aa gjore programmet
 mer brukervennlig.
 */

import java.io.FileWriter;  // For aa skrive ut til fil
import java.io.BufferedWriter;  // For aa skrive ut til fil mer effektivt, FileWriter fungerer alene ogsaa
import java.io.IOException;  // Feilmelding tilhorende FileWriter og Buffered...
import java.rmi.NoSuchObjectException;  // Importert frivillig, ikke strengt talt nodvendig
import java.util.InputMismatchException;  // Kalles paa hvis scanner faar inn bokstav og kaller paa nextInt() osv.
import java.util.NoSuchElementException;  // Kan bli kalt paa hvis brukerinput mangler, ikke vaert nodvendig hittil
import java.util.Scanner;  // For aa ta inn brukerinput
import java.io.File;  // For aa skulle lese fil som en fil
import java.io.FileNotFoundException;  // Medfolgende feilmelding dersom en innlest fil skulle mangle


public class Legesystem{

    private int kjorer = 1;  // Saa lenge denne er 1 saa kjorer programmet, brukes i startSystem og avsluttSystem
    private String forsinkelse;  // Variablen brukes til aa ta inn enter for aa gaa til neste

    /*
    4 lister hovedsaklig til bruk i oversikt-metoden, men blir brukt i andre metoder i 
    tillegg
    */
    private static SortertLenkeliste<Lege> legeliste = new SortertLenkeliste<>();
    private static SortertLenkeliste<Pasient> pasientliste = new SortertLenkeliste<>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<>();
    private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

    // Metoden som starter systemet
    public void startSystem(){
        while(kjorer != 0){  // Saa lenger "kjorer er paa"
            visHovedmeny();  // Skal hele tiden kalle paa visHovedmeny()
            try{
                int valg = new Scanner(System.in).nextInt();  // Avventer brukerinput
                clrscr();  // Sletter innholdet paa skjermen for saa aa vise neste seksjon
                
                if(valg == 0){
                    oversikt();  // Viser oversikten over alle objekter i systemet.
                }
                else if(valg == 1){
                    visOpprettMeny();  // Viser muligheten for aa opprette objekter
                }
                else if(valg == 2){
                    brukReseptMeny();  // gir bruker muligheten til aa bruker en resept
                }
                else if(valg == 3){
                    statistikk();  // Viser statistikk
                }
                else if(valg == 4){
                    skrivTilFil();  // Skriver all objektinformasjon til fil i samme format som innlest fil
                }

                else if(valg == 5){
                    avsluttSystem();  // Kutter lokken og avslutter systemet
                }
                if(valg !=5){  // Hvis bruker ikke avslutter, gir mulighet til aa vente for man gaar tilbake
                    System.out.println("\nTast enter for hovedmeny\n");
                    forsinkelse = new Scanner(System.in).nextLine();
                    clrscr();
                }
            }
            catch(Exception e){
                System.out.println("En feil i inntasting");
            }
        }
    }

    // Printer ut alle mulighetene i systemet til bruker
    public void visHovedmeny(){
        System.out.println("0: Vis oversikt");
        System.out.println("1: Opprett og legg til");
        System.out.println("2: Bruk resept");
        System.out.println("3: Statistikk");
        System.out.println("4: Skriv data til fil");
        System.out.println("5: Avslutt legesystem\n");        
    }

    // Leser fra fil
    private static void lesFraFil(File fil){
        Scanner scanner = null;
        try{
            scanner = new Scanner(fil);
        }catch(FileNotFoundException e){
            System.out.println("Fant ikke filen, starter opp som et tomt Legesystem");
            return;
        }

        String innlest = scanner.nextLine();


        while(scanner.hasNextLine()){

            String[] info = innlest.split(" ");

            // Legger til alle pasientene i filen
            if(info[1].compareTo("Pasienter") == 0){
                while(scanner.hasNextLine()) {
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til pasienter, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til legemiddler
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    String[] inn = innlest.split(", ");  // Deler opp linja i String-"biter" og legger dem inn i arrayen "inn"
                    String navn = inn[0];  // Navnet befinner seg i forst i lista
                    String fnr = inn[1];  // fnr #2 i lista
                    pasientliste.leggTil(new Pasient(navn, fnr));  // Oppretter nytt Pasient-objekt og legger til i lista
                }

            }
            //Legger inn Legemidlene
            else if(info[1].compareTo("Legemidler") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til legemidler, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til leger
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    String[] legemiddel = innlest.split(", ");  // Linja splittes inn i String-elementer og legges til i en array, legemiddel
                    if(legemiddel[1].compareTo("a") == 0){  // array-element 1 har symbolet for type preparat (A,B, eller C)
                        String navn = legemiddel[0];
                        // Bruker replace for aa fjerne mellomrommene. De utloser feilmelding under konvertering til int, double, osv...
                        // parse_type_ er en metode som tilhorer alle datatypene (ikke-primitive), som konverterer input til samme type som seg selv (Sin egen klasse)
                        double pris = Double.parseDouble(legemiddel[2].replace(" ", "")); 
                        double virks = Double.parseDouble(legemiddel[3].replace(" ", ""));

                        // int er primitiv og derfor brukes klassen Integer sin parse-metode til konverteringen av String til int. 
                        int styrkek = Integer.parseInt(legemiddel[4].replace(" ", ""));
                        // Oppretter nytt Preparat og legger til i legemidler-lista
                        legemidler.leggTil(new PreparatA(navn, pris, virks, styrkek));
                    }
                    else if(legemiddel[1].compareTo("b") == 0){
                        String navn = legemiddel[0];
                        double pris = Double.parseDouble(legemiddel[2].replace(" ", "")); 
                        double virks = Double.parseDouble(legemiddel[3].replace(" ", ""));
                        int styrkek = Integer.parseInt(legemiddel[4].replace(" ", ""));
                        legemidler.leggTil(new PreparatB(navn, pris, virks, styrkek));
                    }else if (legemiddel[1].compareTo("c") == 0){
                        String navn = legemiddel[0];
                        double pris = Double.parseDouble(legemiddel[2].replace(" ", "")); 
                        double virks = Double.parseDouble(legemiddel[3].replace(" ", ""));
                        legemidler.leggTil(new PreparatC(navn, pris, virks));
                    }

                }
            }
            //Legger inn leger
            else if(info[1].compareTo("Leger") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    //Om vi er ferdig med å legge til leger, bryt whileløkken,
                    //slik at vi fortsetter til koden for å legge til resepter
                    if(innlest.charAt(0) == '#'){
                        break;
                    }
                    info = innlest.split(", ");
                    int kontrollid = Integer.parseInt(info[1].replace(" ", ""));
                    if(kontrollid == 0){  // i det tilfellet kontrollid = 0, saa opprettes en vanlig Lege
                        legeliste.leggTil(new Lege(info[0]));  // info[0] inneholder navnet som String
                    }else{ // Ellers en Spesialist
                        legeliste.leggTil(new Spesialist(info[0], kontrollid));
                    }
                }

            }
            //Legger inn Resepter
            else if(info[1].compareTo("Resepter") == 0){
                while(scanner.hasNextLine()){
                    innlest = scanner.nextLine();
                    info = innlest.split(", ");
                    Lege utLege = legeliste.hent(0);  // For aa forhindre tom peker
                    Pasient pas = pasientliste.hent(0);
                    Legemiddel lm = legemidler.hent(0);  // maa lage disse utenfor if-testene og ikke-tomme, ellers "finnes de ikke"
                    for(Legemiddel lgm : legemidler){
                        // Sjekker om legemiddelID er det samme onsket i innlest fil
                        // Bryter lokka og tildeler RIKTIGE objekter til pekerene ovenfor
                        if(lgm.hentId() == Integer.parseInt(info[0].replace(" ", ""))){
                            lm = lgm;
                            break;
                        }
                    }
                    for(Lege l : legeliste){
                        if(l.hentNavn().compareTo(info[1]) == 0){
                            utLege = l;
                            break;
                        }
                    }
                    for(Pasient p : pasientliste){
                        if(p.hentID() == Integer.parseInt(info[2].replace(" ", ""))){
                            pas = p;
                            break;
                        }                         
                    }
                    int reit = Integer.parseInt(info[3].replace(" ", ""));
                    try{
                        if(utLege instanceof Spesialist){  // I det tilfellet legen vaar en en spesialist
                            Spesialist sp = (Spesialist) utLege;
                            Resept res = sp.skrivBlaaResept(lm, pas, reit);  // skal Spesialist sin skrivResept-metode kalles, pga. prepA
                            resepter.leggTil(res);
                        }
                        else{ // ellers...
                            Resept res = utLege.skrivBlaaResept(lm, pas, reit);
                            resepter.leggTil(res);
                        }
                    }
                    catch(Exception e){e.printStackTrace();}  // Printer opprinnelsen til feilmeldingen som kan oppstaa
                    
                    
                }
            }
        }
    }
    
    //Denne metoden skriver ut alle liste-elemtene formattert, ikke saa mye mer aa si. 
    private void oversikt(){
        System.out.println("Leger");
        System.out.println("_______________________");
        int tell = 0;
        for(Lege lege : legeliste){
            System.out.println(tell + ": " + lege);
            tell++;
        }
        System.out.println("_______________________");
        System.out.println("\nPasienter");
        System.out.println("_______________________");
        tell = 0;
        for(Pasient pas : pasientliste){
            System.out.print(tell + ": " + pas.hentNavn());
            System.out.println(" (fnr: " + pas.hentFodselsnummer() + ")");
            tell++;
        }
        System.out.println("_______________________");
        System.out.println("\nLegemidler");
        System.out.println("_______________________");
        tell = 0;
        char type;  // Ville spesifisere hva slags preparat
        for(Legemiddel middel : legemidler){
            if(middel instanceof PreparatA){type = 'a';}
            else if(middel instanceof PreparatB){type = 'b';}
            else {type = 'c';}
            System.out.println(tell + ": " + middel.hentNavn() + " (type: " + type + ")");
            tell++;
        }
        System.out.println("_______________________");
        System.out.println("\nResepter");
        System.out.println("_______________________");
        tell = 0;
        for (Resept res : resepter) {
            System.out.println(tell + ": " + res.hentLegemiddel().hentNavn() +
            " (" + res.hentReit() + " reit)");
            tell++;
        }
        System.out.println("_______________________");
    }

    private void visOpprettMeny(){  
        // tilgjengelige muligheter for oprettelse
        System.out.println("Opprett og legg til:");
        System.out.println("0: Pasient");
        System.out.println("1: Lege");
        System.out.println("2: Legemiddel");
        System.out.println("3: Resept");
        System.out.println("\nHva vil du opprette: \n");
        try{
            int in = new Scanner(System.in).nextInt();
            if(in == 0){  // Hvis bruker vil opprette en Pasient
                System.out.println("Pasientens navn: \n");
                String navn = new Scanner(System.in).nextLine();  // tar inn navnet fra bruker
                System.out.println("Pasientens fodselsnummer: \n");
                String fnr = new Scanner(System.in).nextLine();  // tar inn fnr
                pasientliste.leggTil(new Pasient(navn, fnr));  // oppretter Pasient-objekt og legger til i tilsvarende liste
                System.out.println("Pasient opprettet");  // bekreftelse
            }
            
            else if(in == 1){  // Hvis bruker vil opprette en Lege    
                System.out.println("Legens navn: \n");
                String navn = new Scanner(System.in).nextLine();  // tar inn navnet fra bruker
                System.out.println("KontrollID: \n");
                int kontroll = new Scanner(System.in).nextInt();  // tar inn kontrollid og konverterer til int
                if(kontroll != 0){
                    legeliste.leggTil(new Spesialist(navn, kontroll));
                }
                else{legeliste.leggTil(new Lege(navn));
                }
                System.out.println("Lege opprettet");
                
            }

            else if(in == 2){  // Hvis bruker vil opprette et Legemiddel
                System.out.println("Legemiddelnavn: \n"); 
                String middelnavn = new Scanner(System.in).nextLine();  // tar inn legemiddelnavnet
                System.out.println("Skriv type preparat \n 0: a\n 1: b\n 2: c \n");;
                int type = new Scanner(System.in).nextInt();  // Velger type preparat
                if(type == 0){
                    System.out.println("Skriv inn double pris, double virkestoff, og int styrkekonsentrasjon\n"+
                    "(3 verdier separert med mellomrom): \n");
                    Scanner sc = new Scanner(System.in);  // Nytt scanner objekt med dedikert peker
                    double pris = sc.nextDouble();  // tar inn og konverterer
                    double virkestoff = sc.nextDouble();
                    int skons = sc.nextInt();
                    // Oppretter prep og legger til i tilsvarende liste
                    legemidler.leggTil(new PreparatA(middelnavn, pris, virkestoff, skons));
                    System.out.println("PreparatA opprettet");}
                else if(type == 1){
                    System.out.println("Skriv inn double pris, double virkestoff, og int styrkekonsentrasjon\n"+
                    "(3 verdier separert med mellomrom): \n");
                    Scanner sc = new Scanner(System.in);
                    double pris = sc.nextDouble();
                    double virkestoff = sc.nextDouble();
                    int skons = sc.nextInt();
                    legemidler.leggTil(new PreparatB(middelnavn, pris, virkestoff, skons));
                    System.out.println("PreparatB opprettet");
                }
                else if(type == 2){
                    System.out.println("Skriv inn double pris, og double virkestoff\n"+
                    "(2 verdier separert med mellomrom): \n");
                    Scanner sc = new Scanner(System.in);
                    double pris = sc.nextDouble();
                    double virkestoff = sc.nextDouble();
                    legemidler.leggTil(new PreparatC(middelnavn, pris, virkestoff));
                    System.out.println("PreparatC opprettet");
                }
            }

            else if(in == 3){  // hvis bruker vil opprette en resept
                if(legeliste.stoerrelse() == 0 || legemidler.stoerrelse() == 0 
                || pasientliste.stoerrelse() == 0){  // Sjekker om nodvendige objekter finnes for den gaar videre
                    throw new NoSuchObjectException("Tomme lister");}  // Custom tilbakemelding, fanges i catch
                System.out.println("Velg utskrivende lege:");
                int tell = 0;
                for(Lege lege : legeliste){
                    System.out.println(tell + ": " + lege);
                    tell++;
                }
                System.out.println();
                Lege lege = legeliste.hent(new Scanner(System.in).nextInt());  // Lege-objektet hentes etter brukerinput
                System.out.println("Velg legemiddel:");
                tell = 0;
                for(Legemiddel middel : legemidler){
                    System.out.println(tell + ": " + middel.hentNavn());
                    tell++;
                }
                System.out.println();
                Legemiddel legemiddel = legemidler.hent(new Scanner(System.in).nextInt());  // henter legemiddel
                System.out.println("Velg pasient resepten skal gjelde for: ");
                tell = 0;
                for(Pasient pas : pasientliste){
                    System.out.println(tell + ": " + pas.hentNavn());
                    tell++;
                }
                System.out.println();
                Pasient pasient = pasientliste.hent(new Scanner(System.in).nextInt());  // henter pasient
                System.out.println("Skriv antall reiterasjoner: \n");
                int reit = new Scanner(System.in).nextInt();
                System.out.println("Hvilken type resept vil du skrive ut: ");
                System.out.println("0: Blaa Resept\n" + "1: Militaer Resept\n" +
                "2: P-resept\n");
                Resept resept;
                int typeR = new Scanner(System.in).nextInt();  // velger type resept
                if(typeR == 0){  // hvis blaa, lag blaaResept 
                    resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                }
                else if(typeR == 1){ // tilsvarende ... 
                    resept = lege.skrivMilResept(legemiddel, pasient, reit);
                }
                else if(typeR == 2){ // tilsvarende ...
                    resept = lege.skrivPResept(legemiddel, pasient, reit);    
                }
                else{
                    throw new InputMismatchException();
                }
                
                resepter.leggTil(resept); // og legg til i Resept-lista 
                System.out.println("Resept opprettet");  // Bekreftelse
            }
        }  // fanger alle feilmeldingene og gir saa god tilbakemelding som jeg kunne ha tenkt meg mulig i oppgaven
        catch(InputMismatchException e){
            System.out.println("Du skrev bokstaver der du kun skal skrives tall \n");
        }
        catch(NumberFormatException e){
            System.out.println("Du skrev bokstaver der du kun skal skrives tall \n");
        }
        catch(NoSuchElementException e){
            System.out.println("Du skrev ingenting der du skulle skrive noe \n");
        }
        catch(NoSuchObjectException e){
            System.out.println("Du oppretter et objekt som avhenger av at det finnes andre!");
            System.out.println("Vennligst opprett minst en pasient, lege og/eller legemiddel \n");
        }
        catch(UlovligUtskrift e){
            System.out.println("Denne legen kan ikke skrive ut resept med legemiddel av type prepA");
        }
    }

    private void brukReseptMeny(){
        try{
            if(pasientliste.stoerrelse() == 0 || resepter.stoerrelse() == 0){
                throw new NoSuchElementException();
            }
            System.out.println("Velg pasient du vil bruke resept for: \n");
            int tell = 0;  // For oversikt
            // Denne lista skal holde paa alle pasienter med minst en resept, gyldig eller ikke
            SortertLenkeliste<Pasient> slp = new SortertLenkeliste<>(); 
            for(Pasient pasient: pasientliste){
                // Henter reseptlista og sjekker om det finnes minst ett element i den.
                if(pasient.hentReseptliste().stoerrelse() > 0){
                    slp.leggTil(pasient);  // Legger til i lista overfor
                    System.out.println(tell + ": " + pasient.hentNavn());  // Skriver ut info
                    tell++;
                }
            }
            Pasient pasient = slp.hent(new Scanner(System.in).nextInt());  // henter pasient etter brukerinput
                tell = 0;
            System.out.println("Velg hvilken resept du vil bruke: ");
            for(Resept res : pasient.hentReseptliste()){
                // skriver ut alle tilgjengelige resepter hos en valgt pasient
                System.out.println(tell + ": " + res.hentLegemiddel().hentNavn() + "(" + res.hentReit() + " reit)");
                tell++;
            }
            Resept resept = pasient.hentReseptliste().hent(new Scanner(System.in).nextInt());  // Henter resepten
            if(resept.bruk()){  // Og bruker den
                System.out.println("Brukte resept paa " + resept.hentLegemiddel().hentNavn() + 
                "\nAntall gjenvaerende reit: " + resept.hentReit());
            }
            else{
                System.out.println("Kunne ikke bruke resept på " + resept.hentLegemiddel().hentNavn() + 
                "(ingen gjenvaerende reit)");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Du skrev bokstaver der du kun skulle skrive tall");
        }
        catch(NoSuchElementException e){
            System.out.println("Du har ingen pasienter/resepter tilgjengelig.");
        }
    }

    private void avsluttSystem(){
        clrscr();  // Sletter alt paa skjermen
        kjorer = 0;  // og setter kjorer til 0, som avslutter programmet
    }
    
    private static void clrscr(){  // Laant kode fra internettet
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {} 
    }

    // Denne metoden skriver ut onsket statistikk
    private void statistikk(){ 
        int tellVane = 0;  // Beholder for alle vanedannende resepter i hele systemet
        int tellNarko = 0;  // Samme bare for narkotiske
        SortertLenkeliste<Lege> narkoLege = new SortertLenkeliste<>();  // Alle leger som har skrevet ut narkotiske resepter
        SortertLenkeliste<Pasient> narkoPasient = new SortertLenkeliste<>();  // Alle pasienter som har minst en narkotisk resept
        for(Resept res : resepter){
            if(res.hentLegemiddel() instanceof PreparatB){
                tellVane++;  // Hvis instans av prepB, ok teller for vanedannende
            }
            if(res.hentLegemiddel() instanceof PreparatA){  // samme, bare for narkotiske
                tellNarko++;
            }

        }
        System.out.println("Totalt antall utskrvne resepter paa narkotiske legemidler: " + 
        tellNarko);
        System.out.println("Totalt antall utskrevne resepter paa vanedannende legemidler: " + 
        tellVane);
        System.out.println("\nLeger som har skrevet ut resept paa narkotiske legemidler: ");
        int tell = 0;  // Denne telleren har ingen funksjon annen enn aa skrive ut oversiktlig.
        for(Lege lege : legeliste){
            if(lege.antallNarkRes != 0){  // Denne variablen finnes inni Lege-objekter og ble skapt fordi koden ble for komplisert uten
                System.out.println(tell + ": " + lege.hentNavn() + " (antall utskrevede: " + 
                lege.antallNarkRes + ")");
                tell++;
            }
        }
        tell = 0;
        System.out.println("\nPasienter med resept paa narkotiske legemidler: ");
        for(Pasient pas : pasientliste){
            if(pas.antallNarkRes != 0){  // finnes i Pasient-objekter ogsaa. 
                System.out.println(tell + ": " + pas.hentNavn() + " (antall utskrevede: " + 
                pas.antallNarkRes + ")");
                tell++;
            }
        }
    }

    private void skrivTilFil(){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter("Legesystem.txt"));
            out.write("# Pasienter (navn, fnr)");
            out.write(System.lineSeparator());
            for(Pasient pas : pasientliste){
                out.write(String.format("%s, %s", pas.hentNavn(), pas.hentFodselsnummer()));
                out.write(System.lineSeparator());
            }
            out.write("# Legemidler (navn, type, pris, virkestoff [,styrke])");
            out.write(System.lineSeparator());
            for(Legemiddel mid : legemidler){
                String type;
                if(mid instanceof PreparatA){
                    PreparatA prep = (PreparatA) mid;  // Casting for aa faa hentet styrke
                    type = "a";
                    out.write(String.format("%s, %s, %.2f, %.2f, %d", mid.hentNavn(), 
                    type, mid.hentPris(), mid.hentVirkestoff(), prep.hentNarkotiskStyrke()));
                    out.write(System.lineSeparator());
                }
                else if(mid instanceof PreparatB){
                    PreparatB prep = (PreparatB) mid;
                    type = "b";
                    out.write(String.format("%s, %s, %.2f, %.2f, %d", mid.hentNavn(), 
                    type, mid.hentPris(), mid.hentVirkestoff(), prep.hentVanedannendeStyrke()));
                    out.write(System.lineSeparator());
                }
                else if(mid instanceof PreparatC){
                    type = "c";
                    out.write(String.format("%s, %s, %.2f, %.2f", mid.hentNavn(), 
                    type, mid.hentPris(), mid.hentVirkestoff()));
                    out.write(System.lineSeparator());
                }
            }
            out.write("# Leger (navn, kontrollid / 0 hvis vanlig lege)");
            out.write(System.lineSeparator());
            for(Lege lege : legeliste){
                try{
                    Spesialist sp = (Spesialist) lege;
                    out.write(String.format("%s, %d",sp.hentNavn(), sp.hentKontrollID()));
                    out.write(System.lineSeparator());
                }
                catch(ClassCastException c){
                    out.write(String.format("%s, 0",lege.hentNavn()));
                    out.write(System.lineSeparator());
                }
            }
            out.write("# Resepter (legemiddelnummer, legenavn, pasientID, reit)");
            out.write(System.lineSeparator());
            for(Resept res : resepter){
                out.write(String.format("%d, %s, %d, %d", res.hentLegemiddel().hentId(),
                res.hentLege().hentNavn(), res.hentPasientId().hentID(), res.hentReit()));
                out.write(System.lineSeparator());
            }
            out.close();
            System.out.println("Data skrevet til fil.");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Legesystem ls = new Legesystem();
        ls.lesFraFil(new File("Legesystem.txt"));        
        ls.startSystem();
    }
}   