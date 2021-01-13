package AI;

import Tree.Node;
import data_structure.Stick;

import java.util.ArrayList;

public class AlphaBeta_AI extends Abstract_AI implements AI{

    private static final int INFINIT = Integer.MAX_VALUE;
    private static final int MINUS_INFINIT = Integer.MIN_VALUE;

    public AlphaBeta_AI(ArrayList<Stick[]> game) {
        super(game);
    }

    @Override
    public void play() {
        Node root;
        int value = alpha_beta(root,INFINIT,MINUS_INFINIT);
        Stick[] choice;
        if (value == -1){
            choice = root.get_choice();
        }else{
            choice = root.first();
        }
        for (Stick stick : choice){
            stick.setPlayer(2);
            stick.lock();
        }

    }

    private int alpha_beta(Node node, int alpha, int beta){
        if (node.is_leaf()){
            if (node.win()){
                return 1;
            }
            return -1;
        } else {
            int val;
            if (node.is_min_node()){
                val = INFINIT;
                for (Node son : node.getSons()){
                    val = Math.min(val, alpha_beta(son, alpha,beta));
                    son.set_value(val);
                    if (alpha > val) return val;
                    beta = Math.min(beta,val);
                }
            } else {
                val = MINUS_INFINIT;
                for (Node son : node.getSons()) {
                    val = Math.max(val, alpha_beta(son, alpha, beta));
                    son.set_value(val);
                    if (val > beta) return val;
                    alpha = Math.max(alpha,val);
                }
            }
            return val;
        }
    }
}
