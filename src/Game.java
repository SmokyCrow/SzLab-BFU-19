import java.util.ArrayList;

public class Game {
    private ArrayList<Element> elements = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private int mechanicPoints = 0;
    private int saboteurPoints = 0;
    private int time = 300;


    public void addElement(String id){
        int elementId = Integer.parseInt(id.replaceAll("[^0-9]", ""));
        if(id.startsWith("pi"))
            elements.add(new PassiveElement(elementId));

        if(id.startsWith("pu"))
            elements.add(new Pump(elementId));

        if(id.startsWith("ci"))
            elements.add(new Cistern(elementId));

        if(id.startsWith("so"))
            elements.add(new Source(elementId));
    }

    public void addPlayer(String playerId, String elementId){
        int id = Integer.parseInt(playerId.replaceAll("[^0-9]", ""));
        if(playerId.startsWith("s"))
            players.add(new Saboteur(id));

        if(playerId.startsWith("m"))
            players.add(new Mechanic(id));

        Player p = players.get(players.size() - 1);
        Element e = null;
        for (Element element : elements) {
            if (element.toString().equals(elementId))
                e = element;
        }
        if(e != null)
            e.acceptPlayer(p);
    }


    public void connect(String element1Id, String element2Id){
        Element e1 = null;
        Element e2 = null;
        for (Element element : elements) {
            if (element.toString().equals(element1Id))
                e1 = element;
            if (element.toString().equals(element2Id))
                e2 = element;
        }
        if(e1 != null)
            ((PassiveElement) e1).setConnection((ActiveElement) e2);
    }


    public void movePlayer(String playerId, String elementId){
        Player p = null;
        Element e = null;
        for (Player player : players) {
            if (player.toString().equals(playerId))
                p = player;
        }

        for (Element element : elements) {
            if (element.toString().equals(elementId))
                e = element;
        }

        if(p != null)
            p.move(e);
    }


    public void repair(String playerId){
        Player m = null;
        for (Player player : players) {
            if (player.toString().equals(playerId))
                m = player;
        }

        if(m != null){
            ((Mechanic)m).repair();
        }
    }

    public void damage(String playerId){
        Player p = null;
        for (Player player : players) {
            if (player.toString().equals(playerId))
                p = player;
        }

        if(p != null){
            p.punchHole();
        }
    }




    public void listMap(){
        for (Element element : elements) System.out.println(element + " " + element.broken);
        for (Player player : players) System.out.println(player + " " + player.element);
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

    public ArrayList<Element> getElements(){
        return elements;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }
}
