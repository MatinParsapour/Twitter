package util;

public class DemonstrateMenus {
    public void homeMenu(){
        System.out.println("+--------------------+");
        System.out.println("|     1.Sign up      |");
        System.out.println("|     2.Log in       |");
        System.out.println("|     3.Exit         |");
        System.out.println("+--------------------+");
    }

    public void howToLogInMenu(){
        System.out.println("Log in using : ");
        System.out.println("+-------------+");
        System.out.println("| 1.username  |");
        System.out.println("|   2.Email   |");
        System.out.println("+-------------+");
    }

    public void mainMenu(){
        System.out.println("+-------------------------+");
        System.out.println("|      1.Your tweets      |");
        System.out.println("|      2.all tweets       |");
        System.out.println("|      3.profile          |");
        System.out.println("|      4.Exit             |");
        System.out.println("+-------------------------+");
    }

    public void profileMenu(){
        System.out.println("+-----------------------------+");
        System.out.println("|       1.change a field      |");
        System.out.println("|       2.delete a field      |");
        System.out.println("|      3.back to main menu    |");
        System.out.println("+-----------------------------+");
    }

    public void tweetsMenu(){
        System.out.println("+-----------------------------+");
        System.out.println("|       1.see your tweets     |");
        System.out.println("|       2.add a tweet         |");
        System.out.println("|      3.back to main menu    |");
        System.out.println("+-----------------------------+");
    }

    public void eachTweetMenu(){
        System.out.println("+-----------------------------+");
        System.out.println("|    1.Edit current tweet     |");
        System.out.println("|   2.delete current tweet    |");
        System.out.println("|        3.next tweet         |");
        System.out.println("|       4.back to menu        |");
        System.out.println("+-----------------------------+");
    }
}
