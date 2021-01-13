package Tree;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import data_structure.Stick;

public  class Tree {
	
	// Fils du noeud
			ArrayList<Node> sons = new ArrayList<Node>();
			// Lignes du jeu
			ArrayList<Stick[]> lines = new ArrayList<Stick[]>();

	// Chaque noeud et racine génèrent leurs propres fils
	public void generateSons() {
		// on génère tout l'arbre
		
		// Pour chaque ligne
		// On regarde les possibilités A B C D 
		
		
		
		// on retient ce qu'on a déjà généré pour ne pas faire de doublon
		
	}
	
	public Stick[] getBiggestPacket(int linePosition){
			// Dans l'array list, en index 0 on a le plus grand paquet de la ligne 0, ect ect 
			ArrayList<Stick> biggestPacket = new ArrayList<Stick>();
			ArrayList<Stick> temp = new ArrayList<Stick>();
			int maxSize = 0;
			int count = 0;
			for (int i = 0; i < lines.size(); i++) {
				for (int j = 0; j < lines.get(i).length; j++) {
					if(!lines.get(i)[j].getState()) {
						temp.add(lines.get(i)[j]);
						count++;
					}
					else if (count > maxSize) {
						biggestPacket = (ArrayList<Stick>) temp.clone();
						maxSize = count;
						temp.clear();
						count = 0;
				}
				temp.clear();
				count = 0;
			}
			
			
			
			
			
			
			
			
			
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
	}
	
	
	public void leaveNone(Stick[] modifyMe) {
		// on coche tout
		
		
	}
	
	public void leaveUnique(Stick[] modifyMe) {
			
	}
	
	public void leaveTwoUnique(Stick[] modifyMe) {
		
	}
	
	public void leavueOneGroup(Stick[] modifyMe) {
		
	}
	
	public void leavueUniqueAndGroup(Stick[] modifyMe) {
		
	}
		
	public void leaveTwoGroups(Stick[] modifyMe) {
		
	}



	public ArrayList<Node> getSons() {
		return sons;
	}

	public ArrayList<Stick[]> getLines() {
		return lines;
	}
	
	// Parcours de toutes les lignes,
	public boolean is_leaf() {
		boolean notFound = true;
		ArrayList<Integer> groups1;
		ArrayList<Integer> groups2;
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length; j++) {
				if(lines.get(i)[j].getState()) {
					if (notFound==false) {
						return false;
					}
					notFound = false ;
				}
			}
		}
		return true;
	}

	

	
	
	
	
}
