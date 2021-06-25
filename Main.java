import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        FileWriter writer1 = new FileWriter(args[2]);
        PrintWriter printer1 = new PrintWriter(writer1);
        Scanner scan = new Scanner(new File(args[0])); // reading initials.txt
        scan.nextLine();
        String boardSize = scan.nextLine();
        int boardSizeInt = Integer.parseInt(String.valueOf(boardSize.charAt(0)));
        Game b1 = new Game(boardSizeInt);
        scan.nextLine();
        scan.nextLine();
        while (scan.hasNextLine()) {
            String[] words = scan.nextLine().split(" "); //split line by space
            switch (words[0]) { // creating character objects and locating character strings to the board
                case "DWARF":
                    b1.chars.add(new Dwarf(words[1]));
                    b1.locate(words[1], words[2], words[3], b1);
                    break;
                case "ELF":
                    b1.chars.add(new Elf(words[1]));
                    b1.locate(words[1], words[2], words[3], b1);
                    break;
                case "GOBLIN":
                    b1.chars.add(new Goblin(words[1]));
                    b1.locate(words[1], words[2], words[3], b1);
                    break;
                case "HUMAN":
                    b1.chars.add(new Human(words[1]));
                    b1.locate(words[1], words[2], words[3], b1);
                    break;
                case "ORK":
                    b1.chars.add(new Ork(words[1]));
                    b1.locate(words[1], words[2], words[3], b1);
                    break;
                case "TROLL":
                    b1.chars.add(new Troll(words[1]));
                    b1.locate(words[1], words[2], words[3], b1);
                    break;
            }
        }
        printer1.println(b1.draw());

        scan = new Scanner(new File(args[1])); // reading commands.txt line by line
        while (scan.hasNextLine()) {
            String[] command = scan.nextLine().split(" "); //split line by space
            char charType = command[0].charAt(0);
            String[] moves = command[1].split(";");
            int moveLen = moves.length / 2;
            switch (charType) { // doing commands
                case 'D':
                    boolean allIsGood = true;
                    if (moveLen < 3) {
                        for (int i = 0; i < 3; i += 2) {
                            if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Out")) {
                                printer1.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                                allIsGood = false;
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("In")) {
                                b1.move(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                b1.attack(command[0]);
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Same")) {
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Enemy")) {
                                b1.fightToDeath(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                break;
                            }
                        }
                    } else {
                        printer1.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                        allIsGood = false;
                    }

                    if (allIsGood) {
                        printer1.println(b1.draw());
                    }
                    break;

                case 'O':
                    boolean allIsGood1 = true;
                    if (moveLen < 2) {
                        for (int i = 0; i < 1; i += 2) {
                            if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Out")) {
                                printer1.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                                allIsGood1 = false;
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("In")) {
                                b1.heal(command[0]);
                                b1.move(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                b1.attack(command[0]);
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Same")) {
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Enemy")) {
                                b1.heal(command[0]);
                                b1.fightToDeath(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                break;
                            }
                        }
                    } else {
                        printer1.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                        allIsGood1 = false;
                    }
                    if (allIsGood1) {
                        printer1.println(b1.draw());
                    }
                    break;

                case 'H':
                    boolean allIsGood2 = true;
                    if (moveLen < 4) {
                        for (int i = 0; i < 5; i += 2) {
                            if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Out")) {
                                printer1.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                                allIsGood2 = false;
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("In")) {
                                b1.move(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                if (i == 4) {
                                    b1.attack(command[0]);
                                }
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Same")) {
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Enemy")) {
                                b1.fightToDeath(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                break;
                            }
                        }
                    } else {
                        printer1.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                        allIsGood2 = false;
                    }
                    if (allIsGood2) {
                        printer1.println(b1.draw());
                    }
                    break;

                case 'G':
                    boolean allIsGood3 = true;
                    if (moveLen < 5) {
                        for (int i = 0; i < 7; i += 2) {
                            if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Out")) {
                                printer1.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                                allIsGood3 = false;
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("In")) {
                                b1.move(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                b1.attack(command[0]);
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Same")) {
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Enemy")) {
                                b1.fightToDeath(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                break;
                            }

                        }
                    } else {
                        printer1.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                        allIsGood3 = false;
                    }
                    if (allIsGood3) {
                        printer1.println(b1.draw());
                    }
                    break;
                case 'E':
                    boolean allIsGood4 = true;
                    if (moveLen < 5) {
                        for (int i = 0; i < 7; i += 2) {
                            if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Out")) {
                                printer1.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                                allIsGood4 = false;
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("In")) {
                                b1.move(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                if (i < 6) {
                                    b1.attack(command[0]);
                                } else {
                                    b1.rangedAttack(command[0]);
                                }

                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Same")) {
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Enemy")) {
                                b1.fightToDeath(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                break;
                            }

                        }
                    } else {
                        printer1.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                        allIsGood4 = false;
                    }
                    if (allIsGood4) {
                        printer1.println(b1.draw());
                    }
                    break;
                case 'T':
                    boolean allIsGood5 = true;
                    if (moveLen < 2) {
                        for (int i = 0; i < 1; i += 2) {
                            if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Out")) {
                                printer1.println("Error : Game board boundaries are exceeded. Input line ignored.\n");
                                allIsGood5 = false;
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("In")) {
                                b1.move(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                b1.attack(command[0]);
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Same")) {
                                break;
                            } else if (b1.outCheck(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1])).equals("Enemy")) {
                                b1.fightToDeath(command[0], Integer.parseInt(moves[i]), Integer.parseInt(moves[i + 1]));
                                break;
                            }
                        }
                    } else {
                        printer1.println("Error : Move sequence contains wrong number of move steps. Input line ignored.\n");
                        allIsGood5 = false;
                    }
                    if (allIsGood5) {
                        printer1.println(b1.draw());
                    }
                    break;


            }

            if (b1.isGameFinished()) { // check whether the game is finished
                writer1.write("\nGame Finished" + "\n" + b1.whoWins().trim());
                break;
            }
        }


        printer1.close();
        writer1.close();
    }


}
