import java.util.ArrayList;

public abstract class Player {
    protected Element element;


    public void move(Element e){ //0
        element.isNeighbour(e); //1
        // felhasználótól megkérdezni, hogy foglalt-e a cső, ha nem:
        e.acceptPlayer(this); //1
        element.removePlayer(this); //1
    }

    public abstract void doElement();

    public void controlPump(PassiveElement p1, PassiveElement p2){ //0
        ((Pump) element).setInPipe(p1); //1
        ((Pump) element).setOutPipe(p2); //1
    }

    public void setElement(Element e){

    }
}
