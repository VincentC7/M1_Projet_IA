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
		
		// appel aplha beta contrcut
		// genere arbre
		// attribut neoud ia joué
		
		
		

//		for (int i = 0; i < r.lines.size(); i++) {
//			for (int j = 0; j < r.lines.get(i).length; j++) {
//				System.out.println(r.lines.get(i)[j]);
//				}
//			}
//		
//		for (int i = 0; i < r.sons.size(); i++) {
//			for (int j = 0; j < r.sons.get(i).getLines().size(); j++) {
//				for (int k = 0; k < r.sons.get(i).getLines().get(j).length; k++) {
//					System.out.println(r.sons.get(i).getLines().get(j)[k]);
//				}
//				
//			}
//			System.out.println("################# fin état de jeu ################");
//		}
		
		
		
    }
}