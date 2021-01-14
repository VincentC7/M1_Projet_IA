package data_structure;

import java.util.ArrayList;
import java.util.Comparator;

import AI.AI;
import GUI.GUI;
import AI.Random_AI;
import AI.Alpha_beta_AI;

public class Game {

    /**
     * Structure de données du jeu
     */
    private ArrayList<Stick[]> lines;

    /**
     * Joueur en train de jouer
     */
    private int current_player;
    
    
    

    /**
     * Choix du joueur par exemple si J1 veut faire X = [1,3]
     * dans cette variable il y aura [[1,n],[1,n+1],[n+2]]
     */
    private ArrayList<int[]> current_selection;
    private ArrayList<int[]> last_selection;
    private ArrayList<int[]> player_selection;

    private AI artificial_intelligence;

    public Game(int height, String gameplay){
        lines = new ArrayList<>();
        lines.add(new Stick[1]);
        for (int i = 1; i < height; i++) {
            lines.add(new Stick[i*2+1]);
        }
        current_selection = new ArrayList<>();
        switch (gameplay) {
            case "Contre un joueur":
                artificial_intelligence = null;
                break;
            case "Contre IA : Aléatoire":
                artificial_intelligence = new Random_AI(this);
                break;
            case "Contre IA : AlphaBeta":
                artificial_intelligence = new Alpha_beta_AI(this);
                break;
        }
    }

    public String build_string_game(){
        StringBuilder res = new StringBuilder();
        for (Stick[] line : lines) {
            for (Stick stick : line) {
                res.append(stick.getState() ? "1" : "0");
            }
            res.append(";");
        }
        res.setLength(res.length() - 1);
        return res.toString();
    }

    /**
     * # <= bord de la fenêtre
     * | <= mid_x
     *
     * #################################
     * #               |               #
     * #               |               #
     * #               |               #
     * #               |               #
     * #               |               #
     * #               |               #
     * #               |               #
     * #               |               #
     * #################################
     *
     * @param mid_x coordonées x de l'axe de symétrie sur l'interface graphique
     */
    public void setup_game(double mid_x){
        int mid = (int) (mid_x-GUI.STICK_W/2);
        for (int i = 0; i < lines.size(); i++) {
            Stick[] line = lines.get(i);
            int y = (GUI.STICK_H+GUI.STICK_MARGE_Y)*(i);
            line[line.length/2] = new Stick(mid,y,mid+GUI.STICK_W,y+GUI.STICK_H);
            for (int j = 0; j < line.length/2; j++){
                int x_right = mid + (GUI.STICK_W+GUI.STICK_MARGE_X)*(j+1);
                int x_left = mid - (GUI.STICK_W+GUI.STICK_MARGE_X)*(j+1);
                line[line.length/2 - j-1] = new Stick(x_left,y,x_left+GUI.STICK_W,y+GUI.STICK_H);
                line[line.length/2 + j+1] = new Stick(x_right,y,x_right+GUI.STICK_W,y+GUI.STICK_H);
            }
        }
    }

    public ArrayList<Stick[]> getLines() {
        return lines;
    }

    public void apply_choice(int line_index, int start, int end){
        Stick[] line = lines.get(line_index-1);
        for (int i = start; i <= end; i++) {
            line[i].setPlayer(2);
            line[i].lock();
        }
    }

    public int height(){
        return lines.size();
    }

    public int line_width(int line){
        return lines.get(line).length;
    }


    public boolean is_finished() {
        int nb_stick_unused = 0;
        for (Stick[] line : lines) {
            for (Stick stick : line) {
                if (!stick.getState()) nb_stick_unused++;
                if (nb_stick_unused > 1) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String line_jump = System.lineSeparator();
        StringBuilder res = new StringBuilder("Jeu {"+line_jump);
        for (Stick[] line : lines) {
            res.append("\t[").append(line_jump);
            for (Stick stick : line) {
                if (stick == null) res.append("null,");
                else res.append("\t\t").append(stick.toString()).append(line_jump);
            }
            res.append("\t]").append(line_jump);
        }
        return res.append("}").toString();
    }

    public void click(int x, int y){
        for (int i = 0; i < lines.size(); i++) {
            Stick[] line = lines.get(i);
            for (int j = 0; j < line.length; j++) {
                if (line[j].is_click_inside(x,y)){
                    if (verifSelection(i,j)) {
                        line[j].setPlayer(current_player);
                    } else {
                        System.out.println("Tu peux pas faire ça");
                    }
                }
            }
        }
    }

    private boolean verifSelection(int p_line, int p_colomn) {
        if (lines.get(p_line)[p_colomn].getState()) return false;
        if (current_selection.isEmpty()) {
            current_selection.add(new int[]{p_line, p_colomn});
            return true;
        }
        if (current_selection.get(0)[0] != p_line) return false;
        boolean contains = false;
        for (int[] values : current_selection) {
            if (values[0] == p_line && values[1] == p_colomn){
                current_selection.remove(values);
                contains = true;
                break;
            }
        }
        if (!contains) current_selection.add(new int[]{p_line, p_colomn});
        return true;
    }

    private boolean verifValidation(){
        if (current_selection.isEmpty()) return false;
        ArrayList<int[]> copy_current_selection = new ArrayList<>(current_selection);
        copy_current_selection.sort(Comparator.comparingInt(val -> val[1]));
        int previous_stick = copy_current_selection.get(0)[1];
        for (int i = 1; i < copy_current_selection.size(); i++) {
            int current_stick = copy_current_selection.get(i)[1];
            if (current_stick-previous_stick != 1) return false;
            previous_stick = current_stick;
        }
        return true;
    }

    private void lock_selection(){
    	// Si le joueur humain jour alors on mémorise son tour
    	if (current_player == 1) {
        	player_selection=current_selection;

    	}
        for (int[] coord : current_selection) {
            Stick stick = lines.get(coord[0])[coord[1]];
            stick.lock();
        }
    }

    public int getPlayer(){
        return current_player;
    }

    public void setPlayer(int player){
        current_player = player;
    }

	public int change_player() {
        if (verifValidation()){
            lock_selection();
            System.out.println(this);
            last_selection = new ArrayList<>(current_selection);
            current_selection = new ArrayList<>();
            if (artificial_intelligence != null){
                artificial_intelligence.play();
                System.out.println(getPlayer());
                return getPlayer();
            } else {
                return current_player = current_player == 1 ? 2 : 1;
            }
        }else{
            System.out.println("Tu ne respectes pas les règles");
            return -1;
        }
    }

    public ArrayList<int[]> getLast_selection(){
        return last_selection;
    }
}
