import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Player {
    protected Element element;


    public void move(Element e) throws IOException { //0
        element.isNeighbour(e); //1
        // felhasználótól megkérdezni, hogy foglalt-e a cső
        System.out.println("Is the pipe occupied? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y"))
                return;
            else if (s.equals("N")) {
                e.acceptPlayer(this); //1
                element.removePlayer(this); //1
                return;
            }
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    public abstract void doElement();

    public void controlPump(PassiveElement p1, PassiveElement p2){ //0
        ((Pump) element).setInPipe(p1); //1
        ((Pump) element).setOutPipe(p2); //1
    }

    public void setElement(Element e){

    }
}
