import java.util.ArrayList;


public class Room {

    private Room door[];
    private ArrayList<Monster> monsterList;
    private ArrayList<TownsPeople> townsPeopleList;
    private ArrayList<Item> idleItems;
    private static int roomNum = 0;
    private int specificRoomNum;

    public Room() {
        generateSimpleRoom();
        monsterList = new ArrayList<>();
        townsPeopleList = new ArrayList<>();
        idleItems = new ArrayList<>();
        roomNum++;

    }

    public Room[] getDoor() {
        return door;
    }

    public void generateSimpleRoom() {
        door = new Room[4];
    }

    public ArrayList<Monster> getMonsterList() {
        return monsterList;
    }

    public ArrayList<TownsPeople> getTownsPeopleList() {
        return townsPeopleList;
    }

    public void addTownsPeople(int num) {
        for (int i = 0; i < num; i++) {
            townsPeopleList.add(new TownsPeople());
        }
    }

    public void addMonster(int num, int HP) {
        for (int i = 0; i < num; i++) {
            Monster monster = new Monster(HP, Weapon.WeaponsList().get((int) (Math.random() * Weapon.WeaponsList().size())),
                    Armor.armorsList().get((int) (Math.random() * Armor.armorsList().size())));
            monster.setSpecificMonsterNum(Monster.getMonsterNum());
            monsterList.add(monster);
        }
    }

    public void addItem(Item item) {
        idleItems.add(item);

    }

    public ArrayList<Item> getIdleItems() {
        return idleItems;
    }

    public static int getRoomNum() {
        return roomNum;
    }

    public void setSpecificRoomNum(int num) {
        specificRoomNum = num;
    }

    public int getSpecificRoomNum() {
        return specificRoomNum;
    }


    @Override
    public String toString() {
        return "Room " + this.specificRoomNum;
    }
}
