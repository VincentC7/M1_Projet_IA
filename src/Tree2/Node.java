package Tree2;

import data_structure.Stick;

import java.util.ArrayList;

public class Node {

    private ArrayList<Node> sons;
    private int value;

    private String stat_game;
    private String action;
    private int lvl;

    public Node(String stat_game,int lvl, String choice){
        sons = new ArrayList<>();
        this.stat_game = stat_game;
        this.lvl = lvl;
        this.action = choice;
    }

    public void add_son(Node n){
        sons.add(n);
    }

    public boolean is_leaf() {
        return sons.isEmpty();
    }

    public boolean win() {
        return is_min_node();
    }

    public boolean is_min_node() {
        return lvl%2 == 0;
    }

    public ArrayList<Node> getSons() {
        return sons;
    }

    public void set_value(int val) {
        value=val;
    }

    public String get_choice() {
        for (Node son : sons) {
            if (son.value == 1) return son.action;
        }
        return "je passe pas la";
    }

    public String first() {
        return sons.get(0).action;
    }

    @Override
    public String toString() {
        return stat_game;
    }
}
