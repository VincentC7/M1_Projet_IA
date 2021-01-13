package Tree;

import java.util.ArrayList;

import data_structure.Stick;

public  class Tree {
	
	// Fils du noeud
			ArrayList<Node> sons = new ArrayList<Node>();
			// Lignes du jeu
			ArrayList<Stick[]> lines = new ArrayList<Stick[]>();

	// Chaque noeud et racine génèrent leurs propres fils
	public void generateSons() {
		// on génère tout l'arbre
		
		
		
		// on retient ce qu'on a déjà généré pour ne pas faire de doublon
		
	}

	public ArrayList<Node> getSons() {
		return sons;
	}

	public ArrayList<Stick[]> getLines() {
		return lines;
	}
	
	public boolean is_leaf() {

		ArrayList<Integer> groups1;
		ArrayList<Integer> groups2;
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length; j++) {
				if(lines.get(i)[j].getState()) {
					return false;
				}

			}
		}
		
		return true;
	}

	

	
	
	
	
}
