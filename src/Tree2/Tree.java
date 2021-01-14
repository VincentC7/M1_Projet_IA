package Tree2;

import java.util.ArrayList;
import java.util.Collections;

public class Tree {

    private Node root;

    public Tree(String state_game, String choice){
        root = build_tree(state_game,choice);
    }

    public Node build_tree(String stategame, String choice){
        Node node = new Node(stategame,0,choice);
        generate_son(node,stategame,1);
        return node;
    }

    public void generate_son(Node father,String stategame,int lvl){
        ArrayList<String> game = get_packs(stategame);
        if (!game.isEmpty()){
            String choice = game.get(0);
            String[] data = choice.split(";");
            int nb_son = 0;
            switch (data[0]) {
                case "5":
                    nb_son = 6;
                    break;
                case "4":
                    nb_son = 5;
                    break;
                case "3":
                    nb_son = 4;
                    break;
                case "2":
                    nb_son = 2;
                    break;
                case "1":
                    nb_son = 1;
                    break;
            }
            for (int i = 0; i < nb_son; i++) {
                Node n = new Node(stategame,lvl,choice);
                father.add_son(n);
                String new_game = apply_choice(stategame,Integer.parseInt(data[1]),i+1,Integer.parseInt(data[2]),Integer.parseInt(data[3]));
                generate_son(n,new_game,lvl+1);
            }
            String[] last_choice = (game.get(game.size()-1)).split(";");
            if ((last_choice[0]).equals("1")){
                Node n = new Node(stategame,lvl,choice);
                father.add_son(n);
                String new_game = apply_choice(stategame,Integer.parseInt(last_choice[1]),1,Integer.parseInt(last_choice[2]),Integer.parseInt(last_choice[3]));
                generate_son(n,new_game,lvl+1);
            }
        }
    }

    private ArrayList<String> get_packs(String game){
        ArrayList<String> res = new ArrayList<>();
        String[] lines = game.split(";");
        for (int i = 0; i < lines.length; i++) {
            int pack_length = 0;
            char[] line = lines[i].toCharArray();
            for (int index = 0; index < line.length; index++) {
                if (line[index] == '1'){
                    if (pack_length != 0) res.add((Math.min(pack_length, 5)) + ";"+(i+1)+";"+(index-pack_length)+";"+(index-1));
                    pack_length = 0;
                } else {
                    pack_length++;
                }
            }
            if (pack_length != 0) res.add((Math.min(pack_length, 5)) + ";"+(i+1)+";"+(line.length-pack_length)+";"+(line.length-1));
        }
        res.sort(Collections.reverseOrder());
        return res;
    }


    private String apply_choice(String game,int line, int choice){
        return apply_choice(game,line,choice,0,line == 1 ? 1 : line == 2 ? 3 : line == 3 ? 5 : 7);
    }

    private String apply_choice(String game,int line, int choice, int start_pack, int end_pack){
        StringBuilder res = new StringBuilder(game);
        int index_start = line == 1 ? 0 : line == 2 ? 2 : line == 3 ? 6 : 12;
        String sub_str = res.substring(index_start+start_pack,index_start+end_pack+1);
        switch (choice){
            case 1:
                sub_str = apply_choice_1(sub_str);
                break;
            case 2:
                sub_str = apply_choice_2(sub_str);
                break;
            case 3:
                sub_str = apply_choice_3(sub_str);
                break;
            case 4:
                sub_str = apply_choice_4(sub_str);
                break;
            case 5:
                sub_str = apply_choice_5(sub_str);
                break;
            case 6:
                sub_str = apply_choice_6(sub_str);
                break;
        }
        res.replace(index_start+start_pack,index_start+end_pack+1,sub_str);
        return res.toString();
    }

    private static String apply_choice_1(String pack){
        return pack.replaceAll("0","1");
    }

    private static String apply_choice_2(String pack){
        pack = pack.replaceAll("0","1");
        StringBuilder res = new StringBuilder(pack);
        res.setCharAt(0,'0');
        return res.toString();
    }

    private static String apply_choice_3(String pack){
        pack = pack.replaceAll("0","1");
        StringBuilder res = new StringBuilder(pack);
        res.setCharAt(0,'0');
        res.setCharAt(res.length() -1,'0');
        return res.toString();
    }

    private static String apply_choice_4(String pack){
        pack = pack.replaceAll("0","1");
        StringBuilder res = new StringBuilder(pack);
        res.setCharAt(0,'0');
        res.setCharAt(1,'0');
        return res.toString();
    }

    private static String apply_choice_5(String pack){
        pack = pack.replaceAll("0","1");
        StringBuilder res = new StringBuilder(pack);
        res.setCharAt(0,'0');
        res.setCharAt(1,'0');
        res.setCharAt(res.length() -1,'0');
        return res.toString();
    }

    private static String apply_choice_6(String pack){
        pack = pack.replaceAll("0","1");
        StringBuilder res = new StringBuilder(pack);
        res.setCharAt(0,'0');
        res.setCharAt(1,'0');
        res.setCharAt(res.length() -1,'0');
        res.setCharAt(res.length() -2,'0');
        return res.toString();
    }

    public Node getRoot() {
        return root;
    }
}

