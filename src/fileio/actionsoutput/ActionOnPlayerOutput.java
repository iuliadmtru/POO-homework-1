package fileio.actionsoutput;

import fileio.ActionsOutput;

public class ActionOnPlayerOutput extends ActionsOutput {
    private int playerIdx;
    private ActionsOutput output;

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public ActionsOutput getOutput() {
        return output;
    }

    public void setOutput(ActionsOutput output) {
        this.output = output;
    }
}
