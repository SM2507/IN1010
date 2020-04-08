import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint{
    private boolean utskrift = true;
    private static Rute ruter[][];
    public static int labKol, labRad;
    Liste<String> utvei;

    private Labyrint(Rute[][] ruteArr, int kolonner, int rader){
        ruter = ruteArr; labKol = kolonner; labRad = rader;
        for(Rute[] enRad: ruter){
            for(Rute rute : enRad){
                rute.settLabyrint(this);
                rute.settNabo();
            }
        }
    }

    public void utskrift(){
        utskrift = !utskrift;
    }

    public boolean hentUtskrift(){
        return utskrift;
    }

    public Rute[][] hentLabyrint(){return ruter;}

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
        Scanner sctmp = new Scanner(fil);
        String[] info = sctmp.nextLine().split(" "); 
        int lestRader = Integer.parseInt(info[0]);
        int lestKol = Integer.parseInt(info[1]);
        Rute maze[][] = new Rute[lestRader][lestKol];
        int m = 0; 
        while(sctmp.hasNextLine()){
            int n = 0;
            String s = sctmp.nextLine();
            for(char ch : s.toCharArray()){
                if(ch == '.' && ((m == 0 || n == 0) || (m == lestRader-1 || n == lestKol-1)))
                maze[m][n] = new Aapning(m,n);
                else if(ch == '#')
                maze[m][n] = new SortRute(m,n);
                else if(ch == '.')
                maze[m][n] = new HvitRute(m,n);
                n++;
            }
            m++;
        }  
        sctmp.close();
        return new Labyrint(maze, lestKol, lestRader);
    }

    public String toString(){
        String retur = "";
        for(Rute[] rarr : ruter){
            retur += System.lineSeparator();
            for(Rute rute : rarr){
                retur += rute.toString() + " ";
            }
        }
        return retur;
    }

    public Liste<String> finnUtveiFra(int kolnr, int radnr){
        utvei = new Lenkeliste<String>();
        Rute start = ruter[radnr][kolnr];
        start.finnUtvei();
        return utvei;
    }
}