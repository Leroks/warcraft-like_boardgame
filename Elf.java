public class Elf extends Character {
    public int rangedAP;

    public Elf(String name) {
        super(name);
        this.AP = Constants.elfAP;
        this.HP = 70;
        this.moves = Constants.elfMaxMove;
        this.rangedAP = Constants.elfRangedAP;
        this.side = "Calliance";
        this.baseHP = 70;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + 70 + ")"; // change the current HP
    }
}
