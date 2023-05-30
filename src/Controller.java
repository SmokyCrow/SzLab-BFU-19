import java.util.ArrayList;
import java.util.Random;

/**
 * A belso mukodest kezelo osztaly
 * A program altal kezelt funkciokat valositja meg
 */

public class Controller {
    Game game;
    Controller(Game _game){
        game = _game;
        initElements();
    }

    private ArrayList<Cistern> cisterns = new ArrayList<>();

    /**
     * Tagvaltozo, a jatekban levo pumpak listaja
     */
    private ArrayList<Pump> pumps = new ArrayList<>();

    public void initElements(){
        for(int i = 0; i < game.getElements().size(); i++){

            if(game.getElements().toString().startsWith("pu"))

                pumps.add((Pump) game.getElements().get(i));

            if(game.getElements().toString().startsWith("ci"))
                cisterns.add((Cistern) game.getElements().get(i));
        }
    }

    /** A parameterkent kapott pumpan meghívja a breakPump() metodust
     */
    public void breakPump(){
        Random rand = new Random();
        int rand_int = rand.nextInt(0, pumps.size());
        int pumpId = 0;
        while(pumpId != rand_int)
            pumpId++;
        pumps.get(pumpId).breakRandom();
    }

    /** A ciszternanal bizonyos idokozonkent uj csovek letrehozasa
     */
    public void makePipe(){
        for(int i = 0; i < cisterns.size(); i++){


        }
    }

    public void makePump(){

    }
}
