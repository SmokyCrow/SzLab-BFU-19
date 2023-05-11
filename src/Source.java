/**
 * A Forras tipusu jatekmezot modellezo osztaly
 * Innen ered a viz a csorendszerbe, kulonosebb felelossege nincs
 */
public class Source extends ActiveElement{
    private int id;

    public Source(int _id){
        id = _id;
    }

    public void moveWater(){
        PassiveElement outP = pipes.get(0);
        if(outP.getLoad() == 0){
            outP.addWater(1);
        }
    }

    @Override
    public String toString() { return "so_" + id;}

    public int getId(){
        return id;
    }
}
