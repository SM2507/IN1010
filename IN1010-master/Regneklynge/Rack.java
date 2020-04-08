package Regneklynge;

import java.util.ArrayList;

class Rack{  // New class Rack
    private ArrayList<Node> rack = new ArrayList<Node>();  // An empty array of Node objects

    public void settInn(Node node){ 
        rack.add(node);
    } 
    public int getAntNoder(){  // Method for counting nodes
        return rack.size();
    }

    public int antProsessorer(){
        int antall = 0;
        for(Node node : rack){
            antall += node.antProsessorer();
        }
        return antall;
    }
    public int noderMedNokMinne(int paakrevdMinne){
        int antall = 0;
        for(Node node : rack){
            if (node.nokMinne(paakrevdMinne)){
                antall ++;
            }
        }
        return antall;
    }   
    public static void main(String[] args){
        Rack quack = new Rack();
        System.out.println(quack.getAntNoder());
        quack.settInn(new Node(8,8));
        quack.settInn(new Node(6,8));
        System.out.println(quack.getAntNoder());
        System.out.println(quack.antProsessorer());
        System.out.println(quack.noderMedNokMinne(7));
    }
}