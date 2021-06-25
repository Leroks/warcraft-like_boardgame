public class Troll extends Character {
    public Troll(String name) {
        super(name);
        this.AP = Constants.trollAP;
        this.HP = 150;
        this.moves = Constants.trollMaxMove;
        this.side = "Zorde";
        this.baseHP = 150;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + 150 + ")"; // change the current HP
    }
}
