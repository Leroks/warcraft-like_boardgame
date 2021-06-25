public class Character extends Game {
    public int HP;
    public int AP;
    public int moves;
    public String side;
    public String name;
    public int baseHP;

    public Character(String name) {
        super();
        this.name = name;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name + "\t" + this.HP + "\t" + "(" + this.HP + ")"; // change the current HP
    }
}
