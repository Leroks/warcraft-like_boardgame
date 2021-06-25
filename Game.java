import java.util.*;

public class Game {
    public int size;
    public String[][] board;
    public ArrayList<Character> chars = new ArrayList<>(); // ArrayList to hold character objects

    public Game(int size) { // board constructor
        this.size = size;
        this.board = new String[size + 2][size + 2];
        for (int i = 0; i < size + 2; i++)
            for (int j = 0; j < size + 2; j++)
                board[i][j] = "  ";
        for (int i = 0; i < size + 2; i++)
            board[0][i] = "**";
        for (int i = 0; i < size + 2; i++)
            board[size + 1][i] = "**";
        for (int i = 0; i < size + 2; i++)
            board[i][0] = "*";
        for (int i = 0; i < size + 2; i++)
            board[i][size + 1] = "*";

    }

    public Game() { // default constructor for inheriting to the characters

    }

    public void move(String charName, int vertical, int horizontal) { // method to move the characters on the board
        String location = findPart(charName);
        int number = Integer.parseInt(location);
        int row = number / 10;
        int column = number % 10;

        if (row + horizontal < 1 || column + vertical < 1 || column + vertical > size + 2 || row + horizontal > size + 2) {

        } else {
            if (!board[row + horizontal][column + vertical].equals("  ") && !board[row + horizontal][column + vertical].equals("*") && !board[row + horizontal][column + vertical].equals("**")) {
                if ((findChar(board[row + horizontal][column + vertical]).side).equals(findChar(board[row][column]).side)) {
                }
            } else {
                board[row][column] = "  ";
                board[row + horizontal][column + vertical] = charName;
            }
        }

    }


    public String draw() { // method to draw the board
        String out = "";
        for (int i = 0; i < this.size + 2; i++) {
            String line = "";
            for (int j = 0; j < this.size + 2; j++) {
                line += this.board[i][j];
            }
            out += line + "\n";
        }
        chars.sort(Comparator.comparing(Character::getName));
        out += "\n";
        for (Character c : chars) {
            out += c.toString() + "\n";
        }
        return out;
    }


    public void locate(String piece, String col, String row, Game bx) { // method to insert the new Character to the board
        int col1 = Integer.parseInt(col);
        int row1 = Integer.parseInt(row);
        bx.board[row1 + 1][col1 + 1] = piece;
    }

    public String findPart(String charName) {   // method to find the location indices of the board
        for (int i = 0; i < this.size + 2; i++) {
            for (int j = 0; j < this.size + 2; j++) {
                if (this.board[i][j].equals(charName)) {
                    String row = String.valueOf(i);
                    String col = String.valueOf(j);
                    return row + col;
                }
            }
        }
        return null;
    }

    public Character findChar(String name) {    // method to finding the character object from it's name
        for (Character c : chars) {
            if (c.name.equals(name)) {
                return c;
            }
        }
        return null;
    }

    public String outCheck(String charName, int vertical, int horizontal) { // method to find out what will be the next move's cell
        String location = findPart(charName);
        int number = Integer.parseInt(location);
        int row = number / 10;
        int column = number % 10;


        if (row + horizontal < 1 || column + vertical < 1 || column + vertical > size + 2 || row + horizontal > size + 2) {
            return "Out";
        } else {
            if (!board[row + horizontal][column + vertical].equals("  ") && !board[row + horizontal][column + vertical].equals("*") && !board[row + horizontal][column + vertical].equals("**")) {
                if ((findChar(board[row + horizontal][column + vertical]).side).equals(findChar(board[row][column]).side)) {
                    return "Same";
                } else {
                    return "Enemy";
                }
            } else {
                return "In";
            }
        }

    }

    public void attack(String charName) {   //  method for normal attack
        ArrayList<Character> attackList;
        if (findPart(charName) != null) {
            String location = findPart(charName);
            int number = Integer.parseInt(location);
            int row = number / 10;
            int column = number % 10;
            attackList = enemyAround(row, column, findChar(charName));
            int ap = findChar(charName).AP;
            for (Character c : attackList) {
                c.setHP(c.HP - ap);
                if (c.HP <= 0) {
                    removefromBoard(c.name);
                    removeChar(c.name);
                }
            }
        }

    }

