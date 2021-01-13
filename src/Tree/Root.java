package Tree;

import java.util.ArrayList;
import java.util.List;

import GUI.GUI;
import data_structure.Stick;

// Racine de l'arbre qui pointe sur des noeuds
public class Root extends Tree {

	// Fils du noeud
	ArrayList<Node> sons = new ArrayList<Node>();
	// Lignes du jeu
	ArrayList<Stick[]> lines = new ArrayList<Stick[]>();
	
	// Création d'un jeu vide (toutes les lignes ont des groupes de batons non cochées)
	private void initialize() {
        lines.add(new Stick[1]);
        for (int i = 1; i < 4; i++) {
            lines.add(new Stick[i*2+1]);
        }
        int mid = 3;
        int left = 3;
        int right = 3;
        int c = left;
        for (int i = 0 ; i < lines.size(); i++) {
        	for (int j = 0 ; j < lines.get(i).length; j++) {
        		lines.get(i)[j] = new Stick(c,i,right,i);
        		c = c+1;
        	}
        	left --;
        	right ++;
        	c = left;
        }
        	lines.forEach((line) -> {
            for (int i = 0; i < line.length; i++) {
                System.out.println(line[i]);
            }});
		
	}

	
	
	public Root () {
		initialize();
	}
	
	

	
	
	
	
	
}
