package Project.area;

import Project.persons.Person;

import static Project.Game.scanner;

public class Shop extends Area {
    private Person hero;
    private final int price = 10;

    public Shop(Person hero) {
        super("магазин", 1);
        this.hero = hero;
    }

    @Override

    public void run() {
        boolean exit = false;
        String answer;
        int value;
        while (!exit) {
            System.out.printf("Ваше здоровье: %s Золото: %s%n", hero.getHealth(), hero.getGold());
            System.out.println("Для выходя введите exit");
            System.out.printf("Зелье 1 единица здоровья %s gld. Купить: ", price);
            while (!exit && !scanner.hasNextInt()) {
                answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("exit")) exit = true;
            }
            if (!exit) {
                value = Math.min(100 - hero.getHealth(), scanner.nextInt());
                if (value > 0) {
                    if (value * price > hero.getGold()) {
                        System.out.println("у вас недостаточно денег.");
                    } else {
                        hero.setHealth(hero.getHealth() + value);
                        hero.setGold(hero.getGold() - value * price);
                        exit = true;
                    }
                } else if (value < 0 ) System.out.println("Введите положительное значение.");
                else exit = true;
            }
        }
    }
}
