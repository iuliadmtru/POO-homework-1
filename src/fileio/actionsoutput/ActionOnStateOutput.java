package fileio.actionsoutput;

import fileio.ActionsOutput;

public class ActionOnStateOutput extends ActionsOutput {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
