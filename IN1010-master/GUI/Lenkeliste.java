import java.util.Iterator;
import java.util.NoSuchElementException;


public class Lenkeliste<T> implements Liste<T>{
    class Node{  // Indre klasse Node, holder informasjon om data og neste
        public T data;  // Vaart oenskede data lagret 
        public Node neste;  // Peker/ref. til neste Node-objekt
    
        Node(T x){
            data = x;  // Et objekt av klassen Node skal ta inn data gjennom konstruktoeren.
        }
    }

    // Indre klasse LinkelisteIterator, implementerer Iterator<T> interfacet
    class LenkelisteIterator implements Iterator<T>{ 
        private int index = 0;  // Iterator index
        @Override public boolean hasNext(){
            return (index < iBruk);  // Returnerer true hvis det finnes flere etterfolgende elemtenter
        }
        @Override public T next(){
            if(!hasNext()){  // Hvis lista er tom, skal det gis beskjed om det
                throw new NoSuchElementException();
            }
            index++;  // Indekserer Iterator index
            return hent(index-1);  // Returnerer data 
        }
    }
    // Alle java-innebygde lister arver fra Collectible, som implementer Iterable<T>
    // Vaart Liste<T> grensesnitt gjorde i utgangspunktet ikke det, og ble derfor utvidet
    // slik at vi kan iterere gjennom alle lenkeliste-elementer
    @Override  
    public Iterator<T> iterator(){  // Kommer fra Iterable<T> interfacet utvidet i Liste<T>
        return new LenkelisteIterator();  // Skal returnere et LenkelisteIterator objekt
    }

    Node start = null;  // Foerste peker til et Node-objekt.
    protected int iBruk = 0; 

    @Override
    public int stoerrelse(){
        return iBruk;
    }

    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks{
        if( pos < 0 || (pos > iBruk && start != null)|| (start == null && pos != 0) ){  
            throw new UgyldigListeIndeks(pos);
        }
        
        if(start == null && pos == 0){ 
            leggTil(x);
            iBruk--;
        }

        else if(pos == 0){
            Node node = new Node(x); // Dette objektet skal plasseres et bestemt sted.
            node.neste = start;
            start = node;
        }
        else if(pos != 0){
            Node node = new Node(x); // Dette objektet skal plasseres et bestemt sted.
            Node ref = start;  // Midlertidig ref. 
            for(int i = 0; i < pos-1; i++){  // Kommer til oensket posisjon i lista.
                ref = ref.neste;
            }
        node.neste = ref.neste;  // Den nye nodens neste peker til den forrige noden
        ref.neste = node;
        }
        iBruk++;
    }
    @Override
    public void leggTil(T x){
        Node node = new Node(x);  // Oppretter et nytt Node-objekt.
        // Denne noden skal legges til i lista og inneholder dataen.
        iBruk++;

        if(start == null){  
            start = node;  // Hvis lista er tom, blir dette dens foerste element
        }
        else{  // Skal kjoere gjennom alle referansene til den naar en ledig
            Node ref = start;  // Midlertidig ref. til det seneste objektet i loekka.
            while(ref.neste != null){  // Naar en peker staar tom, vil loekka brytes
                ref = ref.neste;  // Referansen peker til et objekt uten peker, det siste.
            }
            ref.neste = node;  // Gir det neste-siste objektet en peker til den nyeste.
            
        }
    }
    @Override
    public void sett(int pos, T x) throws UgyldigListeIndeks{
        if(pos < 0|| pos >= iBruk){
            throw new UgyldigListeIndeks(pos);
        }
        Node node = new Node(x);  // Erstatningsnode

        if(pos == 0){
            node.neste = start.neste;
            start = node;
        }

        if(pos != 0){
            Node ref = start;
            for(int i = 0; i < pos-1; i++){
                ref = ref.neste;
            }
            node.neste = ref.neste.neste;
            ref.neste = node;
        }
    }
    @Override
    public T hent(int pos)throws UgyldigListeIndeks{
        if(start == null || pos < 0|| pos >= iBruk){
            throw new UgyldigListeIndeks(pos);
        }
            Node ref = start;
            for(int i = 0; i < pos; i++){
                ref = ref.neste;
            }
        
        return ref.data;
    }
    @Override
    public T fjern()throws UgyldigListeIndeks{
        if(start == null){
            throw new UgyldigListeIndeks(-1);
        }
        T hold = start.data;
        start = start.neste;
        iBruk--;
        return hold;
    }
    @Override
    public T fjern(int pos) throws UgyldigListeIndeks{
        if(start == null || pos < 0 || pos >= iBruk ){
            throw new UgyldigListeIndeks(pos);
        }
        T hold = hent(pos);
        if(pos == 0){
            start = start.neste;
        }
        else if(pos != 0){
            Node ref = start;
            for(int i = 0; i < pos-1; i++){
                ref = ref.neste;
            }
            if(pos == iBruk-1){    
                ref.neste = null;
            }
            else{
                ref.neste = ref.neste.neste;
            }
        }
        iBruk--;
        return hold;
    }
}
