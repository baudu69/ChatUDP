package vue;

import Donnes.Client.Emission;
import Donnes.Client.Reception;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.Observable;
import java.util.Observer;

public class Chat implements Observer {
    private JButton btnConnexion;
    private JTextField tfMessage;
    private JButton btnEnvoyer;
    private JPanel panelName;
    private JTextArea txt;
    private String message;

    String host;
    DatagramSocket socket;
    Reception r;
    Emission s;
    Thread rt;
    Thread st;

    public Chat() {
        btnEnvoyer.setEnabled(false);
        message = "";
        btnConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connexion();
                btnConnexion.setEnabled(false);
                btnEnvoyer.setEnabled(true);
            }
        });
        btnEnvoyer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    envoyerMsg(tfMessage.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                tfMessage.setText("");
            }
        });
    }

    public JPanel getPanelName() {
        return panelName;
    }

    private void envoyerMsg(String s) throws IOException {
        byte[] buffer = s.getBytes();
        InetAddress ip = InetAddress.getByName(host);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, ip, 7331);
        socket.send(packet);
    }

    private void connexion() {
        host = "127.0.0.1";
        try {
            socket = new DatagramSocket();
        } catch (SocketException socketException) {
            socketException.printStackTrace();
        }
        r = new Reception(socket, this);
        s = new Emission(socket, host);
        rt = new Thread(r);
        st = new Thread(s);
        rt.start(); st.start();
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("app");
        frame.setContentPane(new Chat().panelName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        txt.append(arg.toString() + "\n");
    }
}


