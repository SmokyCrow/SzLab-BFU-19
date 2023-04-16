public abstract class Element {
    private Player[] players;
    private boolean broken = false;

    public void setBroken(boolean b){ this.broken = b;}

    public void repairElement(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->repairElement()");
    }
    public void breakElement(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->breakElement()");
    }

    public boolean placeElement(Element e, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.println("->placeElement()");
        return false;
    }

    public boolean connectElement(Element e){
        return false;
    }

    public Element giveElement(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.println("->giveElement()");
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

    public abstract String toString();
}
