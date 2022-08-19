package cli;

import lombok.Getter;
import java.util.Scanner;

public class CliFSM {
    @Getter
    private CliState state;

    @Getter
    private final Scanner scanner;

    String helloMessage =
            "Please, choose a command number from the list below\n" +
                    "1- User requests\n" +
                    "2- Pet requests\n" +
                    "3- Store requests\n" +
                    "0- Exit program";

    public CliFSM()  {

        state = new IdleState(this);

        scanner = new Scanner(System.in);

        startInputLoop();
    }

    private void startInputLoop() {

        System.out.println(helloMessage);

        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    user();
                    break;
                case "2":
                    pet();
                    break;
                case "3":
                    store();
                    break;
                default:
                    unknownCommand(command);
            }
        }
    }

    public void user() {
        state.user();
    }

    public void pet() {
        state.pet();
    }

    public void store() {
        state.store();
    }

    public void unknownCommand(String cmd) {
        state.unknownCommand(cmd);
    }

    public void setState(CliState state){
        this.state = state;
        state.init();
    }
}
