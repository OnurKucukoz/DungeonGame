public class Monster extends Character {
    private static int monsterNum = 0;
    private  int specificMonsterNum = 0;

    public Monster(int HP, Weapon weapon, Armor armor){
        this.HP = HP;
        this.weapon = weapon;
        this.armor = armor;
        monsterNum++;
    }

    @Override
    public void useWeapon(Character character) {
        super.useWeapon(character);
    }

    @Override
    public String toString() {
        return "Monster" + specificMonsterNum + "{" +
                "HP=" + HP +
                ", weapon=" + weapon +
                ", armor=" + armor +
                '}';
    }

    public  void setSpecificMonsterNum(int num) {
        specificMonsterNum = num;
    }

    public static int getMonsterNum() {
        return monsterNum;
    }
}
