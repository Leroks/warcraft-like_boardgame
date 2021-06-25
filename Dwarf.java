public class Dwarf extends Character {

    public Dwarf(String name) {
        super(name);
        this.AP = Constants.dwarfAP;
        this.HP = 120;
        this.moves = Constants.dwarfMaxMove;
        this.side = "Calliance";
        this.baseHP = 120;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + 120 + ")"; // change the current HP
    }
}
