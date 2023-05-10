/**
 * A Forras tipusu jatekmezot modellezo osztaly
 * Innen ered a viz a csorendszerbe, kulonosebb felelossege nincs
 */
public class Source extends ActiveElement{
    private int id;

    public Source(int _id){
        id = _id;
    }

    @Override
    public String toString() { return "so_" + id;}
}
