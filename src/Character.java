import java.util.ArrayList;
public class Character implements CharacterInterface{
    protected ArrayList<Item> inventory;
    protected int HP;
    protected Weapon weapon;
    protected Armor armor;
    protected Room location;

    @Override
    public void move(Room door) {
        this.location = door;
    }

    @Override
    public void useWeapon(Character character) {
        character.HP -= this.weapon.damage;
        character.HP += character.armor.protectionPower;

    }

    @Override
    public void wear(Armor armor) {
        this.armor = armor;
    }

    @Override
    public void takeIntoInventory(Item item) {

    }
}
