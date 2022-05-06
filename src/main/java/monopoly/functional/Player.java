package monopoly.functional;

import java.util.ArrayList;
import javax.swing.JButton;

public class Player implements Comparable<Player> {
    
    private int playerNum, position, properties, money, initialDiceResult;
    private JButton button;
    private boolean turn;
    private final String name;
    

    public Player(int playerNum, JButton button, String playerName) {
        this.playerNum = playerNum;
        this.button = button;
        this.name = playerName;
        turn = false;
        position = 1;
        money = 700;
        properties = 0;
        this.button.setVisible(true);
    }

    public String getName() {
        return name;
    }

    public int getInitialDiceResult() {
        return initialDiceResult;
    }

    public void setInitialDiceResult(int initialDiceResult) {
        this.initialDiceResult = initialDiceResult;
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
//        checkPosition();
    }
    
    public void checkPosition() {
        Util.getUtil().getBank().checkPosition(this);
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
    
    @Override
    public String toString() {
        ArrayList<String> playerPropertiesArr = Util.getUtil().getBank().getPropertiesByPlayer(name);
        String playerProperties = "";
        for (String s : playerPropertiesArr)
            playerProperties += s + "\n";
        String retStr = "Jugador " + name + " tiene " + properties + " propiedades a su nombre\n"
                + Util.getUtil().getBank().getPropertiesByPlayer(name)
                + playerProperties;
        return retStr;
    }

    @Override
    public int compareTo(Player p) {
        int compareDice = p.getInitialDiceResult();
        return this.initialDiceResult-compareDice;
    }

}
