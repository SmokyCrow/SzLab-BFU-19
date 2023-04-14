import java.nio.channels.Pipe;
import java.util.ArrayList;

public class ActiveElement extends Element{

    private ArrayList<ActiveElement> pipes;

    public void breakRandom(){

    }

    public boolean isNeighbour(Element e){
        return true;
    }
}
