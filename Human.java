public class Human extends Character {
    public Human(String name) {
        super(name);
        this.AP = Constants.humanAP;
        this.HP = 100;
        this.moves = Constants.humanMaxMove;
        this.side = "Calliance";
        this.baseHP = 100;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + 100 + ")"; // change the current HP
    }


}
