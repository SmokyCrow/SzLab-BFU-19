import java.lang.reflect.Array;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Game {
    private ArrayList<Mechanic> mechanics = new ArrayList<>();
    private ArrayList<Saboteur> saboteurs = new ArrayList<>();
    private ArrayList<PassiveElement> pipes = new ArrayList<>();
    private ArrayList<Pump> pumps = new ArrayList<>();
    private ArrayList<Cistern> cisterns = new ArrayList<>();
    private ArrayList<Source> sources = new ArrayList<>();
    private int mechanicPoints = 0;
    private int saboteurPoints = 0;
    private int time = 0;

    public void addMechanic(int id){
        mechanics.add(new Mechanic(id));
    }

    public void addSaboteur(int id){
        saboteurs.add(new Saboteur(id));
    }

    public void addPipe(int id){
        pipes.add(new PassiveElement(null, null, id));
    }

    public void addPump(int id){
        pumps.add(new Pump(id));
    }

    public void addCistern(int id){
        cisterns.add(new Cistern(id));
    }
    public void addSource(int id){
        sources.add(new Source(id));
    }

    public void listMap(){
        for (Mechanic mechanic : mechanics) System.out.println(mechanic);
        for (Saboteur saboteur : saboteurs) System.out.println(saboteur);
        for (Pump pump : pumps) System.out.println(pump);
        for (PassiveElement pipe : pipes) System.out.println(pipe);
        for (Cistern cistern : cisterns) System.out.println(cistern);
        for (Source source : sources) System.out.println(source);
    }

    public ArrayList<Pump> getPumpList(){
        return pumps;
    }

    public void incrementMechanicPoints(int n){
        mechanicPoints += n;
    }

    public void incrementSaboteurPoints(int n){
        saboteurPoints += n;
    }
}