    public ArrayList<Character> enemyAround(int row, int col, Character c) {    // method to find out which enemies are around
        ArrayList<Character> charAttack = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (j != 0 || i != 0) {
                    if (!board[row + i][col + j].equals("  ") && !board[row + i][col + j].equals("**") && !board[row + i][col + j].equals("*")) {
                        if (!findChar(board[row + i][col + j]).side.equals(c.side)) {
                            charAttack.add(findChar(board[row + i][col + j]));
                        }
                    }

                }
            }
        }
        return charAttack;
    }

    public void fightToDeath(String charName, int vertical, int horizontal) {   // method for fight to death
        String location = findPart(charName);
        int attackerHp = findChar(charName).HP;
        int aap = findChar(charName).AP;
        int number = Integer.parseInt(location);
        int row = number / 10;
        int column = number % 10;
        board[row][column] = "  ";
        findChar(board[row + horizontal][column + vertical]).setHP(findChar(board[row + horizontal][column + vertical]).HP - aap);
        if (findChar(board[row + horizontal][column + vertical]).HP > 0) {
            int defHp = findChar(board[row + horizontal][column + vertical]).HP;
            if (attackerHp > defHp) {
                removeChar(board[row + horizontal][column + vertical]);
                removefromBoard(findPart(board[row + horizontal][column + vertical]));
                board[row + horizontal][column + vertical] = charName;
                findChar(charName).setHP(attackerHp - defHp);
            } else if (attackerHp < defHp) {
                removeChar(charName);
                removefromBoard(charName);
                findChar(board[row + horizontal][column + vertical]).setHP(defHp - attackerHp);
            } else {
                removeChar(charName);
                removeChar(board[row + horizontal][column + vertical]);
                board[row + horizontal][column + vertical] = "  ";
            }
        } else {
            removeChar(board[row + horizontal][column + vertical]);
            board[row + horizontal][column + vertical] = charName;

        }


    }

    public void removeChar(String charName) {       // method for removing the character object
        chars.removeIf(c -> c.name.equals(charName));
    }

    public void removefromBoard(String charName) {  // method for removing the character from the board
        for (String[] s : board) {
            for (int i = 0; i < s.length; i++) {
                if (charName.equals(s[i])) {
                    s[i] = "  ";
                }
            }
        }
    }

    public ArrayList<Character> allyAround(int row, int col, Character c) { // method to find out which allies are around
        ArrayList<Character> charAlly = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (j != 0 && i != 0) {
                    if (!board[row + i][col + j].equals("  ") && !board[row + i][col + j].equals("**") && !board[row + i][col + j].equals("*")) {
                        if (findChar(board[row + i][col + j]).side.equals(c.side)) {
                            charAlly.add(findChar(board[row + i][col + j]));
                        }
                    }

                }
            }
        }
        charAlly.add(findChar(board[row][col]));
        return charAlly;
    }

    public void heal(String charName) { // method for using the ork's heal ability
        ArrayList<Character> attackList;
        if (findPart(charName) != null) {
            String location = findPart(charName);
            int number = Integer.parseInt(location);
            int row = number / 10;
            int column = number % 10;
            int maxHp = findChar(charName).baseHP;
            int heal = Constants.orkHealPoints;
            attackList = allyAround(row, column, findChar(charName));
            for (Character c : attackList) {
                c.setHP(c.HP + heal);
                if (c.HP > maxHp) {
                    c.setHP(maxHp);

                }
            }
        }

    }

    public ArrayList<Character> elfEnemyAround(int row, int col, Character c) { // method to find out which enemies are around for elf's ranged attack
        ArrayList<Character> elfRangedEnemies = new ArrayList<>();
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                if (j != 0 || i != 0) {
                    if ((row + i) <= size && (col + j) <= size) {
                        if (!board[row + i][col + j].equals("  ") && !board[row + i][col + j].equals("**") && !board[row + i][col + j].equals("*")) {
                            if (!findChar(board[row + i][col + j]).side.equals(c.side)) {
                                elfRangedEnemies.add(findChar(board[row + i][col + j]));
                            }
                        }
                    }

                }
            }
        }
        return elfRangedEnemies;
    }

    public void rangedAttack(String charName) { // method for elf's ranged attack
        ArrayList<Character> rangedAttackList;
        if (findPart(charName) != null) {
            String location = findPart(charName);
            int number = Integer.parseInt(location);
            int row = number / 10;
            int column = number % 10;
            rangedAttackList = elfEnemyAround(row, column, findChar(charName));
            int ap = findChar(charName).AP;
            for (Character c : rangedAttackList) {
                c.setHP(c.HP - ap);
                if (c.HP <= 0) {
                    removefromBoard(c.name);
                    removeChar(c.name);
                }
            }
        }

    }

    public boolean isGameFinished() {   // method to check whether the game is finished
        int zorde = 0;
        int calliance = 0;
        for (Character c : chars) {
            if (c.side.equals("Calliance")) {
                calliance += 1;
            }
            if (c.side.equals("Zorde")) {
                zorde += 1;
            }
        }
        return zorde == 0 || calliance == 0;
    }

    public String whoWins() {   // method to decide who won
        String out = "";
        for (Character c : chars) {
            out += c.side + " Wins";
            break;
        }
        return out.trim();
    }


}