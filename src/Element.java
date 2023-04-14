public abstract class Element {
    private Player[] players;

    public void repairElement(){

    }
    public void breakElement(){

    }

    public boolean placeElement(Element e){
        return false;
    }

    public boolean connectElement(Element e){
        return false;
    }

    public Element giveElement(){
        return null;
    }

    public boolean giveElementEnd(Element e){
        return false;
    }

    public void acceptPlayer(Player p){

    }

    public void removePlayer(Player p){

    }

    public abstract boolean isNeighbour(Element e);
}
