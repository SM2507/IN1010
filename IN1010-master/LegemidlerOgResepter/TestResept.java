package LegemidlerOgResepter;

class TestResept{
    public static void main(String[] args){
        PreparatC C = new PreparatC("Angiotensin", 450.00, 2.00);  // Legemiddel
        Lege lege = new Lege("Jan-Olav");  // Lege

        MilitaerResept mil = new MilitaerResept(C,lege,1,4);

        System.out.println(mil.farge());
        System.out.println(mil.prisAaBetale()); 
        System.out.println(mil.hentId());
        System.out.println(mil.hentLegemiddel());
        System.out.println(mil.hentLege());
        System.out.println(mil.hentPasientId());
        System.out.println(mil.hentReit());
        System.out.println();

        PResept pr = new PResept(C,lege,2);

        System.out.println(pr.farge());
        System.out.println(pr.prisAaBetale()); 
        System.out.println(pr.hentId());
        System.out.println(pr.hentLegemiddel());
        System.out.println(pr.hentLege());
        System.out.println(pr.hentPasientId());
        System.out.println(pr.hentReit());
        System.out.println();

        BlaaResept blaa = new BlaaResept(C,lege,3,3);

        System.out.println(blaa.farge());
        System.out.println(blaa.prisAaBetale()); 
        System.out.println(blaa.hentId());
        System.out.println(blaa.hentLegemiddel());
        System.out.println(blaa.hentLege());
        System.out.println(blaa.hentPasientId());
        System.out.println(blaa.hentReit());
        
    }
}