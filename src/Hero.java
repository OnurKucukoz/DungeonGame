import java.util.ArrayList;

public class Hero extends Character{
    private int rescuedPeople;
    private int totalItemValue;
    private ArrayList<Item> armors;
    private ArrayList<Item> weapons;

    public Hero(int HP, Weapon weapon, Armor armor, Room location) {
        this.HP = HP;
        this.weapon = weapon;
        this.armor = armor;
        this.location = location;
        rescuedPeople = 0;
        this.inventory = new ArrayList<>();
        armors = new ArrayList<>();
        weapons = new ArrayList<>();
    }


    public Hero(Room location) {
        this.HP = 100;
        this.weapon = new ShortAxe();
        this.armor = new LeatherArmor();
        this.location = location;
        rescuedPeople = 0;
        armors = new ArrayList<>();
        weapons = new ArrayList<>();
        inventory = new ArrayList<>();

    }

    public void move(Room door) {
        super.move(door);
    }

    public void useWeapon(Character character) {
        super.useWeapon(character);
        character.useWeapon(this);
    }

    public void wear(Armor armor) {
        super.wear(armor);
    }

    public void setRescuedPeople(int rescuedPeople) {
        this.rescuedPeople = rescuedPeople;
    }

    public void takeIntoInventory(Item item) {
        if (item instanceof Armor)
            armors.add(item);
        else if (item instanceof Weapon)
            weapons.add(item);
        inventory.add(item);
    }

    public ArrayList<Item> getArmors() {
        return armors;
    }

    public ArrayList<Item> getWeapons() {
        return weapons;
    }



    public String toString() {
        return "you{" +
                ", HP=" + HP +
                ", weapon=" + weapon +
                ", armor=" + armor +
                ", location=" + location +
                ", rescuedPeople=" + rescuedPeople +
                ", totalItemValue=" + totalItemValue +
                '}';
    }

    public void calculateTotalItemValue() {
        totalItemValue = 0;
        for (int i = 0; i < inventory.size(); i++) {
            totalItemValue += inventory.get(i).value;
        }
        totalItemValue += armor.value;
        totalItemValue += weapon.value;
    }


    public int getTotalItemValue() {
        return totalItemValue;
    }

    public int getRescuedPeople() {
        return rescuedPeople;
    }


}
