import java.util.ArrayList;

public class Armor extends Item{
    protected int protectionPower;
    public Armor(){

    }
    public static ArrayList<Armor> armorsList(){
        ArrayList<Armor> armors = new ArrayList<>();
        armors.add(new LightClothing());
        armors.add(new ChainMailArmor());
        armors.add(new LeatherArmor());
        return armors;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "protectionPower=" + protectionPower +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", value=" + value +
                '}';
    }

}
