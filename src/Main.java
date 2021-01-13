import java.util.ArrayList;

import Tree.Root;
import data_structure.Stick;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/jeu.fxml"));
        primaryStage.setTitle("PrimeSticks");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
       //launch(args);
	    Root r = new Root();   
	    System.out.println("Apocalypse");
	    r.generateSons();
		System.out.println("fin du main bitchies");
    }
}