public class Ork extends Character {

    public int heal;

    public Ork(String name) {
        super(name);
        this.AP = Constants.orkAP;
        this.HP = 200;
        this.moves = Constants.orkMaxMove;
        this.heal = Constants.orkHealPoints;
        this.side = "Zorde";
        this.baseHP = 200;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + 200 + ")"; // change the current HP
    }


}
