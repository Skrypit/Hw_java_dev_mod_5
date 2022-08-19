package cli;

import models.User;
import service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserState extends CliState {
    CliState state;
    Scanner scanner;

    public UserState(CliFSM fsm) {
        super(fsm);

        scanner = fsm.getScanner();
        state = fsm.getState();


        System.out.println("Please, choose a command number\n" +
                "1- Create new user\n" +
                "2- Logs out current logged in user session\n" +
                "3- Logs user into the system\n" +
                "4- Delete user\n" +
                "5- Update user\n" +
                "6- Get user by name\n" +
                "7- Create list of users (array)\n" +
                "8- Create list of users (list)\n" +
                "9- Back\n" +
                "0- Exit program"
        );
        inputLoop();
    }

    private void inputLoop() {
        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    createUser();
                    break;
                case "2":
                    logsOut();
                    break;
                case "3":
                    logsIn();
                    break;
                case "4":
                    deleteUser();
                    break;
                case "5":
                    updateUser();
                case "6":
                    getUser();
                case "7":
                    createUserArray();
                case "8":
                    createUserList();
                case "9":
                    toMainMenu();
                    break;
                default:
                    unknownCommand(command);
            }
        }
    }

    @Override
    public void init() {
        inputLoop();
    }

    @Override
    public void unknownCommand(String cmd) {
        System.out.println("Unknown command: " + cmd + "\nPlease, enter the right command");
    }

    @Override
    public void toMainMenu() {
        new CliFSM();
    }

    public void createUser() {

        User newUser = new User();
        setUserId(newUser);
        setUserName(newUser);
        setFirstName(newUser);
        setLastName(newUser);
        setUserEmail(newUser);
        setPassword(newUser);
        setPhoneNumber(newUser);
        newUser.setUserStatus(0);

        try {
            new UserService().createUser(newUser);
            System.out.println("User created!\nChoose next command");

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }


    private void setUserId(User user) {
        while (true) {
            System.out.println("Please, enter the user id:");
            String id = scanner.nextLine();
            try {
                user.setId(Integer.parseInt(id));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setUserName(User user) {
        while (true) {
            System.out.println("Please, enter user name:");
            String name = scanner.nextLine();
            try {
                user.setUserName(name);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setFirstName(User user) {
        while (true) {
            System.out.println("Please, enter first name:");
            String name = scanner.nextLine();
            try {
                user.setFirstName(name);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setLastName(User user) {
        while (true) {
            System.out.println("Please, enter last name:");
            String name = scanner.nextLine();
            try {
                user.setLastName(name);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setUserEmail(User user) {
        while (true) {
            System.out.println("Please, enter user email:");
            String email = scanner.nextLine();
            try {
                user.setEmail(email);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void setPhoneNumber(User user) {
        while (true) {
            System.out.println("Please, enter phone number:");
            String number = scanner.nextLine();
            try {
                user.setPhone(number);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void setPassword(User user) {
        while (true) {
            System.out.println("Please, enter the password:");
            String password = scanner.nextLine();
            try {
                user.setPassword(password);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void getUser(){
        while (true) {
            System.out.println("Please, enter user name:");
            String userName = scanner.nextLine();
            try {
                new UserService().getByNameUser(userName);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }

    public void deleteUser() {
        while (true) {
            System.out.println("Please, enter user name:");
            String userName = scanner.nextLine();
            try {
                new UserService().deleteUserByName(userName);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }

    public void createUserArray() {
        User[] users = new User[2];

        User user1 = new User();
        System.out.println("Please add info for user1.");
        setUserId(user1);
        setUserName(user1);

        User user2 = new User();
        System.out.println("Please add info for user2.");
        setUserId(user2);
        setUserName(user2);

        users[0] = user1;
        users[1] = user2;

        try {
            new UserService().createWithArray(users);
            System.out.println("User array created!\nChoose next command");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void createUserList(){

        List<User> users = new ArrayList<>();

        User user1 = new User();
        System.out.println("Please add info for user1.");
        setUserId(user1);
        setUserName(user1);

        User user2 = new User();
        System.out.println("Please add info for user2.");
        setUserId(user2);
        setUserName(user2);

        users.add(user1);
        users.add(user2);

        try {
            new UserService().createUsersList(users);
            System.out.println("User list created!\nChoose next command");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void logsIn() {
        User newUser = new User();

        setUserName(newUser);
        setPassword(newUser);

        try {
            new UserService().logsIn(newUser.getUserName(), newUser.getPassword());
            System.out.println("Success!\nChoose next command");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void updateUser() {

        User newUser = new User();
        System.out.println("Choose user by enter user name.");
        setUserName(newUser);
        System.out.println("Now you can update user info!");
        setFirstName(newUser);
        setLastName(newUser);
        setUserEmail(newUser);
        setPassword(newUser);
        setPhoneNumber(newUser);
        newUser.setUserStatus(0);

        try {
            new UserService().updateUser(newUser, newUser.getUserName());
            System.out.println("User updated!\nChoose next command");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void logsOut() {
        try {
            new UserService().logsOut();
            System.out.println("Choose next command");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }
}