package cli;

public class IdleState extends CliState {

    public IdleState(CliFSM fsm) {
        super(fsm);
    }

    @Override
    public void user() {
        fsm.setState(new UserState(fsm));
    }
    @Override
    public void pet()  {
        fsm.setState(new PetState(fsm));
    }
    @Override
    public void store(){
        fsm.setState(new StoreState(fsm));
    }

    @Override
    public void unknownCommand(String cmd) {
        System.out.println("Unknown command: " + cmd + "\nPlease, enter the right command");
    }
}

