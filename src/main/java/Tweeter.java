import util.ApplicationContext;

import java.util.*;

public class Tweeter {
    public static void main(String[] args) {
        while (true) {
            try {
                ApplicationContext.getDemonstrateMenus().homeMenu();
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    //TODO create sign up method
                } else if (choice == 2) {
                    //TODO create log in method
                } else if (choice == 3) {
                    System.out.println("Hope to see you soon");
                    break;
                } else {
                    System.out.println("Choose between menu options");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }
}
