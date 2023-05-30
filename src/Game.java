import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * A Game osztály reprezentálja a játékot
 */
public class Game {
    private ArrayList<Element> elements = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();

    private ArrayList<IViewable> graphicList = new ArrayList<>();
    private int mechanicPoints = 0;
    private int saboteurPoints = 0;
    private int time = 300;

    /**
     * Az addElement metódus létrehoz egy új elemet az id paraméter alapján és hozzáadja az elements
     * és a graphicsList listához.
     * @param id az új elem azonosítója
     */
    public void addElement(String id, int x, int y){
        int elementId = Integer.parseInt(id.replaceAll("[^0-9]", ""));
        if(id.startsWith("pi")) {
            GPipe gp = new GPipe(elementId, this);
            elements.add(gp);
            graphicList.add(gp);
        }

        if(id.startsWith("pu")) {
            GPump gp = new GPump(elementId, this, x, y);
            elements.add(gp);
            graphicList.add(gp);
        }

        if(id.startsWith("ci")) {
            GCistern gc = new GCistern(elementId, this, x, y);
            elements.add(gc);
            graphicList.add(gc);
        }

        if(id.startsWith("so")) {
            GSource gs = new GSource(elementId, this, x, y);
            elements.add(gs);
            graphicList.add(gs);
        }
    }

    /**
     * Az addPlayer metódus hozzáad egy új játékost a players listához az id paraméter alapján és egy elemhez rendeli.
     * @param playerId a játékos azonosítója
     * @param elementId az elem azonosítója, amelyhez a játékost rendelni kell
     */
    public void addPlayer(String playerId, String elementId){
        int id = Integer.parseInt(playerId.replaceAll("[^0-9]", ""));
        if(playerId.startsWith("s")) {
            GSaboteur gs = new GSaboteur(id);
            players.add(gs);
            graphicList.add(gs);
        }

        if(playerId.startsWith("m")) {
            GMechanic gm = new GMechanic(id);
            players.add(gm);
            graphicList.add(gm);
        }

        Player p = players.get(players.size() - 1);
        Element e = null;
        for (Element element : elements) {
            if (element.toString().equals(elementId))
                e = element;
        }
        if(e != null)
            e.acceptPlayer(p);
    }

    /**
     * A connect metódus összeköti az element1Id és element2Id paraméterek alapján az elemeket.
     * @param element1Id az első elem azonosítója
     * @param element2Id a második elem azonosítója
     */
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
    /**
     * Játékos mozgatása adott elemre.
     * @param playerId A játékos azonosítója.
     * @param elementId Az elem azonosítója.
     */
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

