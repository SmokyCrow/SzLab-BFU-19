import java.util.ArrayList;
import java.util.Random;

/**
 * A belso mukodest kezelo osztaly
 * A program altal kezelt funkciokat valositja meg
 */

public class Controller {
    /** Tagvaltozo, a jatekban levo ciszternak listaja
     */
    private ArrayList<Cistern> cisterns;
    /**
     * Tagvaltozo, a jatekban levo pumpak listaja
     */
    private ArrayList<Pump> pumps;

    /** A parameterkent kapott pumpan meghívja a breakPump() metodust
     * @param p Amin meghívja a breakPump() metodust
     */
    public void breakPump(Pump p){
        Random rand = new Random();
        int rand_int = rand.nextInt(1, pumps.size() - 1);
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
}
