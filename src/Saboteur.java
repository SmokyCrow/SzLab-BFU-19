public class Saboteur extends Player{
    public void doElement(int depth){ //0
        System.out.println("->doElement()");
        depth +=1;
        element.breakElement(depth); //1
    }
    @Override
    public String toString() { return "saboteur";}

}
