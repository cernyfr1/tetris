import model.Board;
import model.Piece;
import ui.Frame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Piece piece = new Piece(new Board());
        SwingUtilities.invokeLater(() -> new Frame().setVisible(true));

    }
}