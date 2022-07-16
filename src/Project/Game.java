package Project;

import Project.area.Area;
import Project.area.Forest;
import Project.area.Shop;
import Project.persons.Human;
import Project.persons.Person;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static final Random random = new Random();
    public static final Person hero = new Human("S", 10, 10, 0, 1000, 3);
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Optional<Area> action = Optional.empty();
        boolean exit = false;
        while (!exit) {
            switch (getAction(action)) {
                case 1 -> action = Optional.of(new Shop(hero));
                case 2 -> {
                    try {
                        action = Optional.of(new Forest(hero));
                    } catch (Forest.CantFightException e) {
                        System.out.println(e.getMessage());
                        action = Optional.empty();
                    }
                }
                case 3 -> {
                    action = Optional.empty();
                    exit = true;
                }
            }
            if (action.isPresent()) {
                Thread thr = new Thread(action.get());
                thr.start();
                thr.join();
            }
            ;
        }
    }

    // выбор действия или продолжение
    public static int getAction(Optional<Area> area) {
        int continueAction = continueAction(area);
        if (continueAction == 0) {
            int result;
            do {
                System.out.println("1. К торговцу");
                System.out.println("2. В темный лес");
                System.out.println("3. На выход");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                }
                result = scanner.nextInt();
            } while (!(result >= 1 && result <= 3));
            return result;
        } else return continueAction; // продолжить на текущем расположении
    }

    //продолжить / вернуться в главное меню
    static int continueAction(Optional<Area> area) {
        if (area.isPresent()) {
            String command;
            do {
                System.out.printf("вернуться в город - [R], продолжить %s - [C] : ", area.get().getActionName());
                command = scanner.next();
            } while (!"RrCc".contains(command));
            return "Cc".contains(command) ? area.get().getActionInt() : 0;
        } else return 0;
    }

}
