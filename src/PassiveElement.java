public class PassiveElement extends Element{

    private Pump pump1;
    private Pump pump2;

    public void setConnection(Pump p){

    }

    public void removeConnection(Pump p){

    }

    public boolean placeElement(Element e){
        return true;
    }

    public boolean isNeighbour(Element e) {
        return false;
    }

    public void breakElement(){

    }

    public boolean occupied(){
        return true;
    }

    public boolean connected(){
        return true;
    }
}