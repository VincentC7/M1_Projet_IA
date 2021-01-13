package Tree;

import java.util.ArrayList;

import data_structure.Stick;

//Noeud de l'arbre
public class Node extends Tree {
	
	public Node (	ArrayList<Stick[]> lines) {
		this.lines = lines;
	}
	
	public boolean equals (Node node) {
		ArrayList<Integer> groups1;
		ArrayList<Integer> groups2;
		for (int i = 0; i < lines.size(); i++) {
			groups1 = new ArrayList<Integer>();
			groups2 = new ArrayList<Integer>();
			int compteur1 = 0;
			int compteur2 = 0;
			for (int j = 0; j < lines.get(i).length; j++) {
				if(!lines.get(i)[j].getState()) {
					compteur1++;
				}
				else if (compteur1 > 0){
					groups1.add(compteur1);
					compteur1 = 0;
				}
				if(!node.getLines().get(i)[j].getState()) {
					compteur2++;
				}
				else if (compteur2 > 0){
					groups2.add(compteur2);
					compteur2 = 0;
				}
				if (groups1.size() == groups2.size()){
					for (int k = 0; k < groups1.size(); k++) {
						groups2.remove(groups1.get(k));
					}
					if (!groups2.isEmpty()) {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return true;
	
	
}
}
