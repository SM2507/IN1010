package LegemidlerOgResepter;

class TestPreparat{
    public static void main(String[] args){
    PreparatA A = new PreparatA("Codeine", 436.00, 25.00, 10);
    System.out.println(A.hentId());  // Forventer 0 ut i konsollen 
    System.out.println(A.hentNavn());  // Forventer Codeine
    System.out.println(A.hentPris());  // Forventer 436.0
    System.out.println(A.hentVirkestoff());  // Forventer 25.0
    System.out.println(A.hentNarkotiskStyrke());  // Forventer 10
    A.settNyPris(450);
    System.out.println(A.hentPris());  // Forventer 450.0
    System.out.println("____________________");
    
    PreparatB B = new PreparatB("Paracetamol", 80.00, 10.00, 5);
    System.out.println(B.hentId());  // Forventer 1
    System.out.println(B.hentNavn());  // Forventer Paracetamol 
    System.out.println(B.hentPris());  // Forventer 80.0
    System.out.println(B.hentVirkestoff());  // Forventer 10.0
    System.out.println(B.hentVanedannendeStyrke());  // Forventer 5
    B.settNyPris(100);
    System.out.println(B.hentPris()); //Forventer 50.0
    System.out.println("____________________");

    PreparatC C = new PreparatC("Ibuprofen", 45.00, 2.00);
    System.out.println(C.hentId());  // Forventer 2
    System.out.println(C.hentNavn());  // Forventer Ibuprofen
    System.out.println(C.hentPris());  // Forventer 45.0
    System.out.println(C.hentVirkestoff());  // Forventer 2.0
    C.settNyPris(50);
    System.out.println(C.hentPris()); //Forventer 50.0
    
    System.out.println("____________________");
    System.out.println(C);
}
}
