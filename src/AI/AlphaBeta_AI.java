package AI;

import Tree.Node;
import Tree.Root;
import Tree.Tree;
import data_structure.Game;
import data_structure.Stick;

import java.util.ArrayList;

public class AlphaBeta_AI extends Abstract_AI implements AI{

    private static final int INFINIT = Integer.MAX_VALUE;
    private static final int MINUS_INFINIT = Integer.MIN_VALUE;

    private Node last_node_played;
    private Tree tree;

    public AlphaBeta_AI(Game game) {
        super(game);
        //Création de l'arbre
        //tree = ....
    }
    
    public Stick[] get_choice(Game game) {
    	// Générer un arbre avec l'état du jeu actuel
    	ArrayList<Stick[]> cloned = game.getLines();
    	ArrayList<Stick[]> gameToia;
    	Node n = new Node(game.getLines(), new Root(), 0);
    	n.generateSons();
		return null;
    	
    	// trouver un jeu à faire qui rapporte 1 (exécuter alpha_beta)
    	
    	// trouver les batons à cocher dans le vrai jeu
    	
    	// je regarde sa première ligne
//    	je prends son index 0
    	// je sais quen ça correspond au baton isolé du premier niveau
    	
    	

// index0 : I
// index1 : III
// index2 : I III I getindexof(baton)
// index3 : IIIIIII
    	
    	
    	
    	
    	
    	// entre x1 et x2 : 30
    	// entre x1 et x1 il y a 55 en partant de 254
    	
    	
    }

    @Override
    public void play() {
        ArrayList<int[]> player_selection = game.getLast_selection();
        Node root; //Récupérer le bon noeud via player_selection
        int value = alpha_beta(root,INFINIT,MINUS_INFINIT);
        Stick[] choice;
        if (value == 1){
            choice = root.get_choice(); // Sticks à selectionner
        }else{
            choice = root.first();
        }
        for (Stick stick : choice){
            stick.setPlayer(2);
            stick.lock();
        }
        last_node_played = root;
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
    
    public Stick[] get_choice() {
		return null;
    	
    	// on a un node
    	
    	// on regarde chaque fils jusqu'à trouver une valeur 1
    	
    	// on compare la différence entre fils et père
    	
    	// c'est le tableau de baton à jouer
    	
    	
    }
    
}
