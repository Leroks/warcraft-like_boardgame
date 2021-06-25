public class Goblin extends Character {

    public Goblin(String name) {
        super(name);
        this.AP = Constants.goblinAP;
        this.HP = 80;
        this.moves = Constants.goblinMaxMove;
        this.side = "Zorde";
        this.baseHP = 80;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + 80 + ")"; // change the current HP
    }


}
