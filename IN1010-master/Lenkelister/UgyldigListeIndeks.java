package Lenkelister;

class UgyldigListeIndeks extends RuntimeException{
    UgyldigListeIndeks(int index){
        super("Ugyldig indeks:" + index);
    }
}