package Regneklynge;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Regneklynge{
    private int noderPerRack;
    private ArrayList<Rack> klynge = new ArrayList<Rack>();
    
    Regneklynge(String filnavn){
        klynge.add(new Rack());
        try{
            Scanner avleser = new Scanner(new File(filnavn));
    
            noderPerRack = avleser.nextInt();
        
            while (avleser.hasNextLine() && avleser.hasNextInt()){
                int antNoder = avleser.nextInt();
                int minne = avleser.nextInt();
                int antProsessorer = avleser.nextInt();
    
                for (int i = 0; i < antNoder; i++){
                    settInnNode(new Node(minne, antProsessorer));
                }
            }
        
            avleser.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Finner ikke fil");
        }
    }
    
    public void settInnNode(Node node){
        for(int i = 0; i < klynge.size(); i++){
            if(klynge.get(i).getAntNoder() < noderPerRack){
                klynge.get(i).settInn(node);
                break;}

            else if(i == klynge.size()-1 && 
            klynge.get(i).getAntNoder() >= noderPerRack){
                klynge.add(new Rack());
                klynge.get(i+1).settInn(node);
                break;}
        }
    }

    public int antProsessorer(){
        int antall = 0;
        for(int i = 0; i < klynge.size(); i++){
            antall += klynge.get(i).antProsessorer();
        }
        return antall;
    }
    public int noderMedNokMinne(int paakrevdMinne){
        int antall = 0;
        for(int i = 0; i < klynge.size(); i++){
            antall += klynge.get(i).noderMedNokMinne(paakrevdMinne);
        }
        return antall;
    }
    public int antRacks(){
        return klynge.size();
    }
}
    