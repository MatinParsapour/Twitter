package service.impl;

import base.service.BaseServiceImpl;
import domain.User;
import repository.UserRepository;
import service.UserService;
import util.ApplicationContext;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Random;
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
        String email = email(user.getEmail());
        if(email != null){
            user.setEmail(email);
            user.setPassword(password());
            user.setBirthday(birthday());
            createOrUpdate(user);
            System.out.println("You successfully signed up");
            howToLogIn();
        }
    }

    @Override
    public void howToLogIn() {
        while(true){
            try{
                ApplicationContext.getDemonstrateMenus().howToLogInMenu();
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    logInUsingUserName();
                    break;
                }else if(choice == 2){
                    logInUsingEmail();
                    break;
                }else{
                    System.out.println("Choose between menu options");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void logInUsingEmail() {
        System.out.println(" Login ");
        while(true){
            try{
                System.out.print("Email : ");
                String email = new Scanner(System.in).nextLine();
                System.out.print("Password : ");
                String password = new Scanner(System.in).nextLine();
                User user = repository.findUserByEmailAndPassword(email,password);
                if (evaluateUser(user)) break;
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void logInUsingUserName() {
        System.out.println(" Login ");
        while(true){
            try{
                System.out.print("Username : ");
                String userName = new Scanner(System.in).nextLine();
                System.out.print("Password : ");
                String password = new Scanner(System.in).next();
                User user = repository.findUserByUserNameAndPassword(userName, password);
                if (evaluateUser(user)) break;
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void mainMenu(User user) {
        while(true){
            try{
                ApplicationContext.getDemonstrateMenus().mainMenu();
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    //TODO create a method for users to work with their tweets
                }else if(choice == 2){
                    //TODO create a method for users to see tweets of everybody
                }else if(choice == 3){
                    profile(user);
                }else if(choice == 4){
                    break;
                }else{
                    System.out.println("Choose between menu options");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void profile(User user) {
        while(true){
            try{
                ApplicationContext.getDemonstrateInformation().UserInformation(user);
                ApplicationContext.getDemonstrateMenus().profileMenu();
                int choice = new Scanner(System.in).nextInt();
                if(choice == 1){
                    changeProfile(user);
                    createOrUpdate(user);
                }else if(choice == 2){
                    deleteField(user);
                    createOrUpdate(user);
                }else if(choice == 3){
                    break;
                }else {
                    System.out.println("Choose between menu options");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    @Override
    public void changeProfile(User user) {
        System.out.print("Enter name of field : ");
        String field = new Scanner(System.in).next();
        switch (field.toLowerCase()){
            case "firstname":
                user.setFirstName(firstName());
                break;
            case "lastname":
                user.setLastName(lastName());
                break;
            case "username":
                user.setUserName(userName());
                break;
            case "password":
                user.setPassword(password());
                break;
            case "email":
                user.setEmail(email(user.getEmail()));
                break;
            case "phonenumber":
                user.setPhoneNumber(phoneNumber(user.getPhoneNumber()));
                break;
            case "bio":
                user.setBio(bio());
                break;
            case "birthday":
                user.setBirthday(birthday());
                break;
            default:
                System.out.println("Invalid entry");
        }
    }

    @Override
    public void deleteField(User user) {
        System.out.println("Enter name of field : ");
        String field = new Scanner(System.in).next();
        switch (field.toLowerCase()){
            case "firstname":
            case "username" :
            case "email" :
            case "password":
            case "birthday":
                System.out.println("You can't delete this field");
                break;
            case "lastname":
                deleteLastName(user);
                break;
            case "phonenumber":
                deletePhoneNumber(user);
                break;
            case "bio":
                deleteBio(user);
                break;
            default:
                System.out.println("Invalid entry");
        }
    }

    private void deleteBio(User user) {
        try{
            System.out.println("Are you sure");
            System.out.println("1.Yes   2.NO");
            int finalPermission = new Scanner(System.in).nextInt();
            if(finalPermission == 1){
                user.setBio(null);
                System.out.println("Phone number deleted");
            }
        }catch (InputMismatchException exception){
            System.out.println("Invalid entry");
        }
    }

    private void deletePhoneNumber(User user) {
        try{
            System.out.println("Are you sure");
            System.out.println("1.Yes   2.NO");
            int finalPermission = new Scanner(System.in).nextInt();
            if(finalPermission == 1){
                user.setPhoneNumber(null);
                System.out.println("Phone number deleted");
            }
        }catch (InputMismatchException exception){
            System.out.println("Invalid entry");
        }
    }

    private void deleteLastName(User user) {
        try{
            System.out.println("Are you sure");
            System.out.println("1.Yes   2.No");
            int finalPermission = new Scanner(System.in).nextInt();
            if(finalPermission == 1){
                user.setLastName(null);
                System.out.println("Lastname deleted");
            }
        }catch (InputMismatchException exception){
            System.out.println("Invalid entry");
        }
    }

    private boolean evaluateUser(User user) {
        if(user == null){
            System.out.println("Username or Password is incorrect\nor maybe you haven't sign up yet");
            System.out.println("1.Try again                   2.SignUp");
            int choice = new Scanner(System.in).nextInt();
            if(choice == 2){
                signUp();
                return true;
            }
        }else{
            mainMenu(user);
            return true;
        }
        return false;
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

    private String lastName(){
        while(true){
            try{
                System.out.print("Lastname : ");
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

    private String email(String currentEmail){
        Pattern validEmail = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}");
        System.out.print("Email : ");
        String email = new Scanner(System.in).next();
        Matcher machEmail = validEmail.matcher(email);
        while (!machEmail.matches()) {
            System.out.println("this is not a valid email");
            System.out.println("Try again");
            email = new Scanner(System.in).next();
            machEmail = validEmail.matcher(email);
        }
        return validationEmail(email,currentEmail);
    }

    private String validationEmail(String email, String currentEmail) {
        while (true) {
            try{
                Random random = new Random();
                int validationCode = random.nextInt(1000000);
                System.out.println("Please wait, we are sending a validation code to " + email);
                for (int waiting = 0; waiting <= 5; waiting++) {
                    Thread.sleep(1000);
                    System.out.print("" + "🟩");
                }
                System.out.println();
                System.out.println("This is your validation code : " + validationCode);
                System.out.print("Write your validation code : ");
                int validate = new Scanner(System.in).nextInt();
                if (validate == validationCode) {
                    System.out.print("Please wait, we are syncing data");
                    for (int delay = 0; delay <= 5; delay++) {
                        Thread.sleep(1000);
                        System.out.print(" .");
                    }
                    System.out.println("Now you are good to go");
                    System.out.println("\nYour email is valid");
                    return email;
                } else {
                    System.out.println("Invalid code");
                    System.out.println("1.Send another code       2.back to main menu");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 2) {
                        return currentEmail;
                    }
                }
            }catch (InputMismatchException | InterruptedException exception){
                System.out.println("Something went wrong");
                System.out.println("Try again");
            }
        }
    }

    private String phoneNumber(String currentPhoneNumber) {
        Pattern validPhoneNumber = Pattern.compile("[0][9][0-9]{9}");
        System.out.println("Enter your full phone number");
        String phoneNumber = new Scanner(System.in).next();
        Matcher matchPhoneNumber = validPhoneNumber.matcher(phoneNumber);
        while (!matchPhoneNumber.matches()) {
            System.out.println("This is not a valid phone number");
            System.out.println("Try again");
            phoneNumber = new Scanner(System.in).next();
            matchPhoneNumber = validPhoneNumber.matcher(phoneNumber);
        }
        return validationPhoneNumber(phoneNumber,currentPhoneNumber);
    }

    private String validationPhoneNumber(String phoneNumber, String number) {
        while (true) {
            try{
                Random random = new Random();
                int validationCode = random.nextInt(1000000);
                System.out.println("Please wait, we are sending a validation code to " + phoneNumber);
                for (int waiting = 0; waiting <= 5; waiting++) {
                    Thread.sleep(1000);
                    System.out.print("" + "🟩");
                }
                System.out.println("\nThis is your validation code : " + validationCode);
                System.out.print("Write your validation code : ");
                int validate = new Scanner(System.in).nextInt();
                if (validate == validationCode) {
                    System.out.println("Your phone is valid");
                    return phoneNumber;
                } else {
                    System.out.println("Invalid code");
                    System.out.println("1.Send another code       2.back to main menu");
                    int choice = new Scanner(System.in).nextInt();
                    if (choice == 2) {
                        return number;
                    }
                }
            }catch (InputMismatchException | InterruptedException exception){
                System.out.println("Something went wrong");
                System.out.println("Try again");
            }
        }
    }

    private String bio(){
        while(true){
            try{
                System.out.print("Bio : ");
                String bio = new Scanner(System.in).nextLine();
                if(bio.length() <= 160){
                    System.out.println("1.Acceptable        2.Unacceptable");
                    int choice = new Scanner(System.in).nextInt();
                    if(choice == 1){
                        return bio;
                    }
                }else{
                    System.out.println("You should enter bio fewer than 160 character");
                }
            }catch (InputMismatchException exception){
                System.out.println("Invalid entry");
                System.out.println("Try again");
            }
        }
    }

    private LocalDate birthday(){
        System.out.println("Birthday");
        LocalDate date;
        while (true) {
            try {
                System.out.print("Year : ");
                int year = new Scanner(System.in).nextInt();
                int month = month();
                int day = day();
                date = LocalDate.of(year, month, day);
                if (LocalDate.now().isAfter(date)) {
                    break;
                } else {
                    System.out.println("This is not a valid date for your birthday");
                    System.out.println("Try again");
                }
            } catch (InputMismatchException exception) {
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
        return date;
    }

    private int day() {
        while(true){
            try{
                System.out.print("Day : ");
                int day = new Scanner(System.in).nextInt();
                while (day < 1 || day > 31) {
                    System.out.println("This is not a valid number for day");
                    System.out.println("Try again");
                    day = new Scanner(System.in).nextInt();
                }
                return day;
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
    }

    private int month() {
        while(true){
            try{
                System.out.print("Month : ");
                int month = new Scanner(System.in).nextInt();
                while (month < 1 || month > 12) {
                    System.out.println("This is not a valid number for month");
                    System.out.println("Try again");
                    month = new Scanner(System.in).nextInt();
                }
                return month;
            }catch (InputMismatchException exception){
                System.out.println("You should enter number");
                System.out.println("Try again");
            }
        }
    }
}
