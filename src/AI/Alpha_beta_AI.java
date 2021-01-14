package AI;

import data_structure.Game;
import data_structure.Stick;

import Tree2.Node;
import Tree2.Tree;

import java.util.ArrayList;


public class Alpha_beta_AI extends Abstract_AI implements AI{

    private static final int INFINIT = Integer.MAX_VALUE;
    private static final int MINUS_INFINIT = Integer.MIN_VALUE;

    public Alpha_beta_AI(Game game) {
        super(game);
    }

    @Override
    public void play() {
        ArrayList<int[]> player_choice = game.getLast_selection();
        int line = player_choice.get(0)[0];
        int length = player_choice.get(0).length;
        int start = player_choice.get(0)[1];
        int end = player_choice.get(player_choice.size()-1)[1];
        Tree tree = new Tree(game.build_string_game(), ""+length+";"+line+";"+start+";"+end);
        Node n = tree.getRoot();
        int value = alpha_beta(n,INFINIT,MINUS_INFINIT);

        String choice;
        if (value == 1){
            choice = n.get_choice(); // Sticks à selectionner
        }else{
            choice = n.first();
        }

        System.out.println(choice);
        String[] data = choice.split(";");
        game.apply_choice(Integer.parseInt(data[1]),Integer.parseInt(data[2]),Integer.parseInt(data[3]));
    }

    private int alpha_beta(Node node, int alpha, int beta){
        System.out.println(node);
        if (node.is_leaf()) {
            if (node.win()) {
                return 1;
            }
            return -1;
        } else {
            int val;
            int v_alpha = alpha;
            int v_beta = beta;
            if (node.is_min_node()){
                val = INFINIT;
                for (Node son : node.getSons()){
                    val = Math.min(val, alpha_beta(son, v_alpha,v_beta));
                    son.set_value(val);
                    if (v_alpha > val) return val;
                    v_beta = Math.min(v_beta,val);
                }
            } else {
                val = MINUS_INFINIT;
                for (Node son : node.getSons()) {
                    val = Math.max(val, alpha_beta(son, v_alpha, v_beta));
                    son.set_value(val);
                    if (val > v_beta) return val;
                    v_alpha = Math.max(v_alpha,val);
                }
            }
            return val;
        }
    }
}
