package Tree;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import data_structure.Stick;

//Noeud de l'arbre
public class Node extends Tree {
	
	Tree father;
	public Node (ArrayList<Stick[]> lines, Tree father) {
		this.lines = lines;
		this.father = father;
	}
	
	public boolean equals (Node node) {
		System.out.println("Test equals");
		ArrayList<Integer> groupeLigne;
		ArrayList<Integer> groupeTest;
		for (int i = 0; i < lines.size(); i++) {
			groupeLigne = new ArrayList<Integer>();
			groupeTest = new ArrayList<Integer>();
			int compteur1 = 0;
			int compteur2 = 0;
			for (int j = 0; j < lines.get(i).length; j++) {
				if(!lines.get(i)[j].getState()) {
					compteur1++;
				}
				else if (compteur1 > 0){
					groupeLigne.add(compteur1);
					compteur1 = 0;
				}
				if(!node.getLines().get(i)[j].getState()) {
					compteur2++;
				}
				else if (compteur2 > 0){
					groupeTest.add(compteur2);
					compteur2 = 0;
				}
			}
			if (compteur1 > 0){
				groupeLigne.add(compteur1);
			}
			if (compteur2 > 0){
				groupeTest.add(compteur2);
			}
			for (int j = 0; j < groupeLigne.size(); j++) {
				System.out.println(groupeLigne.get(i)+" "+groupeTest.get(i));
			}
			if (groupeLigne.size() == groupeTest.size()){
				for (int k = 0; k < groupeLigne.size(); k++) {
					groupeTest.remove(groupeLigne.get(k));
				}
				if (!groupeTest.isEmpty()) {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public Root searchRoot() {
		return father.searchRoot();
	};
	
	public boolean searchSameNode(Tree node) {
		boolean search = false;
		System.out.println("research node");
		if (this.equals(node)) {
			search = true;
		}
		else {
			for (int i = 0; i < sons.size(); i++) {
				if(sons.get(i).searchSameNode(node)) {
					search =  true;
				}
			}
		}
		return search;
	}
	

}
