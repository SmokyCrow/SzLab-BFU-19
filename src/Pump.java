public class Pump extends ActiveElement{

    @Override
    public String toString() { return "pump";}
    public void setInPipe(PassiveElement p, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->setInPipe(" + p.toString() + ")");
    }

    public void setOutPipe(PassiveElement p, int depth){
        System.out.print("\n");
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->setOutPipe(" + p.toString() + "2)");
    }

    public boolean giveElementEnd(Element e){
        return true;
    }

    public boolean connectElement(Element e) {
        return true;
    }

    public void breakRandom(){

    }

    public void addPipe(PassiveElement p){

    }

    public void disconnectPipe(PassiveElement p){

    }

}
