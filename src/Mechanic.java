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

    public void pickUpPipe(PassiveElement p){ //0
        p.occupied(); //1
        //felh. kérdés, ha nincsenek a csövön:
        //felh. kérd, ha nincs a szerelőnél cső:
        element.giveElementEnd(p); //1
        //felh. kérd, ha ez sikeresen megtörtént:
        this.setNewPipe(p); //1
    }

    public void placePipe(){ //0
        newPipe.setConnection((Pump) element); //1
        element.isNeighbour(newPipe); //1
        //felh. megkérdezni, hogy szomszédos-e, ha igen:
        this.removeNewPipe(); //1
    }

    public void placePump(){
        element.placeElement(newPump);
        //felh. megkérdezni, hogy sikeres-e a lerakás, ha igen:
        this.removeNewPump();
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
