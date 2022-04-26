package monopoly.main;

import monopoly.functional.Util;
import monopoly.gui.Board;

/**
 *
 * @author ivan
 */
public class Main {

    public static void main(String[] args) {
        Util.initPlayerPositions();
        Board.start();
    }
}
