package service.impl;

import base.service.BaseServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public void signUp() {
        System.out.println(" Welcome ");
        System.out.println(" Sign up ");
        User user = new User();
        user.setFirstName(firstName());
        user.setUserName(userName());
        user.setPassword(password());
        createOrUpdate(user);
        logIn();
    }

    @Override
    public void logIn() {
        System.out.println(" Login ");
        while(true){
            try{
                System.out.print("Username : ");
                String userName = new Scanner(System.in).nextLine();
                System.out.print("Password : ");
                String password = new Scanner(System.in).next();
                User user = repository.findUserByUserNameAndPassword(userName, password);
                if(user == null){
                    System.out.println("Username or Password is incorrect\nor maybe you haven't sign up yet");
                    System.out.println("1.Try again                   2.SignUp");
                    int choice = new Scanner(System.in).nextInt();
                    if(choice == 2){
                        signUp();
                        break;
                    }
                }else{
                    //TODO create main menu for user
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    private String firstName(){
        while(true){
            try{
                System.out.print("Firstname : ");
                String firstName = new Scanner(System.in).nextLine();
                System.out.println("1.Acceptable        2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    return firstName;
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    private String userName(){
        while(true){
            try{
                System.out.print("Username : ");
                String userName = new Scanner(System.in).nextLine();
                User user = repository.findUserByUserName(userName);
                if(user == null){
                    System.out.println("1.Acceptable         2.Unacceptable");
                    int choice = new Scanner(System.in).nextInt();
                    if(choice == 1){
                        return userName;
                    }
                }else {
                    System.out.println("This username already exist");
                    System.out.println("Choose another username");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    private String password(){
        while (true) {
            try {
                Pattern pattern = Pattern.compile("[0-9]{10}");
                System.out.print("Password : ");
                String password = new Scanner(System.in).next();
                Matcher matcher = pattern.matcher(password);
                while (!matcher.matches()) {
                    System.out.println("You should enter a 10-digit password");
                    System.out.println("Try again");
                    password = new Scanner(System.in).next();
                    matcher = pattern.matcher(password);
                }
                System.out.println("1.Acceptable         2.Unacceptable");
                int choice = new Scanner(System.in).nextInt();
                if (choice == 1) {
                    return password;
                } else {
                    System.out.println("Now try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Start over");
            }
        }
    }
}
