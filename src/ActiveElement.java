import java.util.ArrayList;
import java.util.List;

/**
 A nem cső (PassiveElement) típusú játékmezőket modellező ősosztály, leszármazottai a Source,
 Cistern, Pump osztályok.
 */

public class ActiveElement extends Element{
    protected int waterInside;
    protected ArrayList<PassiveElement> pipes = new ArrayList<>();

    public ActiveElement(Game _game) {
        super(_game);
    }


    public void breakRandom(){ }

    public int getId(){
        return -1;
    }

    public boolean connectElement(Element e){
        return false;
    }

    public void disconnectPipe(PassiveElement p){

    }

    public boolean isNeighbour(Element e){
        for (PassiveElement pipe : pipes) {
            if (pipe.getId() == ((PassiveElement) e).getId())
                return true;
        }
        return false;
    }

    public int getWaterInside(){
        return waterInside;
    }

    public void setWaterInside(int n){
        waterInside = n;
    }

    public ArrayList<PassiveElement> getPipes(){
        return pipes;
    }



    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() {
        return "activeElement";
    }
}
