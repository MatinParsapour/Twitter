import util.ApplicationContext;

import java.util.*;

public class Twitter {
    public static void main(String[] args) {
        while (true) {
            try {
                ApplicationContext.getDemonstrateMenus().homeMenu();
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    ApplicationContext.getUserService().signUp();
                } else if (choice == 2) {
                    ApplicationContext.getUserService().howToLogIn();
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
