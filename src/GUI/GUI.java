package GUI;

import AI.AI;
import data_structure.Game;
import data_structure.Stick;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

public class GUI implements Initializable {

    public static final int STICK_W = 30;
    public static final int STICK_H = 100;
    public static final int STICK_MARGE_X = 25;
    public static final int STICK_MARGE_Y = 10;
    private static final Color STICK_COLOR_P1 = Color.RED;
    private static final Color STICK_COLOR_P2 = Color.BLUE;


    public Canvas canvas;
    public Label label_current_player;
    public Button btn_validate;
    public ComboBox<String> ia_selector;

    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init_game();
    }

    private void init_game(){
        label_current_player.setText("Joueur : J"+ 1 + "(Rouge)");

        game = new Game(4, ia_selector.getValue());
        double canvas_w = canvas.getWidth();
        double mid = canvas_w/2;
        game.setup_game(mid);
        game.setPlayer(1);

        draw_sticks();
    }

    private void draw_sticks(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(),canvas.getHeight());
        for (Stick[] line : game.getLines()) {
            for (Stick stick : line) {
                int skick_player = stick.getPlayer();
                Color c = !stick.getState() && skick_player == -1  ? Color.GRAY : skick_player == 1 ? STICK_COLOR_P1 : STICK_COLOR_P2;
                draw_stick(stick.getX(),stick.getY(),c);
            }
        }
    }

    private void draw_stick(int x, int y,Color color){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(x,y,STICK_W,STICK_H);
    }

    public void replay(ActionEvent actionEvent) {
        btn_validate.setDisable(false);
        init_game();
    }

    public void canva_click(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX();
        int y = (int) mouseEvent.getY();
        game.click(x,y);
        draw_sticks();
    }

    public void valide_round(ActionEvent actionEvent) {
        int current_player = game.getPlayer();
        int player;
        if ((player = game.change_player()) != -1){
            label_current_player.setText("Joueur : J"+ player+ ("(") + (player == 1 ? "Rouge" : "Bleu") + ")" );
        }
        draw_sticks();
        if (game.is_finished()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Résultat");
            alert.setHeaderText("Gagnant de la partie :");
            alert.setContentText("Le joueur " + current_player + " a gagné");

            alert.showAndWait();
            btn_validate.setDisable(true);
        }
    }

}
