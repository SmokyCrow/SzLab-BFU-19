import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Player {
    protected Element element;

    @Override
    public abstract String toString();
    public void move(Element e, int depth) throws IOException {
        //0
        System.out.print("->move(" + e.toString() + ")\n");
        depth += 1;

        //1
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->isNeighbour(" + e.toString() + ")");
        depth += 1;
        element.isNeighbour(e);

        //Asks the user if the pipe is occupied
        System.out.println("\nIs the " + e.toString() + " occupied? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
                System.out.println("The "+e.toString()+" is already occupied.");
                return;
            }
            else if (s.equals("N")) {
                //1
                e.acceptPlayer(new Mechanic());
                for(int i = 0; i < depth; i++){
                    System.out.print("    ");
                }
                System.out.print("->acceptPlayer(" + this.toString() + ")");
                depth -= 1;
                //2
                element.removePlayer(this);
                System.out.print("\n");
                for(int i = 0; i < depth; i++){
                    System.out.print("    ");
                }
                System.out.print("->removePlayer(" + this.toString() + ")");
                return;
            }
            else{
                System.out.println("Wrong input, please try again!");
            }
        }

    }

    public abstract void doElement(int depth);

    public void controlPump(PassiveElement p1, PassiveElement p2, int depth){
        //0
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->controlPump(" + p1.toString() + "," + p2.toString() + "2)\n");
        depth+= 1;
        //1
        ((Pump)this.element).setInPipe(p1,depth);
        //2
        ((Pump)this.element).setOutPipe(p2,depth);

    }

    public void setElement(Element e){

    }

}
