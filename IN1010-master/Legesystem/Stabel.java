class Stabel<T> extends Lenkeliste<T>{

    public void leggPaa(T x){
        leggTil(x);
    }
    public T taAv() throws UgyldigListeIndeks{
        if (iBruk == 0){
            throw new UgyldigListeIndeks(-1);
        }
        return fjern(iBruk-1);
    }
}