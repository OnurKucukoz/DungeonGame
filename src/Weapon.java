import java.util.ArrayList;

public class Weapon extends Item {
    protected int damage;

    public static ArrayList<Weapon> WeaponsList() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new BroadAxe());
        weapons.add(new CompositeBow());
        weapons.add(new Dagger());
        weapons.add(new LongBow());
        weapons.add(new LongSword());
        weapons.add(new ShortAxe());
        weapons.add(new ShortBow());
        weapons.add(new ShortSword());
        return weapons;
    }

    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                ", damage=" + damage +
                '}';
    }

}
