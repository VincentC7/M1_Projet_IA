package Tree;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import data_structure.Stick;

public  class Tree {
	
	public int depth = 0;
	public int valeur = 0;

	// Fils du noeud
	public ArrayList<Node> sons = new ArrayList<Node>();
			// Lignes du jeu
			public ArrayList<Stick[]> lines = new ArrayList<Stick[]>();

			
			public static ArrayList<Stick[]> cloneArrayList(ArrayList<Stick[]> list) {
				ArrayList<Stick[]> clone = new ArrayList<Stick[]>(list.size());
			    for (Stick[] item : list) clone.add(item.clone());
			    return clone;
			}			
			
			
			
	// Chaque noeud et racine génèrent leurs propres fils
	public void generateSons() {
		
		System.out.println("début generateSons");
		
		if (!this.is_leaf()) {		
			// on génère tout l'arbre
			// Pour chaque ligne
			// On regarde les possibilités A B C D 
			Stick[] bande = getBiggestPacket();
			switch (bande.length) {
			case 1:
				System.out.println("Case 1");
				leaveNone(bande);
				break;
			case 2:
				System.out.println("Case 2");
				leaveNone(bande);
				leaveUnique(bande);
				break;
			case 3:
				System.out.println("Case 3");
				leaveNone(bande);
				leaveUnique(bande);		
				leaveTwoUnique(bande);
				leavueOneGroup(bande);
				break;
			case 4:
				System.out.println("Case 4");
				leaveNone(bande);
				leaveUnique(bande);		
				leaveTwoUnique(bande);
				leavueOneGroup(bande);
				leavueUniqueAndGroup(bande);
				break;
			case 5:
				System.out.println("Case 5");
				leaveNone(bande);
				leaveUnique(bande);		
				leaveTwoUnique(bande);
				leavueOneGroup(bande);
				leavueUniqueAndGroup(bande);
				leaveTwoGroups(bande);
				break;
			default:
				System.out.println("Case défaut");
				System.out.println(bande.length);
				
				leaveNone(bande);
				System.out.println("fin de none");
				leaveUnique(bande);		
				System.out.println("fin de unique");
				leaveTwoUnique(bande);
				System.out.println("fin de 2 unique");
				leavueOneGroup(bande);
				System.out.println("fin de group");
				leavueUniqueAndGroup(bande);
				System.out.println("fin de 1 + group");
				leaveTwoGroups(bande);
				System.out.println("fin de 2 groups");
				break;
			}
			sons.forEach(son -> son.generateSons());
		} else {
			// C'est une feuille, le joueur qui se retrouve face à une feuille a gagné puisque c'est le joueur d'avant qui a du cocher le dernier
			if(this.depth%2==0) {
				this.valeur=1;	
			} else {
				// joueur victoire
				this.valeur=-1;	
			}
			

			System.out.println("#####################################################################################");
		}
		
		
	}
	
	
	public void set_value(int valeur) {
		this.valeur=valeur;
	}
	
	public boolean win() {
		if(this.valeur==-1) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
	// On cherche dans tout le jeu en cours le plus gros paquet (on retourne le premier recontré en cas d'égalité(s))
	public Stick[] getBiggestPacket(){
		System.out.println("getBiggestPacket");
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
				}
				if (count > maxSize) {
					biggestPacket = (ArrayList<Stick>) temp.clone();
					maxSize = count;
				}
				temp.clear();
				count = 0;
			}
			
