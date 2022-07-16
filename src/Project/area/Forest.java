package Project.area;

import Project.Game;
import Project.persons.Dragon;
import Project.persons.Fighter;
import Project.persons.Person;
import Project.persons.Skeleton;

public class Forest extends Area {
    private final Person hero;
    private final Person enemy;

    public static class CantFightException extends Exception {
        public CantFightException(String message) {
            super(message);
        }
    }

    public Forest(Person mainHero) throws CantFightException {
        super("лес", 2);
        this.hero = mainHero;
        if (hero.getHealth() <= 0) {
            throw new CantFightException("Вы не можете драться т.к. у вас нет здоровья. Зайдите к торговцу.");
        }
        synchronized (Game.random) {
            int i = Game.random.nextInt(2);
            enemy =
                    switch (i) {
                        case 0 -> new Skeleton(Skeleton.class.getSimpleName(), 30, 10, 1, 1, 1);
                        case 1 -> new Dragon(Dragon.class.getSimpleName(), 60, 10, 1, 1, 1);
                        default -> throw new IllegalStateException("Unexpected value: " + i);
                    };
        }
        try {
            ((Fighter)enemy).canFight();
        } catch (ClassCastException e) {
            throw new CantFightException(enemy+" не может драться. ");
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Битва: Вы VS " + enemy.getName());
            System.out.printf("Ваше здоровье: %s. Опыт: %s. Ловкость: %s. Золото: %s%n", hero.getHealth(),hero.getExperience(),hero.getDexterity(),hero.getGold());
            while (enemy.getHealth() > 0 && hero.getHealth() > 0) {
                ((Fighter) hero).attack(enemy);
                if (enemy.getHealth() > 0)
                    ((Fighter) enemy).attack(hero);
                Thread.sleep(1);
            }
            if (enemy.getHealth() > 0) {
                System.out.println("Враг победил.");
            } else {
                System.out.println("Вы победили.");
                hero.addExperience();
                hero.addGold(65);
            }
            System.out.printf("Ваше здоровье: %s. Опыт: %s. Ловкость: %s. Золото: %s%n", hero.getHealth(),hero.getExperience(),hero.getDexterity(),hero.getGold());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
