package Project.persons;

import static Project.Game.random;

//только для ? extends Person
public interface Fighter {
    default void attack(Person enemy) {
        Person person = (Person) this;
        synchronized (random) {
            if (person.getDexterity() * 3 > random.nextInt(100)) {
                System.out.printf("%s атакует с силой %s%n", person.getName(), person.getForce());
                enemy.damage(person.getForce());
            } else {
                System.out.printf("%s промахнулся%n", person.getName());
            }
        }
    }

    default boolean canFight() {
        return true;
    }
}
