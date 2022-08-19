package cli;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CliState {
    protected final CliFSM fsm;

    public void init() {}
    public void store() {}
    public void pet(){}
    public void user() {}
    public void unknownCommand(String cmd) {}
    public void toMainMenu()  {}

}