package AI;

import data_structure.Game;

import java.util.ArrayList;
import java.util.Collections;

public class Pragmatic_AI extends Abstract_AI implements AI{

    public Pragmatic_AI(Game game) {
        super(game);
    }

    @Override
    public void play() {
        String game = this.game.build_string_game();
        ArrayList<String> packs = get_packs(game);
        String[] data = packs.get(0).split(";");
        packs.remove(0);
        int single_stick_count = Integer.parseInt(data[1]);
        int pack_count = Integer.parseInt(data[0]);

        String action = packs.get(0);
        String[] choice = action.split(";");
        if (pack_count == 1){
            //Joue le packet pour gagner la game
            if (single_stick_count%2 == 0) {
                int max = Integer.parseInt(choice[3]);
                action = action.substring(0,action.length() - 1) + (max-1);
            }
        } else if (pack_count == 2) {
            //Faire en sorte de garder 2 pack
            if (single_stick_count > 0) {
                action = packs.get(packs.size()-1);
            } else {
                int pack_len = Integer.parseInt(choice[0]);
                if (pack_len > 2){
                    //Joue 4
                    int max = Integer.parseInt(choice[3]);
                    action = action.substring(0,action.length() - 1) + (max-2);
                }//else c'est lose
            }
        } else if (pack_count > 2) {
            int pack_len = Integer.parseInt(choice[0]);
            int max = Integer.parseInt(choice[3]);
            int min = Integer.parseInt(choice[2]);
            if (pack_len > 3) action = action.substring(0,action.length() - 3) + ((max+min)/2) + ";" +((max+min)/2);
        }
        String[] final_choice = action.split(";");
        this.game.apply_choice(Integer.parseInt(final_choice[1]),Integer.parseInt(final_choice[2]),Integer.parseInt(final_choice[3]));
    }

    private ArrayList<String> get_packs(String game){
        ArrayList<String> res = new ArrayList<>();
        String[] lines = game.split(";");
        int packs = 0;
        int solo = 0;
        for (int i = 0; i < lines.length; i++) {
            int pack_length = 0;
            char[] line = lines[i].toCharArray();
            for (int index = 0; index < line.length; index++) {
                if (line[index] == '1'){
                    if (pack_length != 0) {
                        if (pack_length == 1){
                            solo++;
                        }else{
                            packs++;
                        }
                        res.add((Math.min(pack_length, 5)) + ";"+(i+1)+";"+(index-pack_length)+";"+(index-1));
                    }
                    pack_length = 0;
                } else {
                    pack_length++;
                }
            }
            if (pack_length != 0) {
                if (pack_length == 1){
                    solo++;
                }else{
                    packs++;
                }
                res.add((Math.min(pack_length, 5)) + ";"+(i+1)+";"+(line.length-pack_length)+";"+(line.length-1));
            }
        }
        res.sort(Collections.reverseOrder());
        res.add(0, packs+";"+solo);
        return res;
    }
}
