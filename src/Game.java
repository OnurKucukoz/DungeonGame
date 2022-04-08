import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        boolean hasItem = false;
        String heroName;
        Level level = new Level();
        Hero hero = new Hero(level.getSimpleLevel()[0][0]);
        ArrayList<String> heroOptions = new ArrayList<>();
        ArrayList<Room> moveOptions = new ArrayList<>();
        ArrayList<Room> rescuedRoom = new ArrayList<>();
        File file = new File("highscores.txt");
            if(!file.exists())
                file.createNewFile();

        List<Integer> scores = new ArrayList<>();



        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            scores.add(Integer.parseInt(line));
        }


        reader.close();
        System.out.println("Enter your nick");
        heroName = scan.next();

        System.out.println("The GOAT, hero " + heroName + " welcome. " + "Our towns people need your help");


        heroOptions.add("move(change room)");
        heroOptions.add("use your weapon");
        heroOptions.add("change your armor");
        heroOptions.add("take something into your inventory");

        do {
            do {
                moveOptions.clear();
                System.out.println("---------------------------------------------------------------------------------------------------------------------");
                System.out.println(Level.getLevelNum() + " level " + hero);
                System.out.println("The idle items in the room");
                for (int k = 0; k < hero.location.getIdleItems().size(); k++) {
                    System.out.println(hero.location.getIdleItems().get(k).name);
                    hasItem = true;
                }
                if (!hasItem)
                    System.out.println("There is no idle item in this room");

                for (int j = 0; j < hero.location.getDoor().length; j++) {
                    if (hero.location.getDoor()[j] != null)
                        moveOptions.add(hero.location.getDoor()[j]);
                }

                showAvailableRooms(moveOptions);
                showAvailableMonsters(hero.location.getMonsterList());

                for (int i = 0; i < heroOptions.size(); i++)
                    System.out.println("option: " + (i + 1) + " " + heroOptions.get(i));
                int userOption = scan.nextInt();
                if (userOption == 1) {
                    System.out.println("Choose room to move");
                    for (int a = 0; a < moveOptions.size(); a++)
                        System.out.println("enter " + (a + 1) + " to move to " + moveOptions.get(a));
                    try {
                        int userOptionForMovement = scan.nextInt();
                        if (!(userOptionForMovement > moveOptions.size()))
                            hero.move(moveOptions.get(userOptionForMovement - 1));
                        else
                            System.out.println("Hey, you should have chosen available option!");
                    } catch (Exception e) {
                        System.out.println("Hey, you should have chosen available option!");
                    }



                } else if (userOption == 2) {
                    System.out.println("Choose monster to attack");
                    if (hero.location.getMonsterList().size() == 0)
                        System.out.println("There is no monster in here");
                    else {
                        for (int a = 0; a < hero.location.getMonsterList().size(); a++)
                            System.out.println("Enter " + (a + 1) + " to attack " + hero.location.getMonsterList().get(a));
                        try {
                            int userOptionForAttack = scan.nextInt();
                            if (!(userOptionForAttack > hero.location.getMonsterList().size())) {
                                hero.useWeapon(hero.location.getMonsterList().get(userOptionForAttack - 1));
                                if (hero.location.getMonsterList().get(userOptionForAttack - 1).HP <= 0) {
                                    hero.location.addItem(hero.location.getMonsterList().get(userOptionForAttack - 1).armor);
                                    hero.location.addItem(hero.location.getMonsterList().get(userOptionForAttack - 1).weapon);
                                    hero.location.getMonsterList().remove(userOptionForAttack - 1);
                                }
                            } else
                                System.out.println("Hey, you should have chosen available option!");
                        } catch (Exception e) {
                            System.out.println("Hey, you should have chosen available option!");
                        }
                    }

                } else if (userOption == 3) {
                    System.out.println("to change armor enter 1");
                    System.out.println("to change weapon enter 2");
                    try {
                        int userOptionForChangeItem = scan.nextInt();
                        if (userOptionForChangeItem == 1 || userOptionForChangeItem == 2) {
                            if (userOptionForChangeItem == 1) {
                                if (hero.getArmors().size() == 0)
                                    System.out.println("there is no armor to change");
                                else {
                                    for (int x = 0; x < hero.getArmors().size(); x++) {
                                        System.out.println("enter " + (x + 1) + hero.getArmors().get(x));
                                    }

                                }
                                try {
                                    userOptionForChangeItem = scan.nextInt();
                                    if (!(userOptionForChangeItem > hero.getArmors().size())) {

                                        Item item = hero.armor;
                                        hero.armor = (Armor) hero.getArmors().get(userOptionForChangeItem - 1);
                                        hero.takeIntoInventory(item);
                                    } else
                                        System.out.println("Hey, you should have chosen available option!");
                                } catch (Exception e) {
                                    System.out.println("Hey, you should have chosen available option!");
                                }
                            } else if (userOptionForChangeItem == 2) {
                                if (hero.getWeapons().size() == 0)
                                    System.out.println("there is no weapon to change");
                                else
                                    for (int y = 0; y < hero.getWeapons().size(); y++) {
                                        System.out.println("enter " + (y + 1) + hero.getWeapons().get(y));
                                    }
                                try {
                                    userOptionForChangeItem = scan.nextInt();
                                    if (!(userOptionForChangeItem > hero.getArmors().size())) {
                                        Item item = hero.weapon;
                                        hero.weapon = (Weapon) hero.getWeapons().get(userOptionForChangeItem - 1);
                                        hero.takeIntoInventory(item);
                                    } else
                                        System.out.println("Hey, you should have chosen available option!");
                                } catch (Exception e) {
                                    System.out.println("Hey, you should have chosen available option!");
                                }
                            }
                        } else
                            System.out.println("Hey, you should have chosen available option!");
                    } catch (Exception e) {
                        System.out.println("Hey, you should have chosen available option!");
                    }
                } else if (userOption == 4) {
                    System.out.println("enter 0" + " to take all items");
                    for (int b = 0; b < hero.location.getIdleItems().size(); b++) {
                        System.out.println("enter " + (b + 1) + " to take " + hero.location.getIdleItems().get(b));
                    }
                    try {
                        int userOptionForTakingItem = scan.nextInt();
                        if (!(userOptionForTakingItem > hero.location.getIdleItems().size())) {
                            if (userOptionForTakingItem == 0) {
                                for (int b = 0; b < hero.location.getIdleItems().size(); b++) {
                                    hero.takeIntoInventory(hero.location.getIdleItems().get(b));
                                }
                                hero.location.getIdleItems().clear();
                            } else {
                                hero.takeIntoInventory(hero.location.getIdleItems().get(userOptionForTakingItem - 1));
                                hero.location.getIdleItems().remove(userOptionForTakingItem - 1);
                            }
                        } else
                            System.out.println("Hey, you should have chosen available option!");
                    } catch (Exception e) {
                        System.out.println("Hey, you should have chosen available option!");
                    }
                }
                if (hero.location.getMonsterList().size() == 0 && !rescuedRoom.contains(hero.location)) {
                    rescuedRoom.add(hero.location);
                    hero.setRescuedPeople(hero.getRescuedPeople() + hero.location.getTownsPeopleList().size());
                }
                scan.nextLine();

            } while (hero.HP > 0 && level.aliveMonster());
            if (hero.HP > 0) {
                level = new Level();
                hero.location = level.getSimpleLevel()[0][0];

            } else System.out.println("game over");

        } while (hero.HP > 0 && Level.getLevelNum() <= 13);
        System.out.println("GAME OVER");
        hero.calculateTotalItemValue();
        System.out.println(" Your total item value is: " + hero.getTotalItemValue() );
        System.out.println(" Total rescued people number is: " + hero.getRescuedPeople());
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        int num = hero.getTotalItemValue() + hero.getRescuedPeople();
        if (scores.size() < 5) {
            scores.add(num);
            Collections.sort(scores);

        } else {
            scores.add(num);
            Collections.sort(scores);
            scores = scores.subList(1, 6);

        }
        for (int i = scores.size() - 1; i >= 0; i--) {
            writer.write( scores.get(i) + "\n");
        }

        writer.close();
        reader.close();

    }

    public static void showAvailableRooms(ArrayList<Room> rooms) {
        for (Room room : rooms) {
            System.out.println("You can go to " + room.getSpecificRoomNum());
        }

    }

    public static void showAvailableMonsters(ArrayList<Monster> monsters) {
        for (Monster monster : monsters) {
            System.out.println("You can attack to  " + monster);
        }
    }
}
