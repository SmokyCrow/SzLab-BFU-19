/**
 * A szabotőr szerepkört megvalósító osztály, ami a játékos által irányított karakterek egyike.
 */
public class Saboteur extends Player{

    private final int id;

    public Saboteur(int _id){
        id = _id;
    }

    public void makeSlippery(){
        ((PassiveElement)element).setSlipTime(10);
    }

    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() { return "s_" + id;}

}
