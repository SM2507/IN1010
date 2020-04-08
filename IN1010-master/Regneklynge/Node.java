package Regneklynge;

class Node{  // Making a public class
    private int minne;  // Setting instance variable
    private int antProsessor;
    Node(int minne, int antProsessor){  // Creating a constructor
        this.minne = minne; this.antProsessor = antProsessor;
    }
    public int antProsessorer(){  // Making a public method
        return antProsessor;
    }
    public boolean nokMinne(int paakrevdMinne){
        if (minne >= paakrevdMinne){
            return true;
        }
        else {
            return false;
        } 
    }
    public static void main(String[] args){
        Node node = new Node(8,4);
        System.out.println(node.antProsessorer());
        System.out.println(node.nokMinne(16));
        System.out.println(node.nokMinne(8));  
    }
}
    
