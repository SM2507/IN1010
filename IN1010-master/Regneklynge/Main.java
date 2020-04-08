package Regneklynge;

class Main{
    public static void main(String[] args){
        Regneklynge abel = new Regneklynge("Regneklynge.txt");
        
        System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
        System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
        System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
        System.out.println("Antall prosessorer: " + abel.antProsessorer());
        System.out.println("Antall racks: " + abel.antRacks());
    }
}