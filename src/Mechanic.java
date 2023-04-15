import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mechanic extends Player{

    private Pump newPump;
    private PassiveElement newPipe;

    public void doElement() { //0
        element.repairElement(); //1
    }

    public void getPump(){ //0
        element.giveElement(); //1
        Pump p = new Pump(); //2
        this.setNewPump(p); //1
    }

    public void pickUpPipe(PassiveElement p) throws IOException {
        System.out.println("Is the pipe occupied? (Y/N)");
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
                        boolean b = element.giveElementEnd(p); //1
                        if(b == true)
                            this.setNewPipe(p); //1
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

    public void placePipe() throws IOException { //0
        newPipe.setConnection((Pump) element); //1
        //felh. megkérdezni, hogy sikeresen bekototte-e
        System.out.println("Was the connecting successful? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
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
