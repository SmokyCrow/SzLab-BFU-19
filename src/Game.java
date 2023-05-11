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
    private int time = 300;


    public void addMechanic(int id){
        mechanics.add(new Mechanic(id));
    }

    public void addSaboteur(int id){
        saboteurs.add(new Saboteur(id));
    }

    public void addPipe(int id){
        pipes.add(new PassiveElement(id));
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

    public void tick() throws InterruptedException {
        while(time != 0){
            time--;
            wait(1000);
        }
    }

    public void incrementMechanicPoints(int n){
        mechanicPoints += n;
    }

    public void incrementSaboteurPoints(int n){
        saboteurPoints += n;
    }

    public void GameOverCheck(){

    }

    public void elementInit(int n){

    }

    public void playerInit(int s, int m){

    }

    public void placePlayerRandom(Player p){

    }

    public ArrayList<Pump> getPumpList(){
        return pumps;
    }

    public int getPipeNumber(){
        return pipes.size();
    }

    public PassiveElement getPipe(int id){
        for (PassiveElement p: pipes) {
            if(p.getId() == id)
                return p;
        }
        return null;
    }


}
