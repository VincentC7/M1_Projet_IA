package AI;

import data_structure.Stick;

import java.util.ArrayList;

public class Random_AI extends Abstract_AI implements AI{


    public Random_AI(ArrayList<Stick[]> game) {
        super(game);
    }

    @Override
    public void play() {
        System.out.println("Choix IA====================");
        int height_game = lines.size();
        ArrayList<ArrayList<Stick>> pack;
        do{
            int line_random = (int) (Math.random()*height_game);
            System.out.println("ligne" + line_random);
            pack = playable_sticks_on_line(line_random);
        } while (pack.get(0).isEmpty());
        int random_pack = (int) (Math.random()*pack.size());

        //Application de la selection
        for (Stick stick : pack.get(random_pack)){
            System.out.println(stick);
            stick.setPlayer(2);
            stick.lock();
        }
        System.out.println("End choix IA====================");
    }

    /**
     * Methode permetant d'obtenir les packets de batons sur une ligne
     * @param line_index index de la ligne où on souhaite avoir des info
     * @return des pack de batonnets (de taille 1 à n)
     */
    protected ArrayList<ArrayList<Stick>> playable_sticks_on_line(int line_index){
        ArrayList<ArrayList<Stick>> res = new ArrayList<>();
        Stick[] line = lines.get(line_index);
        ArrayList<Stick> pack = new ArrayList<>();
        res.add(pack);
        System.out.println("\t####################");
        for (Stick stick : line) {
            if (stick.getState()){
                if (!pack.isEmpty()){
                    res.add(pack);
                    pack = new ArrayList<>();
                    System.out.println("\t\t--------------------");
                }
            } else {
                System.out.println("\t\t"+stick);
                pack.add(stick);
            }
        }
        System.out.println("\t####################");
        return res;
    }

}
