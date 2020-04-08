import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

class testing{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sctmp = new Scanner(new File("1.in"));
        String[] info = sctmp.nextLine().split(" "); 
        int rader = Integer.parseInt(info[0]);
        int kol = Integer.parseInt(info[1]);
        Rute[][] maze = new Rute[rader][kol];
        int m = 0; int n = 0;
        while(sctmp.hasNextLine()){
            n = 0;
            String s = sctmp.nextLine();
            for(char ch : s.toCharArray()){
                if(ch == '.' && ((m == 0 || n == 0) || (m == rader-1 || n == kol-1)))
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
        int antKol = kol;
        int antRad = rader;
        for(Rute[] rad1 : maze){
            for(Rute r : rad1){
                if(r.rad > 0){
                r.naboNord = maze[r.rad-1][r.kolonne];}
                if(r.rad < antRad-1){
                r.naboSor = maze[r.rad+1][r.kolonne];}
                if(r.kolonne > 0){
                r.naboVest = maze[r.rad][r.kolonne-1];}
                if(r.kolonne < antKol-1){
                r.naboOst = maze[r.rad][r.kolonne+1];}
            }
        }
        for(Rute[] maz : maze){
            System.out.println();
            for(Rute r: maz){
                if(r.naboOst == null || r.naboNord == null || r.naboSor == null || r.naboVest == null)
                System.out.print("O");
                else
                System.out.print("I");
                /*
                if(r instanceof Aapning)
                System.out.print("A");
                else if(r instanceof HvitRute)
                System.out.print("H");
                else if(r instanceof SortRute)
                System.out.print("S");
                else
                System.out.print("O");*/

            }
        }

        }
        /*if(r.rad == 0){
                    if(r.kolonne == 0){
                        r.naboSor = maze[r.rad+1][0];
                        r.naboOst = maze[0][r.kolonne+1];
                    }
                    else if(r.kolonne == antKol-1){
                        r.naboVest = maze[0][r.kolonne-1];
                        r.naboSor = maze[r.rad+1][0];
                    }
                    else{
                        r.naboVest = maze[0][r.kolonne-1];
                        r.naboOst = maze[0][r.kolonne+1];
                        r.naboSor = maze[r.rad+1][r.kolonne];
                    }
                }
                else if(r.rad == antRad-1){
                    if(r.kolonne == 0){
                        r.naboNord = maze[r.rad-1][0];
                        r.naboOst = maze[r.rad][r.kolonne+1];
                    }
                    else if(r.kolonne == antKol-1){
                        r.naboVest = maze[r.rad][r.kolonne-1];
                        r.naboNord = maze[r.rad-1][r.kolonne];
                    }
                    else{
                        r.naboVest = maze[r.rad][r.kolonne-1];
                        r.naboOst = maze[r.rad][r.kolonne+1];
                        r.naboNord = maze[r.rad-1][r.kolonne];
                    }
                }
                else{
                    if(r.kolonne == 0){
                        r.naboNord = maze[r.rad-1][r.kolonne];
                        r.naboSor = maze[r.rad+1][r.kolonne];
                        r.naboOst = maze[r.rad][r.kolonne+1];
                    }
                    else if(r.kolonne == antKol-1){
                        r.naboVest = maze[r.rad][r.kolonne-1];
                        r.naboNord = maze[r.rad-1][r.kolonne];
                        r.naboSor = maze[r.rad+1][r.kolonne];
                    }
                    else{
                        r.naboVest = maze[r.rad][r.kolonne-1];
                        r.naboOst = maze[r.rad][r.kolonne+1];
                        r.naboNord = maze[r.rad-1][r.kolonne];
                        r.naboSor = maze[r.rad+1][r.kolonne];
                    }
                } */
    }
