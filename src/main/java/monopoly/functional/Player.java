package monopoly.functional;

import javax.swing.JButton;

public class Player {
    
    private int playerNum, position, properties, money;
    private JButton button;
    private boolean turn;
    

    public Player(int playerNum, JButton button, boolean turn) {
        this.playerNum = playerNum;
        this.button = button;
        this.turn = turn;
        position = 1;
        money = 700;
        properties = 0;
        this.button.setVisible(true);
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        int[] arr = Util.getUtil().getPlayerPositions().get(position);
        button.setLocation(arr[0], arr[1]);
    }

    public void addToPosition(int moved) {
        position += moved;
        if (position > 40)
            position -= 40;
        int[] arr = Util.getUtil().getPlayerPositions().get(position);
        button.setLocation(arr[0], arr[1]);
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getProperties() {
        return properties;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
