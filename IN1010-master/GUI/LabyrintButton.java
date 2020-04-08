import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class LabyrintButton extends Button {
    private int kolonne, rad;
    private GridPane grid;
    private Labyrint labyrint;

    public LabyrintButton(int klnn, int rd, Labyrint lbrnt, GridPane grd){
        kolonne = klnn;
        rad = rd;
        labyrint = lbrnt;
        grid = grd;
    }

    public int hentKolonne(){return kolonne;}
    public int hentRad(){return rad;}
    public Labyrint hentLabyrint(){return labyrint;}
    public GridPane hentGridPane(){return grid;}
    
}