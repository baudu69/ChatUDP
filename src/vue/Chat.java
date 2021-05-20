package vue;

import javax.swing.*;

public class Chat {
    private JTextField tfPort;
    private JButton btnConnexion;
    private JLabel port;
    private JTextField tfMessage;
    private JButton btnEnvoyer;
    private JList lstMessage;
    private JPanel panelName;

    public static void main(String[] args) {
        JFrame frame = new JFrame("app");
        frame.setContentPane(new Chat().panelName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


