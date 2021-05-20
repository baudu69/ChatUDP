package vue.Client;

import javax.swing.*;

public class Main1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("app");
        frame.setContentPane(new Chat().getPanelName());
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