			Stick[] biggestPacket2 = new Stick[biggestPacket.size()];
			for (int i = 0; i < biggestPacket.size(); i++) {
				biggestPacket2[i] = biggestPacket.get(i);
			}
			return biggestPacket2;
	}
	
	// Ces fonctions prennent en argument le biggestPacket
	// On barre tout
	public void leaveNone(Stick[] modifyMe) {
		System.out.println("Début de leaveNone");
		for(int p =0; p < modifyMe.length ; p++) {
			System.out.println(modifyMe[p]);

		}
		System.out.println("maintenant on va modifier");

		//ArrayList<Stick[]> newSon = new ArrayList<Stick[]>();
		//newSon = (ArrayList<Stick[]>) lines.clone();
		 ArrayList<Stick[]> newSon = cloneArrayList(lines);

		
		
		
		System.out.println("clone avant modif");

		for (int i = 0; i < newSon.size(); i++) {
			for (int j = 0; j < newSon.get(i).length; j++) {
				System.out.println(newSon.get(i)[j]);
				}
			}
		for(int i = 0; i<modifyMe.length; i ++ ) {
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()] = modifyMe[i].clone();
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()].lock();
		}
		
		
		System.out.println("clone APR7S modif");
		for (int i = 0; i < newSon.size(); i++) {
			for (int j = 0; j < newSon.get(i).length; j++) {
				System.out.println(newSon.get(i)[j]);
				}
			}
		
	//	System.out.println("Vérification de duplication");
	//	if (!searchRoot().searchSameNode(this)) {
	//		System.out.println("Nouveau noeud trouvé!");
			sons.add(new Node(newSon, this, this.depth+1));
	//	} else {
	//		System.out.println("noeud déjà existant");
		//}
		
		System.out.println("fin de leave non in function");
	}
	
	// On barre tout sauf le premier bâton du groupe
	public void leaveUnique(Stick[] modifyMe) {
		System.out.println("Début de leaveUnique");
		for(int p =0; p < modifyMe.length ; p++) {
			System.out.println(modifyMe[p]);

		}
		System.out.println("maintenant on va modifier");

		//ArrayList<Stick[]> newSon = new ArrayList<Stick[]>();
		//newSon = (ArrayList<Stick[]>) lines.clone();
		 ArrayList<Stick[]> newSon = cloneArrayList(lines);
		System.out.println("clone avant modif");

		for (int i = 0; i < newSon.size(); i++) {
			for (int j = 0; j < newSon.get(i).length; j++) {
				System.out.println(newSon.get(i)[j]);
				}
			}
		for(int i = 1; i<modifyMe.length; i ++ ) {
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()] = modifyMe[i].clone();
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()].lock();
		}
		
		
		System.out.println("clone APR7S modif");
		for (int i = 0; i < newSon.size(); i++) {
			for (int j = 0; j < newSon.get(i).length; j++) {
				System.out.println(newSon.get(i)[j]);
				}
			}
		
	//	System.out.println("Vérification de duplication");
	//	if (!searchRoot().searchSameNode(this)) {
	//		System.out.println("Nouveau noeud trouvé!");
			sons.add(new Node(newSon, this, this.depth+1));
	//	} else {
	//		System.out.println("noeud déjà existant");
		//}
			System.out.println("fin de leaveUnique");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// On laisse les extrémités isolées
	public void leaveTwoUnique(Stick[] modifyMe) {
		//ArrayList<Stick[]> newSon = new ArrayList<Stick[]>();
		//newSon = (ArrayList<Stick[]>) lines.clone();
		 ArrayList<Stick[]> newSon = cloneArrayList(lines);
		 for(int i = 1; i<modifyMe.length-1; i ++ ) {
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()] = modifyMe[i].clone();
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()].lock();
		}
	//	System.out.println("Vérification de duplication");
	//	if (!searchRoot().searchSameNode(this)) {
	//		System.out.println("Nouveau noeud trouvé!");
			sons.add(new Node(newSon, this, this.depth+1));
		//} else {
		//	System.out.println("noeud déjà existant");
		//}
	}
	
	public void leavueOneGroup(Stick[] modifyMe) {
		//ArrayList<Stick[]> newSon = new ArrayList<Stick[]>();
		//newSon = (ArrayList<Stick[]>) lines.clone();
		 ArrayList<Stick[]> newSon = cloneArrayList(lines);
		for(int i = 2; i<modifyMe.length; i ++ ) {
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()] = modifyMe[i].clone();
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()].lock();
		}
	//	System.out.println("Vérification de duplication");
	//	if (!searchRoot().searchSameNode(this)) {
		//	System.out.println("Nouveau noeud trouvé!");
			sons.add(new Node(newSon, this, this.depth+1));
		//} else {
		//	System.out.println("noeud déjà existant");
		//}		
		
	}
	
	public void leavueUniqueAndGroup(Stick[] modifyMe) {
		//ArrayList<Stick[]> newSon = new ArrayList<Stick[]>();
		//newSon = (ArrayList<Stick[]>) lines.clone();
		 ArrayList<Stick[]> newSon = cloneArrayList(lines);
		for(int i = 2; i<modifyMe.length-1; i ++ ) {
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()] = modifyMe[i].clone();
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()].lock();
		}
		//System.out.println("Vérification de duplication");
		//if (!searchRoot().searchSameNode(this)) {
		//	System.out.println("Nouveau noeud trouvé!");
			sons.add(new Node(newSon, this, this.depth+1));
		//} else {
		//	System.out.println("noeud déjà existant");
		//}
	}
		
	public void leaveTwoGroups(Stick[] modifyMe) {
		//ArrayList<Stick[]> newSon = new ArrayList<Stick[]>();
		//newSon = (ArrayList<Stick[]>) lines.clone();
		 ArrayList<Stick[]> newSon = cloneArrayList(lines);
		for(int i = 2; i<modifyMe.length-2; i ++ ) {
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()] = modifyMe[i].clone();
			newSon.get(modifyMe[i].getY())[modifyMe[i].getX()].lock();
		}
		System.out.println("Vérification de duplication");
		//if (!searchRoot().searchSameNode(this)) {
			//System.out.println("Nouveau noeud trouvé!");
			sons.add(new Node(newSon, this, this.depth+1));
		//} else {
			//System.out.println("noeud déjà existant");
		//}
			
	}



	public ArrayList<Node> getSons() {
		return sons;
	}

	public ArrayList<Stick[]> getLines() {
		return lines;
	}
	
	public Root searchRoot() {
		return null;
	};
	
	public boolean searchSameNode(Tree node) {
		System.out.println("pas la");
		return false;		
	}
	
	// Parcours de toutes les lignes,
	public boolean is_leaf() {
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length; j++) {
				if(!lines.get(i)[j].getState()) {
						return false;
				}
			}
		}


		
		return true;
	}

	

	
	
	
	
}
