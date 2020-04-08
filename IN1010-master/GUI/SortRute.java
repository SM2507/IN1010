public class SortRute extends Rute{
    public SortRute(int rad, int kol){
        super(rad, kol);
    }

    @Override
    public char tilTegn(){return '#';}
    
    public String toString(){return String.valueOf(tilTegn());}
    
    @Override 
    public void gaa(Rute rute, String vei){}

}