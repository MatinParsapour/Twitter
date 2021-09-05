package util;

import domain.User;

public class DemonstrateInformation {
    public void UserInformation(User user){
        int firstNameSize = user.getFirstName().length();
        int lastNameSize;
        int phoneNumberSize;
        int bioSize;
        if(user.getLastName() != null){
            lastNameSize = user.getLastName().length();
        }else{
            lastNameSize = 10;
        }if(user.getPhoneNumber() != null){
            phoneNumberSize = user.getPhoneNumber().length();
        }else{
            phoneNumberSize = 12;
        }if(user.getBio() != null){
            bioSize = user.getBio().length();
        }else{
            bioSize = 5;
        }
        int userNameSize = user.getUserName().length();
        int passwordSize = user.getPassword().length();
        int emailSize = user.getEmail().length();
        int birthdaySize = 10;

        int cover = firstNameSize + lastNameSize + userNameSize + passwordSize + emailSize + phoneNumberSize +
                bioSize + birthdaySize + 48;

        printHeader(firstNameSize, lastNameSize, phoneNumberSize, bioSize, userNameSize, passwordSize, emailSize, birthdaySize, cover);

        printInformation(user, firstNameSize, lastNameSize, phoneNumberSize, bioSize, userNameSize, passwordSize, emailSize, birthdaySize, cover);
    }

    private void printInformation(User user, int firstNameSize,
                                  int lastNameSize, int phoneNumberSize,
                                  int bioSize, int userNameSize,
                                  int passwordSize, int emailSize,
                                  int birthdaySize, int cover) {
        System.out.format("| %" +(-(firstNameSize + 9)) + "s", user.getFirstName());
        if(user.getLastName() != null){
            System.out.format("|%" +(-(lastNameSize + 8)) + "s", user.getLastName());
        }else{
            System.out.format("|%" +(-(lastNameSize + 8)) + "s","");
        }
        System.out.format("|%" +(-(userNameSize + 8)) + "s", user.getUserName());
        System.out.format("|%" +(-(passwordSize + 3)) + "s", user.getPassword());
        System.out.format("|%" +(-(emailSize + 3)) + "s", user.getEmail());
        if(user.getPhoneNumber() != null){
            System.out.format("|%" +(-(phoneNumberSize + 3)) + "s", user.getPhoneNumber());
        }else{
            System.out.format("|%" +(-(phoneNumberSize + 3)) + "s","");
        }
        if(user.getBio() != null){
            System.out.format("|%" +(-(bioSize + 3)) + "s", user.getBio());
        }else{
            System.out.format("|%" +(-(bioSize + 3)) + "s","");
        }
        System.out.format("|%" +(-(birthdaySize + 3)) + "s |\n", user.getBirthday());


        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    private void printHeader(int firstNameSize, int lastNameSize,
                             int phoneNumberSize, int bioSize,
                             int userNameSize, int passwordSize,
                             int emailSize, int birthdaySize,
                             int cover) {
        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");

        System.out.format("| %" +(-(firstNameSize + 9)) + "s","firstname");
        System.out.format("|%" +(-(lastNameSize + 8)) + "s","lastname");
        System.out.format("|%" +(-(userNameSize + 8)) + "s","username");
        System.out.format("|%" +(-(passwordSize + 3)) + "s","password");
        System.out.format("|%" +(-(emailSize + 3)) + "s","email");
        System.out.format("|%" +(-(phoneNumberSize + 3)) + "s","phonenumber");
        System.out.format("|%" +(-(bioSize + 3)) + "s","bio");
        System.out.format("|%" +(-(birthdaySize + 3)) + "s |\n","birthday");

        System.out.print("+");
        for(int header = 0; header <= cover; header++){
            System.out.print("-");
        }
        System.out.println("+");
    }
}
