public class Cistern extends ActiveElement{

    @Override
    public String toString() { return "cistern";}
    public Element giveElement(){
        return null;
    }

    public boolean giveElementEnd(Element e){
        return true;
    }

    public boolean connectElement(Element e){
        return true;
    }

    public void addPipe(PassiveElement p){

    }

}
