package rvt.Todolist;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TodoGui().setVisible(true));
    }
}
