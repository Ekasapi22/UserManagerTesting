import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner; //the scanner is not used

class User implements Comparable<User> {
    private String userName; // User's name
    private String userSurname; //the user's surname is not used at all
    private int userAge; // User's age

    public User(String name, int age) {
        this.userName = name;
        this.userAge = age;
    }

    //there are no setters
    public String getUserName() {
        return userName;
    }

    public int getUserAge() {
        return userAge;
    }

    @Override
    public int compareTo(User other) {
        return other.userAge - this.userAge;
    }
}

public class UserManager {

    private List<User> usersList; // List to store users

    public UserManager() {
        usersList = new ArrayList<>();
        initializeDefaultUsers(); // Populate with default users
    }

    private void initializeDefaultUsers() {
        addUser("DefaultUser", 18); // Adding a default user
    }

    public void addUser(String name, int age) {
        if(name.length()==0){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(age < 0){
            throw new IllegalArgumentException("Age cannot be negative");
        }
        usersList.add(new User(name, age)); // Add a new user to the list
    }

    public void printUserDetails() { //there is no check if there are no users
        for (User u : usersList) {
            System.out.println(u.getUserName() + " is " + u.getUserAge() + " years old."); // Print user details
        }
    }

    public void locateUser(String name) { //a check for empty user list is needed here
        User foundUser = null;
        for (User u : usersList) {
            if (u.getUserName().equals(name)) {
                foundUser = u;
                break; // User found, exit loop
            }
        }

        if (foundUser == null) {
            System.out.println("User not found: " + name);
        } else {
            System.out.println("Found user: " + foundUser.getUserName() + ", Age: " + foundUser.getUserAge());
        }
    }

    public void changeUserAge(String name, int newAge) {
        if (name == null || name.isEmpty()) {
            System.out.println("Invalid name for update");
            return;
        }

        for (User u : usersList) { //a check for negative age is needed here, because we can update the age of a user to a negative number
            if (u.getUserName().equals(name)) {
                u = new User(u.getUserName(), newAge); // Create a new user with updated age
                System.out.println("Updated age for " + name + " to " + newAge);
                return;
            }
        }
        System.out.println("User not found for update: " + name);
    }

    public void printUsersAverageAge() { //this one needs a check for empty list too
        double total = 0;
        for (User user : this.usersList) {
            total += user.getUserAge();
        }
        System.out.println("User mean age: " + total / usersList.size());
    }

    public void printoldestUser() { //the "o" in "oldest" should be Uppercase, like this "Oldest"
        User oldestUser = null;
        for (User u : usersList) {
            if (u.compareTo(oldestUser)>0){ //bad variable name
                oldestUser = u;
            }
        }

        //add a null check here too
        System.out.println("Oldest user: " + oldestUser.getUserName());
    }

    public void sortusers(){ //this method is not used
        usersList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void clear(){
        this.usersList.clear();
    }

    public void unrelated_method() {
        System.out.println("This method performs unrelated operations.");
    } //this method is useless and unnecessary

    public static void main(String[] args) {
        /// you can not make changes to main
        UserManager userManager = new UserManager();
        userManager.addUser("Alice", 25);
        userManager.addUser("Bob", -5); //the age here is negative
        userManager.addUser(null, 30);  //the name here is null
        userManager.addUser("Charlie", 30);
        userManager.printUserDetails();
        userManager.locateUser("Alice");
        userManager.changeUserAge("Alice", 26);
        userManager.changeUserAge("Dave", 40);
        userManager.printUserDetails();
        userManager.printoldestUser();
        userManager.printUsersAverageAge();
        userManager.clear();  //the list is cleared here, therefore, the methods called below will cause errors
        userManager.changeUserAge("Dave", 40); //cannot change bc list is empty
        userManager.printUserDetails();
        userManager.printoldestUser();
        userManager.printUsersAverageAge();

    }
}