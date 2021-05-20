package vue.Client;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("app");
        frame.setContentPane(new Chat().getPanelName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
