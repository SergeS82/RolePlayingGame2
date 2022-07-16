package Project.persons;

public abstract class Person {
    protected String name;  // имя
    protected int dexterity;    // ловкость
    protected int health;       // здоровье
    protected int experience;   // опыт
    protected int gold;         // золото
    protected int force;     // сила

    public Person(String name, int dexterity, int health, int experience, int gold, int strength) {
        this.name = name;
        this.dexterity = dexterity;
        this.health = health;
        this.experience = experience;
        this.gold = gold;
        this.force = strength;
    }
    void damage(int value) {
        health-=Math.min(value,health);
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getForce() {
        return force;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", health=" + health +
                '}';
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
    public void addGold(int add){
        gold += add;
    }

    public void addExperience() {
        experience += 1;
        if (experience%10 == 0) dexterity=Math.min(100,dexterity+10);
    }

    public int getExperience() {
        return experience;
    }

    public int getDexterity() {
        return dexterity;
    }

}
