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
        money = 1500;
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
        if (position > 40) {
            position -= 40;
            money += 200;
            Util.getUtil().getBoard().passByGoPrompt();
        }
        int[] arr = Util.getUtil().getPlayerPositions().get(position);
        button.setLocation(arr[0], arr[1]);
//        checkPosition();
    }
    
    public void checkPosition(int diceResult) {
        Util.getUtil().getBank().checkPosition(this, diceResult);
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
    
    public void payUp(int moneyToPay, Player payee) {
//        System.out.println("--------------------------------------------------------------------------------------------- payUp");
        if (moneyToPay > money) {
            Util.getUtil().getBank().transferProperties(this, payee);
            Util.getUtil().getPlayers().declarePlayerBankrupt(this);
            Util.getUtil().getBoard().declareBankrupt(name, payee.getName());
//            System.out.println("Player " + name + " transfer the properties to player " + payee.getName());
        }
        else {
            money -= moneyToPay;
            payee.setMoney(payee.getMoney() + moneyToPay);
            Util.getUtil().getBoard().rentPrompt(name, payee.getName(), moneyToPay);
//            System.out.println("Player " + name + " pays " + moneyToPay + " to player " + payee.getName());
        }
    }
    
    public void buyProperty(String propertyName, int price) {
        if (price <= money) {
            properties ++;
            money -= price;
            Util.getUtil().getBank().buyProperty(this, propertyName);
        } else {
            Util.getUtil().getBoard().noMoneyPrompt();
        }
    }
    
    @Override
    public String toString() {
        ArrayList<String> playerPropertiesArr = Util.getUtil().getBank().getPropertiesByPlayer(name);
        String playerProperties = "";
        for (String s : playerPropertiesArr)
            playerProperties += "- " + s + "\n";
        String retStr = "Jugador " + name + " tiene $" + money + " en el banco y " + properties + " propiedades a su nombre\n"
                + playerProperties;
        return retStr;
    }

    @Override
    public int compareTo(Player p) {
        int compareDice = p.getInitialDiceResult();
        return this.initialDiceResult-compareDice;
    }

}