        if(p != null) {
            p.move(e);
        }
    }

    /**
     * Az adott játékos (mechanikus) megjavítja az elemenet.
     * @param playerId a játékos azonosítója
     */
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

    /**
     * Az adott játékos kárt okoz a csőben.
     * @param playerId a játékos azonosítója
     */
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

    /**
     Az adott játékos  vezérli a pumpát az adott két passzív elem (cső) között.
     * @param playerId a játékos azonosítója
     * @param pipe1 az első passzív elem azonosítója
     * @param pipe2 a második passzív elem azonosítója
     */
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

    /**
     * Az adott játékos (mechanikus) fel- vagy leviszi az adott passzív elemet.
     * @param playerId a játékos azonosítója
     * @param elementId a passzív elem azonosítója
     */
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

    /**
     * Ez a metódus írja le a pumpa elhelyezést (csőnél), felvételt (ciszternánál).
     * @param playerId a játékos azonosítója
     */
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

    /**
     * Ez a metódus egy játékos akcióját hajtja végre (slip, stick).
     * @param playerId a játékos azonosítója
     * @param action az akció, amit a játékos végrehajt
     */
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

    /**
     * Ez a metódus egy elem pumpájának véletlenszerű meghibásodását okozza.
     * @param elementId az elem azonosítója
     */
    public void randomBreak(String elementId){
        Element pu = null;
        for (Element element : elements) {
            if (element.toString().equals(elementId))
                pu = element;
        }
        ((Pump) pu).breakRandom();
    }

    /**
     * Ez a metódus lecsökkenti az időt minden játékos akciókor, amíg el nem éri a 0-t.
     * itt hívódik meg a víz mozgatásáért felelős függvény, a ciszternánál keletkező csövek létrehozása és
     * egy pumpa random elrontása is
     * */
    public void tick() {
        if(time != 0){
            time--;
            for (Element element : elements){
                //element.moveWater();
                if(element.toString().startsWith("pi")){
                    if(((PassiveElement) element).getSlipTime() != 0)
                        ((PassiveElement) element).setSlipTime(((PassiveElement) element).getSlipTime() - 1);
                    if(((PassiveElement) element).getStickTime() != 0)
                        ((PassiveElement) element).setStickTime(((PassiveElement) element).getStickTime() - 1);
                }
                if(element.toString().contains("ci")){
                    element.moveWater();
                }

            }
            for (Element e : elements) {
                if(e.toString().contains("pu")){
                    e.moveWater();
                }
            }
            for (Element e : elements) {
                if(e.toString().contains("so")){
                    e.moveWater();
                }
            }
        }
        if(time % 10 == 0) {
            randomBreak(randomPump().toString());
            makePipe();
        }
    }

    /**
     * Létrehoz egy új csövet a ciszternában, amit majd fel tud venni a szerelő
     */
    public void makePipe(){
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i).toString().startsWith("ci")){
                GPipe pi = new GPipe(Integer.parseInt(getNewPipeId().replaceAll("[^0-9]", "")), this);
                pi.setConnection((Cistern) elements.get(i));
                String id = getNewPipeId();
                addElement(id, ((IViewable)elements.get(i)).getX(), ((IViewable)elements.get(i)).getY());
                connect(id, elements.get(i).toString());
            }
        }
    }


    /**
     * Visszaad egy véletlenszerűen kiválasztott pumpát az elrontáshoz
     * @return egy pumpa
     */
    Pump randomPump(){
        ArrayList<Pump> pumps = new ArrayList<>();
        for (Element element : elements) {
            if (element.toString().startsWith("pu"))
                pumps.add((Pump) element);
        }
        Random rand = new Random();
        int rand_int = rand.nextInt(0, pumps.size());
        return pumps.get(rand_int);

    }

    /**
     * Ez a metódus megnöveli a szerelő pontjait a megadott értékkel.
     * @param n a növekmény értéke
     */
    public void incrementMechanicPoints(int n){
        mechanicPoints += n;
    }

    /**
     *  Ez a metódus megnöveli a szabotőr pontjait a megadott értékkel.
     * @param n a növekmény értéke
     */
    public void incrementSaboteurPoints(int n){
        saboteurPoints += n;
    }

    /** Ez a metódus ellenőrzi, hogy a játék véget ért-e. */
    public boolean GameOverCheck(){
        return time <= 0;
    }

    /**
     * Ez a metódus visszaadja az összes elemet tartalmazó ArrayList-et.
     * @return az elemeket tartalmazó ArrayList
     */
    public ArrayList<Element> getElements(){
        return elements;
    }
    /**
     * Ez a metódus visszaadja az összes játékost tartalmazó ArrayList-et.
     * @return a játékosokat tartalmazó ArrayList
     * */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * Visszaadja a graphicList listát
     * @return graphicList
     */
    public ArrayList<IViewable> getGraphicList(){return graphicList;}

    /**
     * Ez a metódus visszaad egy új csőelem azonosítót.
     * Az azonosítóban "pi" előtag szerepel, majd egy sorszám következik.
     *  @return az új csőelem azonosítója
     */
    public String getNewPipeId(){
        int ctr = 1;
        for (Element e: elements) {
            if(e.toString().contains("pi"))
                ctr++;
        }
        return "pi_"+ctr;
    }

    /**
     *  Ez a metódus visszaad egy új pumpa azonosítót.
     * Az azonosítóban "pu" előtag szerepel, majd egy sorszám következik.
     @return az új szivattyú azonosítója
     */
    public String getNewPumpId(){
        int ctr = 1;
        for (Element e: elements) {
            if(e.toString().contains("pu"))
                ctr++;
        }
        return "pu_"+ctr;
    }

    /**
     * Visszaadja a megadott azonosítójú csövet.
     * @param id a cső azonosítója
     * @return a cső objektum
     */
    public PassiveElement getPipe(String id){
        Element e = null;
        for (Element element : elements){
            if (element.toString().equals(id))
                e = element;
        }
        return (PassiveElement) e;
    }

    /**
     * Visszaadja a megadott azonosítójú szivattyút.
     * @param id a szivattyú azonosítója
     * @return a szivattyú objektum
     */
    public Pump getPump(String id){
        Element e = null;
        for (Element element : elements){
            if (element.toString().equals(id))
                e = element;
        }
        return (Pump) e;
    }

    /**
     * Visszaadja az idő értékét
     * @return aktuális idő
     */
    public int getTime(){
        return time;
    }

    /**
     * visszaadja a szerelők pontjait
     * @return szerelők pontja
     */
    public int getMechanicPoints(){
        return mechanicPoints;
    }

    /**
     * visszaadja a szabotőrök pontjait
     * @return szabotőrök pontja
     */
    public int getSaboteurPoints(){
        return saboteurPoints;
    }

    /**
     * beállítja a szerelők pontját
     * @param a a beállítandó érték
     */
    public void setMechanicPoints(int a){
        mechanicPoints = a;
    }

    /**
     * beállítja a szabotőrök pontját
     * @param a a beállítandó érték
     */
    public void setSaboteurPoints(int a){
        saboteurPoints = a;
    }
}