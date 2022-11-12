package card.minion;

public class BoardPlacementInfo {
    private int row; // 1 for front row, 0 for back row
    private int column; // between 1 and 5

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
