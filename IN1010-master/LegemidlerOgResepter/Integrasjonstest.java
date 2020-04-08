package LegemidlerOgResepter;

class Integrasjonstest{
    public void ordinaerTest(){
        PreparatA PA = new PreparatA("Codeine", 6000.0, 25.0, 10);  
        PreparatB PB = new PreparatB("Adderall", 1570.0, 10.0, 5);
        PreparatC PC = new PreparatC("Cerazette", 265.00, 2.0);
        Lege lege = new Lege("John Michael Dorian");
        Spesialist SP = new Spesialist("Gregory House", 2391342);
        PResept PR = new PResept(PC,lege,3);
        MilitaerResept MR = new MilitaerResept(PA,SP,1,4);
        BlaaResept BR = new BlaaResept(PB,lege,3,3);
        
        System.out.println(PA + "\n");
        System.out.println(PB + "\n");
        System.out.println(PC + "\n");
        System.out.println(lege + "\n");
        System.out.println(SP + "\n");
        System.out.println(PR + "\n");
        System.out.println(MR + "\n");
        System.out.println(BR + "\n");
    }
    /*
    public void filinnlesning(String filnavn){

        try {
            FileReader fr = new FileReader(filnavn);
            fr.readline();
            for(int i = 0; i<3; i++){
                String frLine =  fr.readline();
                String[] frArray = frLine.split(",");
            }
            

        } 

        catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet");
        }
        catch(IOException e) {
            System.out.println("Input-Output Exception");
        }
    }
*/
    
    public static void main(String[] args) {
        Integrasjonstest iT = new Integrasjonstest();
        iT.ordinaerTest();
    }
}