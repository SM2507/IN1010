import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.FileNotFoundException;

import javafx.scene.shape.Rectangle;
import java.io.File;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.layout.Pane;

public class GUI extends Application{
    Labyrint labyrint;
    @Override
    public void start(Stage stage){
        FileChooser filvelger = new FileChooser();  // FileChooser objekt lages
        File labyrintFil = filvelger.showOpenDialog(stage);  // Aapner mappevindu, og returnerer fil

        try{
            labyrint = Labyrint.lesFraFil(labyrintFil);  
        }
        catch(Exception e){ e.printStackTrace();
                            System.exit(1);}
        
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        Rute[][] ruter = labyrint.hentLabyrint();

        int antRader = labyrint.labRad;
        int str = 600/antRader;
        for(Rute[] rarr : ruter){
            for(Rute r : rarr){
                if(r instanceof SortRute){
                    grid.add(new Rectangle(str, str), r.kolonne, r.rad);
                }
                else if(r instanceof HvitRute){
                    LabyrintButton lb = new LabyrintButton(r.kolonne, r.rad, r.labRef, grid);
                    Exit utgang = new Exit();
                    lb.setOnAction(utgang);
                    lb.setMinSize(0, 0);
                    lb.setPrefSize(str, str);
                    grid.add(lb, r.kolonne, r.rad);
                }
            }
        }
        Pane pane = new Pane();
        pane.getChildren().add(grid);

        Scene scene = new Scene(pane);
        stage.setTitle("Hvordan komme seg ut av en labyrint");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}