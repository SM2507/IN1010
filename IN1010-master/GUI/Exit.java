import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Exit implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event){
        LabyrintButton labKnapp = (LabyrintButton) event.getSource();
        int kolonne = labKnapp.hentKolonne();
        int rad = labKnapp.hentRad();
        Labyrint labyrint = labKnapp.hentLabyrint();
        GridPane grid = labKnapp.hentGridPane();

        labyrint.finnUtveiFra(kolonne, rad);
        Liste<String> utveier = labyrint.hentUtveier();
        String exit = utveier.hent(0);
        
        int str = 600/labyrint.labRad;
        boolean[][] losning = losningStringTilTabell(exit, labyrint.labRad, labyrint.labKol);

        for(int i = 0; i < labyrint.labRad; i++){
            for(int j = 0; j < labyrint.labKol; j++){
                if(losning[i][j]){
                    Rectangle tagging = new Rectangle(str, str);
                    tagging.setFill(Color.TEAL);
                    grid.add(tagging, j, i);
                }
            }
        }
    } 

    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde)
    {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find())
        {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }
}