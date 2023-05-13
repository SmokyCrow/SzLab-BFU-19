import java.io.IOException;
import java.io.RandomAccessFile;
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
            elements.add(new PassiveElement(elementId, this));

        if(id.startsWith("pu"))
            elements.add(new Pump(elementId, this));

        if(id.startsWith("ci"))
            elements.add(new Cistern(elementId, this));

        if(id.startsWith("so"))
            elements.add(new Source(elementId, this));
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
        if(e1.toString().startsWith("pi"))
            ((PassiveElement) e1).setConnection((ActiveElement) e2);
        else if(e2.toString().startsWith("pi"))
            ((PassiveElement) e2).setConnection((ActiveElement) e1);
    }


    public void movePlayer(String playerId, String elementId) throws Exception {
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

    public void setPump(String playerId, String pipe1, String pipe2){
        Player p = null;
        Element pi1 = null;
        Element pi2 = null;
        for (Player player : players) {
            if (player.toString().equals(playerId))
                p = player;
        }
        for (Element element : elements) {
            if (element.toString().equals(pipe1))
                pi1 = element;
            if (element.toString().equals(pipe2))
                pi2 = element;
        }

        if(p != null && pi1 != null && pi2 != null){
            p.controlPump((PassiveElement) pi1, (PassiveElement) pi2);
        }
    }

    public void pipeUpOrDown(String playerId, String elementId){
        Player p = null;
        Element pi = null;

        for (Player player : players) {
            if (player.toString().equals(playerId))
                p = player;
        }
        for (Element element : elements) {
            if (element.toString().equals(elementId))
                pi = element;
        }

        if(p != null && pi != null){
            if(((Mechanic) p).getNewPipe() == null)
                ((Mechanic) p).pickUpPipe((PassiveElement) pi);
            else
                ((Mechanic) p).placePipe();
        }
    }

    public void pumpUpOrDown(String playerId){
        Player p = null;

        for (Player player : players) {
            if (player.toString().equals(playerId))
                p = player;
        }

        if(p != null){
            if (p.element.toString().contains("pi")){
                ((Mechanic) p).placePump();
            }
            else if(p.element.toString().contains("ci")){
                ((Mechanic) p).getPump();
            }
        }
    }

    public void makeAction(String playerId, String action){
        Player p = null;
        for (Player player : players) {
            if (player.toString().equals(playerId))
                p = player;
        }

        if(action.equals("stick")){
            p.makeSticky();
        }

        if(action.equals("slip") && p.toString().startsWith("s")){
            ((Saboteur)p).makeSlippery();
        }

    }

    public void randomBreak(String elementId){
        Element pu = null;
        for (Element element : elements) {
            if (element.toString().equals(elementId))
                pu = element;
        }
        ((Pump) pu).breakRandom();
    }

    public void listMap(String id, RandomAccessFile out) throws IOException {
        Element e = null;
        Player p = null;
        for (Element element : elements){
            if (element.toString().equals(id))
                e = element;
        }

        if(e != null){
            //System.out.println("stat " + id);
            out.writeBytes("stat " + id+"\r\n");
            if(e.toString().startsWith("pi")){
                if(((PassiveElement)e).getE1() != null && ((PassiveElement)e).getE2() != null){
                    //System.out.println("\tends: " + ((PassiveElement)e).getE1() + "," + ((PassiveElement)e).getE2());
                    out.writeBytes("ends: " + ((PassiveElement)e).getE1() + "," + ((PassiveElement)e).getE2()+"\r\n");
                }
                else if(((PassiveElement)e).getE1() == null && ((PassiveElement)e).getE2() != null)
                    //System.out.println("\tends: " + ((PassiveElement)e).getE2());
                    out.writeBytes("ends: " + ((PassiveElement)e).getE2()+"\r\n");
                else if(((PassiveElement)e).getE1() != null && ((PassiveElement)e).getE2() == null)
                    //System.out.println("\tends: " + ((PassiveElement)e).getE1());
                    out.writeBytes("ends: " + ((PassiveElement)e).getE1()+"\r\n");
                else
                    //System.out.println("\tends:");
                    out.writeBytes("ends:\r\n");
                //System.out.println("\tleaking: " + e.broken);
                out.writeBytes("leaking: " + e.broken+"\r\n");
                //System.out.println("\tstickTime: " + ((PassiveElement)e).getStickTime());
                out.writeBytes("stickTime: " + ((PassiveElement)e).getStickTime()+"\r\n");
                //System.out.println("\tslipTime: " + ((PassiveElement)e).getSlipTime());
                out.writeBytes("slipTime: " + ((PassiveElement)e).getSlipTime()+"\r\n");
                //System.out.println("\tprotectTime: " + ((PassiveElement)e).getProtectTime());
                out.writeBytes("protectTime: " + ((PassiveElement)e).getProtectTime()+"\r\n");
                //System.out.println("\twaterInside: " + ((PassiveElement)e).getLoad());
                out.writeBytes("waterInside: " + ((PassiveElement)e).getLoad()+"\r\n");
            }

            if(e.toString().startsWith("pu")){
                //System.out.print("\tpipes: ");
                out.writeBytes("pipes: ");
                for(int i = 0; i < ((Pump)e).getPipes().size() - 1; i++){
                    //System.out.print(((Pump)e).getPipes().get(i) + ",");
                    out.writeBytes(((Pump)e).getPipes().get(i) + ",");
                }
                if(((Pump)e).getPipes().size() > 0)
                    //System.out.print(((Pump)e).getPipes().get(((Pump)e).getPipes().size() - 1));
                    out.writeBytes(((Pump)e).getPipes().get(((Pump)e).getPipes().size() - 1)+"");
                out.writeBytes("\r\n");
                //System.out.println("\n\tinPipe: " + ((Pump)e).getInPipe());
                out.writeBytes("inPipe: " + ((Pump)e).getInPipe()+"\r\n");
                //System.out.println("\toutPipe: " + ((Pump)e).getOutPipe());
                out.writeBytes("outPipe: " + ((Pump)e).getOutPipe()+"\r\n");
                //System.out.println("\twaterInside: " + ((Pump)e).getWaterInside());
                out.writeBytes("waterInside: " + ((Pump)e).getWaterInside()+"\r\n");
                //System.out.println("\tbroken: " + e.broken);
                out.writeBytes("broken: " + e.broken+"\r\n");
            }

            if(e.toString().startsWith("ci")){
                //System.out.print("\tpipes: ");
                out.writeBytes("pipes: ");
                for(int i = 0; i < ((Cistern)e).getPipes().size() - 1; i++){
                    //System.out.print(((Cistern)e).getPipes().get(i) + ",");
                    out.writeBytes(((Cistern)e).getPipes().get(i) + ",");
                }
                //System.out.print(((Cistern)e).getPipes().get(((Cistern)e).getPipes().size() - 1));
                out.writeBytes(((Cistern)e).getPipes().get(((Cistern)e).getPipes().size() - 1)+"");
                out.writeBytes("\r\n");
                //System.out.println("\n\twaterInside: " + ((Cistern)e).getWaterInside());
                out.writeBytes("waterInside: " + ((Cistern)e).getWaterInside()+"\r\n");
            }

            if(e.toString().startsWith("so")){
                //System.out.print("\tpipes: ");
                out.writeBytes("pipes: ");
                for(int i = 0; i < ((Source)e).getPipes().size() - 1; i++){
                    //System.out.print(((Source)e).getPipes().get(i) + ",");
                    out.writeBytes(((Source)e).getPipes().get(i) + ",");
                }
                //System.out.print(((Source)e).getPipes().get(((Source)e).getPipes().size() - 1));
                out.writeBytes(((Source)e).getPipes().get(((Source)e).getPipes().size() - 1)+"");
                out.writeBytes("\r\n");
                //System.out.print("\n");
            }
        }


        for (Player player : players){
            if (player.toString().equals(id))
                p = player;
        }
        if(p != null){
            //System.out.println("stat " + id);
            out.writeBytes("stat " + id +"\r\n");
            if(p.toString().startsWith("s")){
                //System.out.println("\tpos: " + p.element);
                out.writeBytes("pos: " + p.element+"\r\n");
                //System.out.println("\tstuck: " + p.getStuck());
                out.writeBytes("stuck: " + p.getStuck()+"\r\n");
            }

            if(p.toString().startsWith("m")){
                //System.out.println("\tpos: " + p.element);
                out.writeBytes("pos: " + p.element+"\r\n");
                //System.out.println("\tstuck: " + p.getStuck());
                out.writeBytes("stuck: " + p.getStuck()+"\r\n");
                //System.out.println("\thasPipe: " + (((Mechanic)p).getNewPipe() != null));
                out.writeBytes("hasPipe: " + (((Mechanic)p).getNewPipe() != null)+"\r\n");
                //System.out.println("\thasPump: " + (((Mechanic)p).getNewPump() != null));
                out.writeBytes("hasPump: " + (((Mechanic)p).getNewPump() != null)+"\r\n");
            }
        }


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

    public String getNewPipeId(){
        int ctr = 1;
        for (Element e: elements) {
            if(e.toString().contains("pi"))
                ctr++;
        }
        return "pi_"+ctr;
    }

    public String getNewPumpId(){
        int ctr = 1;
        for (Element e: elements) {
            if(e.toString().contains("pu"))
                ctr++;
        }
        return "pu_"+ctr;
    }

    public PassiveElement getPipe(String id){
        Element e = null;
        for (Element element : elements){
            if (element.toString().equals(id))
                e = element;
        }
        return (PassiveElement) e;
    }

    public Pump getPump(String id){
        Element e = null;
        for (Element element : elements){
            if (element.toString().equals(id))
                e = element;
        }
        return (Pump) e;
    }

}