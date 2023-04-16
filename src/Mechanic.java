import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mechanic extends Player{

    private Pump newPump;
    private PassiveElement newPipe;


    @Override
    public String toString() { return "mechanic";}
    public void doElement(int depth) { //0
        System.out.println("->doElement()");
        depth +=1;
        element.repairElement(depth); //1
    }

    public void getPump(){ //0
        element.giveElement(); //1
        Pump p = new Pump(); //2
        this.setNewPump(p); //1
    }

    public void pickUpPipe(PassiveElement p, int depth) throws IOException {
        System.out.println("->pickUpPipe(" + p.toString() + ")");
        depth += 1;
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->occupied()");
        System.out.println("\nIs the pipe occupied? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y"))
                return;
            else if (s.equals("N")) {
                System.out.println("Does the mechanic have a pipe already? (Y/N)");
                while(true) {
                    s = reader.readLine();
                    if(s.equals("Y"))
                        return;
                    else if (s.equals("N")) {
                        for (int i = 0; i < depth; i++) {
                            System.out.print("    ");
                        }
                        System.out.print("->giveElementEnd(" + p.toString() + ")\n");
                        boolean b = element.giveElementEnd(p); //1

                        if (b == true){
                            for (int i = 0; i < depth; i++) {
                                System.out.print("    ");
                                }
                                System.out.print("->setNewPipe(" + p.toString() + ")");
                                depth -= 1;
                                this.setNewPipe(p); //1
                            }
                        return;
                    }
                    else{
                        System.out.println("Wrong input, please try again!");
                    }
                }
            }
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    public void placePipe(int depth) throws IOException {
        System.out.println("->placePipe()");
        depth += 1;
        this.newPipe = new PassiveElement();
        newPipe.setConnection((Pump) element);
        for(int i = 0; i < depth; i++){
            System.out.print("  ");
        }
        System.out.print("->isNeighbour(newpipe)\n");
        System.out.println("Was the connecting successful? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
                for(int i = 0; i < depth; i++){
                    System.out.print("  ");
                }
                System.out.print("->removeNewPipe()");
                this.removeNewPipe(); //1
                return;
            }
            else if (s.equals("N"))
                return;
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    public void placePump() throws IOException {
        element.placeElement(newPump);
        //felh. megkérdezni, hogy sikeres-e a lerakás
        System.out.println("Was the placing successful? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
                this.removeNewPump(); //1
                return;
            }
            else if (s.equals("N"))
                return;
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    public void removeNewPump(){

    }

    public void setNewPump(Pump p){

    }

    public void removeNewPipe(){

    }

    public void setNewPipe(PassiveElement p){

    }

}
