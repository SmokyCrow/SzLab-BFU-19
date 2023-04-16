import java.util.ArrayList;

public class ActiveElement extends Element{

    @Override
    public String toString() {
        return "pipe";
    }
    private ArrayList<ActiveElement> pipes;

    public void breakRandom(){

    }

    public boolean isNeighbour(Element e){
        return true;
    }


}
