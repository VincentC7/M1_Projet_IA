package AI;

import data_structure.Game;
import data_structure.Stick;

import java.util.ArrayList;

abstract class Abstract_AI {

    //Etat du jeu actuel (faire que de la lecture sinon ça fait un effet de bord)
    //Le seul truc autorisé à faire sur cet objet c'est stick.lock() ça permet de vérouiller un stick definitivement
    protected Game game;

    //Constructeur
    Abstract_AI(Game game){
        this.game = game;
    }

}
