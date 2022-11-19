package fileio.actionsoutput;

import fileio.ActionsOutput;

import java.util.ArrayList;

public class ActionOnDeckOutput extends ActionsOutput {
    private ArrayList<ActionOnCardOutput> cardOutputs;

    public ArrayList<ActionOnCardOutput> getCardOutputs() {
        return cardOutputs;
    }

    public void setCardOutputs(ArrayList<ActionOnCardOutput> cardOutputs) {
        this.cardOutputs = cardOutputs;
    }
}
